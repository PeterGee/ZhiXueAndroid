package com.risenb.studyknowledge.beans.college;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/11/23.
 */

public class CollegeInfoBean extends NetBaseBean {

    public static class DataBean {
        /**
         * college : {"collegeAccBank":"1","collegeAccBankinfo":"1","collegeAccPwd":"","collegeBackimg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/93be8da7-b3dc-4131-b480-cc07bae1a77e.jpg","collegeBalance":1,"collegeBanner":"碎片化学习作为一种获取知识交流情感的方式，已经成为人们日常生活中不可缺少的一项重要内容，尤其是在二十一世纪这个知识经济时代，自主学习已是人们不断满足自身需要、充实原有知识结构，获取有价值的信息，并最终取得成功的法宝。","collegeCode":"","collegeCreationTime":1495465694000,"collegeDelYn":0,"collegeEmail":"bjzx2016@qq.com","collegeGrade":7,"collegeGradetime":1542939878000,"collegeId":58,"collegeIncomes":1,"collegeInfo":"碎片化学习作为一种获取知识交流情感的方式，已经成为人们日常生活中不可缺少的一项重要内容，尤其是在二十一世纪这个知识经济时代，自主学习已是人们不断满足自身需要、充实原有知识结构，获取有价值的信息，并最终取得成功的法宝。","collegeLoginPwd":"E10ADC3949BA59ABBE56E057F20F883E","collegeLogo":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/e7f8c650-8f60-4b37-b100-387f8f5e525f.png","collegeName":"要学就学","collegePhone":"18301606300","collegePrice":"","collegeType":1,"collegeUser":"杜勇刚","collegeZxScale":0,"scale":0,"sort":1,"updatetime":"","updator":"","userUrl":"http://1x9x.cn/user/wx/oauth/baseurl.html?redirect=college_58","userUrlImgCode":"http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170713/f4416c23223461385cbbe3f04ff05851.gif"}
         * totalPage : 1
         * isUpdated : false
         * type : 2
         * totalRecord : 3
         * topicList : [{"topicId":284,"creationTime":"2017-05-22 23:55","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/7eea4293-3dbb-462a-8bbe-a5c0e57ad959.jpg","topicUseyn":1,"topicName":"要学就学-问题反馈","topicPayType":1,"topicType":2},{"topicId":304,"creationTime":"2017-07-25 10:42","topicImg":"http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170725/611aba8748e66e1b16b3e7dabba82bbb.jpg","topicUseyn":1,"topicName":"社群经济--你的社群你做主","topicPayType":1,"topicType":1},{"topicId":344,"creationTime":"2017-10-23 11:02","topicImg":"http://1x9x.cn/college/res/img/logo_20161205174614.png","topicUseyn":1,"topicName":"话题1","topicPayType":1,"topicType":4}]
         * timestamp : 1511423223873
         */

        private CollegeBean college;
        private int totalPage;
        private boolean isUpdated;
        private int type;
        private int totalRecord;
        private String timestamp;
        private List<TopicListBean> topicList;

        public CollegeBean getCollege() {
            return college;
        }

        public void setCollege(CollegeBean college) {
            this.college = college;
        }

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public List<TopicListBean> getTopicList() {
            return topicList;
        }

        public void setTopicList(List<TopicListBean> topicList) {
            this.topicList = topicList;
        }



        public static class TopicListBean {
            /**
             * topicId : 284
             * creationTime : 2017-05-22 23:55
             * topicImg : http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/7eea4293-3dbb-462a-8bbe-a5c0e57ad959.jpg
             * topicUseyn : 1
             * topicName : 要学就学-问题反馈
             * topicPayType : 1
             * topicType : 2
             */

            private int topicId;
            private String creationTime;
            private String topicImg;
            private int topicUseyn;
            private String topicName;
            private int topicPayType;
            private int topicType;

            public int getTopicId() {
                return topicId;
            }

            public void setTopicId(int topicId) {
                this.topicId = topicId;
            }

            public String getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(String creationTime) {
                this.creationTime = creationTime;
            }

            public String getTopicImg() {
                return topicImg;
            }

            public void setTopicImg(String topicImg) {
                this.topicImg = topicImg;
            }

            public int getTopicUseyn() {
                return topicUseyn;
            }

            public void setTopicUseyn(int topicUseyn) {
                this.topicUseyn = topicUseyn;
            }

            public String getTopicName() {
                return topicName;
            }

            public void setTopicName(String topicName) {
                this.topicName = topicName;
            }

            public int getTopicPayType() {
                return topicPayType;
            }

            public void setTopicPayType(int topicPayType) {
                this.topicPayType = topicPayType;
            }

            public int getTopicType() {
                return topicType;
            }

            public void setTopicType(int topicType) {
                this.topicType = topicType;
            }
        }
    }
}
