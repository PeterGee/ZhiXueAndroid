package com.risenb.studyknowledge.adapter.college;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.college.CollegeVipBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/10/23.
 */

public class CollegeVipAdapter extends BaseQuickAdapter<CollegeVipBean.DataBean.CollegeGradeListBean, BaseViewHolder> {
    public CollegeVipAdapter(@LayoutRes int layoutResId, @Nullable List<CollegeVipBean.DataBean.CollegeGradeListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollegeVipBean.DataBean.CollegeGradeListBean item) {
//        helper.setText(R.id.tv_vip_grade,item.getVip());
//        helper.setIsRecyclable(false);

        ImageView iv_vip_grade = helper.getView(R.id.iv_vip_grade);
        GlideImageLoader.create(iv_vip_grade).loadImage(item.getCollegeGradeImg(),R.mipmap.unify_image_ing);

        helper.setText(R.id.tv_if_live, item.getCollegeLivePostYn()+"");
        helper.setText(R.id.tv_live_num, item.getCollegeLiveNum()+"");

    }
}
