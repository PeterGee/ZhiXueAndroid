package com.risenb.studyknowledge.adapter.live;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.live.SelectLecturersBean;
import com.risenb.studyknowledge.beans.live.TeacherListBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/9/28.
 */

public class SelectLecturersAdapter extends BaseQuickAdapter<TeacherListBean, BaseViewHolder> {

    public SelectLecturersAdapter(@LayoutRes int layoutResId, @Nullable List<TeacherListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeacherListBean item) {

        helper.setText(R.id.tv_name, item.getUserName());
        ImageView iv_head_img = helper.getView(R.id.iv_head_img);
        GlideImageLoader.create(iv_head_img).loadCircleImage(item.getUserImg(),R.mipmap.unify_circle_head);
        helper.setText(R.id.tv_id, "ID："+item.getTeacherId());
    }
}
