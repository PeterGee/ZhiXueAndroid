package com.risenb.studyknowledge.ui.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.login.UserInfoBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.TabUI;
import com.risenb.studyknowledge.utils.SPUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.Code;

/**
 * Created by yy on 2017/9/16.
 * 登录页面
 */
@ContentView(R.layout.ui_login)
public class LoginUI extends BaseUI implements LoginP.LoginFace {
    @ViewInject(R.id.iv_get_code)
    ImageView iv_get_code;
    @ViewInject(R.id.et_telphone)
    EditText et_telphone;//用户名
    @ViewInject(R.id.et_pwd)
    EditText et_pwd;//密码
    @ViewInject(R.id.et_code)
    EditText et_code;//验证码

    //产生的验证码
    private String realCode;
    private LoginP mLoginP;
    public static final String LOGIN_FLAG="login_flag";
    public static final String LOGIN_IN="login_in";
    public static final String LOGIN_OUT="login_out";
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
        setTitle(getResources().getString(R.string.main_title));//学院管理系统
        rightVisible(getResources().getString(R.string.register));//注册

        //将验证码用图片的形式显示出来
        iv_get_code.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase().trim();
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, LoginUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        mLoginP = new LoginP(this, getActivity());
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_right,R.id.tv_login,R.id.tv_forget_pwd,R.id.iv_get_code})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_right://注册
                RegisterUI.start(this);
                break;
            case R.id.tv_login://登录
                mLoginP.getLoginbind(realCode);
                break;
            case R.id.tv_forget_pwd://忘记密码
                ForgetPasswordUI.start(this);
                break;
            case R.id.iv_get_code:
                iv_get_code.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            default:
                break;
        }
    }

    /**
     * 用户名
     * @return
     */
    @Override
    public String getLoginPhone() {
        return et_telphone.getText().toString().trim();
    }

    /**
     * 密码
     * @return
     */
    @Override
    public String getLoginPwd() {
        return et_pwd.getText().toString().trim();
    }

    /**
     * 验证码
     * @return
     */
    @Override
    public String getCode() {
        return et_code.getText().toString().toLowerCase().trim();
    }

    /**
     * 登录接口成功后的回调
     * @param result
     */
    @Override
    public void setLogin(UserInfoBean.DataBean result) {
        application.setUserInfoBean(result);
        application.setC(String.valueOf(result.getUser().getUserId()));
        SPUtils.put(this,LOGIN_FLAG,LOGIN_IN);//自动登录标识
        TabUI.start(this);
        finish();
    }
}
