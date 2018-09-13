package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.MedalBean;
import com.risenb.studyknowledge.beans.institution.MedalInfoBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 *勋章列表
 */

public class MedalManagerP extends PresenterBase {
    private MedalListFace medalListFace;
    public MedalManagerP(MedalListFace face, FragmentActivity activity) {
        this.medalListFace = face;
        setActivity(activity);
    }

    public void getMedalList(String c, String collegeId,String page, String limit) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().getMedalList(c,collegeId,page,limit, new
                DataCallback<MedalBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Utils.getUtils().dismissDialog();
                makeText(activity.getResources().getString(R.string.network_error));
            }

            @Override
            public void onSuccess(MedalBean.DataBean result, int i) {
                Utils.getUtils().dismissDialog();
                List<MedalInfoBean> medalTypeList = result.getMedalTypeList();
                medalListFace.medalListSuccess(medalTypeList);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                Utils.getUtils().dismissDialog();
                makeText(errorMsg);
            }
        });

    }
    public interface MedalListFace {
        void medalListSuccess(List<MedalInfoBean> medalTypeList);
    }
}
