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
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.login.GetCodeP;
import com.risenb.studyknowledge.utils.PhoneUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;

/**
 * Created by yy on 2017/11/7.
 * 修改绑定手机
 */
@ContentView(R.layout.ui_modify_phone)
public class ModifyPhoneUI extends BaseUI implements GetCodeP.GetCodeFace {
    private GetCodeP mGetCodeP;

    @ViewInject(R.id.et_tel)
    private EditText et_tel;

    @ViewInject(R.id.et_code)
    private EditText et_code;

    @ViewInject(R.id.tv_get_code)
    private TextView tv_get_code;
    private NotifyUserInfoP mNotifyUserInfoP;

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
        setTitle(getResources().getString(R.string.modify_phone));//修改绑定手机
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ModifyPhoneUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void prepareData() {
        mGetCodeP = new GetCodeP(this, getActivity());
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({R.id.tv_next, R.id.tv_get_code})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_get_code:
                if (isInput()) {
                    MyCount time = new MyCount(60000, 1000);
                    time.start();
                    mGetCodeP.getCode(et_tel.getText().toString().trim(), GetCodeP.TYPE_NEED);
                }
                break;

            case R.id.tv_next://下一步
                String code = et_code.getText().toString().trim();
                String tel = et_tel.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    ToastUtils.showToast("请输入验证码");
                    return;
                }

                if (isInput()) {
                    mNotifyUserInfoP.modifyInfo("", tel, "", code, "");
                }

                break;
            default:
                break;

        }
    }

    @Override
    public void setCode() {

    }


    /**
     * 输入判断
     *
     * @return
     */
    public boolean isInput() {
        if (TextUtils.isEmpty(et_tel.getText().toString().trim())) {
            makeText(getActivity().getResources().getString(R.string.login_phone));
            return false;
        }
        if (!PhoneUtils.isMobileNO(et_tel.getText().toString().trim())) {
            makeText(getActivity().getResources().getString(R.string.login_right_phone));
            return false;
        }
        return true;
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

}
