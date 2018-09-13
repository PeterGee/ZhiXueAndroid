package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yjyvi on 2017/12/12.
 */

public class PostAccountBean extends NetBaseBean {


    /**
     * isUpdated : false
     * postAccountList : [{"createDate":"时间","postName":"帖子名称","sumCost":"收益","topicName":"话题名称"}]
     * timestamp : 1494482889251
     * totalPage : 1
     * totalRecord : 10
     */

    private boolean isUpdated;
    private String timestamp;
    private int totalPage;
    private int totalRecord;
    private List<PostAccountListBean> postAccountList;

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

    public List<PostAccountListBean> getPostAccountList() {
        return postAccountList;
    }

    public void setPostAccountList(List<PostAccountListBean> postAccountList) {
        this.postAccountList = postAccountList;
    }

    public static class PostAccountListBean {
        /**
         * createDate : 时间
         * postName : 帖子名称
         * sumCost : 收益
         * topicName : 话题名称
         */

        private String createDate;
        private String postName;
        private String sumCost;
        private String topicName;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getSumCost() {
            return sumCost;
        }

        public void setSumCost(String sumCost) {
            this.sumCost = sumCost;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }
    }
}
