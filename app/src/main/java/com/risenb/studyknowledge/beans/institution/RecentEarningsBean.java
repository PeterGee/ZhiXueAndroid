package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

/**
 * Created by yjyvi on 2017/12/11.
 */

public class RecentEarningsBean extends NetBaseBean {

    /**
     * account : {"sumTopic":"0.00","sumPost":"0.00","sumScalGive":0.75,"sumAcc":"0.00","sumGive":5,"sumCost":5,"collegeIncomes":0.75,"sumGift":1,"collegeBalance":0.25}
     */

    private AccountBean account;

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public static class AccountBean {
        /**
         * sumTopic : 0.00
         * sumPost : 0.00
         * sumScalGive : 0.75
         * sumAcc : 0.00
         * sumGive : 5
         * sumCost : 5
         * collegeIncomes : 0.75
         * sumGift : 1
         * collegeBalance : 0.25
         */

        private String sumTopic;
        private String sumPost;
        private String  SumYouChangGive;
        private double sumScalGive;
        private String sumAcc;
        private int sumGive;
        private int sumCost;
        private double collegeIncomes;
        private int sumGift;
        private double collegeBalance;

        public String getSumYouChangGive() {
            return SumYouChangGive;
        }

        public void setSumYouChangGive(String sumYouChangGive) {
            SumYouChangGive = sumYouChangGive;
        }

        public String getSumTopic() {
            return sumTopic;
        }

        public void setSumTopic(String sumTopic) {
            this.sumTopic = sumTopic;
        }

        public String getSumPost() {
            return sumPost;
        }

        public void setSumPost(String sumPost) {
            this.sumPost = sumPost;
        }

        public double getSumScalGive() {
            return sumScalGive;
        }

        public void setSumScalGive(double sumScalGive) {
            this.sumScalGive = sumScalGive;
        }

        public String getSumAcc() {
            return sumAcc;
        }

        public void setSumAcc(String sumAcc) {
            this.sumAcc = sumAcc;
        }

        public int getSumGive() {
            return sumGive;
        }

        public void setSumGive(int sumGive) {
            this.sumGive = sumGive;
        }

        public int getSumCost() {
            return sumCost;
        }

        public void setSumCost(int sumCost) {
            this.sumCost = sumCost;
        }

        public double getCollegeIncomes() {
            return collegeIncomes;
        }

        public void setCollegeIncomes(double collegeIncomes) {
            this.collegeIncomes = collegeIncomes;
        }

        public int getSumGift() {
            return sumGift;
        }

        public void setSumGift(int sumGift) {
            this.sumGift = sumGift;
        }

        public double getCollegeBalance() {
            return collegeBalance;
        }

        public void setCollegeBalance(double collegeBalance) {
            this.collegeBalance = collegeBalance;
        }
    }
}
