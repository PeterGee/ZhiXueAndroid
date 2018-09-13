package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.AdviceListBean;
import com.risenb.studyknowledge.beans.institution.AnnounceBean;
import com.risenb.studyknowledge.beans.institution.FeedbackBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 意见反馈列表
 */

public class FeedbackAdapter extends BaseQuickAdapter<AdviceListBean,BaseViewHolder>{
    public FeedbackAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public FeedbackAdapter(@LayoutRes int layoutResId, @Nullable List<AdviceListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdviceListBean item) {
        helper.setText(R.id.tv_feedback_name,item.getUserName());//反馈人姓名,
        helper.setText(R.id.tv_feedback_time,item.getAdviceCreationTime());//反馈时间,
        int type = item.getAdviceType();//反馈来源（0：用户，1：学院）
        String[] feedback_type = mContext.getResources().getStringArray(R.array.feedback_type);
        helper.setText(R.id.tv_feedback_type,feedback_type[type]);
        int adviceReadyn = item.getAdviceReadyn();//是否已读（0：否，1：是）
        String[] adviceReadyn_type = mContext.getResources().getStringArray(R.array.adviceReadyn);
        helper.setText(R.id.tv_feedback_isRead,adviceReadyn_type[adviceReadyn]);
        helper.setText(R.id.tv_feedback_phone,item.getUserPhone());//反馈人电话号码,
        helper.setText(R.id.tv_feedback_email,item.getUserEmail());//反馈人邮箱
        helper.setText(R.id.tv_feedback_content,item.getAdviceContent());//反馈内容


    }
}
