package com.risenb.studyknowledge.ui.info;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;

/**
 * Created by yy on 2017/11/7.
 * 密码修改
 */
@ContentView(R.layout.ui_modify_pwd)
public class ModifyPwdUI extends BaseUI implements ModifyPwdP.ModifyPwdListener {

    public ModifyPwdP mModifyPwdP;

    @ViewInject(R.id.et_old_pwd)
    private EditText et_old_pwd;

    @ViewInject(R.id.et_new_pwd)
    private EditText et_new_pwd;

    @ViewInject(R.id.et_new_pwd)
    private EditText et_new_pwd2;

    @ViewInject(R.id.tv_commit)
    private TextView tv_commit;


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
        setTitle(getResources().getString(R.string.pwd_modify));//密码修改

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ModifyPwdUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void prepareData() {
        mModifyPwdP = new ModifyPwdP(this, this);

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @Override
    public void modifySuccess() {
        ToastUtils.showToast("修改成功");
        finish();
    }

    @OnClick(R.id.tv_commit)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                String newPwd = et_new_pwd.getText().toString().trim();
                String newPwd2 = et_new_pwd2.getText().toString().trim();
                String oldPwd = et_old_pwd.getText().toString().trim();

                if (TextUtils.isEmpty(oldPwd)) {
                    ToastUtils.showToast("请输入旧密码");
                    return;
                }
                if (TextUtils.isEmpty(newPwd)) {
                    ToastUtils.showToast("请输入新密码");
                    return;
                }
                if (TextUtils.isEmpty(newPwd2)) {
                    ToastUtils.showToast("请输入确认密码");
                    return;
                }

                if (!newPwd.equals(newPwd2)) {
                    ToastUtils.showToast("两次密码不一致，请请输入正确");
                    return;
                }

                mModifyPwdP.setModifyPwdData(oldPwd, newPwd);

                break;
        }
    }
}
