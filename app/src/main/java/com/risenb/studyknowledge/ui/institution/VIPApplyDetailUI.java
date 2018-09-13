package com.risenb.studyknowledge.ui.institution;

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
import com.risenb.studyknowledge.adapter.institution.VIPApplyAdapter;
import com.risenb.studyknowledge.beans.institution.VIPApplyBean;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.personal.BlacklistP;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/28.
 * VIP申请明细
 */
@ContentView(R.layout.ui_vip_apply_list)
public class VIPApplyDetailUI extends BaseUI implements MyRefreshLayoutListener, VIPApplyDetailP.VIPApplyDetailFace {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_vip_apply_list)
    RecyclerView rv_vip_apply_list;//VIP申请明细列表
    private List<VIPApplyBean.DataBean.VipDetailListBean> vipDetailList;
    private VIPApplyAdapter mVipApplyAdapter;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String C="1643";
    private String CollegeId="45";
    private VIPApplyDetailP mVipApplyDetailP;

    @Override
    protected void back() {
        finish();
    }
    @Override
    protected boolean isStatusBar() {
        return true;
    }
    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.vip_apply));//VIP申请明细
        LIMIT=getResources().getString(R.string.limit_10);

        mVipApplyAdapter = new VIPApplyAdapter(R.layout.vip_apply_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_vip_apply_list.setAdapter(mVipApplyAdapter);
        rv_vip_apply_list.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mVipApplyAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_vip_apply_list.getParent());
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, VIPApplyDetailUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mVipApplyDetailP = new VIPApplyDetailP(this, getActivity());
        mVipApplyDetailP.getVIPApplyDetailList(mVipApplyDetailP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        PAGE=1;
        TIMESTAMP="";
        mVipApplyDetailP.getVIPApplyDetailList(mVipApplyDetailP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mVipApplyDetailP.getVIPApplyDetailList(mVipApplyDetailP.LOAD,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    /**
     * 获取vip申请明细列表成功的回调
     * @param tag
     * @param timestamp
     * @param result
     */
    @Override
    public void getVIPApplyDetailListSuccess(String tag, String timestamp, List<VIPApplyBean.DataBean.VipDetailListBean> result) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mVipApplyDetailP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mVipApplyDetailP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;

        this.vipDetailList=result;
        mVipApplyAdapter.setNewData(vipDetailList);
        mVipApplyAdapter.notifyDataSetChanged();
    }

    /**
     * 获取vip申请明细列表失败的回调
     */
    @Override
    public void getVIPApplyDetailListFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }
}
