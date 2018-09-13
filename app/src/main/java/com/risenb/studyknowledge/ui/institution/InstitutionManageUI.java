package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;

/**
 * Created by yy on 2017/9/14.
 * 学院管理
 */
@ContentView(R.layout.ui_institution_manage)
public class InstitutionManageUI extends BaseUI {

    @Override
    protected void back() {
        finish();
    }
    @Override
    protected boolean isStatusBar() {
        return true;
    }
    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.institution_manage));

    }
    public static void start(Context context) {
        Intent starter = new Intent(context, InstitutionManageUI.class);
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
    @OnClick({R.id.rl_institution_manage,R.id.rl_open_institution,R.id.rl_member_level_setting,R
            .id.rl_medal_manage,R.id.rl_vip_apply,R.id.rl_report_manage,R.id
            .rl_friendly_business_in,R.id.rl_friendly_business_out,R.id.rl_recent_earnings,R.id
            .rl_cash_details,R.id.rl_announcement_edit,R.id.rl_feedback,R.id.rl_about_platform
    })
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_institution_manage://学院管理
                AddCollegeUI.start(view.getContext());
                break;
            case R.id.rl_open_institution://开通学院
//                AddCollegeUI.start(view.getContext(),true);
                break;
            case R.id.rl_member_level_setting://会员等级设置
                MemberLevelSettingUI.start(this);
                break;
            case R.id.rl_medal_manage://勋章管理
                MedalManagerUI.start(this);
                break;
            case R.id.rl_vip_apply://VIP申请明细
                VIPApplyDetailUI.start(this);
                break;
            case R.id.rl_report_manage://举报管理
                ReportManageUI.start(this);
                break;
            case R.id.rl_friendly_business_in://友商管理(购进)
                BusinessInUI.start(this);
                break;
            case R.id.rl_friendly_business_out://友商管理(售出)
                BusinessOutUI.start(this);
                break;
            case R.id.rl_recent_earnings://近期收益
                RecentEarningsUI.start(this);
                break;
            case R.id.rl_cash_details://提现明细
                CashDetailsUI.start(this);
                break;
            case R.id.rl_announcement_edit://公告编辑
                AnnounceEditUI.start(this);
                break;
            case R.id.rl_feedback://意见反馈
                FeedbackListUI.start(this);
                break;
            case R.id.rl_about_platform://关于平台
                AboutPlatformUI.start(this);
                break;
            default:
                break;

        }
    }
}
