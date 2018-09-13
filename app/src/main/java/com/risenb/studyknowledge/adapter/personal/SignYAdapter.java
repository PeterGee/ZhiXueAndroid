package com.risenb.studyknowledge.adapter.personal;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.SignInterceptBean;

import java.util.List;

/**
 * Created by yy on 2017/11/8.
 */

public class SignYAdapter extends BaseQuickAdapter<SignInterceptBean,BaseViewHolder> {
    public SignYAdapter(@LayoutRes int layoutResId, @Nullable List<SignInterceptBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SignInterceptBean item) {
        helper.setText(R.id.tv_y_intercept,item.getIntercept());
    }
}
