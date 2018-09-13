package com.risenb.studyknowledge.pop;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lidroid.mutils.MUtils;
import com.risenb.studyknowledge.R;

public class PopToast {
    public PopToast(final FragmentActivity activity, final String content) {

        final AlertDialog alertDialog = new AlertDialog.Builder(activity, R.style.popup_window_style).create();
        alertDialog.show();

        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.pop_toast);
        window.setWindowAnimations(R.style.popup_window_style);

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ActionBar.LayoutParams.WRAP_CONTENT;
        params.height = ActionBar.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

        TextView tv_title = (TextView) window.findViewById(R.id.tv_pop_toast);
        tv_title.setText(content);

        MUtils.getMUtils().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog.dismiss();
            }
        }, 2000);
    }
}
