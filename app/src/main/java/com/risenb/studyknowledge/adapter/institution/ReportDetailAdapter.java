package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.ReportDetailBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/20.
 * 举报详情
 */

public class ReportDetailAdapter extends BaseQuickAdapter<ReportDetailBean.ComplaintListBean,BaseViewHolder>{
    public ReportDetailAdapter(@LayoutRes int layoutResId, @Nullable List<ReportDetailBean.ComplaintListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReportDetailBean.ComplaintListBean item) {
        ImageView iv_img = helper.getView(R.id.iv_icon);
        GlideImageLoader.create(iv_img).loadImage(item.getUserImg(),R.mipmap.unify_image_ing);

        helper.setText(R.id.tv_complaintInfo,String.valueOf(item.getComplaintInfoType()));
        helper.setText(R.id.tv_name,item.getUserName());
        helper.setText(R.id.tv_report_content,item.getComplaintInfo());
    }
}
