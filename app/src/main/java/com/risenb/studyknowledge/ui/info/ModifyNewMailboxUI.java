package com.risenb.studyknowledge.ui.info;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.UserBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.PhoneUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;

/**
 * Created by yy on 2017/11/7.
 * 绑定新邮箱
 */
@ContentView(R.layout.ui_modify_new_mailbox)
public class ModifyNewMailboxUI extends BaseUI implements NotifyUserInfoP.ModifyUserInfoListener, EmailCodeP.EmailCodeListener {
    private NotifyUserInfoP mNotifyUserInfoP;

    //验证码
    @ViewInject(R.id.et_email_code)
    private EditText et_email_code;


    //邮件地址
    @ViewInject(R.id.et_email)
    private EditText et_email;

    @ViewInject(R.id.tv_get_code)
    private TextView tv_get_code;
    public EmailCodeP mEmailCodeP;


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
        setTitle(getResources().getString(R.string.modify_mailbox));//修改绑定邮箱
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ModifyNewMailboxUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void prepareData() {
        mNotifyUserInfoP = new NotifyUserInfoP(this,this);
        mEmailCodeP = new EmailCodeP(this,this);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({R.id.tv_confirm_bind, R.id.tv_get_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm_bind://确认绑定

                String emailCode = et_email_code.getText().toString().trim();
                String email = et_email.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    ToastUtils.showToast("请输入邮箱");
                    return;
                }

                if (TextUtils.isEmpty(emailCode)) {
                    ToastUtils.showToast("请输入邮箱验证码");
                    return;
                }

                mNotifyUserInfoP.modifyInfo("", "", email, emailCode, "");

                break;
            case R.id.tv_get_code:
                if (isInput()) {
                    MyCount time = new MyCount(60000, 1000);
                    time.start();
                    mEmailCodeP.setEmailCode(et_email.getText().toString().trim(),"0");
                }
                break;
            default:
                break;

        }
    }

    /**
     * 定时器
     */
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

    /**
     * 输入判断
     * @return
     */
    public boolean isInput() {
        if (TextUtils.isEmpty(et_email.getText().toString().trim())) {
            ToastUtils.showToast("请输入邮箱");
            return false;
        }
        if (!PhoneUtils.isEmail(et_email.getText().toString().trim())) {
            ToastUtils.showToast("请输入正确的邮箱地址");
            return false;
        }
        return true;
    }

    @Override
    public void modifySuccess(UserBean result) {
        ToastUtils.showToast("修改成功");
        finish();
    }

    @Override
    public void emailCodeSuccess() {
        ToastUtils.showToast("获取成功");
    }
}
