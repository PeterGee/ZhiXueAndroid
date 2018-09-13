package com.risenb.studyknowledge.ui.login;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;
import com.risenb.studyknowledge.utils.PhoneUtils;

import okhttp3.Call;

/**
 * 找回密码
 */

public class ForgetPasswordP extends PresenterBase {

    private ForgetPasswordFace forgetPasswordFace;

    public ForgetPasswordP(ForgetPasswordFace face, FragmentActivity activity) {
        this.forgetPasswordFace = face;
        setActivity(activity);
    }

    /**
     * 提交找回密码
     */
    public void getForgetPassword() {
        if (TextUtils.isEmpty(forgetPasswordFace.getTelephone())) {
            makeText(getActivity().getResources().getString(R.string.login_phone));
            return;
        }
        if (!PhoneUtils.isMobileNO(forgetPasswordFace.getTelephone())) {
            makeText(getActivity().getResources().getString(R.string.login_right_phone));
            return;
        }
        if (TextUtils.isEmpty(forgetPasswordFace.getCode())) {
            makeText(getActivity().getResources().getString(R.string.login_right_code));
            return;
        }
        if (TextUtils.isEmpty(forgetPasswordFace.getPwd())) {
            makeText(getActivity().getResources().getString(R.string.login_pwd));
            return;
        }
        if (forgetPasswordFace.getPwd().length() < 6) {
            makeText(getActivity().getResources().getString(R.string.login_long_pwd));
            return;
        }
        if (!PhoneUtils.isPwd(forgetPasswordFace.getPwd())) {
            makeText(getActivity().getResources().getString(R.string.login_pwd_letter));
            return;
        }
        if (TextUtils.isEmpty(forgetPasswordFace.getConfirmPwd())) {
            makeText(getActivity().getResources().getString(R.string.login_confirm_pwd));
            return;
        }
        if(!TextUtils.equals(forgetPasswordFace.getPwd(),forgetPasswordFace.getConfirmPwd())){
            makeText(getActivity().getResources().getString(R.string.login_confirm_error));
            return;
        }

        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().forgetPwd(forgetPasswordFace.getTelephone(), forgetPasswordFace.getCode(), forgetPasswordFace.getPwd(),
                new DataCallback<NetBaseBean>() {

            @Override
            public void onError(Call call, Exception e, int i) {
                Utils.getUtils().dismissDialog();
                makeText(activity.getResources().getString(R.string.network_error));

            }

            @Override
            public void onSuccess(NetBaseBean result, int i) {
                Utils.getUtils().dismissDialog();
                forgetPasswordFace.setForgetPassword();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                Utils.getUtils().dismissDialog();
                makeText(errorMsg);

            }
        });
    }

    public interface ForgetPasswordFace{
        String getTelephone();

        String getCode();

        String getPwd();

        String getConfirmPwd();

        void setForgetPassword();

    }
}
