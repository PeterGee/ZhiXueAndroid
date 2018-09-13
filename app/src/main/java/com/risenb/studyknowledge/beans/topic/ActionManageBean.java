package com.risenb.studyknowledge.beans.topic;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/9/20.
 */

public class ActionManageBean extends NetBaseBean{
    public static class DataBean {
        /**
         * totalPage : 1
         * isUpdated : false
         * activityList : [{"activityId":1799,"postJoinNum":1,"topicId":340,"activityName":"930活动测试","topicName":"109话题添加测试1631","startTime":"2017-08-29 10:50","endTime":"2017-09-30 10:50","postPicture":"图片地址"},{"activityId":1800,"postJoinNum":0,"topicId":340,"activityName":"109活动修改测试","topicName":"109话题添加测试1631","startTime":"2017-09-14 08:06","endTime":"2017-09-14 08:03","postPicture":""}]
         * totalRecord : 2
         * timestamp : 1511400657113
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<ActivityListBean> activityList;

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

        public List<ActivityListBean> getActivityList() {
            return activityList;
        }

        public void setActivityList(List<ActivityListBean> activityList) {
            this.activityList = activityList;
        }


    }

//    private String actionName;
//
//
//
//    public String getActionName() {
//        return actionName;
//    }
//
//    public void setActionName(String actionName) {
//        this.actionName = actionName;
//    }


}
