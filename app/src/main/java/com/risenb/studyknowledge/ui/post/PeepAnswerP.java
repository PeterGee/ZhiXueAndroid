package com.risenb.studyknowledge.ui.post;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/27.
 * 偷看贴子
 */

public class PeepAnswerP extends PresenterBase {
    private  PeepAnswerListener mPeepAnswerListener;

    public PeepAnswerP(FragmentActivity fragmentActivity , PeepAnswerListener peepAnswerListener) {
        setActivity(fragmentActivity);
        this.mPeepAnswerListener = peepAnswerListener;

    }

    public void setPeepAnswer(String postId,
                              String floorId, String peepUserId,
                              String peepBeUserId, String peepPrice){
        NetworkUtils.getNetworkUtils().peepAnswer(application.getC(), postId, postId, peepUserId, peepBeUserId, peepPrice, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                mPeepAnswerListener.peepAnswerSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }

        });
    }

    public interface PeepAnswerListener{
        void peepAnswerSuccess();
    }
}
