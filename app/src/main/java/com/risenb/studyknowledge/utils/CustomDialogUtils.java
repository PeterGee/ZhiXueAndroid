package com.risenb.studyknowledge.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.views.CustomDialog;

/**
 * Created by zhuzh on 2017/3/29.
 * <p>
 * 通用对话框和PopupWindow
 */

public class CustomDialogUtils {

    private static CustomDialogUtils mCustomDialogUtils;

    public static CustomDialogUtils getInstance() {
        if (mCustomDialogUtils == null) {
            mCustomDialogUtils = new CustomDialogUtils();
        }
        return mCustomDialogUtils;
    }

    private PopupWindow mHeadPopupWindow;

    public void createCustomDialog(Context context, String dialogContent,
                                   final View.OnClickListener positiveCallBack) {
        final CustomDialog dialog = new CustomDialog(context,
                R.style.custom_dialog_style_new,
                R.layout.custom_common_dialog_layout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

        if (!TextUtils.isEmpty(dialogContent)) {
            ((TextView) dialog.findViewById(R.id.tv_dialog_content))
                    .setText(dialogContent);
        }

        dialog.findViewById(R.id.tv_ok).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (positiveCallBack != null) {
                            positiveCallBack.onClick(null);
                        }
                        dialog.dismiss();
                    }
                });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
    }
    public void createDialog(Context context, String dialogContent, final View.OnClickListener callBack1, final View.OnClickListener callBack2) {
        final CustomDialog dialog = new CustomDialog(context,
                R.style.custom_dialog_style_new,
                R.layout.custom_common_dialog_layout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

        if (!TextUtils.isEmpty(dialogContent)) {
            ((TextView) dialog.findViewById(R.id.tv_dialog_content))
                    .setText(dialogContent);
        }

        dialog.findViewById(R.id.tv_ok).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callBack1 != null) {
                            callBack1.onClick(null);
                        }
                        dialog.dismiss();
                    }
                });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callBack2 != null) {
                            callBack2.onClick(null);
                        }
                        dialog.dismiss();
                    }
                });
    }
}
