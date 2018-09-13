package com.risenb.studyknowledge.ui.institution;

import android.text.TextUtils;

import com.risenb.studyknowledge.beans.institution.TopicAccountBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/11.
 */

public class IncomeTopicP extends PresenterBase {

    private IncomeTopicListener mIncomeTopicListener;

    public IncomeTopicP(IncomeTopicListener incomeTopicListener, BaseUI baseUI) {
        this.mIncomeTopicListener = incomeTopicListener;
        setActivity(baseUI);
    }


    public void setIncomeTopicData(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit) {
        if (TextUtils.isEmpty(timestamp)) {
            timestamp = "";
        }
        NetworkUtils.getNetworkUtils().getTopicAccount(c, collegeId, startDate, endDate, timestamp, page, limit, new DataCallback<TopicAccountBean>() {
            @Override
            public void onSuccess(TopicAccountBean result, int i) {
                mIncomeTopicListener.incomeTopicData(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }

    public interface IncomeTopicListener {
        void incomeTopicData(TopicAccountBean topicAccountBean);
    }
}
