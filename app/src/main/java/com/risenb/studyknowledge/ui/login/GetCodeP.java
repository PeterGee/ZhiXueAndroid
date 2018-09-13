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

public class GetCodeP extends PresenterBase {

    private GetCodeFace getCodeFace;
    public static final String TYPE_NEED="1";
    public static final String TYPE_UNNEED="0";
    public static final String TYPE_FORGET_PWD="2";
    public GetCodeP(GetCodeFace face, FragmentActivity activity) {
        this.getCodeFace = face;
        setActivity(activity);
    }

    /**
     * 获取验证码
     * type  是否需要验证手机号是否存在注册，0-不需要，1-需要 2-忘记密码
     */
    public void getCode(String telphone,String type) {
        if (TextUtils.isEmpty(telphone)) {
            makeText(getActivity().getResources().getString(R.string.login_phone));
            return;
        }
        if (!PhoneUtils.isMobileNO(telphone)) {
            makeText(getActivity().getResources().getString(R.string.login_right_phone));
            return;
        }
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().getCode(telphone,type, new
                DataCallback<NetBaseBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Utils.getUtils().dismissDialog();
                makeText(activity.getResources().getString(R.string.network_error));
            }

            @Override
            public void onSuccess(NetBaseBean result, int i) {
                Utils.getUtils().dismissDialog();
                getCodeFace.setCode();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                Utils.getUtils().dismissDialog();
                makeText(errorMsg);
            }
        });

    }
    public interface GetCodeFace {
        void setCode();
    }
}
