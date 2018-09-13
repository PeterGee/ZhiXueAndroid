package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

/**
 * Created by yjyvi on 2017/12/12.
 */

public class CashMoneyInfoBean extends NetBaseBean {


    /**
     * cashInfo : {"collegeAccBankinfo":"招商银行","collegeBalance":0.25}
     */

    private CashInfoBean cashInfo;

    public CashInfoBean getCashInfo() {
        return cashInfo;
    }

    public void setCashInfo(CashInfoBean cashInfo) {
        this.cashInfo = cashInfo;
    }

    public static class CashInfoBean {
        /**
         * collegeAccBankinfo : 招商银行
         * collegeBalance : 0.25
         */

        private String collegeAccBankinfo;
        private double collegeBalance;

        public String getCollegeAccBankinfo() {
            return collegeAccBankinfo;
        }

        public void setCollegeAccBankinfo(String collegeAccBankinfo) {
            this.collegeAccBankinfo = collegeAccBankinfo;
        }

        public double getCollegeBalance() {
            return collegeBalance;
        }

        public void setCollegeBalance(double collegeBalance) {
            this.collegeBalance = collegeBalance;
        }
    }
}
