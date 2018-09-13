package com.risenb.studyknowledge.adapter.personal;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/20.
 * 踢出的会员adapter
 */

public class KickOutMemberAdapter extends BaseQuickAdapter<AttendanceBean,BaseViewHolder>{
    public KickOutMemberAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public KickOutMemberAdapter(@LayoutRes int layoutResId, @Nullable List<AttendanceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AttendanceBean item) {
        ImageView iv_kick_member_head = helper.getView(R.id.iv_kick_member_head);//头像
        GlideImageLoader.create(iv_kick_member_head).loadCircleImage(item.getUserImg(),R.mipmap.unify_circle_head);
        ImageView iv_kick_member_level = helper.getView(R.id.iv_kick_member_level);//会员等级图片
        GlideImageLoader.create(iv_kick_member_level).loadImage(item.getAttendGradeImg(),R.mipmap.unify_image_ing);
        helper.setText(R.id.tv_kick_member_name,item.getAttendUsername());//昵称
        helper.setText(R.id.tv_kick_time,item.getUpdatetime());//踢出时间
        helper.addOnClickListener(R.id.tv_invite);//邀请
    }
}
