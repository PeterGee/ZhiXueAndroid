package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.IncomeTypeAdapter;
import com.risenb.studyknowledge.beans.institution.RecentEarningsBean;
import com.risenb.studyknowledge.beans.institution.RecentIncomeBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/25.
 * 近期收益
 */
@ContentView(R.layout.ui_recent_earnings)
public class RecentEarningsUI extends BaseUI implements BaseQuickAdapter.OnItemClickListener, InquireTimeFragment.OnInquireTimeListener, RecentEarningsP.RecentEarningListener {
    @ViewInject(R.id.rv_recent_income)
    RecyclerView rv_recent_income;

    @ViewInject(R.id.ll_title)
    LinearLayout ll_title;


    @ViewInject(R.id.tv_college_prices)
    TextView tv_college_prices;

    @ViewInject(R.id.tv_college_count)
    TextView tv_college_count;

    @ViewInject(R.id.tv_gift_prices)
    TextView tv_gift_prices;

    @ViewInject(R.id.tv_gift_count)
    TextView tv_gift_count;

    private String mCollegeId = "45";


    private List<RecentIncomeBean> list = new ArrayList<>();
    private IncomeTypeAdapter mIncomeTypeAdapter;
    private InquireTimeFragment mInquireTimeFragment;
    private Integer[] incomeIcon = new Integer[]{R.mipmap.income_group_icon, R.mipmap
            .income_topic_icon, R.mipmap.income_post_icon, R.mipmap.income_reward_icon, R.mipmap
            .income_reward_divide_icon, R.mipmap.income_question_icon};
    public RecentEarningsP mReportManageP;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    @Override
    protected boolean isShowViewBg() {
        return true;
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.recent_earnings));//近期收益
        rightVisible(getResources().getString(R.string.inquire));//查询
        ll_title.setBackgroundResource(R.mipmap.bg_title_earning);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv_recent_income.setLayoutManager(gridLayoutManager);

        mReportManageP = new RecentEarningsP(this, this);
        mReportManageP.getRecentEarnings("1156", mCollegeId, "", "");


//        DividerGridItemDecoration itemDecoration = new DividerGridItemDecoration(this, R.drawable.divider_line);
//        rv_recent_income.addItemDecoration(itemDecoration);


    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RecentEarningsUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right://查询
                showFragment(true);
                break;
            default:
                break;
        }
    }

    /**
     * 条目点击事件
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position == 0) {//跳转人群收益
            IncomeGroupUI.start(this);
        } else if (position == 1) {//跳转话题收益
            IncomeTopicUI.start(this);
        } else if (position == 2) {//跳转帖子收益
            IncomePostUI.start(this);
        } else if (position == 3) {//跳转打赏收益
            IncomeRewardUI.start(this);
        } else if (position == 4) {//跳转打赏分成
            IncomeRewardDivideUI.start(this);
        } else if (position == 5) {//跳转提问收益
            IncomePaidQuestionUI.start(this);
        }
    }

    /**
     * 显示查询时间弹窗
     *
     * @param show
     */
    private void showFragment(boolean show) {
        if (mInquireTimeFragment == null) {
            mInquireTimeFragment = new InquireTimeFragment();
            mInquireTimeFragment.setOnInquireTimeListener(this);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mInquireTimeFragment.isAdded()) {
                return;
            }
            transaction.add(R.id.fl_inquire, mInquireTimeFragment).commit();
        } else {
            transaction.remove(mInquireTimeFragment).commit();
        }
    }

    /**
     * 关闭查询时间回调
     */
    @Override
    public void closeInquireTimeListener(String startTime, String endTime) {
        showFragment(false);
        mReportManageP.getRecentEarnings("1156", mCollegeId, startTime, endTime);
    }

    @Override
    public void recentEarningData(RecentEarningsBean recentEarningsBean) {

        tv_college_prices.setText(String.valueOf(recentEarningsBean.getAccount().getCollegeIncomes()));
        tv_college_count.setText(String.valueOf(recentEarningsBean.getAccount().getCollegeBalance()));
        tv_gift_count.setText(String.valueOf(recentEarningsBean.getAccount().getSumGift()));
        tv_gift_prices.setText(String.valueOf(recentEarningsBean.getAccount().getSumCost()));

        String[] incomeType = getResources().getStringArray(R.array.income_type);

        for (int i = 0; i < incomeType.length; i++) {
            RecentIncomeBean bean = new RecentIncomeBean();
            bean.setIncomeType(incomeType[i]);
            bean.setIncomePrice("0");
            bean.setResourceIcon(incomeIcon[i]);
            list.add(bean);
        }

        mIncomeTypeAdapter = new IncomeTypeAdapter(R.layout.income_type_item, list, recentEarningsBean);
        rv_recent_income.setAdapter(mIncomeTypeAdapter);
        mIncomeTypeAdapter.setOnItemClickListener(this);

        //重置时间字符串
        if (mInquireTimeFragment != null) {
            mInquireTimeFragment.initStartEndTime("", "");
        }
    }
}
