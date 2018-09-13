package com.risenb.studyknowledge.ui.personal;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.PaidQuestionListAdapter;
import com.risenb.studyknowledge.adapter.personal.TalkAboutListAdapter;
import com.risenb.studyknowledge.beans.personal.MemberTopicListBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.LazyLoadFragment;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.List;

/**
 * 有偿问答
 */
public class PaidQuestionFragment extends LazyLoadFragment implements MyRefreshLayoutListener, MemberDetailP
        .MemberDetailFace {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_member_detail_list)
    RecyclerView paid_question_list;//有偿问答列表
    private List<MemberTopicListBean> mTopicList;
    private PaidQuestionListAdapter mMemberTopicListAdapter;
    private MemberDetailP mMemberDetailP;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String C="1643";
    private String attendId;
    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_member_detail, container, false);
    }

    @Override
    public void setControlBasis() {
        LIMIT=getResources().getString(R.string.limit_10);
        attendId=getArguments().getString(MemberDetailUI.ATTEND_ID);

        mMemberTopicListAdapter = new PaidQuestionListAdapter(R.layout.member_detail_topic_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        paid_question_list.setAdapter(mMemberTopicListAdapter);
        paid_question_list.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mMemberTopicListAdapter.setEmptyView(R.layout.empty_member_detail_view,(ViewGroup) paid_question_list
                .getParent());
    }

    @Override
    public void prepareData() {

    }
    public void onFirstUserVisible() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mMemberDetailP = new MemberDetailP(this, getActivity());
        mMemberDetailP.getMemberDetail(mMemberDetailP.REFRESH,C,attendId,mMemberDetailP
                .TYPE_PAY_QUESTION,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        PAGE=1;
        TIMESTAMP="";
        mMemberDetailP.getMemberDetail(mMemberDetailP.REFRESH,C,attendId,mMemberDetailP
                .TYPE_PAY_QUESTION,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mMemberDetailP.getMemberDetail(mMemberDetailP.LOAD,C,attendId,mMemberDetailP
                .TYPE_PAY_QUESTION,TIMESTAMP,PAGE+"",LIMIT);
    }

    /**
     * 话题列表成功的回调
     * @param tag
     * @param timestamp
     * @param postList
     */
    @Override
    public void memberDetailSuccess(String tag, String timestamp, List<MemberTopicListBean> postList) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mMemberDetailP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mMemberDetailP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;
        this.mTopicList=postList;
        mMemberTopicListAdapter.setNewData(mTopicList);
        mMemberTopicListAdapter.notifyDataSetChanged();
    }

    /**
     * 话题列表失败的回调
     */
    @Override
    public void memberDetailFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }
}
