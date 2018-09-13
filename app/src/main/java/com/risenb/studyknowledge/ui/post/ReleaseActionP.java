package com.risenb.studyknowledge.ui.post;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;
import com.risenb.studyknowledge.utils.ToastUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/14.
 */

public class ReleaseActionP extends PresenterBase {

    private  ReleaseActionListener mReleaseActionListener;

    public ReleaseActionP(ReleaseActionListener releaseActionListener, FragmentActivity fragmentActivity){
        setActivity(fragmentActivity);
        this.mReleaseActionListener = releaseActionListener;

    }

    public void setReleaseAction(String topicId,
                                 String activityImg, String activityName,
                                 String activityType, String activityIsTop,
                                 String activityWriterId, String startTime,
                                 String endTime,String activityContent){
        NetworkUtils.getNetworkUtils().addActivity(application.getC(), topicId, activityImg, activityName, activityType,
                activityIsTop, activityWriterId, startTime, endTime, activityContent,
                new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                mReleaseActionListener.releaseActionSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }

    public interface ReleaseActionListener{
        void releaseActionSuccess();
    }
}
