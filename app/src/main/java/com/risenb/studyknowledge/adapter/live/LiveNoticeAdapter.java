package com.risenb.studyknowledge.adapter.live;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.live.LiveNoticeBean;
import com.risenb.studyknowledge.beans.live.PostLiveListBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/9/22.
 */

public class LiveNoticeAdapter extends BaseQuickAdapter<PostLiveListBean, BaseViewHolder> {
    public LiveNoticeAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public LiveNoticeAdapter(@LayoutRes int layoutResId, @Nullable List<PostLiveListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PostLiveListBean item) {
        helper.setIsRecyclable(false);

        helper.setText(R.id.tv_action_name,item.getPostName());
        helper.addOnClickListener(R.id.tv_menu_one).addOnClickListener(R.id.tv_menu_two).addOnClickListener(R.id.content);

//        helper.setImageResource(R.id.iv_img,R.drawable.ic_launcher);
//        Glide.with(mContext).load("http://ww2.sinaimg.cn/large/7a8aed7bjw1f3c7zc3y3rj20rt15odmp.jpg").into((ImageView) helper.getView(R.id.iv_img));

        ImageView iv_live_img = helper.getView(R.id.iv_live_img);
        GlideImageLoader.create(iv_live_img).loadImage(item.getUserImg(),R.mipmap.unify_image_ing);
        helper.setText(R.id.tv_live_time, item.getPostLivetime());
        helper.setText(R.id.tv_topic_name, "话题："+item.getTopicName());
        helper.setText(R.id.tv_lecturer, "讲师："+item.getUserName());
        helper.setText(R.id.tv_notice_content, "预告："+item.getPostInfo());




    }


}
