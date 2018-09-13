package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yjyvi on 2017/12/12.
 */

public class RewardDivideBean extends NetBaseBean {

    /**
     * GiveScalAccountList : [{"createDate":"时间","giveToName":"讲师","sumScalGive":"礼物分成收益"}]
     * isUpdated : false
     * timestamp : 1494482889251
     * totalPage : 1
     * totalRecord : 10
     */

    private boolean isUpdated;
    private String timestamp;
    private int totalPage;
    private int totalRecord;
    private List<GiveScalAccountListBean> GiveScalAccountList;

    public boolean isIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<GiveScalAccountListBean> getGiveScalAccountList() {
        return GiveScalAccountList;
    }

    public void setGiveScalAccountList(List<GiveScalAccountListBean> GiveScalAccountList) {
        this.GiveScalAccountList = GiveScalAccountList;
    }

    public static class GiveScalAccountListBean {
        /**
         * createDate : 时间
         * giveToName : 讲师
         * sumScalGive : 礼物分成收益
         */

        private String createDate;
        private String giveToName;
        private String sumScalGive;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getGiveToName() {
            return giveToName;
        }

        public void setGiveToName(String giveToName) {
            this.giveToName = giveToName;
        }

        public String getSumScalGive() {
            return sumScalGive;
        }

        public void setSumScalGive(String sumScalGive) {
            this.sumScalGive = sumScalGive;
        }
    }
}
