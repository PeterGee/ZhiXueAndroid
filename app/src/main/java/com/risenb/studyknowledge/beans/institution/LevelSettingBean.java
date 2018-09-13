package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/10/10.
 * 会员等级设置
 */

public class LevelSettingBean extends NetBaseBean{


    public static class DataBean {
        private List<UserCollegeListBean> userCollegeList;

        public List<UserCollegeListBean> getUserCollegeList() {
            return userCollegeList;
        }

        public void setUserCollegeList(List<UserCollegeListBean> userCollegeList) {
            this.userCollegeList = userCollegeList;
        }

        public static class UserCollegeListBean {
            /**
             * userCollegegradePoints : 0
             * userCollegegradeName : VIP1
             * userCollegegradeId : 170
             */

            private int userCollegegradePoints;
            private String userCollegegradeName;
            private int userCollegegradeId;

            public int getUserCollegegradePoints() {
                return userCollegegradePoints;
            }

            public void setUserCollegegradePoints(int userCollegegradePoints) {
                this.userCollegegradePoints = userCollegegradePoints;
            }

            public String getUserCollegegradeName() {
                return userCollegegradeName;
            }

            public void setUserCollegegradeName(String userCollegegradeName) {
                this.userCollegegradeName = userCollegegradeName;
            }

            public int getUserCollegegradeId() {
                return userCollegegradeId;
            }

            public void setUserCollegegradeId(int userCollegegradeId) {
                this.userCollegegradeId = userCollegegradeId;
            }
        }
    }
}
