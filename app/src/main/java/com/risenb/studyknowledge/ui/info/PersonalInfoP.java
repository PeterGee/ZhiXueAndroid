package com.risenb.studyknowledge.ui.info;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.beans.UserBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/6.
 */

public class PersonalInfoP extends PresenterBase {

    private PersonalInfoListener mPersonalInfoListener;

    public PersonalInfoP(PersonalInfoListener personalInfoListener, FragmentActivity personalInfoUI) {
        this.mPersonalInfoListener = personalInfoListener;
        setActivity(personalInfoUI);
    }

    public void getPersonalInfo() {
        String c;
        Utils.getUtils().showProgressDialog(getActivity(), null);
        if (application != null && TextUtils.isEmpty(application.getC())) {
            c = "1651";
        } else {
            c = application.getC();
        }

        NetworkUtils.getNetworkUtils().getPersonalInfo(c, new DataCallback<UserBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Utils.getUtils().dismissDialog();
            }

            @Override
            public void onSuccess(UserBean result, int i) {
                Utils.getUtils().dismissDialog();
                mPersonalInfoListener.personalInfoData(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                Utils.getUtils().dismissDialog();
                mPersonalInfoListener.personalInfoData(null);
            }
        });
    }

    public interface PersonalInfoListener {
        void personalInfoData(UserBean userBean);
    }
}
