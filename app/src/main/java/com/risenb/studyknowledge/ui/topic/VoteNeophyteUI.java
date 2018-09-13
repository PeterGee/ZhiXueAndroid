package com.risenb.studyknowledge.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.topic.VoteNeophyteAdapter;
import com.risenb.studyknowledge.beans.topic.VoteDetailListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzh on 2017/9/30.
 *
 * 投票参与者
 */
@ContentView(R.layout.vote_neophyte_ui)
public class VoteNeophyteUI extends BaseUI implements MyRefreshLayoutListener, VoteNeophyteP.VoteNeophyteFace {

    @ViewInject(R.id.mrl_vote_neophyte)
    private MyRefreshLayout mrl_vote_neophyte;
    @ViewInject(R.id.rv_vote_neophyte)
    private RecyclerView rv_vote_neophyte;

    private VoteNeophyteAdapter mAdapter;
    private List<VoteDetailListBean> listData = new ArrayList<>();
//    private List<VoteNeophyteBean.VoteOptionBean> list = new ArrayList<>();
    private String voteId;//投票ID
    private VoteNeophyteP voteNeophyteP;
    private int PAGE=1;
    private String LIMIT = "10";
    private String TIMESTAMP="";
    private String CollegeId="45";


    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context,String voteId) {
        Intent starter = new Intent(context, VoteNeophyteUI.class);
        starter.putExtra("voteId",voteId);
        context.startActivity(starter);
    }
    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.vote_neophyte));
        voteId = getIntent().getStringExtra("voteId");

        mAdapter = new VoteNeophyteAdapter(R.layout.vote_neophyte_item, listData);
        rv_vote_neophyte.setAdapter(mAdapter);
        rv_vote_neophyte.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_vote_neophyte.getParent());

        mrl_vote_neophyte.setMyRefreshLayoutListener(this);//刷新加载
    }

    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        voteNeophyteP = new VoteNeophyteP(this, getActivity());
        voteNeophyteP.getVoteDetailList(voteNeophyteP.REFRESH,application.getC(),voteId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @Override
    public void onRefresh(View view) {
        PAGE = 1;
        voteNeophyteP.getVoteDetailList(voteNeophyteP.REFRESH,application.getC(),voteId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        voteNeophyteP.getVoteDetailList(voteNeophyteP.LOAD,application.getC(),voteId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    public void voteDetailListSuccess(String tag, String time, List<VoteDetailListBean> list) {
        Utils.getUtils().dismissDialog();
        if(TextUtils.equals(tag,voteNeophyteP.REFRESH)){
            mrl_vote_neophyte.refreshComplete();
        }else if(TextUtils.equals(tag,voteNeophyteP.LOAD)){
            mrl_vote_neophyte.loadMoreComplete();
        }
        TIMESTAMP=time;
        listData=list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void voteDetailListFail() {
        Utils.getUtils().dismissDialog();
        mrl_vote_neophyte.refreshComplete();
        mrl_vote_neophyte.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }
}
