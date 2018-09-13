package com.risenb.studyknowledge.ui.institution;

import android.text.TextUtils;

import com.risenb.studyknowledge.beans.institution.PostAccountBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/11.
 */

public class IncomePostP extends PresenterBase {

    private IncomePostListener mIncomePostListener;

    public IncomePostP(IncomePostListener incomePostListener, BaseUI baseUI) {
        this.mIncomePostListener = incomePostListener;
        setActivity(baseUI);
    }


    public void setIncomePostData(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit) {
        if (TextUtils.isEmpty(timestamp)) {
            timestamp = "";
        }
        NetworkUtils.getNetworkUtils().getPostAccount(c, collegeId, startDate, endDate, timestamp, page, limit, new DataCallback<PostAccountBean>() {
            @Override
            public void onSuccess(PostAccountBean result, int i) {
                mIncomePostListener.incomePostData(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }

    public interface IncomePostListener {
        void incomePostData(PostAccountBean postAccountBean);
    }
}
