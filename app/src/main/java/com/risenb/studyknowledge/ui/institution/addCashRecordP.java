package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/12.
 * 申请提现
 */

public class addCashRecordP extends PresenterBase {

    private AddCashRecordListener mAddCashRecordListener;

    public addCashRecordP(AddCashRecordListener addCashRecordListener, FragmentActivity baseUI) {
        setActivity(baseUI);
        this.mAddCashRecordListener = addCashRecordListener;
    }

    public void setaddCashRecordData(String c, String collegeId, String cashValue) {
        NetworkUtils.getNetworkUtils().addCashRecord(c, collegeId, cashValue, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                if (result != null) {
                    mAddCashRecordListener.addCashRecordData(result);
                }
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                mAddCashRecordListener.addCashRecordField();
            }

            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                mAddCashRecordListener.addCashRecordField();
            }


        });

    }

    public interface AddCashRecordListener {
        void addCashRecordData(NetBaseBean cashMoneyInfoBean);

        void addCashRecordField();
    }
}
