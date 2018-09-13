package com.risenb.studyknowledge.beans.login;

import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.UserBean;

/**
 * Created by yy on 2017/11/14.
 */

public class UserInfoBean extends NetBaseBean {

    public static class DataBean {
        /**
         * colleges :
         * user : {"updatetime":1510565662000,"updator":"","useBalance":0,"useYn":0,"userCardPw":"","userCollegeId":"","userConcensNum":0,"userCreattime":1510565664000,"userEmail":"","userFansNum":0,"userGiftPoints":0,"userId":1648,"userImg":"","userIncome":0,"userIntro":"","userMyword":"","userName":"13477055271","userOpenid":"","userPassword":"D9F6E636E369552839E7BB8057AEB8DA","userPhone":"13477055271","userPostPoints":0,"userReturnPoints":0,"userRtoken":"","userSex":"","userSigninPoints":0,"userType":1}
         * status : true
         */

        private String colleges;
        private UserBean user;
        private boolean status;
        private String errorMsg;

        public String getColleges() {
            return colleges;
        }

        public void setColleges(String colleges) {
            this.colleges = colleges;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }
}
