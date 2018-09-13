package com.risenb.studyknowledge.beans.college;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/10/23.
 */

public class CollegeVipBean extends NetBaseBean {
    public static class DataBean {
        private List<CollegeGradeListBean> collegeGradeList;

        public List<CollegeGradeListBean> getCollegeGradeList() {
            return collegeGradeList;
        }

        public void setCollegeGradeList(List<CollegeGradeListBean> collegeGradeList) {
            this.collegeGradeList = collegeGradeList;
        }

        public static class CollegeGradeListBean {
            /**
             * collegeDelyn : 0
             * collegeGradeId : 20
             * collegeGradeImg : http://bjzx2016.oss-cn-shanghai.aliyuncs.com/admin/upload/image/af7abc28-3695-4df6-8110-d72ea3753ac8.jpg
             * collegeGradeMprice : 0
             * collegeGradeName : VIP0
             * collegeGradeYprice : 0
             * collegeLimitStu : 500
             * collegeLimitTopic : 5
             * collegeLiveNum : 0
             * collegeLivePostYn : 0
             * updatetime : 1484873494000
             * updator : 1
             */

            private int collegeDelyn;
            private int collegeGradeId;
            private String collegeGradeImg;
            private int collegeGradeMprice;
            private String collegeGradeName;
            private int collegeGradeYprice;
            private int collegeLimitStu;
            private int collegeLimitTopic;
            private int collegeLiveNum;
            private int collegeLivePostYn;
            private long updatetime;
            private int updator;

            public int getCollegeDelyn() {
                return collegeDelyn;
            }

            public void setCollegeDelyn(int collegeDelyn) {
                this.collegeDelyn = collegeDelyn;
            }

            public int getCollegeGradeId() {
                return collegeGradeId;
            }

            public void setCollegeGradeId(int collegeGradeId) {
                this.collegeGradeId = collegeGradeId;
            }

            public String getCollegeGradeImg() {
                return collegeGradeImg;
            }

            public void setCollegeGradeImg(String collegeGradeImg) {
                this.collegeGradeImg = collegeGradeImg;
            }

            public int getCollegeGradeMprice() {
                return collegeGradeMprice;
            }

            public void setCollegeGradeMprice(int collegeGradeMprice) {
                this.collegeGradeMprice = collegeGradeMprice;
            }

            public String getCollegeGradeName() {
                return collegeGradeName;
            }

            public void setCollegeGradeName(String collegeGradeName) {
                this.collegeGradeName = collegeGradeName;
            }

            public int getCollegeGradeYprice() {
                return collegeGradeYprice;
            }

            public void setCollegeGradeYprice(int collegeGradeYprice) {
                this.collegeGradeYprice = collegeGradeYprice;
            }

            public int getCollegeLimitStu() {
                return collegeLimitStu;
            }

            public void setCollegeLimitStu(int collegeLimitStu) {
                this.collegeLimitStu = collegeLimitStu;
            }

            public int getCollegeLimitTopic() {
                return collegeLimitTopic;
            }

            public void setCollegeLimitTopic(int collegeLimitTopic) {
                this.collegeLimitTopic = collegeLimitTopic;
            }

            public int getCollegeLiveNum() {
                return collegeLiveNum;
            }

            public void setCollegeLiveNum(int collegeLiveNum) {
                this.collegeLiveNum = collegeLiveNum;
            }

            public int getCollegeLivePostYn() {
                return collegeLivePostYn;
            }

            public void setCollegeLivePostYn(int collegeLivePostYn) {
                this.collegeLivePostYn = collegeLivePostYn;
            }

            public long getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(long updatetime) {
                this.updatetime = updatetime;
            }

            public int getUpdator() {
                return updator;
            }

            public void setUpdator(int updator) {
                this.updator = updator;
            }
        }
    }

//    private String vip;
//
//    public String getVip() {
//        return vip;
//    }
//
//    public void setVip(String vip) {
//        this.vip = vip;
//    }


}
