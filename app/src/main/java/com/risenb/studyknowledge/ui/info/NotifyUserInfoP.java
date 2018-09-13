package com.risenb.studyknowledge.ui.info;

import android.support.v4.app.FragmentActivity;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.UserBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 * 修改用户名
 */

public class NotifyUserInfoP extends PresenterBase {

    private ModifyUserInfoListener modifyUsernameFace;

    public NotifyUserInfoP(ModifyUserInfoListener face, FragmentActivity activity) {
        this.modifyUsernameFace = face;
        setActivity(activity);
    }

    public void modifyInfo(String userName, String mobile, String email, String code, String userIntro) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().modifyUsernameInfo(userName, mobile, email, code, userIntro, new
                DataCallback<UserBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(UserBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        modifyUsernameFace.modifySuccess(result);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }

    public interface ModifyUserInfoListener {
        void modifySuccess(UserBean result);
    }
}
