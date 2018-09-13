package com.risenb.studyknowledge.beans.live;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/9/28.
 */

public class SelectLecturersBean extends NetBaseBean {
    public static class DataBean {
        /**
         * totalPage : 1
         * teacherList : [{"userImg":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLCNt2nmyGqjPwZ9fJnXx5dMBzlLND35t8Ib66PlvpR1xibxkPghas6cpoFt6PBJn3wSvJ4EEpYmXZQ/0","teacherId":82,"userName":"汪洋大海"},{"userImg":"http://wx.qlogo.cn/mmopen/SCug0ESSOHib7VNsNWJYg07OkR477M7l4sysVgHHWvTOn8kdTOAOdX7QK1OtmLznMHvZa2ywpvN12jUxUsDalqR2Bt2peIMVh/0","teacherId":661,"userName":"闫铮"},{"userImg":"http://wx.qlogo.cn/mmopen/12fJtZooEJ5O36Z9WibV8YqibicSibIf1jGicPkCmYRX3asDGSCWPibOwsOUhPibhfdK85Sfgamy6eVD8ChWv7EEgYZjQ/0","teacherId":702,"userName":"阿帅"}]
         * isUpdated : false
         * totalRecord : 3
         * timestamp : 1511772716557
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<TeacherListBean> teacherList;

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

        public List<TeacherListBean> getTeacherList() {
            return teacherList;
        }

        public void setTeacherList(List<TeacherListBean> teacherList) {
            this.teacherList = teacherList;
        }


    }

//    private String name;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


}
