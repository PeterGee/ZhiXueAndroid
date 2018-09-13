package com.risenb.studyknowledge.ui.post;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/18.
 */

public class CommentPostP extends PresenterBase {
    private  CommentPostListener mCommentPostListener;

    public CommentPostP(CommentPostListener commentPostListener, FragmentActivity fragmentActivity) {
        this.mCommentPostListener = commentPostListener;
        setActivity(fragmentActivity);
    }


    public void setCommentPost(String postId, String type, String floorData) {
        NetworkUtils.getNetworkUtils().commentPost(application.getC(), postId, type, floorData, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                mCommentPostListener.commentPostSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });


    }


    public interface CommentPostListener {
        void commentPostSuccess();
    }
}
