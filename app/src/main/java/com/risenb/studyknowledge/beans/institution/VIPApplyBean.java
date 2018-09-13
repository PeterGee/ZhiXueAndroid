package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/9/28.
 * Vip申请明细
 */

public class VIPApplyBean extends NetBaseBean{

    public static class DataBean {
        /**
         * vipDetailList : [{"vipGradeName":"VIP6","createTime":"2017-03-21 18:19:16","gradeType":0,"time":1,"status":1}]
         * totalPage : 0
         * isUpdated : false
         * totalRecord : 0
         * timestamp : 1511577950093
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<VipDetailListBean> vipDetailList;

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

        public List<VipDetailListBean> getVipDetailList() {
            return vipDetailList;
        }

        public void setVipDetailList(List<VipDetailListBean> vipDetailList) {
            this.vipDetailList = vipDetailList;
        }

        public static class VipDetailListBean {
            /**
             * vipGradeName : VIP6
             * createTime : 2017-03-21 18:19:16
             * gradeType : 0
             * time : 1
             * status : 1
             */

            private String vipGradeName;
            private String createTime;
            private int gradeType;
            private int time;
            private int status;

            public String getVipGradeName() {
                return vipGradeName;
            }

            public void setVipGradeName(String vipGradeName) {
                this.vipGradeName = vipGradeName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getGradeType() {
                return gradeType;
            }

            public void setGradeType(int gradeType) {
                this.gradeType = gradeType;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
