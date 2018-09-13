package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.ReportDetailAdapter;
import com.risenb.studyknowledge.beans.institution.ReportDetailBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/10/20.
 * 举报明细
 */
@ContentView(R.layout.ui_report_detail)
public class ReportDetailUI extends BaseUI implements MyRefreshLayoutListener, ReportDetailP.ReportDetailListener {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;

    @ViewInject(R.id.rv_detail_list)
    RecyclerView rv_detail_list;//举报明细列表

    @ViewInject(R.id.tv_report_topic)
    TextView tv_report_topic;//话题

    @ViewInject(R.id.tv_report_name)
    TextView tv_report_name;//创建者姓名

    @ViewInject(R.id.tv_time)
    TextView tv_time;//创建时间

    @ViewInject(R.id.tv_count)
    TextView tv_count;//举报人数

    @ViewInject(R.id.tv_content)
    TextView tv_content;//举报内容

    @ViewInject(R.id.iv_img)
    ImageView iv_img;//创建者头像


    private List<ReportDetailBean> list = new ArrayList<>();
    private ReportDetailAdapter mReportDetailAdapter;
    public ReportDetailP mReportDetailP;
    private int page = 1;
    private int limit = 10;
    public String mComplaintID;
    private String mCollegeId = "45";

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
        setTitle(getResources().getString(R.string.report_detail));//举报明细

        mComplaintID = getIntent().getStringExtra("complaintID");
        mReportDetailP = new ReportDetailP(this, this);
        mReportDetailP.getReportDetail(mCollegeId, mComplaintID, "", String.valueOf(page), String.valueOf(limit));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_detail_list.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);

    }

    public static void start(Context context, String complaintID) {
        Intent starter = new Intent(context, ReportDetailUI.class);
        starter.putExtra("complaintID", complaintID);
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
        refresh_layout.refreshComplete();
        mReportDetailP.getReportDetail(mCollegeId, mComplaintID, "", String.valueOf(page), String.valueOf(limit));
    }

    /**
     * 加载
     *
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refresh_layout.loadMoreComplete();
            }
        }).start();

        page++;

        mReportDetailP.getReportDetail(mCollegeId, mComplaintID, "", String.valueOf(page), String.valueOf(limit));


    }

    @Override
    public void reportDetailData(ReportDetailBean reportDetailBean) {
        if (page == 1) {
            //顶部数据
            ReportDetailBean.ComplaintBean complaintBean = reportDetailBean.getComplaint();
            tv_report_topic.setText(complaintBean.getPostName());
            tv_report_name.setText(complaintBean.getPostWriterId());
            GlideImageLoader.create(iv_img).loadImage(complaintBean.getTopicImg(),R.mipmap.unify_image_ing);
            tv_time.setText(complaintBean.getComplaintCreationTime());
            tv_count.setText(String.valueOf(reportDetailBean.getComplaintList().size())+"人 举报");
            tv_content.setText(complaintBean.getComplaintContent());

            //举报明细列表
            mReportDetailAdapter = new ReportDetailAdapter(R.layout.report_detail_item, reportDetailBean.getComplaintList());
            rv_detail_list.setAdapter(mReportDetailAdapter);
        } else {
            mReportDetailAdapter.addData(reportDetailBean.getComplaintList());
            mReportDetailAdapter.notifyDataSetChanged();
        }
    }
}
