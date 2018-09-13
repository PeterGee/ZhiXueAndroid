package com.risenb.studyknowledge.adapter.topic;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.ActionNeophyteBean;
import com.risenb.studyknowledge.beans.topic.ActivityUserListBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/9/22.
 */

public class ActionNeophyteAdapter extends BaseQuickAdapter<ActivityUserListBean,BaseViewHolder> {

    public ActionNeophyteAdapter(@LayoutRes int layoutResId, @Nullable List<ActivityUserListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActivityUserListBean item) {

        helper.setText(R.id.tv_player, item.getUserName());
        helper.setText(R.id.tv_phone_num, "联系方式："+item.getTel());
        helper.setText(R.id.tv_join_time, item.getActivityTime());

    }
}
