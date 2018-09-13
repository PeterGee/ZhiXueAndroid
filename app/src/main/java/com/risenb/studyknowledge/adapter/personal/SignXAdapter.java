package com.risenb.studyknowledge.adapter.personal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.SignInterceptBean;

import java.util.List;

/**
 * Created by yy on 2017/11/8.
 */

public class SignXAdapter extends BaseQuickAdapter<SignInterceptBean,BaseViewHolder> {
    public SignXAdapter(@LayoutRes int layoutResId, @Nullable List<SignInterceptBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SignInterceptBean item) {
        int width = ((Activity)mContext).getWindowManager().getDefaultDisplay().getWidth();
        int x_width = width - Utils.getUtils().getDimen(R.dimen.dm125);
        TextView tv_x_intercept = helper.getView(R.id.tv_x_intercept);
        ViewGroup.LayoutParams params = tv_x_intercept.getLayoutParams();
        params.width =x_width/30;
        tv_x_intercept.setLayoutParams(params);
//        if(TextUtils.equals(item.getIntercept(),"10")||TextUtils.equals(item.getIntercept(),"20")){
//            tv_x_intercept.setGravity(Gravity.CENTER);
//        }else {
//            tv_x_intercept.setGravity(Gravity.LEFT);
//        }
        helper.setText(R.id.tv_x_intercept,item.getIntercept());
    }
}
