package com.risenb.studyknowledge.ui;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.risenb.studyknowledge.MyApplication;
import com.risenb.studyknowledge.R;

/**
 * Presenter 基类
 *
 * @author Administrator
 */
public abstract class PresenterBase{

    private static Toast sToast;
    protected FragmentActivity activity;
    protected MyApplication application;

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
        application = (MyApplication) activity.getApplication();
    }

    protected void makeText(final String content) {

        if (sToast == null) {
            sToast = Toast.makeText(application.getApplicationContext(), content, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(content);

        }
        sToast.show();
    }

    protected String getUrl(int id) {
        return getActivity().getResources().getString(R.string.service_host_address).concat(getActivity().getString(id));
    }
}
