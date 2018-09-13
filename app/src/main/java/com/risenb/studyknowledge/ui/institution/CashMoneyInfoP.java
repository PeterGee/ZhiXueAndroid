package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.CashMoneyInfoBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/12.
 * 提现信息
 */

public class CashMoneyInfoP extends PresenterBase {

    private CashMoneyInfoListener mCashMoneyInfoListener;

    public CashMoneyInfoP(CashMoneyInfoListener cashMoneyInfoListener, FragmentActivity baseUI) {
        setActivity(baseUI);
        this.mCashMoneyInfoListener = cashMoneyInfoListener;
    }

    public void setCashMoneyInfoData(String c, String collegeId) {
        NetworkUtils.getNetworkUtils().getCashInfo(c, collegeId, new DataCallback<CashMoneyInfoBean>() {
            @Override
            public void onSuccess(CashMoneyInfoBean result, int i) {
                if (result != null) {
                    mCashMoneyInfoListener.cashMoneyInfoData(result);
                }
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                mCashMoneyInfoListener.cashMoneyInfoField();
            }

            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                mCashMoneyInfoListener.cashMoneyInfoField();
            }


        });

    }

    public interface CashMoneyInfoListener {
        void cashMoneyInfoData(CashMoneyInfoBean cashMoneyInfoBean);
        void cashMoneyInfoField();
    }
}
