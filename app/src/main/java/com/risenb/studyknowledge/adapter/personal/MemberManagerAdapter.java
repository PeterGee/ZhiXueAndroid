package com.risenb.studyknowledge.adapter.personal;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/20.
 * C端会员管理adapter
 */

public class MemberManagerAdapter extends BaseQuickAdapter<AttendanceBean,BaseViewHolder>{
    public MemberManagerAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public MemberManagerAdapter(@LayoutRes int layoutResId, @Nullable List<AttendanceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AttendanceBean item) {
        helper.setText(R.id.tv_member_name,item.getAttendUsername());//昵称
        ImageView iv_member_head = helper.getView(R.id.iv_member_head);//头像
        GlideImageLoader.create(iv_member_head).loadCircleImage(item.getUserImg(),R.mipmap.unify_circle_head);
        ImageView iv_member_level = helper.getView(R.id.iv_member_level);//会员等级图片
        GlideImageLoader.create(iv_member_level).loadImage(item.getAttendGradeImg(),R.mipmap.unify_image_ing);
        int type = item.getAttendType();//人员类型(0：学生、1：管理员、2老师
        String[] identity = mContext.getResources().getStringArray(R.array.member_identity);
        if(type==0){
            helper.setText(R.id.tv_member_type,identity[1]);
        }else if(type==1){
            helper.setText(R.id.tv_member_type,identity[2]);
        }else if(type==2){
            helper.setText(R.id.tv_member_type,identity[0]);
        }
        int isBlack = item.getAttendAllowYn();//1不是黑名单 2是黑名单
        if(isBlack==1){
            helper.setText(R.id.tv_isBlack,mContext.getResources().getString(R.string.black_false));
        }else if(isBlack==2){
            helper.setText(R.id.tv_isBlack,mContext.getResources().getString(R.string.black_true));
        }
        int talkLimit = item.getAttendTalkLimit();//是否禁言(0：否、1：是)
        if(talkLimit==0){
            helper.setText(R.id.tv_noSpeaking,mContext.getResources().getString(R.string.nospeaking_false));
        }else if(talkLimit==1){
            helper.setText(R.id.tv_noSpeaking,mContext.getResources().getString(R.string.nospeaking_true));
        }

        helper.addOnClickListener(R.id.tv_edit);//编辑
        helper.addOnClickListener(R.id.tv_kick_out);//踢出
        helper.addOnClickListener(R.id.content);//条目
    }
}
