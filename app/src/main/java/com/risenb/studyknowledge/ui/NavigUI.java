package com.risenb.studyknowledge.ui;

import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import com.lidroid.mutils.MUtils;
import com.lidroid.mutils.utils.UIManager;
import com.lidroid.xutils.view.annotation.ContentView;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.login.LoginUI;

/**
 * 导航页
 *
 * @author Administrator
 */
@ContentView(R.layout.ui_navig)
public class NavigUI extends BaseUI {

    @Override
    protected void back() {
        UIManager.getInstance().popActivity(getClass());
    }

    @Override
    protected void onCreate() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
    }

    @Override
    protected void setControlBasis() {
        MUtils.getMUtils().machineInformation();
    }

    @Override
    protected void prepareData() {
        MUtils.getMUtils().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFinishing()) {
                    if (isLogin()) {
                        TabUI.start(NavigUI.this);
                    } else {
//                        Intent intent = new Intent(getActivity(), TabUI.class);
                        Intent intent = new Intent(getActivity(), LoginUI.class);
                        startActivity(intent);
                    }
                    UIManager.getInstance().popActivity(getClass());
                }
            }
        }, 1000);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
}
