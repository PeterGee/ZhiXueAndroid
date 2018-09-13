package com.risenb.studyknowledge.ui.info;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/13.
 */

public class ModifyPwdP extends PresenterBase {
    private ModifyPwdListener mModifyPwdListener;

    public ModifyPwdP(ModifyPwdListener modifyPwdListener, FragmentActivity fragmentActivity) {
        setActivity(fragmentActivity);
        this.mModifyPwdListener = modifyPwdListener;
    }


    public void setModifyPwdData(String pwd, String newPwd) {
        NetworkUtils.getNetworkUtils().updatePwd(pwd, newPwd, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                mModifyPwdListener.modifySuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
            }

            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
            }


        });
    }

    public interface ModifyPwdListener {
        void modifySuccess();
    }
}
