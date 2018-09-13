package com.risenb.studyknowledge.ui.info;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;

/**
 * Created by yy on 2017/11/7.
 * 修改绑定邮箱
 */
@ContentView(R.layout.ui_modify_mailbox)
public class ModifyMailboxUI extends BaseUI {
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
        Intent starter = new Intent(context, ModifyMailboxUI.class);
        context.startActivity(starter);
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
    @OnClick({R.id.tv_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_next://下一步
                ModifyNewMailboxUI.start(this);
                break;
            default:
                break;

        }
    }
}
