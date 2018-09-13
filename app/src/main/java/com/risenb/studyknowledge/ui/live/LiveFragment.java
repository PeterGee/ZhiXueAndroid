package com.risenb.studyknowledge.ui.live;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.live.LiveNoticeAdapter;
import com.risenb.studyknowledge.beans.live.LiveNoticeBean;
import com.risenb.studyknowledge.beans.live.PostLiveListBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.LazyLoadFragment;
import com.risenb.studyknowledge.ui.personal.MemberManagerP;
import com.risenb.studyknowledge.ui.post.ReleaseContentsUI;
import com.risenb.studyknowledge.views.DividerItemDecoration;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import static com.risenb.studyknowledge.R.id.refresh_layout;
import static com.risenb.studyknowledge.R.id.rl_cost;
import static com.risenb.studyknowledge.R.id.rv_member_manager;

/**
 * Created by zhuzh on 2017/9/13.
 *
 * 直播预告
 */

public class LiveFragment extends LazyLoadFragment implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemChildClickListener, LiveFragmentP.LiveFragmentFace {
    @ViewInject(R.id.mrl_live_notice)
    private MyRefreshLayout mrl_live_notice;
    @ViewInject(R.id.rv_live_notice)
    private RecyclerView rv_live_notice;
    @ViewInject(R.id.tv_release)
    private TextView tv_release;
    @ViewInject(R.id.ll_release)
    private LinearLayout ll_release;

    private LiveNoticeAdapter mAdapter;
    private List<PostLiveListBean> listData = new ArrayList<>();
    private LiveFragmentP liveFragmentP;
    private int PAGE=1;
    private String LIMIT = "10";
    private String TIMESTAMP="";
    private String C="702";
    private String CollegeId="45";

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.ui_live, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
//        mAdapter.notifyDataSetChanged();
        PAGE =1;
        liveFragmentP.getLiveList(liveFragmentP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);

    }

    @Override
    public void setControlBasis() {

        mAdapter = new LiveNoticeAdapter(R.layout.live_notice_item);
        rv_live_notice.setAdapter(mAdapter);
        rv_live_notice.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_live_notice.getParent());
        //添加分隔线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), R.drawable.divider_activity_line, LinearLayoutManager.VERTICAL);
        rv_live_notice.addItemDecoration(itemDecoration);
        mrl_live_notice.setMyRefreshLayoutListener(this);//刷新加载
        mAdapter.setOnItemChildClickListener(this);//侧滑菜单监听

        //发布
        ll_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReleaseLiveUI.start(getActivity(),null);
            }
        });

    }

    public void onFirstUserVisible() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        liveFragmentP.getLiveList(liveFragmentP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    public void prepareData() {
        liveFragmentP = new LiveFragmentP(this, getActivity());

//        Utils.getUtils().showProgressDialog(getActivity(), null);
//        liveFragmentP = new LiveFragmentP(this, getActivity());
//        liveFragmentP.getLiveList(liveFragmentP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }



    @Override
    public void onRefresh(View view) {
        PAGE = 1;
        liveFragmentP.getLiveList(liveFragmentP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);

    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        liveFragmentP.getLiveList(liveFragmentP.LOAD,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);


    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.tv_menu_one:
//                makeText("编辑"+position);
                PostLiveListBean bean = listData.get(position);
                ReleaseLiveUI.start(getActivity(), bean);
                break;
            case R.id.tv_menu_two:
//                listData.remove(position);
//                mAdapter.notifyDataSetChanged();
                liveFragmentP.deleteLive(C, listData.get(position).getPostId(), position);
                break;
            case R.id.content:
//                makeText(listData.get(position).getName());
//                ReleaseContentsUI.start(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    public void liveListSuccess(String tag, String time, List<PostLiveListBean> list) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,liveFragmentP.REFRESH)){
            mrl_live_notice.refreshComplete();
        }else if(TextUtils.equals(tag,liveFragmentP.LOAD)){
            mrl_live_notice.loadMoreComplete();
        }
        TIMESTAMP=time;
        listData=list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void liveListFail() {
        Utils.getUtils().dismissDialog();
        mrl_live_notice.refreshComplete();
        mrl_live_notice.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 删除成功
     * @param position
     */
    @Override
    public void deleteLiveSuccess(int position) {
        listData.remove(position);
        mAdapter.notifyDataSetChanged();
    }
}
