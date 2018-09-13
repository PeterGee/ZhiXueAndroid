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
import com.risenb.studyknowledge.beans.institution.PaidQuestionBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/26.
 * 有偿提问收益明细
 */
@ContentView(R.layout.ui_incoming)
public class IncomePaidQuestionUI extends BaseUI implements MyRefreshLayoutListener, IncomePaidQuestionP.InComePaidQuestionListener {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_income_list)
    RecyclerView rv_income_list;//收益列表
    private List<IncomeDetailBean> list = new ArrayList<>();
    private IncomeDetailAdapter mIncomeGroupAdapter;
    public IncomePaidQuestionP mIncomePaidQuestionP;
    private int page = 1;
    private String limit = "10";
    private String timeStamp;

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
        setTitle(getResources().getString(R.string.income_paid_question));//有偿提问收益明细

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_income_list.setLayoutManager(linearLayoutManager);

        mIncomeGroupAdapter = new IncomeDetailAdapter(R.layout.income_detail_item, list);
        rv_income_list.setAdapter(mIncomeGroupAdapter);

        refresh_layout.setMyRefreshLayoutListener(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, IncomePaidQuestionUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void prepareData() {
        mIncomePaidQuestionP = new IncomePaidQuestionP(this, this);
        mIncomePaidQuestionP.setIncomePaidQuestion("45", "", "", "", String.valueOf(page), limit);

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
        mIncomePaidQuestionP.setIncomePaidQuestion("45", "", "", "", String.valueOf(page), limit);

    }

    /**
     * 加载
     *
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        mIncomePaidQuestionP.setIncomePaidQuestion("45", "", "", "", String.valueOf(page), limit);

    }

    @Override
    public void inComePaidQuestionSuccess(PaidQuestionBean paidQuestionBean) {

        refresh_layout.loadMoreComplete();
        refresh_layout.refreshComplete();
        if (paidQuestionBean == null) {
            return;
        }

        timeStamp = paidQuestionBean.getTimestamp();

        if (paidQuestionBean.getYouChangAccountList() != null && paidQuestionBean.getYouChangAccountList().size() > 0) {
            //转换数据
            for (PaidQuestionBean.YouChangAccountListBean youChangAccountListBean : paidQuestionBean.getYouChangAccountList()) {
                IncomeDetailBean bean = new IncomeDetailBean();
                bean.setName(youChangAccountListBean.getGiveToName());
                bean.setPrice(String.valueOf(youChangAccountListBean.getYouChangMoney()));
                bean.setTime(youChangAccountListBean.getCreateDate());
                bean.setPriceName("");
                list.add(bean);
            }
        }
        mIncomeGroupAdapter.setNewData(list);
    }
}
