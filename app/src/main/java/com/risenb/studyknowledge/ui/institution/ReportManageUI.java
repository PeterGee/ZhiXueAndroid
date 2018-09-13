package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.TopicPageAdapter;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.CommonUtils;
import com.risenb.studyknowledge.utils.SPUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.evntBusBean.ReportEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yy on 2017/10/18.
 * 举报管理
 */
@ContentView(R.layout.ui_report_manage)
public class ReportManageUI extends BaseUI implements TabLayout.OnTabSelectedListener, RankFragment.OnRankListener {
    @ViewInject(R.id.ll_bottom_choose)
    LinearLayout ll_bottom_choose;
    @ViewInject(R.id.ll_bottom_delete)
    LinearLayout ll_bottom_delete;
    @ViewInject(R.id.iv_all_checked)
    ImageView iv_all_checked;//全选
    @ViewInject(R.id.tab_report)
    TabLayout tab_report;
    @ViewInject(R.id.vp_report)
    ViewPager vp_report;//举报列表
    @ViewInject(R.id.tv_right)
    TextView tv_right;
    @ViewInject(R.id.iv_right)
    ImageView iv_right;
    private List<Fragment> list_fm;
    private List<String> list_title;
    private PostReportFragment mPostReportFragment;//帖子举报
    private FloorReportFragment mFloorReportFragment;//楼层举报
    private boolean isCheckedAll_post = false;//是否全选
    private boolean isCheckedAll_floor = false;//是否全选
    private boolean isShowRank = false;
    public static String RANK = "rank";
    private RankFragment mRankFragment;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.report_manage));//举报管理
        rightVisible(getResources().getString(R.string.rank), R.mipmap.down_arrow_white);

        init();//初始化tablayout
        //清空首选项
        SPUtils.put(this, RANK, "");//清空排序首选相
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ReportManageUI.class);
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

    private void init() {
        list_fm = new ArrayList<>();
        mPostReportFragment = new PostReportFragment();//帖子举报
        mFloorReportFragment = new FloorReportFragment();//楼层举报
        list_fm.add(mPostReportFragment);
        list_fm.add(mFloorReportFragment);

        //将名称加载tab名字列表
        list_title = new ArrayList<>();
        list_title.add(getResources().getString(R.string.post_report));//帖子举报
        list_title.add(getResources().getString(R.string.floor_report));//楼层举报
        //设置TabLayout的模式
        tab_report.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tab_report.addTab(tab_report.newTab().setText(list_title.get(0)));
        tab_report.addTab(tab_report.newTab().setText(list_title.get(1)));

        TopicPageAdapter topicPageAdapter = new TopicPageAdapter(getSupportFragmentManager(), list_fm, list_title);
        vp_report.setAdapter(topicPageAdapter);
        tab_report.setupWithViewPager(vp_report);
        //设置下划线宽度
        tab_report.post(new Runnable() {
            @Override
            public void run() {
                CommonUtils.reflex(tab_report, 60, 60);
            }
        });
        tab_report.setOnTabSelectedListener(this);
    }

    @OnClick({R.id.tv_delete_multiSelect, R.id.ll_cancel, R.id.tv_delete_all, R.id.ll_all_check, R.id
            .ll_delete, R.id.ll_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_delete_multiSelect://多选删除
                ll_bottom_choose.setVisibility(View.VISIBLE);
                ll_bottom_delete.setVisibility(View.GONE);
                mPostReportFragment.isShowChoose(true);
                mFloorReportFragment.isShowChoose(true);
                isCheckedAll_post = false;
                isCheckedAll_floor = false;
                iv_all_checked.setImageResource(R.mipmap.unchecked_gray_report);
                break;
            case R.id.tv_delete_all://全部删除
                int mPosition = tab_report.getSelectedTabPosition();//当前显示的fm角标
                if (mPosition == 0) {
                    mPostReportFragment.deleteAll();
                } else if (mPosition == 1) {
                    mFloorReportFragment.deleteAll();
                }
                break;
            case R.id.ll_all_check://全选
                int mPosition1 = tab_report.getSelectedTabPosition();//当前显示的fm角标
                if (mPosition1 == 0) {//控制帖子举报
                    isCheckedAll_post = !isCheckedAll_post;
                    mPostReportFragment.allChecked(isCheckedAll_post);
                    if (isCheckedAll_post) {
                        iv_all_checked.setImageResource(R.mipmap.checked_blue_report);
                    } else {
                        iv_all_checked.setImageResource(R.mipmap.unchecked_gray_report);
                    }
                } else if (mPosition1 == 1) {//控制楼层举报
                    isCheckedAll_floor = !isCheckedAll_floor;
                    mFloorReportFragment.allChecked(isCheckedAll_floor);
                    if (isCheckedAll_floor) {
                        iv_all_checked.setImageResource(R.mipmap.checked_blue_report);
                    } else {
                        iv_all_checked.setImageResource(R.mipmap.unchecked_gray_report);
                    }
                }
                break;
            case R.id.ll_delete://删除
                int mPosition2 = tab_report.getSelectedTabPosition();//当前显示的fm角标
                if (mPosition2 == 0) {
                    mPostReportFragment.checkedDelete();
                    isCheckedAll_post = false;
                } else if (mPosition2 == 1) {
                    mFloorReportFragment.checkedDelete();
                    isCheckedAll_floor = false;
                }
                iv_all_checked.setImageResource(R.mipmap.unchecked_gray_report);
                break;
            case R.id.ll_cancel://取消
                ll_bottom_choose.setVisibility(View.GONE);
                ll_bottom_delete.setVisibility(View.VISIBLE);
                mPostReportFragment.isShowChoose(false);
                mFloorReportFragment.isShowChoose(false);
                break;
            case R.id.ll_right://排序
                isShowRank = !isShowRank;
                showFragment(isShowRank);
                break;
            default:
                break;
        }
    }

    /**
     * 是否显示排序fm
     *
     * @param show
     */
    private void showFragment(boolean show) {
        if (mRankFragment == null) {
            mRankFragment = new RankFragment();
            mRankFragment.setOnRankListener(this);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mRankFragment.isAdded()) {
                return;
            }
            transaction.add(R.id.fl_contener, mRankFragment).commit();
            iv_right.setImageResource(R.mipmap.up_arrow_white);
        } else {
            transaction.remove(mRankFragment).commit();
            iv_right.setImageResource(R.mipmap.down_arrow_white);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        if (position == 0) {
            if (isCheckedAll_post) {
                iv_all_checked.setImageResource(R.mipmap.checked_blue_report);
            } else {
                iv_all_checked.setImageResource(R.mipmap.unchecked_gray_report);
            }
        } else {
            if (isCheckedAll_floor) {
                iv_all_checked.setImageResource(R.mipmap.checked_blue_report);
            } else {
                iv_all_checked.setImageResource(R.mipmap.unchecked_gray_report);
            }
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 关闭排序列表弹窗的回调
     *
     * @param view
     */
    @Override
    public void closeRankListener(View view, int rankPosition) {

        String[] ranks = getResources().getStringArray(R.array.rank);
        tv_right.setText(ranks[rankPosition]);
        isShowRank = false;
        showFragment(isShowRank);
        SPUtils.put(this, RANK, ranks[rankPosition]);

        if (rankPosition != 0) {
            EventBus.getDefault().post(new ReportEvent().setEventType(ReportEvent.SORT_REPORT).setData(rankPosition));
        }
    }


    //====EventBust回调事件
    @Subscribe
    public void sortEvent(ReportEvent reportEvent){

    }
}
