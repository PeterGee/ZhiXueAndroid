package com.risenb.studyknowledge.adapter.personal;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.MemberIdentityBean;
import com.risenb.studyknowledge.beans.personal.MemberLevelBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 会员身份adapter
 */

public class MemberIdentityAdapter extends BaseQuickAdapter<MemberIdentityBean,BaseViewHolder>{
    public MemberIdentityAdapter(@LayoutRes int layoutResId, @Nullable List<MemberIdentityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberIdentityBean item) {
        helper.setText(R.id.tv_member_identity,item.getIdentity());
        if(item.isChecked()){
            helper.setTextColor(R.id.tv_member_identity,mContext.getResources().getColor(R.color.red_checked));
        }else {
            helper.setTextColor(R.id.tv_member_identity,mContext.getResources().getColor(R.color
                    .gray_9));
        }
    }
}
