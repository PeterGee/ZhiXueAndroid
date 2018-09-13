package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;

import com.lidroid.xutils.view.annotation.ContentView;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;

/**
 * Created by yy on 2017/11/7.
 * 关于平台
 */
@ContentView(R.layout.ui_about_platform)
public class AboutPlatformUI extends BaseUI {
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
        setTitle(getResources().getString(R.string.about_platform));//关于平台

    }
    public static void start(Context context) {
        Intent starter = new Intent(context, AboutPlatformUI.class);
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
}
