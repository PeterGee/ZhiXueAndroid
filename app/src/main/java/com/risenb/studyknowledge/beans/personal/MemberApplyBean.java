package com.risenb.studyknowledge.beans.personal;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/9/19.
 * 会员申请bean
 */

public class MemberApplyBean extends NetBaseBean{


    public static class DataBean {
        /**
         * applyVipList : [{"medalTypeMig":[],"userImg":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLCNt2nmyGqjPwZ9fJnXx5dMBzlLND35t8Ib66PlvpR1xibxkPghas6cpoFt6PBJn3wSvJ4EEpYmXZQ/0","attendTalkTime":"","attendAllowYn":1,"attendType":2,"attendId":2274,"attendCollegeId":45,"updatetime":"2017-11-22 17:40:12","attendGradeImg":"http://1x9x.cn/user//res/img/vip01.png","attendTalkLimit":0,"attendUsername":"汪洋大海"},{"medalTypeMig":[],"userImg":"http://wx.qlogo.cn/mmopen/qzOlKibbqGuvuWdpkHvy8P5WBicwuqjEhSGPalc3SGrK0HdK54x8FEKNJ75d1uTnrGAQtGOWAdCpqxetrxjUOPY2Rhyo51321ic/0","attendTalkTime":"","attendAllowYn":1,"attendType":0,"attendId":2847,"attendCollegeId":45,"updatetime":"2017-11-22 16:28:43","attendGradeImg":"http://1x9x.cn/user//res/img/vip01.png","attendTalkLimit":0,"attendUsername":"洪盛珍"}]
         * totalPage : 0
         * isUpdated : false
         * totalRecord : 0
         * timestamp : 1511418344683
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<AttendanceBean> applyVipList;

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

        public List<AttendanceBean> getApplyVipList() {
            return applyVipList;
        }

        public void setApplyVipList(List<AttendanceBean> applyVipList) {
            this.applyVipList = applyVipList;
        }

    }
}
