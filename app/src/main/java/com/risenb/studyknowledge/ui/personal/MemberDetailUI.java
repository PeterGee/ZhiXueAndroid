package com.risenb.studyknowledge.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.MedalIconAdapter;
import com.risenb.studyknowledge.adapter.personal.TopicPageAdapter;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.CommonUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/9/22.
 * 会员详情
 */
@ContentView(R.layout.ui_member_detail)
public class MemberDetailUI extends BaseUI  {
    @ViewInject(R.id.tab_topic)
    TabLayout tab_topic;
    @ViewInject(R.id.vp_topic)
    ViewPager vp_topic;//话题列表
    @ViewInject(R.id.rv_medal)
    RecyclerView rv_medal;
    @ViewInject(R.id.tv_member_name)
    TextView tv_member_name;
    @ViewInject(R.id.iv_head_pic)
    ImageView iv_head_pic;
    @ViewInject(R.id.iv_member_level)
    ImageView iv_member_level;
    private List<Fragment> list_fm;
    private List<String> list_title;
    private MedalIconAdapter mMedalIconAdapter;
    public static final String ATTEND_ID="attendId";
    private AttendanceBean mMemberInfoBean;
    @Override
    protected void back() {
        Intent mIntent = new Intent();
        mIntent.putExtra(MemberManagerUI.MEMBER_INFO,mMemberInfoBean);
        setResult(MemberSettingUI.RESULT_CODE,mIntent);
        finish();
    }
    @Override
    protected boolean isStatusBar() {
        return true;
    }
    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.member_detail));//会员详情
        rightVisible(R.mipmap.edit);

        //会员基本信息
        mMemberInfoBean = getIntent().getParcelableExtra(MemberManagerUI.MEMBER_INFO);
        //初始化会员基本信息
        init(mMemberInfoBean);
        //初始化大家谈、有偿提问fm
        initFragment();

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

    /**
     * 初始化会员基本信息
     */
    public void init(AttendanceBean mMemberInfoBean){
        //头像
        GlideImageLoader.create(iv_head_pic).loadCircleImage(mMemberInfoBean.getUserImg(),R.mipmap.unify_circle_head);
        //昵称
        tv_member_name.setText(mMemberInfoBean.getAttendUsername());
        //会员等级图片
        GlideImageLoader.create(iv_member_level).loadImage(mMemberInfoBean.getAttendGradeImg(),R.mipmap.unify_image_ing);
        //会员勋章图片（最多展示10张）
        List<String> medalTypeMig = mMemberInfoBean.getMedalTypeMig();

        mMedalIconAdapter = new MedalIconAdapter(R.layout.medal_icon_detail_item, medalTypeMig);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_medal.setAdapter(mMedalIconAdapter);
        rv_medal.setLayoutManager(linearLayoutManager);
    }


    /**
     * 初始化大家谈、有偿提问fm
     */
    public void initFragment(){
        //初始化各fragment
        TalkAboutFragment talkAboutFragment = new TalkAboutFragment();
        PaidQuestionFragment paidQuestionFragment = new PaidQuestionFragment();
        //将fragment装进列表中
        list_fm=new ArrayList<>();
        list_fm.add(talkAboutFragment);
        list_fm.add(paidQuestionFragment);
        //将名称加载tab名字列表
        list_title=new ArrayList<>();
        list_title.add(getResources().getString(R.string.talk_about));
        list_title.add(getResources().getString(R.string.paid_question));
        //设置TabLayout的模式
        tab_topic.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tab_topic.addTab(tab_topic.newTab().setText(list_title.get(0)));
        tab_topic.addTab(tab_topic.newTab().setText(list_title.get(1)));

        TopicPageAdapter topicPageAdapter = new TopicPageAdapter(getSupportFragmentManager(), list_fm, list_title);
        vp_topic.setAdapter(topicPageAdapter);
        tab_topic.setupWithViewPager(vp_topic);
        //设置下划线宽度
        tab_topic.post(new Runnable() {
            @Override
            public void run() {
                CommonUtils.reflex(tab_topic,60,60);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString(ATTEND_ID, mMemberInfoBean.getAttendId()+"");
        talkAboutFragment.setArguments(bundle);
        paidQuestionFragment.setArguments(bundle);
    }
    public static final int REQUEST_CODE=1;
    @OnClick({R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_right://编辑
                Intent starter = new Intent(this,MemberSettingUI.class);
                starter.putExtra(MemberManagerUI.MEMBER_INFO,mMemberInfoBean);
                startActivityForResult(starter,REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==MemberSettingUI.RESULT_CODE&&requestCode==REQUEST_CODE){
            mMemberInfoBean = data.getParcelableExtra(MemberManagerUI.MEMBER_INFO);
            init(mMemberInfoBean);
        }
    }
}
