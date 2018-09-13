package com.risenb.studyknowledge.ui.post;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.InputMethodUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.CustomPopWindow;

import java.util.ArrayList;

/**
 * Created by zhuzh on 2017/9/26.
 * <p>
 * 帖子列表
 */
@ContentView(R.layout.posts_list_ui)
public class PostsListUI extends BaseUI {

    @ViewInject(R.id.tab_posts)
    private SlidingTabLayout tab_posts;
    @ViewInject(R.id.vp_content)
    private ViewPager vp_content;
    @ViewInject(R.id.iv_search)
    private ImageView iv_search;
    @ViewInject(R.id.ll_post_search)
    private LinearLayout ll_post_search;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.ll_release)
    private LinearLayout ll_release;
    @ViewInject(R.id.et_search)
    private EditText et_search;

    private CustomPopWindow mCustomPopWindow;
    private int type = 1;
    private String searchContent;
    //    private PostsCourseFragment postsFragment;
    private PostsCourseFragment postsFragment1;
    private PostsCourseFragment postsFragment2;
    private PostsCourseFragment postsFragment3;
    private int postTopicId;


    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context, int postTopicId) {
        Intent starter = new Intent(context, PostsListUI.class);
        starter.putExtra("postTopicId", postTopicId);
        context.startActivity(starter);
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
//        setTitle(getResources().getString(R.string.posts_list));
//        rightVisible(R.mipmap.search);
        tv_title.setText(getResources().getString(R.string.posts_list));

        postTopicId = getIntent().getIntExtra("postTopicId", postTopicId);

        String[] strings = {"课程", "大家谈", "有偿提问"};
        ArrayList<Fragment> fragmentList = new ArrayList<>();

        postsFragment1 = new PostsCourseFragment();
        postsFragment1.setPostType(1);
        postsFragment1.setPostTopicId(String.valueOf(postTopicId));
        fragmentList.add(postsFragment1);

        postsFragment2 = new PostsCourseFragment();
        postsFragment2.setPostType(2);
        postsFragment2.setPostTopicId(String.valueOf(postTopicId));
        fragmentList.add(postsFragment2);

        postsFragment3 = new PostsCourseFragment();
        postsFragment3.setPostType(3);
        postsFragment3.setPostTopicId(String.valueOf(postTopicId));
        fragmentList.add(postsFragment3);


        tab_posts.setViewPager(vp_content, strings, this, fragmentList);

        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                type = position + 1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_content.setOffscreenPageLimit(3);


        //编辑框，软键盘搜索按钮监听
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hideInputMethod(v);//隐藏软键盘
                    searchRecord();
                    return true;
                }
                return false;
            }
        });

    }

    public void searchRecord() {
        searchContent = et_search.getText().toString().trim();
        if (!TextUtils.isEmpty(searchContent)) {
            if (type == 1) {
                postsFragment1.setKey(searchContent);
                postsFragment1.startRequest(1);
            } else if (type == 2) {
                postsFragment2.setKey(searchContent);
                postsFragment2.startRequest(2);
            } else if (type == 3) {
                postsFragment3.setKey(searchContent);
                postsFragment3.startRequest(3);
            }

        } else {
            makeText("请输入搜索内容");
            return;
        }
    }

    @OnClick({R.id.rl_back, R.id.ll_release, R.id.rl_search, R.id.iv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_release:
                showPopWindow(type);
                break;
            case R.id.rl_search:
                iv_search.setVisibility(View.GONE);
                ll_post_search.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_close:
                et_search.setText("");
                iv_search.setVisibility(View.VISIBLE);
                ll_post_search.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void showPopWindow(int type) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menu, null);
        //处理popWindow 显示内容
        handleLogic(contentView, type);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(false) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.7f) // 控制亮度
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e("TAG", "onDismiss");
                    }
                })
                .enableOutsideTouchableDissmiss(true)// 设置点击PopupWindow之外的地方，popWindow不关闭，如果不设置这个属性或者为true，则关闭
//                .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)//显示大小
                .setAnimationStyle(R.style.AnimUp)
                .create();
//                .showAsDropDown(tv_test_three, 0, 20); //控件下方
        mCustomPopWindow.showAsDropDown(ll_release, 0, -(ll_release.getHeight() + mCustomPopWindow.getHeight()) - 30); //控件上方
//                mCustomPopWindow.showAtLocation(ll_manage_fragment, Gravity.BOTTOM, 0, 0); //父布局中间

    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView, final int type) {
        LinearLayout menu1 = (LinearLayout) contentView.findViewById(R.id.menu1);
        LinearLayout menu2 = (LinearLayout) contentView.findViewById(R.id.menu2);
        LinearLayout menu3 = (LinearLayout) contentView.findViewById(R.id.menu3);

        if (type == 3) {
            menu2.setVisibility(View.GONE);
            menu3.setVisibility(View.GONE);
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                switch (v.getId()) {
                    case R.id.menu1:// 帖子
                        ReleasePostUI.start(getActivity(), type, postTopicId);
                        break;
                    case R.id.menu2:// 活动
                        ReleaseActionUI.start(getActivity(), null);
                        break;
                    case R.id.menu3:// 投票
                        ReleaseVoteUI.start(getActivity(), null);
                        break;
                }
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
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
}
