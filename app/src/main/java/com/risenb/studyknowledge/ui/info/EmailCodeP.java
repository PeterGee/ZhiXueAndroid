package com.risenb.studyknowledge.ui.info;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/14.
 */

public class EmailCodeP extends PresenterBase {
    private  EmailCodeListener mEmailCodeListener;

    public EmailCodeP(EmailCodeListener emailCodeListener, FragmentActivity fragmentActivity) {
        setActivity(fragmentActivity);
        this.mEmailCodeListener = emailCodeListener;
    }


    public void setEmailCode(String email, String type) {
        NetworkUtils.getNetworkUtils().getEmailCode(email, type, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                mEmailCodeListener.emailCodeSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }

        });
    }

    public interface EmailCodeListener {
        void emailCodeSuccess();
    }

}
