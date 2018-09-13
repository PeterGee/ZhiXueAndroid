package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.BusinessInfoBean;
import com.risenb.studyknowledge.utils.CommonUtils;

import java.util.Date;
import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/20.
 * 友商售出
 */

public class BusinessOutAdapter extends BaseQuickAdapter<BusinessInfoBean,BaseViewHolder>{
    public BusinessOutAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public BusinessOutAdapter(@LayoutRes int layoutResId, @Nullable List<BusinessInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessInfoBean item) {
        ImageView iv_business_pic = helper.getView(R.id.iv_business_pic);//图片
        GlideImageLoader.create(iv_business_pic).loadImage(item.getTopicImg(),R.mipmap.unify_image_ing);

        SpannableString spannableString = new SpannableString("购进话题 ".concat(item.getTopicName()));
        spannableString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color
                .gray_9)), 0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color
                .gray_3)), 5,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.tv_businessIn_topic,spannableString);//购进话题
        helper.setText(R.id.tv_businessIn_college,item.getCollegeName());//购进学院名称
        long endtime = item.getBuytopicEndtime();
        String endTime = CommonUtils.getHHmm(new Date(endtime));
        helper.setText(R.id.tv_businessIn_endtime,endTime);//到期时间

        helper.addOnClickListener(R.id.tv_edit);//编辑
        helper.addOnClickListener(R.id.tv_delete);//删除

    }
}
