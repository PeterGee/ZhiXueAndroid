package com.risenb.studyknowledge.ui.institution;

import android.text.TextUtils;

import com.risenb.studyknowledge.beans.institution.RewardDivideBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/11.
 */

public class IncomeRewardDivideP extends PresenterBase {

    private IncomeRewardDivideListener mIncomeRewardDivideListener;

    public IncomeRewardDivideP(IncomeRewardDivideListener incomeRewardDivideListener, BaseUI baseUI) {
        this.mIncomeRewardDivideListener = incomeRewardDivideListener;
        setActivity(baseUI);
    }


    public void setIncomeRewardDivideData(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit) {
        if (TextUtils.isEmpty(timestamp)) {
            timestamp = "";
        }
        NetworkUtils.getNetworkUtils().getGiveScalAccount(c, collegeId, startDate, endDate, timestamp, page, limit, new DataCallback<RewardDivideBean>() {
            @Override
            public void onSuccess(RewardDivideBean result, int i) {
                mIncomeRewardDivideListener.incomeRewardDivideData(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }

    public interface IncomeRewardDivideListener {
        void incomeRewardDivideData(RewardDivideBean rewardDivideBean);
    }
}
