package com.risenb.studyknowledge.ui.info;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.college.CollegeNameAdapter;
import com.risenb.studyknowledge.beans.college.CollegeNameBean;
import com.risenb.studyknowledge.beans.college.JoinedCollegeBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.college.CollegeMoreP;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/11/10.
 * 加入的学院
 */
@ContentView(R.layout.ui_joined_college)
public class JoinedCollegeUI extends BaseUI implements MyRefreshLayoutListener, CollegeMoreP.CollegeMoreListener {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_joined_college)
    RecyclerView rv_joined_college;//加入的学院
    private List<CollegeNameBean> list = new ArrayList<>();
    private CollegeNameAdapter mCollegeNameAdapter;
    private CollegeMoreP mCollegeMoreP;

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
        setTitle(getResources().getString(R.string.joined_college));//加入的学院

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_joined_college.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);


    }

    public static void start(Context context) {
        Intent starter = new Intent(context, JoinedCollegeUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void prepareData() {
        mCollegeMoreP = new CollegeMoreP(this, this);
        mCollegeMoreP.setCollegeMore();
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
        refresh_layout.refreshComplete();
        mCollegeMoreP.setCollegeMore();
    }

    /**
     * 加载
     *
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                refresh_layout.loadMoreComplete();
//            }
//        }).start();
//        mCollegeNameAdapter.addData(list);

    }

    @Override
    public void collegeMoreSuccess(List<JoinedCollegeBean.DataBeanX.DataBean> dataBean) {
        mCollegeNameAdapter = new CollegeNameAdapter(R.layout.joined_college_item, dataBean);
        rv_joined_college.setAdapter(mCollegeNameAdapter);
    }
}
