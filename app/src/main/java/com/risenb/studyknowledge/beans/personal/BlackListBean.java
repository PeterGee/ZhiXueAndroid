package com.risenb.studyknowledge.beans.personal;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/11/22.
 * 黑名单
 */

public class BlackListBean extends NetBaseBean {

    public static class DataBean {
        /**
         * totalPage : 0
         * blackList : [{"medalTypeMig":["http://mpic.tiankong.com/a45/e04/a45e0429e5828421d9d08f780dccd5ac/640.jpg"],"userImg":"http://wx.qlogo.cn/mmopen/SCug0ESSOHib7VNsNWJYg07LQlZmIfzexmspRzhzXBfBQcia5hqvMk0mVXiavLKNtev5q4YMicjaH6iczDQno4UjmoUYK42AZ5JgS/0","attendTalkTime":"","attendAllowYn":2,"attendType":2,"attendId":2270,"attendCollegeId":45,"updatetime":"2017-11-22 15:30:13","attendGradeImg":"http://1x9x.cn/user//res/img/vip01.png","attendTalkLimit":0,"attendUsername":"李洁老师"}]
         * isUpdated : false
         * totalRecord : 0
         * timestamp : 1511342843665
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<AttendanceBean> blackList;

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

        public List<AttendanceBean> getBlackList() {
            return blackList;
        }

        public void setBlackList(List<AttendanceBean> blackList) {
            this.blackList = blackList;
        }


    }
}
