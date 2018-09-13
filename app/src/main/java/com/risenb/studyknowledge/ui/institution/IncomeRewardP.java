package com.risenb.studyknowledge.ui.institution;

import android.text.TextUtils;

import com.risenb.studyknowledge.beans.institution.IncomeRewardBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/11.
 */

public class IncomeRewardP extends PresenterBase {

    private IncomeRewardListener mIncomeRewardListener;

    public IncomeRewardP(IncomeRewardListener incomeRewardListener, BaseUI baseUI) {
        this.mIncomeRewardListener = incomeRewardListener;
        setActivity(baseUI);
    }


    public void setIncomeRewardData(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit) {
        if (TextUtils.isEmpty(timestamp)) {
            timestamp = "";
        }
        NetworkUtils.getNetworkUtils().getGiveAccount(c, collegeId, startDate, endDate, timestamp, page, limit, new DataCallback<IncomeRewardBean>() {
            @Override
            public void onSuccess(IncomeRewardBean result, int i) {
                mIncomeRewardListener.incomeRewardData(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }

    public interface IncomeRewardListener {
        void incomeRewardData(IncomeRewardBean incomeRewardBean);
    }
}
