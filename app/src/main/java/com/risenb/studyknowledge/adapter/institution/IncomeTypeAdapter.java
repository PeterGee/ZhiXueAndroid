package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.RecentEarningsBean;
import com.risenb.studyknowledge.beans.institution.RecentIncomeBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 近期收益类型
 */

public class IncomeTypeAdapter extends BaseQuickAdapter<RecentIncomeBean, BaseViewHolder> {
    private RecentEarningsBean mRecentEarningsBean;

    public IncomeTypeAdapter(@LayoutRes int layoutResId, @Nullable List<RecentIncomeBean> data, RecentEarningsBean recentEarningsBean) {
        super(layoutResId, data);
        this.mRecentEarningsBean = recentEarningsBean;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecentIncomeBean item) {
        helper.setText(R.id.tv_income_type, item.getIncomeType());//收益类型
        String price;
        switch (item.getIncomeType()) {
            case "入群收益":
                price = String.valueOf(mRecentEarningsBean.getAccount().getSumAcc());
                break;
            case "话题收益":
                price = String.valueOf(mRecentEarningsBean.getAccount().getSumTopic());
                break;
            case "帖子收益":
                price = String.valueOf(mRecentEarningsBean.getAccount().getSumPost());
                break;
            case "打赏收益":
                price = String.valueOf(mRecentEarningsBean.getAccount().getSumGive());
                break;
            case "打赏分成":
                price = String.valueOf(mRecentEarningsBean.getAccount().getSumScalGive());
                break;
            case "提问收益":
                price = String.valueOf(mRecentEarningsBean.getAccount().getSumYouChangGive());
                break;
            default:
                price = item.getIncomePrice();
                break;
        }

        helper.setText(R.id.tv_income_price, price);
        helper.setImageResource(R.id.iv_income_icon, item.getResourceIcon());
    }
}
