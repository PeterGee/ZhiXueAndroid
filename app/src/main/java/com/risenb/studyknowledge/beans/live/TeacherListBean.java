package com.risenb.studyknowledge.beans.live;

import java.io.Serializable;

/**
 * Created by zhuzh on 2017/11/27.
 */

public class TeacherListBean implements Serializable {

        /**
         * userImg : http://wx.qlogo.cn/mmopen/ajNVdqHZLLCNt2nmyGqjPwZ9fJnXx5dMBzlLND35t8Ib66PlvpR1xibxkPghas6cpoFt6PBJn3wSvJ4EEpYmXZQ/0
         * teacherId : 82
         * userName : 汪洋大海
         */

        private String userImg;
        private int teacherId;
        private String userName;

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
}
