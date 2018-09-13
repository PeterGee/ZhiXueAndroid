package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.AnnounceBean;
import com.risenb.studyknowledge.beans.institution.MedalBean;
import com.risenb.studyknowledge.beans.institution.MedalInfoBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/20.
 * 勋章管理
 */

public class MedalManagerAdapter extends BaseQuickAdapter<MedalInfoBean,BaseViewHolder>{
    public MedalManagerAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public MedalManagerAdapter(@LayoutRes int layoutResId, @Nullable List<MedalInfoBean>
            data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MedalInfoBean item) {
        //勋章图片
        ImageView iv_medal_pic = helper.getView(R.id.iv_medal_pic);
        GlideImageLoader.create(iv_medal_pic).loadImage(item.getMedalTypeMig(),R.mipmap.medal_icon);
        helper.setText(R.id.tv_medal_name,item.getMedalTypeName());
        helper.setText(R.id.tv_medal_describe,item.getMedalTypeInfo());

        helper.addOnClickListener(R.id.tv_delete);//删除
        helper.addOnClickListener(R.id.tv_edit);//编辑
        helper.addOnClickListener(R.id.content);
    }
}
