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

public class ReplyCommentP extends PresenterBase {
    private ReplyCommentListener mReplyCommentListener;

    public ReplyCommentP(ReplyCommentListener replyCommentListener, FragmentActivity fragmentActivity) {
        setActivity(fragmentActivity);
        this.mReplyCommentListener = replyCommentListener;

    }

    public void setReplyComment(String postId, String floorId,
                                String commentUserId, String beCommentUserId, String talkInfo) {

        if (TextUtils.isEmpty(beCommentUserId)) {
            makeText("被评论ID不能为空");
            return;
        }
        NetworkUtils.getNetworkUtils().commentReply(
                application.getC(), postId, floorId,
                commentUserId, beCommentUserId, talkInfo, new DataCallback<NetBaseBean>() {
                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        mReplyCommentListener.replyCommentSuccess();
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {

                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }


                });
    }

    public interface ReplyCommentListener {
        void replyCommentSuccess();
    }
}
