package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.AnnounceBean;
import com.risenb.studyknowledge.beans.institution.LevelSettingBean;
import com.risenb.studyknowledge.utils.InputMethodUtils;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 会员等级设置
 */

public class LevelSettingAdapter extends BaseQuickAdapter<LevelSettingBean.DataBean.UserCollegeListBean,
        BaseViewHolder>{
    public LevelSettingAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public LevelSettingAdapter(@LayoutRes int layoutResId, @Nullable List<LevelSettingBean
            .DataBean.UserCollegeListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final LevelSettingBean.DataBean.UserCollegeListBean item) {
        helper.setText(R.id.tv_level,item.getUserCollegegradeName());
        final EditText et_jifen = helper.getView(R.id.et_jifen);
        String jifen = item.getUserCollegegradePoints() + "";
        et_jifen.setText(jifen);
        et_jifen.setSelection(et_jifen.getText().length());
        et_jifen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String content = et_jifen.getText().toString().trim();
                    if(Integer.valueOf(content)<=0){
                        et_jifen.setText("0");
                    }
                    item.setUserCollegegradePoints(Integer.valueOf(et_jifen.getText().toString().trim()));
                    InputMethodUtils.hideInputMethod(v);
                }
            }
        });

    }
}
