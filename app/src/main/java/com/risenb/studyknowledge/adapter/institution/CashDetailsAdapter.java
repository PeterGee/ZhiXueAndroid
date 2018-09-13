package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.CashDetailsBean;
import com.risenb.studyknowledge.beans.institution.VIPApplyBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 提现明细
 */

public class CashDetailsAdapter extends BaseQuickAdapter<CashDetailsBean,BaseViewHolder>{
    public CashDetailsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public CashDetailsAdapter(@LayoutRes int layoutResId, @Nullable List<CashDetailsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CashDetailsBean item) {
        helper.setText(R.id.tv_cash_money,item.getCashValue()+"元");//提现金额
        helper.setText(R.id.tv_apply_time,item.getCashCreationtime());//申请时间
        int payStatus = item.getPayStatus();//支付状态 0=申请中；1=已审批打款，2=驳回申请；
        String[] cashPayStatus = mContext.getResources().getStringArray(R.array.cash_pay_state);
        helper.setText(R.id.tv_pay_state,cashPayStatus[payStatus]);
        helper.setText(R.id.tv_update_time,item.getUpdatetime());//审核时间
    }
}
