package com.risenb.studyknowledge.adapter.personal;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.MemberApplyBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/19.
 * 会员申请adapter
 */

public class MemberApplyAdapter extends BaseQuickAdapter<AttendanceBean,BaseViewHolder> {
    public MemberApplyAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public MemberApplyAdapter(@LayoutRes int layoutResId, @Nullable List<AttendanceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AttendanceBean item) {
        ImageView iv_member_head = helper.getView(R.id.iv_member_head);//头像
        GlideImageLoader.create(iv_member_head).loadCircleImage(item.getUserImg(),R.mipmap.unify_circle_head);
        ImageView iv_member_level = helper.getView(R.id.iv_member_level);//会员等级图片
        GlideImageLoader.create(iv_member_level).loadImage(item.getAttendGradeImg(),R.mipmap.unify_image_ing);
        helper.setText(R.id.tv_member_name,item.getAttendUsername());//昵称

        if(item.isShow()){
            helper.setVisible(R.id.iv_member_checked,true);
        }else {
            helper.setVisible(R.id.iv_member_checked,false);
        }
        if(item.isChecked()){
            helper.setImageResource(R.id.iv_member_checked,R.mipmap.blue_check);
        }else {
            helper.setImageResource(R.id.iv_member_checked,R.mipmap.gray_uncheck);
        }

    }


}
