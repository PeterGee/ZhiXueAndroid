package com.risenb.studyknowledge.ui.institution;

import com.risenb.studyknowledge.beans.institution.ReportDetailBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/8.
 * 举报明细
 */

public class ReportDetailP extends PresenterBase {
    private  ReportDetailListener mReportDetailListener;

    public ReportDetailP(ReportDetailListener reportDetailListener, BaseUI baseUI) {
        this.mReportDetailListener = reportDetailListener;
        setActivity(baseUI);
    }

    public void getReportDetail(String collegeId, String complaintId,  String timestamp, String page, String limit){
        NetworkUtils.getNetworkUtils().getReportDes(application.getC(), collegeId, complaintId, timestamp, page, limit, new DataCallback<ReportDetailBean>() {
            @Override
            public void onSuccess(ReportDetailBean result, int i) {
                mReportDetailListener.reportDetailData(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }

    public interface  ReportDetailListener{
        void reportDetailData(ReportDetailBean reportDetailBean);
    }
}
