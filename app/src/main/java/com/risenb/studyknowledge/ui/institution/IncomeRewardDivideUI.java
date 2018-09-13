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
import com.risenb.studyknowledge.beans.institution.RewardDivideBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/26.
 * 打赏分成收益明细
 */
@ContentView(R.layout.ui_incoming)
public class IncomeRewardDivideUI extends BaseUI implements MyRefreshLayoutListener, IncomeRewardDivideP.IncomeRewardDivideListener {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_income_list)
    RecyclerView rv_income_list;//收益列表
    private List<IncomeDetailBean> list = new ArrayList<>();
    private IncomeDetailAdapter mIncomeGroupAdapter;


    private String timeStamp;
    private int page = 1;
    private int limit = 10;
    public IncomeRewardDivideP mIncomeRewardDivideP;

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
        setTitle(getResources().getString(R.string.income_reward_divide));//打赏分成收益明细

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_income_list.setLayoutManager(linearLayoutManager);


        mIncomeGroupAdapter = new IncomeDetailAdapter(R.layout.income_detail_item, list);
        rv_income_list.setAdapter(mIncomeGroupAdapter);


        refresh_layout.setMyRefreshLayoutListener(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, IncomeRewardDivideUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void prepareData() {
        mIncomeRewardDivideP = new IncomeRewardDivideP(this, this);
        mIncomeRewardDivideP.setIncomeRewardDivideData("1165", "45", "", "", timeStamp, String.valueOf(page), String.valueOf(limit));

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
        list.clear();
        page = 1;
        mIncomeRewardDivideP.setIncomeRewardDivideData("1165", "45", "", "", timeStamp, String.valueOf(page), String.valueOf(limit));
    }

    /**
     * 加载
     *
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        mIncomeRewardDivideP.setIncomeRewardDivideData("1165", "45", "", "", timeStamp, String.valueOf(page), String.valueOf(limit));
    }

    @Override
    public void incomeRewardDivideData(RewardDivideBean rewardDivideBean) {
        refresh_layout.loadMoreComplete();
        refresh_layout.refreshComplete();
        if (rewardDivideBean == null) {
            return;
        }
        timeStamp = rewardDivideBean.getTimestamp();

        //转换数据
        for (RewardDivideBean.GiveScalAccountListBean postAccountList : rewardDivideBean.getGiveScalAccountList()) {
            IncomeDetailBean bean = new IncomeDetailBean();
            bean.setName(postAccountList.getGiveToName());
            bean.setPrice(String.valueOf(postAccountList.getSumScalGive()));
            bean.setTime(postAccountList.getCreateDate());
            list.add(bean);
        }

        mIncomeGroupAdapter.setNewData(list);

    }
}
