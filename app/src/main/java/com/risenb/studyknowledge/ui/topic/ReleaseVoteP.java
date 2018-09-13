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

public class ReleaseVoteP extends PresenterBase {
    private  ReleaseVoteListener mReleaseVoteListener;

    public ReleaseVoteP(ReleaseVoteListener releaseVoteListener, FragmentActivity fragmentActivity) {
        setActivity(fragmentActivity);
        this.mReleaseVoteListener = releaseVoteListener;
    }

    public void setReleaseVote(String topicId, String voteName, String topicType, String voteIsTop,
                               String voteWriterId, String startTime, String endTime, String voteSecNames,
                               boolean isMultipleChoice) {
        NetworkUtils.getNetworkUtils().addVote(application.getC(), topicId, voteName, topicType, voteIsTop, voteWriterId, startTime, endTime, voteSecNames, isMultipleChoice, new DataCallback<NetBaseBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onSuccess(NetBaseBean result, int i) {
                mReleaseVoteListener.releaseVoteSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }
        });
    }


    public interface ReleaseVoteListener {
        void releaseVoteSuccess();
    }
}
