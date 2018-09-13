package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.beans.institution.PaidQuestionBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yy on 2017/9/26.
 * 有偿提问收益明细
 */
public class IncomePaidQuestionP extends PresenterBase {
    private InComePaidQuestionListener mInComePaidQuestionListener;

    public IncomePaidQuestionP(InComePaidQuestionListener inComePaidQuestionListener, FragmentActivity fragmentActivity) {
        setActivity(fragmentActivity);
        this.mInComePaidQuestionListener = inComePaidQuestionListener;
    }

    public void setIncomePaidQuestion(String collegeId,
                                      String startDate, String endDate,
                                      String timestamp, String page,
                                      String limit) {
        NetworkUtils.getNetworkUtils().getYouChangAccount(application.getC(),
                collegeId, startDate, endDate,
                timestamp, page, limit, new DataCallback<PaidQuestionBean>() {
            @Override
            public void onSuccess(PaidQuestionBean result, int i) {
                mInComePaidQuestionListener.inComePaidQuestionSuccess(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }

    public interface InComePaidQuestionListener {
        void inComePaidQuestionSuccess(PaidQuestionBean paidQuestionBean);
    }

}
