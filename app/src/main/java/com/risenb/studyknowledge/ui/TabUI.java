package com.risenb.studyknowledge.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.MUtils;
import com.lidroid.mutils.screen.ScreenUtils;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.MyApplication;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.TabAdapter;
import com.risenb.studyknowledge.adapter.college.CollegeNameAdapter;
import com.risenb.studyknowledge.beans.college.CollegeNameBean;
import com.risenb.studyknowledge.beans.college.JoinedCollegeBean;
import com.risenb.studyknowledge.enums.EnumTAB;
import com.risenb.studyknowledge.enums.EnumUtils;
import com.risenb.studyknowledge.ui.college.CollegeMoreP;
import com.risenb.studyknowledge.ui.info.JoinedCollegeUI;
import com.risenb.studyknowledge.ui.info.PersonalInfoUI;
import com.risenb.studyknowledge.ui.institution.InstitutionManageUI;
import com.risenb.studyknowledge.ui.login.LoginUI;
import com.risenb.studyknowledge.utils.MyConfig;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.MyViewPager;
import com.risenb.studyknowledge.views.NotifyRadioButton;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述：导航
 *
 * @author wanjian
 */
@ContentView(R.layout.ui_tab)
public class TabUI extends BaseUI implements OnClickListener, BaseQuickAdapter.OnItemClickListener, CollegeMoreP.CollegeMoreListener {
    private static TabUI tabUI;
    @ViewInject(R.id.view)
    View bottomView;
    @ViewInject(R.id.ll_title)
    LinearLayout ll_title;
    @ViewInject(R.id.mvp_tab)
    private MyViewPager vp_tab;
    @ViewInject(R.id.drawer_layout)
    private static DrawerLayout drawer_layout;
    @ViewInject(R.id.rb_tab_2)
    private static NotifyRadioButton rb_tab_2;

    @ViewInject(R.id.iv_menu_head)
    private ImageView iv_menu_head;

    @ViewInject(R.id.tv_sign)
    private TextView tv_sign;

    @ViewInject(R.id.tv_name)
    private TextView tv_name;

    @ViewInject(R.id.rv_college_name)
    private RecyclerView rv_college_name;

    private CollegeNameAdapter mAdapter;
    private List<CollegeNameBean> listData = new ArrayList<>();
    public CollegeMoreP mCollegeMoreP;

    @Override
    protected void back() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers();
        } else {
            exit();
        }
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    @Override
    protected void setControlBasis() {
        mCollegeMoreP = new CollegeMoreP(this, this);
        StatusBarUtils.transparencyBar(this);
        setTitle(MUtils.getMUtils().getMem("CollegeName"));
        rightVisible(R.mipmap.institution_icon);
        leftVisible(R.mipmap.head_icon);
        backGone();//隐藏返回按钮
        initMenu();
        tabUI = this;
        Drawable drawable;
        int right = Utils.getUtils().getDimen(R.dimen.dm040);
        int bottom = Utils.getUtils().getDimen(R.dimen.dm040);

        EnumTAB[] enumArr = EnumTAB.values();
        for (int i = 0; i < enumArr.length; i++) {
            enumArr[i].setRadioButton((RadioButton) findViewById(enumArr[i].getId()));
            enumArr[i].getRadioButton().setOnClickListener(this);
            enumArr[i].getRadioButton().setText(enumArr[i].getTitle());

            drawable = getResources().getDrawable(enumArr[i].getDrawable());
            drawable.setBounds(0, 0, right, bottom);
            enumArr[i].getRadioButton().setCompoundDrawables(null, drawable, null, null);
        }

        ViewGroup.MarginLayoutParams bottomParams = (ViewGroup.MarginLayoutParams) bottomView
                .getLayoutParams();
        bottomParams.height = ScreenUtils.getScreenUtils().getBottomStatusHeight(getActivity());
        bottomView.setLayoutParams(bottomParams);
        bottomView.setBackgroundColor(getResources().getColor(R.color.black));

        vp_tab.setOffscreenPageLimit(5);


        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setAutoMeasureEnabled(true);
        rv_college_name.setLayoutManager(layout);
    }

    /**
     * 初始化侧滑菜单
     */
    private void initMenu() {
        mCollegeMoreP.setCollegeMore();

        if (MyApplication.getUserInfoBean() != null && MyApplication.getUserInfoBean().getUser() != null) {
            Glide.with(this).load(MyApplication.getUserInfoBean().getUser().getUserImg()).into(iv_menu_head);
            tv_sign.setText(MyApplication.getUserInfoBean().getUser().getUserIntro());
            tv_name.setText(MyApplication.getUserInfoBean().getUser().getUserName());
        } else {
            iv_menu_head.setImageResource(R.mipmap.head_icon);
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, TabUI.class);
        context.startActivity(starter);
    }

    public void closeTitle() {
        ll_title.setVisibility(View.GONE);
    }

    public void openTitle() {
        ll_title.setVisibility(View.VISIBLE);
    }

    @Override
    protected void prepareData() {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        vp_tab.setAdapter(tabAdapter);
        vp_tab.setNoScroll(true);//禁止不能滑动
        vp_tab.setOffscreenPageLimit(5);
        vp_tab.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                EnumTAB[] enumArr = EnumTAB.values();
                for (int i = 0; i < enumArr.length; i++) {
                    enumArr[i].getRadioButton().setChecked(i == position);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        drawer_layout.setScrimColor(Color.TRANSPARENT);
        drawer_layout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                View mContent = drawer_layout.getChildAt(0);
//                View mMenu = drawerView;
//
//                float scale = 1 - slideOffset;
//                float leftScale = 1 - 0.3f * scale;
//                float rightScale = 0.7f + scale * 0.3f;
//                ViewHelper.setScaleX(mMenu, leftScale);
//                ViewHelper.setScaleY(mMenu, leftScale);
//                ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
//                ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * slideOffset*0.9f);
//                ViewHelper.setPivotX(mContent, 0);
//                ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
//                ViewHelper.setScaleX(mContent, rightScale);
//                ViewHelper.setScaleY(mContent, rightScale);

                View content = drawer_layout.getChildAt(0);
                int offset = (int) (drawerView.getWidth() * slideOffset);
                content.setTranslationX(offset);

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    /**
     * 左边菜单开关事件
     */
    public static void openLeftLayout() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers();
        } else {
            drawer_layout.openDrawer(GravityCompat.START);
        }
    }

    @OnClick({R.id.iv_left, R.id.iv_right, R.id.iv_menu_head, R.id.rl_college_joined})
    public void onClick(View view) {
        EnumTAB[] enumArr = EnumTAB.values();
        for (int i = 0; i < enumArr.length; i++) {
            if (enumArr[i].getId() == view.getId()) {
                setCurrentTabByTag(enumArr[i]);
                break;
            }
        }
        for (int i = 1; i < enumArr.length; i++) {
            if (enumArr[i].getId() == view.getId()) {
                openTitle();
                break;
            }
        }
        switch (view.getId()) {
            case R.id.rb_tab_1:
                closeTitle();
                break;
            case R.id.iv_left://个人中心
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawers();
                } else {
                    drawer_layout.openDrawer(GravityCompat.START);
                    initMenu();
                }
                break;
            case R.id.iv_right://学院管理

                InstitutionManageUI.start(this);
                break;
            case R.id.iv_menu_head://跳转个人信息界面
                if (isLogin()) {
                    PersonalInfoUI.start(this);
                } else {
                    LoginUI.start(this);
                }
                break;
            case R.id.rl_college_joined://跳转加入的学院页面
                JoinedCollegeUI.start(this);
                break;
            default:
                break;
        }
    }

    public void setCurrentTabByTag(EnumTAB enumTab) {
        EnumTAB[] enumArr = EnumTAB.values();
        for (int i = 0; i < enumArr.length; i++) {
            enumArr[i].getRadioButton().setChecked(enumArr[i] == enumTab);
        }
        vp_tab.setCurrentItem(EnumUtils.getEnumUtils().getIdx(enumTab), false);
    }

    /**
     * 帖子消息红点
     *
     * @param notify
     */
    public static void setNotify(boolean notify) {
        rb_tab_2.notify(notify);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        JoinedCollegeBean.DataBeanX.DataBean bean = mAdapter.getData().get(position);
        setTitle(bean.getCollegeName());
        MUtils.getMUtils().saveMem("collegeId", String.valueOf(bean.getCollegeId()));
        MUtils.getMUtils().saveMem("CollegeName", bean.getCollegeName());
        drawer_layout.closeDrawers();

        EnumTAB[] enumArr = EnumTAB.values();
        for (int i = 0; i < enumArr.length; i++) {
            enumArr[1].getFragment().setControlBasis();
            enumArr[1].getFragment().prepareData();
        }
    }

    @Override
    public void collegeMoreSuccess(List<JoinedCollegeBean.DataBeanX.DataBean> dataBean) {
        mAdapter = new CollegeNameAdapter(R.layout.joined_college_item, dataBean);
        rv_college_name.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }
}