package com.risenb.studyknowledge.ui.topic;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/15.
 */

public class UpdateActionP extends PresenterBase {
    private  UpdateActionListener mUpdateActionListener;

    public UpdateActionP(UpdateActionListener updateActionListener, FragmentActivity fragmentActivity) {
        setActivity(fragmentActivity);
        this.mUpdateActionListener = updateActionListener;

    }

    public void setUpdateAction( String topicId, String activityId,
                                String activityImg, String activityName,
                                String activityType, String activityIsTop,
                                String activityWriterId, String startTime,
                                String endTime, String activityContent) {
        NetworkUtils.getNetworkUtils().updateActivity(application.getC(), topicId,
                activityId, activityImg, activityName, activityType,
                activityIsTop, activityWriterId,
                startTime, endTime, activityContent, new DataCallback<NetBaseBean>() {
                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        mUpdateActionListener.updateActionSuccess();
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {

                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }


                });
    }

    public interface UpdateActionListener {
        void updateActionSuccess();
    }
}
