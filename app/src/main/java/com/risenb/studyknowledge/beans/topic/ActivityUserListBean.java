package com.risenb.studyknowledge.beans.topic;

/**
 * Created by zhuzh on 2017/11/23.
 */

public class ActivityUserListBean {

        /**
         * activityName : 930活动测试
         * activityTime : 2017-09-30 10:54
         * tel : 13111111111
         * userName : 报名人930
         */

        private String activityName;
        private String activityTime;
        private String tel;
        private String userName;

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivityTime() {
            return activityTime;
        }

        public void setActivityTime(String activityTime) {
            this.activityTime = activityTime;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
}
