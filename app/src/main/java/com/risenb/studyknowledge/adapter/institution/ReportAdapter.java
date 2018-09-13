package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.ReportBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/20.
 * 举报列表
 */

public class ReportAdapter extends BaseQuickAdapter<ReportBean.ComplaintListBean, BaseViewHolder> {
    public ReportAdapter(@LayoutRes int layoutResId, @Nullable List<ReportBean.ComplaintListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReportBean.ComplaintListBean item) {
        helper.setText(R.id.tv_report_topic, item.getPostName());
        helper.setText(R.id.tv_report_name, item.getPostWriterId());
        helper.setText(R.id.tv_count, String.valueOf(item.getComplaintCount())+"人 举报");
        helper.setText(R.id.tv_time, String.valueOf(item.getComplaintCreationTime()));

        helper.setText(R.id.tv_content, String.valueOf(TextUtils.isEmpty(item.getComplaintContent()) ? "" : item.getComplaintContent()));

        ImageView iv_img = helper.getView(R.id.iv_img);
        GlideImageLoader.create(iv_img).loadImage(item.getTopicImg(),R.mipmap.unify_image_ing);

        helper.addOnClickListener(R.id.iv_choose);
        if (item.isShow()) {
            helper.setVisible(R.id.iv_choose, true);
        } else {
            helper.setVisible(R.id.iv_choose, false);
        }
        if (item.isChecked()) {
            helper.setImageResource(R.id.iv_choose, R.mipmap.checked_blue_report);
        } else {
            helper.setImageResource(R.id.iv_choose, R.mipmap.unchecked_gray_report);
        }
    }
}
