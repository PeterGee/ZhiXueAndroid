package com.risenb.studyknowledge.adapter.topic;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.ui.post.PostsListUI;

import java.util.List;

import glideimageview.GlideImageLoader;


/**
 * Created by zhuzh on 2017/8/19.
 */
public class TopicListAdapter extends BaseItemDraggableAdapter<TopicListBean, BaseViewHolder> {


    private boolean mIsPostList;

    public TopicListAdapter(int layoutResId, List<TopicListBean> data, boolean isPostList) {
        super(layoutResId, data);
        this.mIsPostList = isPostList;
    }

    @Override
    protected void convert(BaseViewHolder helper, final TopicListBean item) {
        //关闭复用
        helper.setIsRecyclable(false);

        helper.setText(R.id.tv_topic_name, item.getTopicName());//话题名字
//        helper.setTextColor(R.id.tv_added, mContext.getResources().getColor(R.color.gray));

        ImageView iv_topic_img = helper.getView(R.id.iv_topic_img);
        GlideImageLoader.create(iv_topic_img).loadImage(item.getTopicImg(), R.mipmap.unify_image_ing);
        int type = item.getTopicType();//话题类型
        String[] topics = mContext.getResources().getStringArray(R.array.topic_type);
        if (type == 1) {
            helper.setText(R.id.tv_post_status, topics[1]);
        } else if (type == 2) {
            helper.setText(R.id.tv_post_status, topics[2]);
        } else if (type == 3) {
            helper.setText(R.id.tv_post_status, topics[0]);
        }
        int costType = item.getTopicPayType();//话题付费类型
        String[] costs = mContext.getResources().getStringArray(R.array.add_topic);
        if (costType == 1) {
            helper.setText(R.id.tv_charge, costs[0]);
            helper.getView(R.id.tv_restrict).setVisibility(View.GONE);
        } else if (costType == 2) {
            helper.setText(R.id.tv_charge, costs[1]);
            helper.getView(R.id.tv_restrict).setVisibility(View.GONE);
        } else if (costType == 3) {
            helper.setText(R.id.tv_restrict, "限制：" + item.getTopicVipName());
            helper.getView(R.id.tv_charge).setVisibility(View.GONE);
        } else if (costType == 4) {
            helper.setText(R.id.tv_restrict, "限制：" + costs[3]);
            helper.getView(R.id.tv_charge).setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_topic_time, item.getCreationTime());

        if (mIsPostList) {
            helper.setVisible(R.id.right, false);
        }else {
            helper.setVisible(R.id.right, true);
            int topicUseyn = item.getTopicUseyn();//是否上架
            if (topicUseyn == 1) {
                helper.setText(R.id.tv_added, "已上架");
                helper.setText(R.id.tv_menu_two, "下架");//侧滑菜单文字
                helper.setBackgroundColor(R.id.tv_menu_two, mContext.getResources().getColor(R.color.white));
                helper.setTextColor(R.id.tv_menu_two, mContext.getResources().getColor(R.color.gray_9));
            } else if (topicUseyn == 0) {
                helper.setText(R.id.tv_added, "已下架");
                helper.setText(R.id.tv_menu_two, "上架");
            }

            helper.addOnClickListener(R.id.tv_menu_one).addOnClickListener(R.id.tv_menu_two).addOnClickListener(R.id.content);
        }

        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostsListUI.start(mContext, item.getTopicId());
            }
        });


    }

}
