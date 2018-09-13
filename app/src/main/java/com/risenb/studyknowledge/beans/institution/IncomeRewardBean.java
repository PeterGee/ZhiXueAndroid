package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yjyvi on 2017/12/11.
 */

public class IncomeRewardBean extends NetBaseBean {


    /**
     * totalPage : 1
     * isUpdated : false
     * giveAccountList : [{"giveToName":"李洁老师","sumCost":5,"createDate":"2017-08-22 00:00"}]
     * totalRecord : 1
     * timestamp : 1512985805045
     */

    private int totalPage;
    private boolean isUpdated;
    private int totalRecord;
    private String timestamp;
    private List<GiveAccountListBean> giveAccountList;

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

    public List<GiveAccountListBean> getGiveAccountList() {
        return giveAccountList;
    }

    public void setGiveAccountList(List<GiveAccountListBean> giveAccountList) {
        this.giveAccountList = giveAccountList;
    }

    public static class GiveAccountListBean {
        /**
         * giveToName : 李洁老师
         * sumCost : 5
         * createDate : 2017-08-22 00:00
         */

        private String giveToName;
        private int sumCost;
        private String createDate;

        public String getGiveToName() {
            return giveToName;
        }

        public void setGiveToName(String giveToName) {
            this.giveToName = giveToName;
        }

        public int getSumCost() {
            return sumCost;
        }

        public void setSumCost(int sumCost) {
            this.sumCost = sumCost;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
