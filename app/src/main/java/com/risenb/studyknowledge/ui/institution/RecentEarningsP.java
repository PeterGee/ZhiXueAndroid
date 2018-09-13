package com.risenb.studyknowledge.ui.institution;

import com.risenb.studyknowledge.beans.institution.RecentEarningsBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/8.
 * 近期收益接口
 */

public class RecentEarningsP extends PresenterBase {
    private RecentEarningListener mRecentEarningListener;

    public RecentEarningsP(RecentEarningListener recentEarningListener, BaseUI baseUI) {
        setActivity(baseUI);
        this.mRecentEarningListener = recentEarningListener;
    }


    public void getRecentEarnings(String c, String collegeId, String startDate, String endDate) {
        NetworkUtils.getNetworkUtils().getAccount(c, collegeId, startDate, endDate, new DataCallback<RecentEarningsBean>() {
            @Override
            public void onSuccess(RecentEarningsBean result, int i) {
                mRecentEarningListener.recentEarningData(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }
        });
    }

    public interface RecentEarningListener {
        void recentEarningData(RecentEarningsBean recentEarningsBean);
    }
}
