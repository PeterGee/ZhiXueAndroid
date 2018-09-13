package com.risenb.studyknowledge.beans.personal;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/11/22.
 * 会员设置
 */

public class MemberSettingBean extends NetBaseBean {

    public static class DataBean {
        /**
         * attendance : {"medalTypeMig":["http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170703/7821c2ecb99b787e4a0fd3de77963ca8.png","http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170703/2423e79589eeb6f9164c27adb8f97ad9.jpg","http://mpic.tiankong.com/a45/e04/a45e0429e5828421d9d08f780dccd5ac/640.jpg","http://mpic.tiankong.com/51e/23a/51e23a4d16222397e4908d183d83ca86/640.jpg@360h","http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171121/34a392d34dd060566a27187b41ead286.png","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg"],"userImg":"http://wx.qlogo.cn/mmopen/SCug0ESSOHib7VNsNWJYg07LQlZmIfzexmspRzhzXBfBQcia5hqvMk0mVXiavLKNtev5q4YMicjaH6iczDQno4UjmoUYK42AZ5JgS/0","attendTalkTime":"","attendAllowYn":2,"attendType":2,"attendId":2270,"attendCollegeId":45,"updatetime":"2017-11-22 11:04:22","attendGradeImg":"http://1x9x.cn/user//res/img/vip01.png","attendTalkLimit":0,"attendUsername":"李洁老师"}
         */

        private AttendanceBean attendance;

        public AttendanceBean getAttendance() {
            return attendance;
        }

        public void setAttendance(AttendanceBean attendance) {
            this.attendance = attendance;
        }

    }
}
