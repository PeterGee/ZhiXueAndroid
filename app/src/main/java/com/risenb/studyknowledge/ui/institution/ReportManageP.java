package com.risenb.studyknowledge.ui.institution;

import com.risenb.studyknowledge.beans.institution.ReportBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/8.
 * 举报列表
 */

public class ReportManageP extends PresenterBase {

    private  ReportListener mReportListener;

    public  ReportManageP (ReportListener reportListener, BaseUI baseUI){
        this.mReportListener = reportListener;
        setActivity(baseUI);
    }

    public void getReportList(String c, String collegeId, String complaintType, String orderBy, String timestamp, String page, String limit){
        NetworkUtils.getNetworkUtils().getReportList(c, collegeId, complaintType, orderBy, timestamp, page, limit, new DataCallback<ReportBean>() {
            @Override
            public void onSuccess(ReportBean result, int i) {
                mReportListener.reportListData(result.getComplaintList());
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }


    /**
     * 删除举报
     * @param c
     * @param collegeId
     * @param complaintType
     * @param complaintToId
     */
    public void delReport( String collegeId, String complaintType, String complaintToId){
        NetworkUtils.getNetworkUtils().delReport(application.getC(), collegeId, complaintType, complaintToId,  new DataCallback<ReportBean>() {
            @Override
            public void onSuccess(ReportBean result, int i) {
                mReportListener.delReport();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }




    public  interface  ReportListener{
        void reportListData(List<ReportBean.ComplaintListBean> reportBean);
        void delReport();
    }
}
