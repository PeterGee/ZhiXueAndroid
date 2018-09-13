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
 * 帖子收入明细类型
 */

public class IncomePostAdapter extends BaseQuickAdapter<IncomeDetailBean,BaseViewHolder>{
    public IncomePostAdapter(@LayoutRes int layoutResId, @Nullable List<IncomeDetailBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IncomeDetailBean item) {
        helper.setText(R.id.tv_post_name,item.getName());//名称
        helper.setText(R.id.tv_post_content,item.getContent());//内容
        helper.setText(R.id.tv_post_price,item.getPrice());//价格
        helper.setText(R.id.tv_post_time,item.getTime());//时间
    }
}
