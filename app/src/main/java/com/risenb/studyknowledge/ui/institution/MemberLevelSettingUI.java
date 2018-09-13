package com.risenb.studyknowledge.ui.institution;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.LevelSettingAdapter;
import com.risenb.studyknowledge.beans.institution.LevelSettingBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.KeyboardUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/10/10.
 * 会员等级设置
 */
@ContentView(R.layout.ui_level_setting)
public class MemberLevelSettingUI extends BaseUI implements MemberLevelSettingP.MemberLevelSettingFace {
    @ViewInject(R.id.rl_level_setting)
    RelativeLayout rl_level_setting;
    @ViewInject(R.id.ll_title)
    LinearLayout ll_title;
    @ViewInject(R.id.rv_level_setting)
    RecyclerView rv_level_setting;
    @ViewInject(R.id.tv_save)
    TextView tv_save;
    private List<LevelSettingBean.DataBean.UserCollegeListBean> mLevelSettingList;
    private LevelSettingAdapter mLevelSettingAdapter;
    private String C="1643";
    private String collegeId="45";
    private MemberLevelSettingP mMemberLevelSettingP;

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
        setTitle(getResources().getString(R.string.member_level_setting));//会员等级设置
        ll_title.setBackgroundResource(R.mipmap.bg_info_title);

        mLevelSettingAdapter = new LevelSettingAdapter(R.layout.level_setting_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_level_setting.setAdapter(mLevelSettingAdapter);
        rv_level_setting.setLayoutManager(linearLayoutManager);
        mLevelSettingAdapter.setEmptyView(R.layout.empty_member_detail_view,(ViewGroup) rv_level_setting.getParent());

    }
    public static void start(Context context) {
        Intent starter = new Intent(context, MemberLevelSettingUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        mMemberLevelSettingP = new MemberLevelSettingP(this, getActivity());
        mMemberLevelSettingP.getLevelSettingList(C,collegeId);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_save,R.id.rl_level_setting,R.id.rv_level_setting})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_save://保存
                tv_save.setFocusable(true);
                tv_save.setFocusableInTouchMode(true);
                tv_save.requestFocus();
                StringBuilder levelId = new StringBuilder();
                StringBuilder levelName = new StringBuilder();
                StringBuilder levelPoint = new StringBuilder();
                for (int i = 0; i < mLevelSettingList.size(); i++) {
                    levelId.append(mLevelSettingList.get(i).getUserCollegegradeId());
                    levelId.append(",");
                    levelName.append(mLevelSettingList.get(i).getUserCollegegradeName());
                    levelName.append(",");
                    levelPoint.append(mLevelSettingList.get(i).getUserCollegegradePoints());
                    levelPoint.append(",");
                }
                String levelIds = levelId.substring(0, levelId.length() - 1);
                String levelNames = levelName.substring(0, levelName.length() - 1);
                String levelPoints = levelPoint.substring(0, levelPoint.length() - 1);
                mMemberLevelSettingP.saveMemberLevelSetting(C,collegeId,levelIds,levelNames,levelPoints);
                break;
            case R.id.rl_level_setting:
                rl_level_setting.setFocusable(true);
                rl_level_setting.setFocusableInTouchMode(true);
                rl_level_setting.requestFocus();
                break;
            default:
                break;
        }
    }

    /**
     * 获取会员等级设置列表成功的回调
     * @param result
     */
    @Override
    public void getLevelSettingListSuccess(List<LevelSettingBean.DataBean.UserCollegeListBean> result) {
        this.mLevelSettingList=result;
        mLevelSettingAdapter.setNewData(mLevelSettingList);
        mLevelSettingAdapter.notifyDataSetChanged();
    }

    /**
     * 会员等级设置保存成功的回调
     */
    @Override
    public void saveLevelSettingSuccess() {
        makeText("保存成功！");
    }
}
