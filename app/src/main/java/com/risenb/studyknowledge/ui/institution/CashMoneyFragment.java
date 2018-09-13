package com.risenb.studyknowledge.ui.institution;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.CashMoneyInfoBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.utils.ToastUtils;

/**
 * Created by yy on 2017/9/28.
 * 提现金额弹窗
 */

public class CashMoneyFragment extends BaseFragment implements CashMoneyInfoP.CashMoneyInfoListener, addCashRecordP.AddCashRecordListener {


    @ViewInject(R.id.tv_save)
    private TextView tv_save;

    @ViewInject(R.id.tv_name)
    private TextView tv_name;

    @ViewInject(R.id.tv_price)
    private TextView tv_price;

    @ViewInject(R.id.et_input_price)
    private EditText et_input_price;

    public CashMoneyInfoP mCashMoneyInfoP;
    public addCashRecordP mAddCashRecordP;


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_cash_money, container, false);
    }

    @Override
    public void setControlBasis() {
        mAddCashRecordP = new addCashRecordP(this, getActivity());
        mCashMoneyInfoP = new CashMoneyInfoP(this, getActivity());
        mCashMoneyInfoP.setCashMoneyInfoData("1651", "45");
    }

    @Override
    public void prepareData() {

    }

    @OnClick({R.id.rl_cash_bg, R.id.tv_back, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_cash_bg://提现金额背景
                mOnCashMoneyListener.closeCashMoneyListener();
                break;
            case R.id.tv_back://返回
                mOnCashMoneyListener.closeCashMoneyListener();
                break;
            case R.id.tv_save:
                String cashMoney = et_input_price.getText().toString().trim();
                if (TextUtils.isEmpty(cashMoney)) {
                    ToastUtils.showToast("请输入提现金额");
                } else if (Integer.parseInt(cashMoney) > 10) {
                    mAddCashRecordP.setaddCashRecordData("1651", "45", cashMoney);
                } else {
                    ToastUtils.showToast("提现金额不得小于十元");
                }
            default:
                break;
        }
    }


    /**
     * 关闭查询时间弹窗的接口回调
     */
    private OnCashMoneyListener mOnCashMoneyListener;

    @Override
    public void cashMoneyInfoData(CashMoneyInfoBean cashMoneyInfoBean) {
        tv_name.setText(cashMoneyInfoBean.getCashInfo().getCollegeAccBankinfo());
        tv_price.setText("￥" + cashMoneyInfoBean.getCashInfo().getCollegeBalance());
    }

    @Override
    public void cashMoneyInfoField() {
        mOnCashMoneyListener.closeCashMoneyListener();
    }

    @Override
    public void addCashRecordData(NetBaseBean cashMoneyInfoBean) {
        mOnCashMoneyListener.closeCashMoneyListener();
    }

    @Override
    public void addCashRecordField() {
        mOnCashMoneyListener.closeCashMoneyListener();
    }

    public interface OnCashMoneyListener {
        void closeCashMoneyListener();
    }

    public void setOnCashMoneyListener(OnCashMoneyListener onCashMoneyListener) {
        mOnCashMoneyListener = onCashMoneyListener;
    }

}
