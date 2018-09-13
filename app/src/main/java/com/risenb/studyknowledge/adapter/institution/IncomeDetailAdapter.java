package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.IncomeDetailBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 入群收益明细类型
 */

public class IncomeDetailAdapter extends BaseQuickAdapter<IncomeDetailBean, BaseViewHolder> {
    public IncomeDetailAdapter(@LayoutRes int layoutResId, @Nullable List<IncomeDetailBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IncomeDetailBean item) {
        helper.setText(R.id.tv_income_name, item.getName());//名称
        helper.setText(R.id.tv_income_time, item.getTime());//时间
        helper.setText(R.id.tv_income_price, item.getPrice() + "元");//价格
        helper.setText(R.id.tv_price_name, item.getPriceName());//价格名目
    }
}
