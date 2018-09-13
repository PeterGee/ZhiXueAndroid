package com.risenb.studyknowledge.ui.post;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/18.
 */

public class ReleasePostP extends PresenterBase {
    private ReleasePostListener mReleasePostListener;

    public ReleasePostP(ReleasePostListener releasePostListener, FragmentActivity fragmentActivity) {
        this.mReleasePostListener = releasePostListener;
        setActivity(fragmentActivity);

    }

    public void setReleasePost(String postTopicId, String postName, String postType,
                               String postWriterId, String postIsFree,
                               String postPrice, String postIsTop,
                               String postContent) {
        if ("1".equals(postIsFree)) {
            postPrice = "0";
        }

        if (TextUtils.isEmpty(postIsTop)) {
            postIsTop = "0";
        }


        NetworkUtils.getNetworkUtils().addPost(application.getC(), postTopicId, postName, postType, postWriterId, postIsFree, postPrice, postIsTop, postContent, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                mReleasePostListener.releasePostSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }

        });
    }

    public interface ReleasePostListener {
        void releasePostSuccess();
    }
}
