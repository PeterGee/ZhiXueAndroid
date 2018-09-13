package com.risenb.studyknowledge.adapter.post;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.beans.posts.PostsDetailsBean;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by zhuzh on 2017/11/28.
 */

public class WorkCommentReplyAdapter extends BaseQuickAdapter<PostsDetailsBean.WorkCommentListBean.TalkInfoBean, BaseViewHolder> {

    private int floorId;

    public WorkCommentReplyAdapter(@LayoutRes int layoutResId, @Nullable List<PostsDetailsBean.WorkCommentListBean.TalkInfoBean> data, int floorId) {
        super(layoutResId, data);
        this.floorId = floorId;
    }

    @Override
    protected void convert(BaseViewHolder helper, PostsDetailsBean.WorkCommentListBean.TalkInfoBean item) {
        final String finalMFloorUserId = PostCommentReplyAdapter.showReply(helper, item.getWorkTalkStr(), floorId);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复楼层的回调
                EventBus.getDefault().post(new PostEvent().setEventType(PostEvent.REPLY_WORK_COMMENT).setData(finalMFloorUserId + "=" + floorId));
            }
        });
    }


}
