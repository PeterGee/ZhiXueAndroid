package com.risenb.studyknowledge.ui.college;

import android.support.v4.app.FragmentActivity;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.college.CollegeInfoBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by zhuzh on 2017/11/23.
 */

public class CollegeInfoP extends PresenterBase {

    private CollegeInfoFace collegeInfoFace;

    public CollegeInfoP(CollegeInfoFace collegeInfoFace, FragmentActivity activity) {
        this.collegeInfoFace = collegeInfoFace;
        setActivity(activity);
    }

    public void getCollegeInfo(final String c) {
        NetworkUtils.getNetworkUtils().getCollegeInfo(c, new DataCallback<CollegeInfoBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                Utils.getUtils().dismissDialog();

            }

            @Override
            public void onSuccess(CollegeInfoBean.DataBean result, int i) {
                collegeInfoFace.collegeInfoSuccess(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                Utils.getUtils().dismissDialog();

            }
        });
    }


    public void getEditCollegeInfo(String collegeId,
                                   String collegeName, String collegeUser,
                                   String collegeAccBankinfo, String collegeAccBank,
                                   String collegeBackimg, String scale,
                                   String collegeType, String collegePrice,
                                   String collegeDelYn, String collegeInfo){
        NetworkUtils.getNetworkUtils().editCollege(
                application.getC(), collegeId, collegeName, collegeUser,
                collegeAccBankinfo, collegeAccBank, collegeBackimg,
                scale, collegeType, collegePrice,
                collegeDelYn, collegeInfo, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                collegeInfoFace.editCollegeInfoSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                Utils.getUtils().dismissDialog();
            }

            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                Utils.getUtils().dismissDialog();
            }
        });
    }

    public interface CollegeInfoFace{

        void collegeInfoSuccess(CollegeInfoBean.DataBean result);

        void editCollegeInfoSuccess();
    }
}
