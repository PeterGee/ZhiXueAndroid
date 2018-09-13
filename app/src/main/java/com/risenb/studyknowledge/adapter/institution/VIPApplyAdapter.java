package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.VIPApplyBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * vip申请明细
 */

public class VIPApplyAdapter extends BaseQuickAdapter<VIPApplyBean.DataBean.VipDetailListBean,BaseViewHolder>{
    public VIPApplyAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public VIPApplyAdapter(@LayoutRes int layoutResId, @Nullable List<VIPApplyBean.DataBean.VipDetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VIPApplyBean.DataBean.VipDetailListBean item) {
        helper.setText(R.id.tv_vip_level,item.getVipGradeName());//VIP等级名称
        helper.setText(R.id.tv_vip_deadline,item.getTime()+"");//期限
        int status = item.getStatus();//状态(0：购买中、1：通过、2：拒绝)
        String[] pay_state = mContext.getResources().getStringArray(R.array.pay_state);
        helper.setText(R.id.tv_vip_state,pay_state[status]);
        int type = item.getGradeType();//购买类型(0：按年、1：按月
        String[] gradeType = mContext.getResources().getStringArray(R.array.gradeType);
        helper.setText(R.id.tv_vip_type,gradeType[type]);
        String createTime = item.getCreateTime();//2017-03-21 18:19:16
        String[] split = createTime.split(" ");//空格切割
        helper.setText(R.id.tv_vip_date,split[0]);//年月日
        helper.setText(R.id.tv_vip_time,split[1]);//小时分钟

    }
}
