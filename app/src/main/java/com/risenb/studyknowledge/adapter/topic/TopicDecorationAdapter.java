package com.risenb.studyknowledge.adapter.topic;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.TopicDecorationBean;
import com.risenb.studyknowledge.beans.topic.TopicListBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/9/25.
 */

public class TopicDecorationAdapter extends BaseQuickAdapter<TopicListBean,BaseViewHolder> {

    public TopicDecorationAdapter(@LayoutRes int layoutResId, @Nullable List<TopicListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopicListBean item) {

        helper.setText(R.id.tv_topic_name, item.getTopicName());
    }
}
