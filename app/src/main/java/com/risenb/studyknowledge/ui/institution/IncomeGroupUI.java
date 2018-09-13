package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.IncomeDetailAdapter;
import com.risenb.studyknowledge.beans.institution.IncomeDetailBean;
import com.risenb.studyknowledge.beans.institution.IncomeGroupBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/26.
 * 入群收益明细
 */
@ContentView(R.layout.ui_incoming)
public class IncomeGroupUI extends BaseUI implements MyRefreshLayoutListener, IncomeGroupP.IncomeGroupListener {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_income_list)
    RecyclerView rv_income_list;//收益列表
    private List<IncomeDetailBean> list = new ArrayList<>();
    private IncomeDetailAdapter mIncomeGroupAdapter;
    public IncomeGroupP mIncomeGroupP;
    private int page = 1;
    private int limit = 10;
    private String timeStamp = "";

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
        setTitle(getResources().getString(R.string.income_group));//入群收益明细

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_income_list.setLayoutManager(linearLayoutManager);
        mIncomeGroupAdapter = new IncomeDetailAdapter(R.layout.income_detail_item, list);
        rv_income_list.setAdapter(mIncomeGroupAdapter);
        refresh_layout.setMyRefreshLayoutListener(this);

        mIncomeGroupP = new IncomeGroupP(this, this);
        mIncomeGroupP.setIncomeGroupData("1165", "45", "", "", "", String.valueOf(page), String.valueOf(limit));


    }

    public static void start(Context context) {
        Intent starter = new Intent(context, IncomeGroupUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    /**
     * 刷新
     *
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        page = 1;
        list.clear();
        mIncomeGroupP.setIncomeGroupData("1165", "45", "", "", timeStamp, String.valueOf(page), String.valueOf(limit));
    }

    /**
     * 加载
     *
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        mIncomeGroupP.setIncomeGroupData("1165", "45", "", "", timeStamp, String.valueOf(page), String.valueOf(limit));
    }

    @Override
    public void incomeGroupData(IncomeGroupBean incomeGroupBean) {
        refresh_layout.loadMoreComplete();
        refresh_layout.refreshComplete();

        if (incomeGroupBean == null) {
            return;
        }
        timeStamp = incomeGroupBean.getTimestamp();

        //转换数据
        if (incomeGroupBean.getCollegeAccountList() != null && incomeGroupBean.getCollegeAccountList().size() > 0) {
            for (IncomeGroupBean.CollegeAccountListBean collegeAccountListBean : incomeGroupBean.getCollegeAccountList()) {
                IncomeDetailBean bean = new IncomeDetailBean();
                bean.setName(collegeAccountListBean.getUserName());
                bean.setPrice(collegeAccountListBean.getSumCost());
                bean.setTime(collegeAccountListBean.getCreateDate());
                bean.setPriceName("");
                list.add(bean);
            }
        }
        mIncomeGroupAdapter.setNewData(list);


    }

    @Override
    public void getDataField() {
        refresh_layout.loadMoreComplete();
        refresh_layout.refreshComplete();
    }
}
