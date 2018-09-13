package com.risenb.studyknowledge.beans.personal;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 踢出的会员bena
 */

public class KickOutMemberBean extends NetBaseBean{


    public static class DataBean {
        /**
         * totalPage : 0
         * isUpdated : false
         * delynVipList : [{"medalTypeMig":[],"userImg":"http://wx.qlogo.cn/mmopen/SCug0ESSOHib7VNsNWJYg07OkR477M7l4sysVgHHWvTOn8kdTOAOdX7QK1OtmLznMHvZa2ywpvN12jUxUsDalqR2Bt2peIMVh/0","attendTalkTime":"2017-11-21 17:34:16","attendAllowYn":1,"attendType":0,"attendId":2791,"attendCollegeId":45,"updatetime":"2017-11-21 17:24:15","attendGradeImg":"http://1x9x.cn/user//res/img/vip01.png","attendTalkLimit":1,"attendUsername":"闫铮"},{"medalTypeMig":[],"userImg":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM7jOuVlg6DpCKEyzMXmpHVSicKKCCxN3UnryYxEwK1WDnGPlFKkVxRicLJ3ibuDY66ibUGzIGS75KsOibA/0","attendTalkTime":"","attendAllowYn":1,"attendType":2,"attendId":2801,"attendCollegeId":45,"updatetime":"2017-11-21 17:24:33","attendGradeImg":"http://1x9x.cn/user//res/img/vip01.png","attendTalkLimit":0,"attendUsername":"韩雪"}]
         * totalRecord : 0
         * timestamp : 1511337855485
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<AttendanceBean> delynVipList;

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

        public List<AttendanceBean> getDelynVipList() {
            return delynVipList;
        }

        public void setDelynVipList(List<AttendanceBean> delynVipList) {
            this.delynVipList = delynVipList;
        }

    }
}
