package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.CashDetailsAdapter;
import com.risenb.studyknowledge.beans.institution.CashDetailsBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.List;

/**
 * Created by yy on 2017/9/29.
 * 提现明细
 */
@ContentView(R.layout.ui_cash_details)
public class CashDetailsUI extends BaseUI implements MyRefreshLayoutListener, CashMoneyFragment.OnCashMoneyListener, CashRecordListP.CashRecordListFace {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_cash_details)
    RecyclerView rv_cash_details;//提现明细列表
    private List<CashDetailsBean> mCashRecordList;
    private CashDetailsAdapter mCashDetailsAdapter;
    private CashMoneyFragment mCashMoneyFragment;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String C="1643";
    private String CollegeId="45";
    private CashRecordListP mCashRecordListP;

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
        setTitle(getResources().getString(R.string.cash_details));//提现明细
        rightVisible(getResources().getString(R.string.cash));//提现
        LIMIT=getResources().getString(R.string.limit_10);

        mCashDetailsAdapter = new CashDetailsAdapter(R.layout.cash_details_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_cash_details.setAdapter(mCashDetailsAdapter);
        rv_cash_details.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mCashDetailsAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_cash_details.getParent());
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, CashDetailsUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mCashRecordListP = new CashRecordListP(this, getActivity());
        mCashRecordListP.getCashRecordList(mCashRecordListP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_right://提现
                showFragment(true);
                break;
            default:
                break;
        }
    }
    /**
     * 显示提现金额弹窗
     * @param show
     */
    private void showFragment(boolean show) {
        if (mCashMoneyFragment == null) {
            mCashMoneyFragment = new CashMoneyFragment();
            mCashMoneyFragment.setOnCashMoneyListener(this);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mCashMoneyFragment.isAdded()){
                return;
            }
            transaction.add(R.id.fl_cash,mCashMoneyFragment).commit();
        }else {
            transaction.remove(mCashMoneyFragment).commit();
        }
    }
    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        PAGE=1;
        TIMESTAMP="";
        mCashRecordListP.getCashRecordList(mCashRecordListP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mCashRecordListP.getCashRecordList(mCashRecordListP.LOAD,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    /**
     * 关闭提现金额弹窗回调
     */
    @Override
    public void closeCashMoneyListener() {
        showFragment(false);
    }

    /**
     * 获取提现列表成功的回调
     * @param tag
     * @param timestamp
     * @param result
     */
    @Override
    public void getCashRecordListSuccess(String tag, String timestamp, List<CashDetailsBean> result) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mCashRecordListP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mCashRecordListP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;

        //黑名单列表
        this.mCashRecordList=result;
        mCashDetailsAdapter.setNewData(mCashRecordList);
        mCashDetailsAdapter.notifyDataSetChanged();
    }

    /**
     * 获取提现列表失败的回调
     */
    @Override
    public void getCashRecordListFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }
}
