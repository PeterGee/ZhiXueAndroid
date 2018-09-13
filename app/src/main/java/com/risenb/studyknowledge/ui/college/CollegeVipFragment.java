package com.risenb.studyknowledge.ui.college;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.college.CollegeVipAdapter;
import com.risenb.studyknowledge.adapter.live.LiveNoticeAdapter;
import com.risenb.studyknowledge.beans.college.CollegeVipBean;
import com.risenb.studyknowledge.beans.live.LiveNoticeBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import static com.risenb.studyknowledge.R.id.rl_about_platform;
import static com.risenb.studyknowledge.R.id.rv_live_notice;
import static com.risenb.studyknowledge.R.id.tab_college;

/**
 * Created by zhuzh on 2017/10/23.
 */

public class CollegeVipFragment extends BaseFragment implements MyRefreshLayoutListener, CollegeVipP.CollegeVipFace {

    @ViewInject(R.id.mrl_college_vip)
    private MyRefreshLayout mrl_college_vip;
    @ViewInject(R.id.rv_college_vip)
    private RecyclerView rv_college_vip;

    private CollegeVipAdapter mAdapter;
    private List<CollegeVipBean.DataBean.CollegeGradeListBean> listData = new ArrayList<>();
    private CollegeVipP collegeVipP;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.college_vip_fm, container, false);

    }

    @Override
    public void setControlBasis() {
        mAdapter = new CollegeVipAdapter(R.layout.college_vip_item, listData);
        rv_college_vip.setAdapter(mAdapter);
        rv_college_vip.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrl_college_vip.setMyRefreshLayoutListener(this);
    }

    @Override
    public void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        collegeVipP = new CollegeVipP(this, getActivity());
        collegeVipP.getCollegeVip(CollegeVipP.REFRESH);
    }

    @Override
    public void onRefresh(View view) {
        collegeVipP.getCollegeVip(CollegeVipP.REFRESH);

    }

    @Override
    public void onLoadMore(View view) {
        collegeVipP.getCollegeVip(CollegeVipP.LOAD);
    }


    @Override
    public void vipListSuccess(String tag, List<CollegeVipBean.DataBean.CollegeGradeListBean> list) {
        Utils.getUtils().dismissDialog();
        if(TextUtils.equals(tag,collegeVipP.REFRESH)){
            mrl_college_vip.refreshComplete();
        }else if(TextUtils.equals(tag,collegeVipP.LOAD)){
            mrl_college_vip.loadMoreComplete();
        }
        listData=list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void liveListFail() {
        Utils.getUtils().dismissDialog();
        mrl_college_vip.refreshComplete();
        mrl_college_vip.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }
}
