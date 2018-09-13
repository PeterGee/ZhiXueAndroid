package com.risenb.studyknowledge.ui.post;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/19.
 */

public class UpdatePostP extends PresenterBase {
    private  UpdatePostListener mUpdatePostListener;

    public UpdatePostP(UpdatePostListener updatePostListener, FragmentActivity fragmentActivity){
        setActivity(fragmentActivity);
        this.mUpdatePostListener = updatePostListener;
    }

    public void setUpdatePost(String postId, String postName, String postIsFree,
                              String postPrice, String postIsTop,
                              String postContent){
        NetworkUtils.getNetworkUtils().updatePost(application.getC(), postId, postName, postIsFree, postPrice, postIsTop, postContent, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                mUpdatePostListener.updatePostSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }

        });
    }

    public interface UpdatePostListener{
        void updatePostSuccess();
    }
}
