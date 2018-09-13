package com.risenb.studyknowledge.adapter.post;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.posts.PostListBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/9/26.
 */

public class PostsCourseAdapter extends BaseQuickAdapter<PostListBean, BaseViewHolder> {

    public PostsCourseAdapter(@LayoutRes int layoutResId, @Nullable List<PostListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PostListBean item) {

        helper.setText(R.id.tv_name, item.getUserName());
//        helper.getView(R.id.tv_live_status).setVisibility(View.VISIBLE);
        helper.setVisible(R.id.tv_live_status, false);
        int postType = item.getPostType();
        Log.i("postType", postType + "");
        if (postType == 1 || postType == 2) {
            helper.setVisible(R.id.tv_cost_status, true);
            helper.setVisible(R.id.tv_cost_money, false);
            helper.setVisible(R.id.tv_peep_num, false);
            int postIsFree = item.getPostIsFree(); //1=免费；2=付费
            if (postIsFree == 1) {
                helper.setText(R.id.tv_cost_status, "免费");
            } else if (postIsFree == 2) {
                helper.setText(R.id.tv_cost_status, "付费");
            }
        } else if (postType == 3) {
            helper.setVisible(R.id.tv_cost_status, false);
            helper.setVisible(R.id.tv_cost_money, true);
            helper.setText(R.id.tv_cost_money, "赏金" + item.getPostReward());
        }
        helper.setText(R.id.tv_post_name1, item.getPostName());
        helper.setText(R.id.tv_post_name2, item.getPostName());
        helper.setText(R.id.tv_post_time, item.getPostCreationTime());
        helper.setText(R.id.tv_collect_num, "浏览 " + item.getPostCollection());
        helper.setText(R.id.tv_comment_num, "评论 " + item.getPostTalkNum());
        helper.setText(R.id.tv_peep_num, "偷看 " + item.getPostPeepNum());
    }
}
