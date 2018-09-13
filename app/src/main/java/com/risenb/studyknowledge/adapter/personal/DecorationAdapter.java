package com.risenb.studyknowledge.adapter.personal;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.MedalBean;
import com.risenb.studyknowledge.beans.institution.MedalInfoBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/21.
 * 勋章列表
 */

public class DecorationAdapter extends BaseQuickAdapter<MedalInfoBean,BaseViewHolder> {
    public DecorationAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public DecorationAdapter(@LayoutRes int layoutResId, @Nullable List<MedalInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MedalInfoBean item) {
        //勋章图片
        ImageView iv_member_medal = helper.getView(R.id.iv_member_medal);
        GlideImageLoader.create(iv_member_medal).loadImage(item.getMedalTypeMig(),R.mipmap.medal_icon);
        helper.setText(R.id.tv_decoration_name,item.getMedalTypeName());
        if(item.isChecked()){
            helper.setImageResource(R.id.iv_member_checked,R.mipmap.blue_check);
        }else {
            helper.setImageResource(R.id.iv_member_checked,R.mipmap.gray_uncheck);
        }
    }
}
