package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.IncomePostAdapter;
import com.risenb.studyknowledge.beans.institution.IncomeDetailBean;
import com.risenb.studyknowledge.beans.institution.PostAccountBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/26.
 * 帖子收益明细
 */
@ContentView(R.layout.ui_incoming)
public class IncomePostUI extends BaseUI implements MyRefreshLayoutListener, IncomePostP.IncomePostListener {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_income_list)
    RecyclerView rv_income_list;//收益列表
    private List<IncomeDetailBean> list = new ArrayList<>();
    private IncomePostAdapter mIncomePostAdapter;
    private String timeStamp;
    private int page = 1;
    private int limit = 10;
    public IncomePostP mIncomePostP;

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
        setTitle(getResources().getString(R.string.income_post));//帖子收益明细

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_income_list.setLayoutManager(linearLayoutManager);

        mIncomePostAdapter = new IncomePostAdapter(R.layout.income_post_detail_item, list);
        rv_income_list.setAdapter(mIncomePostAdapter);

        mIncomePostP = new IncomePostP(this, this);
        mIncomePostP.setIncomePostData("1165", "45", "", "", timeStamp, String.valueOf(page), String.valueOf(limit));


        refresh_layout.setMyRefreshLayoutListener(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, IncomePostUI.class);
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
        mIncomePostP.setIncomePostData("1165", "45", "", "", timeStamp, String.valueOf(page), String.valueOf(limit));

    }

    /**
     * 加载
     *
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        mIncomePostP.setIncomePostData("1165", "45", "", "", timeStamp, String.valueOf(page), String.valueOf(limit));
    }

    @Override
    public void incomePostData(PostAccountBean postAccountBean) {

        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
        if (postAccountBean == null) {
            return;
        }
        timeStamp = postAccountBean.getTimestamp();
        if (postAccountBean.getPostAccountList() != null && postAccountBean.getPostAccountList().size() > 0) {
            //转换数据
            for (PostAccountBean.PostAccountListBean postAccountList : postAccountBean.getPostAccountList()) {
                IncomeDetailBean bean = new IncomeDetailBean();
                bean.setName(postAccountList.getPostName());
                bean.setPrice(String.valueOf(postAccountList.getSumCost()));
                bean.setTime(postAccountList.getCreateDate());
                bean.setPriceName(postAccountList.getTopicName());
                list.add(bean);
            }
        }
        mIncomePostAdapter.setNewData(list);

    }
}
