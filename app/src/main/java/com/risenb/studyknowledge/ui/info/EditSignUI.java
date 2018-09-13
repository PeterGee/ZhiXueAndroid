package com.risenb.studyknowledge.ui.info;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.UserBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.MaxTextLengthFilter;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;
import com.risenb.studyknowledge.utils.evntBusBean.UserEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by yy on 2017/11/7.
 * 编辑签名
 */
@ContentView(R.layout.ui_edit_sign)
public class EditSignUI extends BaseUI implements NotifyUserInfoP.ModifyUserInfoListener {
    @ViewInject(R.id.et_sign)
    EditText et_sign;
    @ViewInject(R.id.tv_sign_length)
    TextView tv_sign_length;
    private NotifyUserInfoP mEditUsernameP;

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

        EventBus.getDefault().register(this);
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.edit_sign));//编辑签名

        //搜索内容超出20字提示
        et_sign.setFilters(new InputFilter[]{new MaxTextLengthFilter(20,getResources().getString
                (R.string.et_length_sign_tip))});
        //编辑文字改变的监听
        et_sign.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = et_sign.getText().toString();
                tv_sign_length.setText(content.length()+"/20");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, EditSignUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        mEditUsernameP = new NotifyUserInfoP(this, getActivity());
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_save://保存
                String signContent = et_sign.getText().toString().trim();
                if (TextUtils.isEmpty(signContent)) {
                    ToastUtils.showToast("请输入签名内容");
                    return;
                }
                mEditUsernameP.modifyInfo("", "", "", "", signContent);

                break;
            default:
                break;

        }
    }

    @Override
    public void modifySuccess(UserBean result) {
        EventBus.getDefault().post(new UserEvent().setEventType(UserEvent.UPDATE_USER_SIGN).setData(result.getUserIntro()));
        finish();
    }

    @Subscribe
    public void updateUserInfo(UserEvent userEvent) {

    }
}
