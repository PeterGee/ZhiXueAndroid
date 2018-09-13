package com.risenb.studyknowledge.adapter.personal;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;
import com.risenb.studyknowledge.beans.personal.MemberLevelBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 会员等级adapter
 */

public class MemberLevelAdapter extends BaseQuickAdapter<MemberLevelBean,BaseViewHolder>{
    public MemberLevelAdapter(@LayoutRes int layoutResId, @Nullable List<MemberLevelBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberLevelBean item) {
        helper.setText(R.id.tv_member_level,item.getUserCollegegradeName());
        if(item.isChecked()){
            helper.setBackgroundColor(R.id.rl_member_level,mContext.getResources().getColor(R
                    .color.gray_level_bg));
            helper.setTextColor(R.id.tv_member_level,mContext.getResources().getColor(R.color.orange));
        }else {
            helper.setBackgroundColor(R.id.rl_member_level,mContext.getResources().getColor(R
                    .color.white));
            helper.setTextColor(R.id.tv_member_level,mContext.getResources().getColor(R.color
                    .gray_6));
        }
    }
}
