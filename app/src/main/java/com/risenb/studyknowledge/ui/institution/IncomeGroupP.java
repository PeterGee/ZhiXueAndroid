package com.risenb.studyknowledge.ui.institution;

import android.text.TextUtils;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.IncomeGroupBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/11.
 */

public class IncomeGroupP extends PresenterBase {

    private IncomeGroupListener mIncomeGroupListener;

    public IncomeGroupP(IncomeGroupListener incomeGroupListener, BaseUI baseUI) {
        this.mIncomeGroupListener = incomeGroupListener;
        setActivity(baseUI);
    }


    public void setIncomeGroupData(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit) {
        if (TextUtils.isEmpty(timestamp)) {
            timestamp = "";
        }
        NetworkUtils.getNetworkUtils().getCollegeAccount(c, collegeId, startDate, endDate, timestamp, page, limit, new DataCallback<IncomeGroupBean>() {
            @Override
            public void onSuccess(IncomeGroupBean result, int i) {
                mIncomeGroupListener.incomeGroupData(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                mIncomeGroupListener.getDataField();
            }

            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                mIncomeGroupListener.getDataField();
            }


        });
    }

    public interface IncomeGroupListener {
        void incomeGroupData(IncomeGroupBean incomeGroupBean);
        void getDataField();
    }
}
