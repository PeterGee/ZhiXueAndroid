package com.risenb.studyknowledge.ui;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.mutils.network.PopFail;
import com.lidroid.mutils.screen.ScreenUtils;
import com.lidroid.mutils.utils.CrashHandler;
import com.lidroid.mutils.utils.LoadOver;
import com.lidroid.mutils.utils.Log;
import com.lidroid.mutils.utils.OnLoadOver;
import com.lidroid.mutils.utils.UIManager;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.ViewUtils;
import com.risenb.studyknowledge.MyApplication;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.login.LoginUI;
import com.risenb.studyknowledge.utils.SPUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.permission.EasyPermission;

/**
 * 描述：自定义Activity
 *
 * @author wangjian
 */
public abstract class BaseUI extends FragmentActivity implements OnLoadOver {
    private long exitTime = 0;
    protected MyApplication application;
    private boolean isContentView = false;
    protected boolean isDestroy = true;
    private PopFail popFail;
    public  int READ_WRITE=100;//读写权限
    /**
     * 描述：返回
     */
    protected abstract void back();

    /**
     * 描述：设置控件基础
     */
    protected abstract void setControlBasis();

    /**
     * 描述：准备数据
     */
    protected abstract void prepareData();

    /**
     * 描述：联网
     */
    protected abstract void netWork();

    /**
     * 描述：创建
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrashHandler.getInstance().init(getActivity());
        //沉浸式布局
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        onCreate();
        ViewUtils.inject(this);
        View view =  findViewById(R.id.view_statusbar);
        if(view!=null){
            if (isStatusBar()){
                int height = StatusBarUtils.getStatusBarHeight(this);
                ViewGroup.LayoutParams params =view.getLayoutParams();
                params.height =height ;
                view.setLayoutParams(params);
            }
        }
        if(isShowViewBg()){
            View view_bg =  findViewById(R.id.view_bg);
            view_bg.setVisibility(View.VISIBLE);
        }
        application = (MyApplication) getApplication();
        Log.mem();
        UIManager.getInstance().pushActivity(this);
        new LoadOver(getActivity(), this);
        setControlBasis();
        RelativeLayout rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        if(rl_title!=null){
            int height = StatusBarUtils.getStatusBarHeight(this);
            ViewGroup.LayoutParams params = rl_title.getLayoutParams();
            params.height = Utils.getUtils().getDimen(R.dimen.dm128)-height;
            rl_title.setLayoutParams(params);
        }
        if (rl_title != null) {
            popFail = new PopFail(rl_title, getActivity());
            popFail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    netWork();
                }
            });
        }
        RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
        if (back != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    back();
                }
            });
        }

        TextView v_top = (TextView) findViewById(R.id.v_top);
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

        prepareData();
    }
    public void getPermission(){
        //获取读写权限
        EasyPermission.with(this).addRequestCode(READ_WRITE)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();

    }
    @Override
    protected void onDestroy() {
        if (isDestroy) {
            UIManager.getInstance().popActivity(getClass());
        }
        super.onDestroy();
    }
    protected boolean isStatusBar(){
        return false;
    }
    protected boolean isShowViewBg(){
        return false;
    }

    protected void onCreate() {

    }

    public  boolean isLogin(){
        String loginFlag = SPUtils.getString(BaseUI.this, LoginUI.LOGIN_FLAG);
        if(TextUtils.equals(loginFlag,LoginUI.LOGIN_IN)){
            return  true;
        }
        return false;
    }

    @Override
    public void setContentView(int layoutResID) {
        if (!isContentView) {
            isContentView = true;
            super.setContentView(layoutResID);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected FragmentActivity getActivity() {
        return BaseUI.this;
    }

    /**
     * 联网失败
     */
    protected void showFail() {
        popFail.showAsDropDown();
    }

    /**
     * 描述：退出程序
     */
    protected void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            UIManager.getInstance().popAllActivity();
        }
    }

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
        RelativeLayout rl_left = (RelativeLayout) findViewById(R.id.rl_left);
        if (rl_left != null) {
            rl_left.setVisibility(View.VISIBLE);
            ImageView iv_left = (ImageView) findViewById(R.id.iv_left);
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

    private static Toast sToast;
    protected void makeText(final String content) {

        if (sToast == null) {
            sToast = Toast.makeText(application.getApplicationContext(), content, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(content);

        }
        sToast.show();
    }
}
