package com.risenb.studyknowledge.adapter.topic;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.ChargePrivilegeBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/11/14.
 */

public class ChargePrivilegeAdapter extends BaseQuickAdapter<ChargePrivilegeBean, BaseViewHolder> {

    public ChargePrivilegeAdapter(@LayoutRes int layoutResId, @Nullable List<ChargePrivilegeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChargePrivilegeBean item) {

        helper.setText(R.id.tv_nickname, item.getName());
        if(item.isChecked()){
            helper.setImageResource(R.id.iv_select,R.mipmap.checked_blue_report);
        }else {
            helper.setImageResource(R.id.iv_select,R.mipmap.unchecked_gray_report);
        }
    }
}
