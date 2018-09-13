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
 * 黑名单adapter
 */

public class BlackListAdapter extends BaseQuickAdapter<AttendanceBean,BaseViewHolder>{
    public BlackListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public BlackListAdapter(@LayoutRes int layoutResId, @Nullable List<AttendanceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AttendanceBean item) {
        ImageView iv_blacklist_head = helper.getView(R.id.iv_blacklist_head);//头像
        GlideImageLoader.create(iv_blacklist_head).loadCircleImage(item.getUserImg(),R.mipmap.unify_circle_head);
        ImageView iv_blacklist_level = helper.getView(R.id.iv_blacklist_level);//会员等级图片
        GlideImageLoader.create(iv_blacklist_level).loadImage(item.getAttendGradeImg(),R.mipmap.unify_image_ing);

        helper.setText(R.id.tv_blacklist_name,item.getAttendUsername());//昵称
        helper.setText(R.id.tv_black_time,item.getUpdatetime());//拉黑时间
        helper.addOnClickListener(R.id.tv_cancel_black);//取消拉黑
    }
}
