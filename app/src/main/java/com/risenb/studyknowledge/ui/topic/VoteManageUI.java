package com.risenb.studyknowledge.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.topic.VoteManageAdapter;
import com.risenb.studyknowledge.beans.topic.VoteListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.post.ReleaseVoteUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.DividerItemDecoration;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import static com.risenb.studyknowledge.R.id.ll_right;

/**
 * Created by zhuzh on 2017/9/21.
 * <p>
 * 投票管理
 */
@ContentView(R.layout.vote_manage_ui)
public class VoteManageUI extends BaseUI implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemChildClickListener, VoteManageP.VoteManageFace {

    @ViewInject(R.id.mrl_vote_manage)
    private MyRefreshLayout mrl_vote_manage;
    @ViewInject(R.id.rv_vote_manage)
    private RecyclerView rv_vote_manage;

    private VoteManageAdapter mAdapter;
    private List<VoteListBean> listData = new ArrayList<>();
    private VoteManageP voteManageP;
    private int PAGE = 1;
    private String LIMIT = "10";
    private String TIMESTAMP = "";
    private String CollegeId = "45";
    private int mCurrentPosition;
    private boolean isFirst = true;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, VoteManageUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isFirst) {
            PAGE = 1;
            voteManageP.getVoteList(voteManageP.REFRESH, application.getC(), CollegeId, TIMESTAMP, PAGE + "", LIMIT);
        }
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.vote_manage));
        rightVisible(R.mipmap.add_title_iv);

        mAdapter = new VoteManageAdapter(R.layout.vote_manage_item, listData);
        rv_vote_manage.setAdapter(mAdapter);
        rv_vote_manage.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv_vote_manage.getParent());

        //添加分隔线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, R.drawable.divider_activity_line, LinearLayoutManager.VERTICAL);
        rv_vote_manage.addItemDecoration(itemDecoration);

        mrl_vote_manage.setMyRefreshLayoutListener(this);//刷新加载
        mAdapter.setOnItemChildClickListener(this);//侧滑菜单监听
    }

    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        voteManageP = new VoteManageP(this, getActivity());
        voteManageP.getVoteList(voteManageP.REFRESH, application.getC(), CollegeId, TIMESTAMP, PAGE + "", LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({ll_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case ll_right:
                ReleaseVoteUI.start(this, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh(View view) {
        PAGE = 1;
        voteManageP.getVoteList(voteManageP.REFRESH, application.getC(), CollegeId, TIMESTAMP, PAGE + "", LIMIT);

    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        voteManageP.getVoteList(voteManageP.LOAD, application.getC(), CollegeId, TIMESTAMP, PAGE + "", LIMIT);

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
//            case R.id.tv_menu_one:
//                ReleaseVoteUI.start(this,listData.get(position));
//                break;
            case R.id.tv_menu_two:
                this.mCurrentPosition = position;
                voteManageP.deleteVote(String.valueOf(listData.get(position).getVoteId()));
                break;
            default:
                break;
        }
    }

    @Override
    public void voteListSuccess(String tag, String time, List<VoteListBean> list) {
        isFirst = false;
        Utils.getUtils().dismissDialog();
        if (TextUtils.equals(tag, voteManageP.REFRESH)) {
            mrl_vote_manage.refreshComplete();
        } else if (TextUtils.equals(tag, voteManageP.LOAD)) {
            mrl_vote_manage.loadMoreComplete();
        }
        TIMESTAMP = time;
        listData = list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void voteListFail() {
        Utils.getUtils().dismissDialog();
        mrl_vote_manage.refreshComplete();
        mrl_vote_manage.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void delVoteSuccess() {
        listData.remove(mCurrentPosition);
        mAdapter.notifyDataSetChanged();
    }
}
