package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yjyvi on 2017/12/11.
 */

public class IncomeGroupBean extends NetBaseBean {

    /**
     * totalPage : 1
     * totalRecord : 10
     * isUpdated : false
     * timestamp : 1494482889251
     * collegeAccountList : [{"userName":"用户名","sumCost":"收益","createDate":"时间"}]
     */

    private int totalPage;
    private int totalRecord;
    private boolean isUpdated;
    private String timestamp;
    private List<CollegeAccountListBean> collegeAccountList;

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

    public List<CollegeAccountListBean> getCollegeAccountList() {
        return collegeAccountList;
    }

    public void setCollegeAccountList(List<CollegeAccountListBean> collegeAccountList) {
        this.collegeAccountList = collegeAccountList;
    }

    public static class CollegeAccountListBean {
        /**
         * userName : 用户名
         * sumCost : 收益
         * createDate : 时间
         */

        private String userName;
        private String sumCost;
        private String createDate;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSumCost() {
            return sumCost;
        }

        public void setSumCost(String sumCost) {
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
