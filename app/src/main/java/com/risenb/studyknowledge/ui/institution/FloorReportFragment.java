package com.risenb.studyknowledge.ui.institution;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.ReportAdapter;
import com.risenb.studyknowledge.beans.institution.ReportBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.evntBusBean.ReportEvent;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 楼层举报
 */
public class FloorReportFragment extends BaseFragment implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, ReportManageP.ReportListener {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_report_list)
    RecyclerView rv_report_list;//楼层举报列表
    private List<ReportBean> postList = new ArrayList<>();
    private ReportAdapter mReportAdapter;
    private boolean isShowCheck;
    private ReportManageP mReportManageP;
    private int page = 1;
    private int limit = 10;
    private String mComplaintType = "2";
    private String mOrderBy = "1";
    private String mCollegeId = "45";

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_report_list, container, false);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void setControlBasis() {
        EventBus.getDefault().register(this);

        mReportManageP = new ReportManageP(this, (BaseUI) getActivity());
        mReportManageP.getReportList(
                application.getC(),
                mCollegeId,
                mComplaintType,
                mOrderBy,
                "",
                String.valueOf(page),
                String.valueOf(limit)
        );


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_report_list.setLayoutManager(linearLayoutManager);

        refresh_layout.setMyRefreshLayoutListener(this);

    }

    @Override
    public void prepareData() {

    }

    /**
     * 清空列表
     */
    public void deleteAll() {
        mReportAdapter.getData().clear();
        mReportAdapter.notifyDataSetChanged();
    }

    /**
     * 是否显示勾选圈
     *
     * @param isShowCheck
     */
    public void isShowChoose(boolean isShowCheck) {
        this.isShowCheck = isShowCheck;
        for (int i = 0; i < mReportAdapter.getData().size(); i++) {
            mReportAdapter.getData().get(i).setShow(isShowCheck);
        }
        mReportAdapter.notifyDataSetChanged();
    }

    /**
     * 选中删除
     */
    public void checkedDelete() {
        List<ReportBean.ComplaintListBean> checkedList = new ArrayList<>();
        StringBuilder chooseIds = new StringBuilder();
        for (int i = 0; i < mReportAdapter.getData().size(); i++) {
            if (mReportAdapter.getData().get(i).isChecked()) {
                checkedList.add(mReportAdapter.getData().get(i));
                chooseIds.append(mReportAdapter.getData().get(i).getComplaintId() + ",");
            }
        }

        String chooseId;
        if (!TextUtils.isEmpty(chooseIds)) {
            chooseId = chooseIds.subSequence(0, chooseIds.length()-1).toString();
        } else {
            return;
        }
        mReportAdapter.getData().removeAll(checkedList);
        mReportManageP.delReport(mCollegeId, "2", chooseId);
        mReportAdapter.notifyDataSetChanged();
        chooseIds.setLength(0);
    }

    /**
     * 是否全选
     *
     * @param isChecked
     */
    public void allChecked(boolean isChecked) {
        for (int i = 0; i < mReportAdapter.getData().size(); i++) {
            mReportAdapter.getData().get(i).setChecked(isChecked);
        }
        mReportAdapter.notifyDataSetChanged();
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
        mReportManageP.getReportList(
                application.getC(),
                mCollegeId,
                mComplaintType,
                mOrderBy,
                "",
                String.valueOf(page),
                String.valueOf(limit)
        );
    }

    /**
     * 加载
     *
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
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

        mReportManageP.getReportList(
                application.getC(),
                mCollegeId,
                mComplaintType,
                mOrderBy,
                "",
                String.valueOf(page),
                String.valueOf(limit)
        );

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ReportBean.ComplaintListBean complaintListBean = (ReportBean.ComplaintListBean) adapter.getData().get(position);
        complaintListBean.setChecked(!complaintListBean.isChecked());
        mReportAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ReportBean.ComplaintListBean item = (ReportBean.ComplaintListBean) adapter.getData().get(position);
        ReportDetailUI.start(getContext(), String.valueOf(item.getComplaintId()));
    }

    @Override
    public void reportListData(List<ReportBean.ComplaintListBean> reportBean) {
        if (page == 1) {
            mReportAdapter = new ReportAdapter(R.layout.report_list_item, reportBean);
            mReportAdapter.setOnItemChildClickListener(this);
            mReportAdapter.setOnItemClickListener(this);
            rv_report_list.setAdapter(mReportAdapter);
        } else {
            mReportAdapter.addData(reportBean);
            mReportAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void delReport() {

    }



    //====EventBust回调事件,切换排序
    @Subscribe
    public void sortEvent(ReportEvent reportEvent){
        if (ReportEvent.SORT_REPORT==reportEvent.getEventType()) {
            mOrderBy= String.valueOf((int) reportEvent.getData());
            page = 1;
            mReportManageP.getReportList(
                    application.getC(),
                    mCollegeId,
                    mComplaintType,
                    mOrderBy,
                    "",
                    String.valueOf(page),
                    String.valueOf(limit)
            );
        }
    }
}
