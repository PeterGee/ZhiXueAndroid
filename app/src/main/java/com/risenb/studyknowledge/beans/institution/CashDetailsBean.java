package com.risenb.studyknowledge.beans.institution;

/**
 * Created by yy on 2017/9/29.
 * 提现明细
 */

public class CashDetailsBean {
    /**
     * cashCreationtime : 2017-11-16 15:49
     * cashValue : 0.5
     * updatetime : 2017-11-16 15:49
     * payStatus : 0
     */

    private String cashCreationtime;
    private double cashValue;
    private String updatetime;
    private int payStatus;

    public String getCashCreationtime() {
        return cashCreationtime;
    }

    public void setCashCreationtime(String cashCreationtime) {
        this.cashCreationtime = cashCreationtime;
    }

    public double getCashValue() {
        return cashValue;
    }

    public void setCashValue(double cashValue) {
        this.cashValue = cashValue;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }
}
