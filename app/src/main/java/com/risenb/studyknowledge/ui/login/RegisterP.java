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
 * Created by zhuzh on 2017/4/18.
 */

public class RegisterP extends PresenterBase {

    private RegistFace registFace;
    public RegisterP(RegistFace face, FragmentActivity activity) {
        this.registFace = face;
        setActivity(activity);
    }
    /**
     * 注册的网络请求
     */
    public void register() {
        if (TextUtils.isEmpty(registFace.getTelephone())) {
            makeText(getActivity().getResources().getString(R.string.login_phone));
            return;
        }
        if (!PhoneUtils.isMobileNO(registFace.getTelephone())) {
            makeText(getActivity().getResources().getString(R.string.login_right_phone));
            return;
        }
        if (TextUtils.isEmpty(registFace.getCode())) {
            makeText(getActivity().getResources().getString(R.string.login_right_code));
            return;
        }
        if (TextUtils.isEmpty(registFace.getPwd())) {
            makeText(getActivity().getResources().getString(R.string.login_pwd));
            return;
        }
        if (registFace.getPwd().length() < 6) {
            makeText(getActivity().getResources().getString(R.string.login_long_pwd));
            return;
        }
        if (!PhoneUtils.isPwd(registFace.getPwd())) {
            makeText(getActivity().getResources().getString(R.string.login_pwd_letter));
            return;
        }
        if (TextUtils.isEmpty(registFace.getConfirmPwd())) {
            makeText(getActivity().getResources().getString(R.string.login_confirm_pwd));
            return;
        }
        if(!TextUtils.equals(registFace.getPwd(),registFace.getConfirmPwd())){
            makeText(getActivity().getResources().getString(R.string.login_confirm_error));
            return;
        }

        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().register(registFace.getTelephone(), registFace.getPwd(), registFace.getCode(),
                new DataCallback<NetBaseBean>() {

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));

                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        registFace.setRegister();
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }

    public interface RegistFace {
        String getTelephone();
        String getCode();
        String getPwd();
        String getConfirmPwd();
        void setRegister();
    }
}
