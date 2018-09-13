package com.risenb.studyknowledge.adapter.post;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.posts.PostsDetailsBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/10/9.
 */

public class WorkCommentListAdapter extends BaseQuickAdapter<PostsDetailsBean.WorkCommentListBean, BaseViewHolder> {


    public WorkCommentListAdapter(@LayoutRes int layoutResId, @Nullable List<PostsDetailsBean.WorkCommentListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PostsDetailsBean.WorkCommentListBean item) {

        helper.setText(R.id.tv_nickname, item.getFloorInfo().getUserName());
        helper.setText(R.id.tv_time, item.getFloorInfo().getFloorCreationtime());
        GlideImageLoader.create((ImageView) helper.getView(R.id.iv_head)).loadCircleImage(item.getFloorInfo().getUserImg(), R.mipmap.unify_circle_head);

        helper.setText(R.id.tv_comment_floor, item.getFloorInfo().getFloorData());

        RecyclerView rv_comment_reply = helper.getView(R.id.rv_comment_reply);
        WorkCommentReplyAdapter mAdapter = new WorkCommentReplyAdapter(R.layout.post_comment_reply_item, item.getTalkInfo(),item.getFloorInfo().getFloorId());
//        mAdapter.setData(item.getVoteOptionBean());
        rv_comment_reply.setAdapter(mAdapter);
        rv_comment_reply.setLayoutManager(new LinearLayoutManager(mContext));
        rv_comment_reply.setNestedScrollingEnabled(false);

    }
}
