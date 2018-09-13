package com.risenb.studyknowledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.mutils.screen.ScreenUtils;
import com.lidroid.xutils.ViewUtils;
import com.risenb.studyknowledge.MyApplication;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.utils.StatusBarUtils;

/**
 * 描述：自定义Fragment
 *
 * @author wangjian
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 视图
     */
    protected View view;
    protected LayoutInflater inflater;
    protected MyApplication application = null;

    /**
     * 描述：创建
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 描述：加载视图
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadViewLayout(inflater, container);
        application = (MyApplication) getActivity().getApplication();
        ViewUtils.inject(this, view);
        if (isStatusBar()){
            int height = StatusBarUtils.getStatusBarHeight(getActivity());
            View view =  findViewById(R.id.view_statusbar);
            ViewGroup.LayoutParams params =view.getLayoutParams();
            params.height = height;
            view.setLayoutParams(params);
        }
        setControlBasis();
        prepareData();
        TextView v_top = (TextView) view.findViewById(R.id.v_top);
        if (v_top != null) {
            ViewGroup.MarginLayoutParams topParams = (ViewGroup.MarginLayoutParams) v_top.getLayoutParams();
            topParams.height = ScreenUtils.getScreenUtils().getStatusHeight(getActivity());
            v_top.setLayoutParams(topParams);
        }
        TextView v_bottom = (TextView) findViewById(R.id.v_bottom);
        if (v_bottom != null) {
            ViewGroup.MarginLayoutParams bottomParams = (ViewGroup.MarginLayoutParams) v_bottom.getLayoutParams();
            bottomParams.height = ScreenUtils.getScreenUtils().getBottomStatusHeight(getActivity());
            v_bottom.setLayoutParams(bottomParams);
        }
        return view;
    }

    protected boolean isStatusBar(){
        return false;
    }

    /**
     * 描述：跳转页面
     *
     * @param intent
     */
    public void startActivity(Intent intent) {
        getActivity().startActivity(intent);
    }

    protected View findViewById(int id) {
        return view.findViewById(id);
    }

    /**
     * 描述：toast提示
     *
     * @param msg
     */
    protected void makeText(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 描述：加载视图
     */
    protected abstract void loadViewLayout(LayoutInflater inflater, ViewGroup container);

    /**
     * 描述：设置控件基础
     */
    public abstract void setControlBasis();

    /**
     * 描述：准备数据
     */
    public abstract void prepareData();


    /**
     * 描述：设置标题
     *
     * @param text 标题
     */
    protected void setTitle(String text) {
        TextView tv_title = (TextView) findViewById(R.id.title);
        if (tv_title != null) {
            tv_title.setText(text);
        }
    }

    /**
     * 描述:隐藏返回按钮
     */
    protected void backGone() {
        RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
        if (back != null) {
            back.setVisibility(View.GONE);
        }
    }

    /**
     * 描述:显示左菜单全部
     */
    protected void leftVisible(String title, int drawable) {
        backGone();
        leftVisible(title);
        leftVisible(drawable);
    }

    /**
     * 描述:显示左菜单文字
     */
    protected void leftVisible(String title) {
        LinearLayout ll_left = (LinearLayout) findViewById(R.id.ll_left);
        if (ll_left != null) {
            ll_left.setVisibility(View.VISIBLE);
        }
        TextView tv_left = (TextView) findViewById(R.id.tv_left);
        if (tv_left != null) {
            tv_left.setVisibility(View.VISIBLE);
            tv_left.setText(title);
        }
    }

    /**
     * 描述:显示左菜单图片
     */
    protected void leftVisible(int drawable) {
        LinearLayout ll_left = (LinearLayout) findViewById(R.id.ll_left);
        if (ll_left != null) {
            ll_left.setVisibility(View.VISIBLE);
        }
        ImageView iv_left = (ImageView) findViewById(R.id.iv_left);
        if (iv_left != null) {
            iv_left.setVisibility(View.VISIBLE);
            iv_left.setImageResource(drawable);
        }
    }

    /**
     * 描述:显示右菜单全部
     */
    protected void rightVisible(String title, int drawable) {
        rightVisible(title);
        rightVisible(drawable);
    }

    /**
     * 描述:显示右菜单文字
     */
    protected void rightVisible(String title) {
        LinearLayout ll_right = (LinearLayout) findViewById(R.id.ll_right);
        if (ll_right != null) {
            ll_right.setVisibility(View.VISIBLE);
        }
        TextView tv_right = (TextView) findViewById(R.id.tv_right);
        if (tv_right != null) {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(title);
        }
    }

    /**
     * 描述:显示右菜单图片
     */
    protected void rightVisible(int drawable) {
        LinearLayout ll_right = (LinearLayout) findViewById(R.id.ll_right);
        if (ll_right != null) {
            ll_right.setVisibility(View.VISIBLE);
        }
        ImageView iv_right = (ImageView) findViewById(R.id.iv_right);
        if (iv_right != null) {
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(drawable);
        }
    }
}
