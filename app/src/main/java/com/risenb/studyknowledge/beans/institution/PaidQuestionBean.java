package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yjyvi on 2017/12/22.
 */

public class PaidQuestionBean extends NetBaseBean {

    /**
     * YouChangAccountList : [{"giveToName":"阿帅","youChangMoney":0.45,"createDate":"2017-12-21 13:25"},{"giveToName":"阿帅","youChangMoney":0.45,"createDate":"2017-12-21 13:25"},{"giveToName":"阿帅","youChangMoney":0.9,"createDate":"2017-12-21 13:26"},{"giveToName":"阿帅","youChangMoney":0.9,"createDate":"2017-12-21 13:26"},{"giveToName":"阿帅","youChangMoney":1.35,"createDate":"2017-12-21 13:28"},{"giveToName":"阿帅","youChangMoney":1.35,"createDate":"2017-12-21 13:28"},{"giveToName":"阿帅","youChangMoney":1.8,"createDate":"2017-12-21 13:31"},{"giveToName":"阿帅","youChangMoney":1.8,"createDate":"2017-12-21 13:31"},{"giveToName":"阿帅","youChangMoney":2.25,"createDate":"2017-12-21 13:32"},{"giveToName":"阿帅","youChangMoney":2.25,"createDate":"2017-12-21 13:32"}]
     * totalPage : 2
     * isUpdated : false
     * totalRecord : 14
     * timestamp : 1513930085538
     */

    private int totalPage;
    private boolean isUpdated;
    private int totalRecord;
    private String timestamp;
    private List<YouChangAccountListBean> YouChangAccountList;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<YouChangAccountListBean> getYouChangAccountList() {
        return YouChangAccountList;
    }

    public void setYouChangAccountList(List<YouChangAccountListBean> YouChangAccountList) {
        this.YouChangAccountList = YouChangAccountList;
    }

    public static class YouChangAccountListBean {
        /**
         * giveToName : 阿帅
         * youChangMoney : 0.45
         * createDate : 2017-12-21 13:25
         */

        private String giveToName;
        private double youChangMoney;
        private String createDate;

        public String getGiveToName() {
            return giveToName;
        }

        public void setGiveToName(String giveToName) {
            this.giveToName = giveToName;
        }

        public double getYouChangMoney() {
            return youChangMoney;
        }

        public void setYouChangMoney(double youChangMoney) {
            this.youChangMoney = youChangMoney;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
