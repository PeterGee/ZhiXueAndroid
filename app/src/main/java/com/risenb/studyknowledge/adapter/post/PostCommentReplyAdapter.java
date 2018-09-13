package com.risenb.studyknowledge.adapter.post;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.posts.PostsDetailsBean;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by zhuzh on 2017/11/28.
 */

public class PostCommentReplyAdapter extends BaseQuickAdapter<PostsDetailsBean.PostCommentListBean.TalkInfoBean, BaseViewHolder> {

    private static int floorId;//贴子楼层ID


    public PostCommentReplyAdapter(@LayoutRes int layoutResId, @Nullable List<PostsDetailsBean.PostCommentListBean.TalkInfoBean> data, int floorId) {
        super(layoutResId, data);
        this.floorId = floorId;
    }

    @Override
    protected void convert(BaseViewHolder helper, final PostsDetailsBean.PostCommentListBean.TalkInfoBean item) {

        final String finalMFloorUserId = showReply(helper, item.getTalkStr(), floorId);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复楼层的回调
                EventBus.getDefault().post(new PostEvent().setEventType(PostEvent.REPLY_POST_COMMENT).setData(finalMFloorUserId + "=" + floorId));
            }
        });
    }


    /**
     * 回复数据转换及拼接
     * @param helper
     * @param talkStr
     * @param floorId
     * @return
     */
    public static String showReply(BaseViewHolder helper, String talkStr , final int floorId) {

        String commentUserId = "";//回复者ID
        String beCommentUserId = "";//被回复者ID
        String mFloorUserId = "";
        if (TextUtils.isEmpty(talkStr)) {
            talkStr = "";
        } else {
            String[] split = talkStr.split("=");
            talkStr = split[0];//需要显示的内容部份

            if (split.length == 5) {
                commentUserId = split[3].split(",")[0];
                beCommentUserId = split[4];//被回复者ID
            } else {
                commentUserId = split[3];
            }
        }

        String[] split = talkStr.split(":");
        String nickname = split[0];//回复评论者的昵称

        String commentContent = ": " + split[1];//回复的内容
        TextView text = helper.getView(R.id.tv_comment_reply);

        //如果包含回复说明是楼层中的对某人的回复，谁回复谁的
        if (nickname.contains("回复")) {
            String[] replyNickname = nickname.split("回复");
            mFloorUserId = beCommentUserId;
            changeTextColor(text, nickname + commentContent, 0, replyNickname[0].length(), replyNickname[0].length() + 3, nickname.length());
            nickname = replyNickname[0];
        } else {
            //回复贴子中的某条评论的
            mFloorUserId = commentUserId;
            changeTextColor(text, nickname + commentContent, 0, nickname.length(), 0, 0);
        }


        return  mFloorUserId + "=" + nickname;

    }


    /**
     * 改变字体颜色
     *
     * @param text
     * @param content
     * @param start
     * @param end
     * @param start2
     * @param end2
     */
    public static void changeTextColor(TextView text, String content, int start, int end, int start2, int end2) {
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new ForegroundColorSpan(text.getContext().getResources().getColor(R.color.blue)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (start2 != 0 && end2 != 0) {
            style.setSpan(new ForegroundColorSpan(text.getContext().getResources().getColor(R.color.blue)), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        text.setText(style);
    }
}
