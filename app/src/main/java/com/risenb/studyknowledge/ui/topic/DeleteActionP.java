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

public class DeleteActionP extends PresenterBase {

    private DeleteActionListener mDeleteActionListener;

    public DeleteActionP(DeleteActionListener deleteActionListener, FragmentActivity fragmentActivity) {
        setActivity(fragmentActivity);
        this.mDeleteActionListener = deleteActionListener;


    }

    public void setDeleteAction(String c, String activityId) {
        NetworkUtils.getNetworkUtils().delActivity(c, activityId, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                mDeleteActionListener.deleteActionSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }

        });
    }

    public interface DeleteActionListener {
        void deleteActionSuccess();
    }
}
