package com.risenb.studyknowledge.ui.info;

import android.content.Context;
import android.content.Intent;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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
 * 编辑用户名
 */
@ContentView(R.layout.ui_edit_username)
public class EditUsernameUI extends BaseUI implements NotifyUserInfoP.ModifyUserInfoListener {
    @ViewInject(R.id.et_username)
    EditText et_username;
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
        setTitle(getResources().getString(R.string.edit_username));//编辑用户名

        //用户名内容超出10字提示
        et_username.setFilters(new InputFilter[]{new MaxTextLengthFilter(10, getResources().getString
                (R.string.et_length_username))});
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, EditUsernameUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void prepareData() {
        mNotifyUserInfoP = new NotifyUserInfoP(this, getActivity());
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save://保存
                String userName = et_username.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    ToastUtils.showToast("请输入用户名");
                    return;
                }
                mNotifyUserInfoP.modifyInfo(userName, "", "", "", "");
                break;
            default:
                break;

        }
    }


    /**
     * 修改用户名成功的回调
     */
    @Override
    public void modifySuccess(UserBean result) {
        EventBus.getDefault().post(new UserEvent().setEventType(UserEvent.UPDATE_USERNAME).setData(result.getUserName()));
        finish();
    }

    @Subscribe
    public void updateUserInfo(UserEvent userEvent) {

    }
}
