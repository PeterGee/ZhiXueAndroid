package com.risenb.studyknowledge.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;

/**
 * Created by yy on 2017/9/18.
 * 忘记密码
 */
@ContentView(R.layout.ui_forget_pwd)
public class ForgetPasswordUI extends BaseUI implements GetCodeP.GetCodeFace, ForgetPasswordP.ForgetPasswordFace {
    @ViewInject(R.id.et_telphone)
    EditText et_telphone;//手机号
    @ViewInject(R.id.et_code)
    EditText et_code;//验证码
    @ViewInject(R.id.et_pwd)
    EditText et_pwd;//密码
    @ViewInject(R.id.et_confirm_pwd)
    EditText et_confirm_pwd;//确认密码
    @ViewInject(R.id.tv_get_code)
    TextView tv_get_code;//获取验证码
    private GetCodeP mGetCodeP;
    private ForgetPasswordP mForgetPasswordP;

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
        setTitle(getResources().getString(R.string.find_pwd));//找回密码
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, ForgetPasswordUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        mGetCodeP = new GetCodeP(this, getActivity());
        mForgetPasswordP = new ForgetPasswordP(this, getActivity());
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_get_code,R.id.tv_commit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_get_code://获取短信验证码
                mGetCodeP.getCode(getTelephone(),GetCodeP.TYPE_FORGET_PWD);
                break;
            case R.id.tv_commit://提交
                mForgetPasswordP.getForgetPassword();
                break;
            default:
                break;

        }
    }
    /**
     * 获取注册手机号
     * @return
     */
    @Override
    public String getTelephone() {
        return et_telphone.getText().toString().trim();
    }

    /**
     * 获取验证码
     * @return
     */
    @Override
    public String getCode() {
        return et_code.getText().toString().trim();
    }

    /**
     * 获取密码
     * @return
     */
    @Override
    public String getPwd() {
        return et_pwd.getText().toString().trim();
    }

    /**
     * 获取确认密码
     * @return
     */
    @Override
    public String getConfirmPwd() {
        return et_confirm_pwd.getText().toString().trim();
    }

    /**
     * 获取验证码成功后的回调
     */
    @Override
    public void setCode() {
        MyCount time = new MyCount(60000, 1000);
        time.start();
    }

    /**
     * 忘记密码请求成功的回调
     */
    @Override
    public void setForgetPassword() {
        finish();
    }

    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            tv_get_code.setText(getActivity().getResources().getString(R.string.get_code));
            tv_get_code.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_get_code.setText(millisUntilFinished / 1000 + getActivity().getResources().getString(R.string.login_code_later));
            tv_get_code.setClickable(false);
        }
    }
}
