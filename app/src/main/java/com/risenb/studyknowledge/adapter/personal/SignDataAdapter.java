package com.risenb.studyknowledge.adapter.personal;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.SignInManagerBean;
import com.risenb.studyknowledge.beans.personal.SignInterceptBean;

import java.util.List;

/**
 * Created by yy on 2017/11/8.
 */

public class SignDataAdapter extends BaseQuickAdapter<SignInManagerBean.DataBean.TotalNumBean,BaseViewHolder> {
    public SignDataAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public SignDataAdapter(@LayoutRes int layoutResId, @Nullable List<SignInManagerBean.DataBean.TotalNumBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SignInManagerBean.DataBean.TotalNumBean item) {
        int width = ((Activity)mContext).getWindowManager().getDefaultDisplay().getWidth();
        int x_width = width - Utils.getUtils().getDimen(R.dimen.dm125);
        RelativeLayout ll_data_intercept = helper.getView(R.id.ll_data_intercept);
        ViewGroup.LayoutParams params = ll_data_intercept.getLayoutParams();
        params.width =x_width/30;
        ll_data_intercept.setLayoutParams(params);

        ImageView iv_data = helper.getView(R.id.iv_data);
        ViewGroup.LayoutParams params1 = iv_data.getLayoutParams();
        Integer integer = item.getNum();
        if(TextUtils.equals(item.getNum()+"","0")){
            params1.height=0;
        }else {
            params1.height= (integer*Utils.getUtils().getDimen(R.dimen.dm080)/100)+Utils.getUtils().getDimen(R.dimen.dm022);
        }
        iv_data.setLayoutParams(params1);
    }
}
