package com.risenb.studyknowledge.adapter.college;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.college.JoinedCollegeBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/10/26.
 */

public class CollegeNameAdapter extends BaseQuickAdapter<JoinedCollegeBean.DataBeanX.DataBean, BaseViewHolder> {
    public CollegeNameAdapter(@LayoutRes int layoutResId, @Nullable List<JoinedCollegeBean.DataBeanX.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JoinedCollegeBean.DataBeanX.DataBean item) {

        helper.setText(R.id.tv_college_name, item.getCollegeName());

        GlideImageLoader.create((ImageView) helper.getView(R.id.iv_college_img)).loadCircleImage(item.getCollegeLogo(), R.mipmap.unify_circle_head);


    }
}
