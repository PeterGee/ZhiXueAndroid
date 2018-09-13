package com.risenb.studyknowledge.adapter.personal;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.MemberTopicListBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 会员详情有偿提问话题列表adapter
 */

public class PaidQuestionListAdapter extends BaseQuickAdapter<MemberTopicListBean,BaseViewHolder>{
    public PaidQuestionListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public PaidQuestionListAdapter(@LayoutRes int layoutResId, @Nullable List<MemberTopicListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberTopicListBean item) {
        helper.setVisible(R.id.ll_post_reward,true);//显示有偿金额
        helper.setVisible(R.id.tv_isPay,false);
        helper.setVisible(R.id.view_peep,true);
        helper.setVisible(R.id.tv_peep_num,true);//显示偷看


        helper.setText(R.id.tv_topic_name,item.getPostName());//话题

        helper.setText(R.id.tv_teacher_name,item.getPostWriterId());//讲师名称
        helper.setText(R.id.tv_post_reward,item.getPostReward()+"");//赏金

        helper.setText(R.id.tv_time,item.getPostCreationTime());//时间
        helper.setText(R.id.tv_content, item.getPostContent());//话题内容
        int scanNum = item.getPostSeeNum();//浏览人数
        helper.setText(R.id.tv_scan_number, "浏览 "+scanNum);
        int commentNum = item.getPostTalkNum();//评论人数
        helper.setText(R.id.tv_comment_num, "评论 "+commentNum);
        int peepNum = item.getPostPeepNum();//偷看人数
        helper.setText(R.id.tv_peep_num, "偷看 "+peepNum);

    }
}
