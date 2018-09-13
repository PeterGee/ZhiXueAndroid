package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.RankBean;
import com.risenb.studyknowledge.beans.personal.MemberLevelBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 会员等级adapter
 */

public class RankAdapter extends BaseQuickAdapter<RankBean,BaseViewHolder>{
    public RankAdapter(@LayoutRes int layoutResId, @Nullable List<RankBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RankBean item) {
        helper.setText(R.id.tv_rank,item.getRank());
        helper.addOnClickListener(R.id.tv_rank);
        if(item.isChecked()){
            helper.setBackgroundColor(R.id.rl_rank,mContext.getResources().getColor(R
                    .color.gray_level_bg));
            helper.setTextColor(R.id.tv_rank,mContext.getResources().getColor(R.color.orange));
        }else {
            helper.setBackgroundColor(R.id.rl_rank,mContext.getResources().getColor(R
                    .color.white));
            helper.setTextColor(R.id.tv_rank,mContext.getResources().getColor(R.color.gray_6));
        }
    }
}
