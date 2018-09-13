package com.risenb.studyknowledge.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.MedalIconAdapter;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;

import java.util.List;

/**
 * Created by yy on 2017/9/21.
 * 会员设置
 */
@ContentView(R.layout.ui_member_setting)
public class MemberSettingUI extends BaseUI implements DecorationFragment.OnDecorationListener, IdentityFragment.OnIdentityListener, NospeakingTimeFragment.OnTimeCheckedListener, MemberSettingP.MemberSettingFace {
    @ViewInject(R.id.iv_is_black)
    ImageView iv_is_black;//是否拉黑
    @ViewInject(R.id.iv_is_nospeaking)
    ImageView iv_is_nospeaking;//是否禁言
    @ViewInject(R.id.tv_nospeaking_time)
    TextView tv_nospeaking_time;//禁言至xx提示
//    @ViewInject(R.id.iv_show_identity)
//    ImageView iv_show_identity;
    @ViewInject(R.id.tv_identity)
    TextView tv_identity;
    @ViewInject(R.id.rv_medal_icon)
    RecyclerView rv_medal_icon;
    @ViewInject(R.id.et_member_name)
    EditText et_member_name;//会员名称
    private boolean isBlack;//是否拉黑
    private boolean isNospeaking;//是否禁言
    private DecorationFragment mDecorationFragment;
    private IdentityFragment mIdentityFragment;
    private MedalIconAdapter mMedalIconAdapter;
    public static final int RESULT_CODE=10;
    public static final String  IDENTITY_TYPE="identity_type";
    private AttendanceBean mMemberInfoBean;
    private String C="1643";
    private int attendType;//会员身份(0：学生、1：管理、2：老师、默认为0)
    private int attendAllowYn;//是否拉黑(是否允许再加入：1：是、2：否)
    private int attendTalkLimit;//是否禁言(0：否、1：是)
    private String attendTalkTime="";//禁言时间
    private String medalTypeIds="";//勋章id
    public static final int BLACK_TRUE=2;//黑名单
    public static final int BLACK_FALSE=1;
    public static final int NOSPEAKING_TRUE=1;//禁言
    public static final int NOSPEAKING_FALSE=0;
    private boolean isNospeakingInit;//会员初始化时的禁言状态
    private NospeakingTimeFragment mTimeFragment;
    private String mTalkTime;
    private MemberSettingP mMemberSettingP;
    private int mAttendId;


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
        setTitle(getResources().getString(R.string.member_setting));//会员设置
        //会员基本信息
        mMemberInfoBean = getIntent().getParcelableExtra(MemberManagerUI.MEMBER_INFO);
        //初始化
        init();

    }

    @Override
    protected void prepareData() {
        mMemberSettingP = new MemberSettingP(this, getActivity());

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    /**
     * 初始化
     */
    public void init(){
        et_member_name.setText(mMemberInfoBean.getAttendUsername());
        et_member_name.requestFocus();
        et_member_name.setSelection(mMemberInfoBean.getAttendUsername().length());//将光标移至文字末尾
        mAttendId = mMemberInfoBean.getAttendId();
        //人员类型(0：学生、1：管理员、2老师
        attendType = mMemberInfoBean.getAttendType();
        String[] identity = getResources().getStringArray(R.array.member_identity);
        if(attendType ==0){
            tv_identity.setText(identity[1]);
        }else if(attendType ==1){
            tv_identity.setText(identity[2]);
        }else if(attendType ==2){
            tv_identity.setText(identity[0]);
        }
        attendAllowYn=mMemberInfoBean.getAttendAllowYn();
       //1不是黑名单 2是黑名单
        if(mMemberInfoBean.getAttendAllowYn()==BLACK_FALSE){
            isBlack=false;
        }else if(mMemberInfoBean.getAttendAllowYn()==BLACK_TRUE){
            isBlack=true;
        }
        showBlackIcon(isBlack);
        //是否禁言(0：否、1：是)
        attendTalkLimit=mMemberInfoBean.getAttendTalkLimit();
        //禁言时间
        mTalkTime = mMemberInfoBean.getAttendTalkTime();
        if(mMemberInfoBean.getAttendTalkLimit()==NOSPEAKING_FALSE){
            isNospeaking=false;
            isNospeakingInit=false;
        }else if(mMemberInfoBean.getAttendTalkLimit()==NOSPEAKING_TRUE){
            isNospeaking=true;
            isNospeakingInit=true;
        }
        showNoSpeakingIcon(isNospeaking);

        mMedalIconAdapter = new MedalIconAdapter(R.layout.medal_icon_item,mMemberInfoBean.getMedalTypeMig());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_medal_icon.setAdapter(mMedalIconAdapter);
        rv_medal_icon.setLayoutManager(linearLayoutManager);


    }
    @OnClick({R.id.iv_is_black,R.id.iv_is_nospeaking,R.id.rl_show_identity,R.id
            .rl_show_decoration,R.id.tv_setting_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_is_black://是否拉黑
                isBlack=!isBlack;
                showBlackIcon(isBlack);
                break;
            case R.id.iv_is_nospeaking://是否禁言
                isNospeaking=!isNospeaking;
                showNoSpeakingIcon(isNospeaking);
                showNospeakingTimeFragment(isNospeaking);
                break;
            case R.id.rl_show_identity://显示会员身份弹窗
                showIdentityFragment(true);
                break;
            case R.id.rl_show_decoration://显示勋章弹窗
                showFragment(true);
                break;
            case R.id.tv_setting_save://保存
                mMemberSettingP.getMemberInfo(C,mAttendId+"",attendType+"",attendAllowYn+"",
                        attendTalkLimit+"",attendTalkTime,medalTypeIds);
                break;
            default:
                break;
        }
    }
    public void showBlackIcon(boolean flag){
        if(flag){
            iv_is_black.setBackgroundResource(R.mipmap.open);
            attendAllowYn=BLACK_TRUE;//黑名单
        }else {
            iv_is_black.setBackgroundResource(R.mipmap.close);
            attendAllowYn=BLACK_FALSE;//1不是黑名单
        }
    }
    public void showNoSpeakingIcon(boolean flag){
        if(flag){
            iv_is_nospeaking.setBackgroundResource(R.mipmap.open);
            attendTalkLimit=NOSPEAKING_TRUE;//1：是禁言
            tv_nospeaking_time.setVisibility(View.VISIBLE);
            attendTalkTime="3";//0分钟
            if(!TextUtils.isEmpty(mTalkTime)){
                tv_nospeaking_time.setText(getResources().getString(R.string.nospeaking_to).concat(mTalkTime));
            }
        }else {
            iv_is_nospeaking.setBackgroundResource(R.mipmap.close);
            attendTalkLimit=NOSPEAKING_FALSE;//否禁言(0：否
            tv_nospeaking_time.setVisibility(View.GONE);
            attendTalkTime="";
        }
    }
    /**
     * 显示身份弹窗
     * @param show
     */
    private void showIdentityFragment(boolean show) {
        if (mIdentityFragment == null) {
            mIdentityFragment = new IdentityFragment();
            mIdentityFragment.setIdentityListener(this);
        }
        mIdentityFragment.setIdentity_type(attendType);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mIdentityFragment.isAdded()){
                return;
            }
            transaction.add(R.id.fl_identity,mIdentityFragment).commit();
        }else {
            transaction.remove(mIdentityFragment).commit();
        }
    }
    /**
     * 显示禁言时间弹窗
     * @param show
     */
    private void showNospeakingTimeFragment(boolean show) {
        if (mTimeFragment == null) {
            mTimeFragment = new NospeakingTimeFragment();
            mTimeFragment.setTimeCheckedListener(this);
        }
        mTimeFragment.setTalkTime(mTalkTime);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mTimeFragment.isAdded()){
                return;
            }
            transaction.add(R.id.fl_identity,mTimeFragment).commit();
        }else {
            transaction.remove(mTimeFragment).commit();
        }
    }
    /**
     * 显示勋章弹窗
     * @param show
     */
    private void showFragment(boolean show) {
        if (mDecorationFragment == null) {
            mDecorationFragment = new DecorationFragment();
            mDecorationFragment.setDecorationListener(this);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mDecorationFragment.isAdded()){
                return;
            }
            transaction.add(R.id.fl_decoration,mDecorationFragment).commit();
        }else {
            transaction.remove(mDecorationFragment).commit();
        }
    }

    /**
     * 关闭勋章列表弹窗的回调
     * @param view
     */
    @Override
    public void closeDecorationListener(View view) {
        showFragment(false);
    }

    /**
     * 选中勋章回调
     * @param view
     */
    @Override
    public void medalIconListener(View view,String ids,List<String> medalImg) {
        this.medalTypeIds=ids;
        mMedalIconAdapter.setNewData(medalImg);
        mMedalIconAdapter.notifyDataSetChanged();
        showFragment(false);
    }

    /**
     * 关闭会员身份弹窗的回调
     * @param view
     */
    @Override
    public void closeIdentityListener(View view) {
        showIdentityFragment(false);
    }

    /**
     * 选中身份回调
     * @param identity
     */
    @Override
    public void checkedIdentityListener(String identity,int type) {
        //iv_show_identity.setVisibility(View.GONE);
        //tv_identity.setVisibility(View.VISIBLE);
        tv_identity.setText(identity);
        this.attendType=type;
        showIdentityFragment(false);
    }

    /**
     * 关闭禁言时间弹窗
     * @param view
     */
    @Override
    public void closeTimeCheckedListener(View view) {
        showNoSpeakingIcon(isNospeakingInit);
        showNospeakingTimeFragment(false);

    }

    /**
     * 选中禁言时间弹窗
     */
    @Override
    public void checkedTimeListener(String position,String tip) {
        this.mTalkTime=tip;
        isNospeaking=true;
        showNoSpeakingIcon(isNospeaking);
        this.attendTalkTime=position;
        showNospeakingTimeFragment(false);
    }

    @Override
    public String getMemberName() {
        return et_member_name.getText().toString().trim();
    }

    /**
     * 保存会员信息成功的回调
     */
    @Override
    public void saveMemberInfoSuccess(AttendanceBean result) {
        Intent mIntent = new Intent();
        mIntent.putExtra(MemberManagerUI.MEMBER_INFO,result);
        setResult(MemberSettingUI.RESULT_CODE,mIntent);
        finish();
    }
}
