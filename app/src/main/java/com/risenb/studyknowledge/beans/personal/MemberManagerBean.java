package com.risenb.studyknowledge.beans.personal;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/11/15.
 * C端会员管理
 */

public class MemberManagerBean extends NetBaseBean  {


    public static class DataBean {
        /**
         * userCollege : [{"userCollegegradeName":"VIP1","userCollegegradeId":"170"},{"userCollegegradeName":"VIP2","userCollegegradeId":"171"},{"userCollegegradeName":"VIP3","userCollegegradeId":"172"},{"userCollegegradeName":"VIP4","userCollegegradeId":"173"},{"userCollegegradeName":"VIP5","userCollegegradeId":"174"},{"userCollegegradeName":"VIP6","userCollegegradeId":"175"},{"userCollegegradeName":"VIP7","userCollegegradeId":"176"}]
         * totalPage : 0
         * attendanceList : [{"medalTypeMig":["http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170703/7821c2ecb99b787e4a0fd3de77963ca8.png","http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170703/2423e79589eeb6f9164c27adb8f97ad9.jpg","http://mpic.tiankong.com/a45/e04/a45e0429e5828421d9d08f780dccd5ac/640.jpg","http://mpic.tiankong.com/51e/23a/51e23a4d16222397e4908d183d83ca86/640.jpg@360h","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=350105340,3522980783&fm=27&gp=0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg"],"userImg":"http://wx.qlogo.cn/mmopen/SCug0ESSOHib7VNsNWJYg07LQlZmIfzexmspRzhzXBfBQcia5hqvMk0mVXiavLKNtev5q4YMicjaH6iczDQno4UjmoUYK42AZ5JgS/0","attendAllowYn":1,"attendType":2,"attendId":2270,"attendCollegeId":45,"updatetime":"2017-09-30 11:01:20","attendGradeImg":"http://1x9x.cn/user//res/img/vip01.png","attendTalkLimit":0,"attendUsername":"李洁老师"},{"medalTypeMig":[],"userImg":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLCNt2nmyGqjPwZ9fJnXx5dMBzlLND35t8Ib66PlvpR1xibxkPghas6cpoFt6PBJn3wSvJ4EEpYmXZQ/0","attendAllowYn":1,"attendType":0,"attendId":2274,"attendCollegeId":45,"updatetime":"","attendGradeImg":"http://1x9x.cn/user//res/img/vip01.png","attendTalkLimit":0,"attendUsername":"汪洋大海"}]
         * isUpdated : false
         * totalRecord : 0
         * timestamp : 1510822786254
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<MemberLevelBean> userCollege;
        private List<AttendanceBean> attendanceList;

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

        public List<MemberLevelBean> getUserCollege() {
            return userCollege;
        }

        public void setUserCollege(List<MemberLevelBean> userCollege) {
            this.userCollege = userCollege;
        }

        public List<AttendanceBean> getAttendanceList() {
            return attendanceList;
        }

        public void setAttendanceList(List<AttendanceBean> attendanceList) {
            this.attendanceList = attendanceList;
        }
    }
}
