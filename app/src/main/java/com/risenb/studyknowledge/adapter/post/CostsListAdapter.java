package com.risenb.studyknowledge.adapter.post;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.posts.CostsListBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/11/7.
 */

public class CostsListAdapter extends BaseQuickAdapter<CostsListBean,BaseViewHolder> {


    public CostsListAdapter(@LayoutRes int layoutResId, @Nullable List<CostsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CostsListBean item) {
        helper.setText(R.id.tv_cost_item,item.getContent());
        if(item.isChecked()){
            helper.setTextColor(R.id.tv_cost_item,mContext.getResources().getColor(R.color.red_checked));
        }else {
            helper.setTextColor(R.id.tv_cost_item,mContext.getResources().getColor(R.color.gray_6));
        }
    }
}
