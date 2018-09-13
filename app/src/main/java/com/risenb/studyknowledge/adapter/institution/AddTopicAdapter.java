package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.TopicListBean;

import java.util.List;

/**
 * Created by yy on 2017/9/21.
 * 添加所属话题
 */

public class AddTopicAdapter extends BaseQuickAdapter<TopicListBean, BaseViewHolder> {

    public AddTopicAdapter(@LayoutRes int layoutResId, @Nullable List<TopicListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopicListBean item) {
        helper.setText(R.id.tv_topic, item.getTopicName());
    }
}
