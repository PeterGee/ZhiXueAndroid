package com.risenb.studyknowledge.ui.personal;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.personal.SignInManagerBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;
import com.risenb.studyknowledge.utils.PhoneUtils;

import java.util.List;

import okhttp3.Call;


/**
 * 签到管理
 */

public class SignInManagerP extends PresenterBase {

    private SignInManagerFace signInManagerFace;
    public SignInManagerP(SignInManagerFace face, FragmentActivity activity) {
        this.signInManagerFace = face;
        setActivity(activity);
    }

    /**
     * 签到管理
     * @param c               登录标识
     * @param collegeId      学院ID
     * @param searchMonth    查询年月份 格式xx年xx月
     */
    public void getSignList(String c, String collegeId,String searchMonth) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().getSignin(c,collegeId,searchMonth, new
                DataCallback<SignInManagerBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Utils.getUtils().dismissDialog();
                makeText(activity.getResources().getString(R.string.network_error));
            }

            @Override
            public void onSuccess(SignInManagerBean.DataBean result, int i) {
                Utils.getUtils().dismissDialog();
                signInManagerFace.getSignInSuccess(result.getSearchMonth(),result.getTotalNum());
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                Utils.getUtils().dismissDialog();
                makeText(errorMsg);
            }
        });

    }
    public interface SignInManagerFace {
        //获取签到管理成功的回调
        void getSignInSuccess(String month,List<SignInManagerBean.DataBean.TotalNumBean> result);
    }
}
