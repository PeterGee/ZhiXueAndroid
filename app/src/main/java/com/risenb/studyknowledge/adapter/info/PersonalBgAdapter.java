package com.risenb.studyknowledge.adapter.info;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.info.PersonalBgBean;
import com.risenb.studyknowledge.beans.institution.TopicBean;

import java.util.List;

/**
 * Created by yy on 2017/9/21.
 * 个性背景
 */

public class PersonalBgAdapter extends BaseQuickAdapter<PersonalBgBean,BaseViewHolder> {

    public PersonalBgAdapter(@LayoutRes int layoutResId, @Nullable List<PersonalBgBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonalBgBean item) {
        if(item.isInUse()){
            helper.setBackgroundRes(R.id.bt_change_bg,R.mipmap.bg_orange);
            helper.setText(R.id.bt_change_bg,R.string.in_use);
        }else {
            helper.setBackgroundRes(R.id.bt_change_bg,R.mipmap.bg_blue);
            helper.setText(R.id.bt_change_bg,R.string.click_change);
        }
    }
}
