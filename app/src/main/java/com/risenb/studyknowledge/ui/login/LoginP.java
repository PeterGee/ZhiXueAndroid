package com.risenb.studyknowledge.ui.login;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.login.UserInfoBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;
import com.risenb.studyknowledge.utils.PhoneUtils;


import okhttp3.Call;

/**
 * Created by zhuzh on 2017/4/18.
 */

public class LoginP extends PresenterBase {
    private LoginFace loginFace;
    public LoginP(LoginFace loginFace, FragmentActivity activity) {
        this.loginFace = loginFace;
        setActivity(activity);
    }

    /**
     * 登陆的网络请求
     */
    public void getLoginbind(String code) {

        if (TextUtils.isEmpty(loginFace.getLoginPhone())) {
            makeText(getActivity().getResources().getString(R.string.login_phone));
            return;
        }
        if (!PhoneUtils.isMobileNO(loginFace.getLoginPhone())) {
            makeText(getActivity().getResources().getString(R.string.login_right_phone));
            return;
        }
        if (TextUtils.isEmpty(loginFace.getLoginPwd())) {
            makeText(getActivity().getResources().getString(R.string.login_pwd));
            return;
        }
        if (loginFace.getLoginPwd().length() < 6) {
            makeText(getActivity().getResources().getString(R.string.login_long_pwd));
            return;
        }
//        if (!PhoneUtils.isPwd(loginFace.getLoginPwd())) {
//            makeText(getActivity().getResources().getString(R.string.login_pwd_letter));
//            return;
//        }
     /*   if(TextUtils.isEmpty(loginFace.getCode())){
            makeText(getActivity().getResources().getString(R.string.login_right_code));
            return;
        }
        if(!TextUtils.equals(code,loginFace.getCode())){
            makeText(getActivity().getResources().getString(R.string.code_error));
            return;
        }*/
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().login(loginFace.getLoginPhone(), loginFace.getLoginPwd(),
                new DataCallback<UserInfoBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Utils.getUtils().dismissDialog();
                makeText(activity.getResources().getString(R.string.network_error));
            }
            @Override
            public void onSuccess(UserInfoBean.DataBean result, int i) {
                Utils.getUtils().dismissDialog();
                loginFace.setLogin(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                Utils.getUtils().dismissDialog();
                makeText(errorMsg);
            }
        });
    }

    /**
     * 第三方登陆
     * 、、、@param unionid
     */
//    public void thirdLogin(String unionid, String uniqueDeviceID) {
//
//        Utils.getUtils().showProgressDialog(getActivity(), null);
//        NetworkUtils.getNetworkUtils().thirdLogin(unionid, uniqueDeviceID, new DataCallback<UserBean.DataBean>() {
//
//            @Override
//            public void onSuccess(UserBean.DataBean result, int i) {
//                Utils.getUtils().dismissDialog();
//                loginFace.setThirdLogin(result);
//            }
//
//            @Override
//            public void onStatusError(String errorCode, String errorMsg) {
//                Utils.getUtils().dismissDialog();
//                makeText(errorMsg);
//            }
//
//            @Override
//            public void onError(Call call, Exception e, int i) {
//                Utils.getUtils().dismissDialog();
//                makeText(activity.getResources().getString(R.string.network_error));
//            }
//
//        });
//
//    }

    public interface LoginFace {
        String getLoginPhone();
        String getLoginPwd();
        String getCode();
        void setLogin( UserInfoBean.DataBean result);

        //void setThirdLogin(UserBean.DataBean result);
    }
}
