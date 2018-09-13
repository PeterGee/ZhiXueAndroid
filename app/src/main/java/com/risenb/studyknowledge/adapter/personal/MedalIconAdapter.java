package com.risenb.studyknowledge.adapter.personal;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;
import com.risenb.studyknowledge.beans.personal.MedalIconBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/20.
 * 会员设置勋章图标adapter
 */

public class MedalIconAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public MedalIconAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iv_medal_pic = helper.getView(R.id.iv_medal_pic);//勋章图片
        GlideImageLoader.create(iv_medal_pic).loadImage(item,R.mipmap.medal_icon);
    }
}
