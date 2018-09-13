package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/9/30.
 * 意见反馈
 */

public class FeedbackBean extends NetBaseBean{

    public static class DataBean {
        /**
         * totalPage : 1
         * isUpdated : false
         * totalRecord : 2
         * adviceList : [{"adviceContent":"阿大是大非的","adviceId":118,"adviceCreationTime":"2017-06-19 09:10","adviceReadyn":1,"userPhone":"18671562516","userEmail":"183866896@qq.com","userName":"阿帅","adviceType":0},{"adviceContent":"将计就计","adviceId":119,"adviceCreationTime":"2017-06-19 14:27","adviceReadyn":0,"userPhone":"18671562516","userEmail":"183866896@qq.com","userName":"阿帅","adviceType":0}]
         * timestamp : 1511429059975
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<AdviceListBean> adviceList;

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

        public List<AdviceListBean> getAdviceList() {
            return adviceList;
        }

        public void setAdviceList(List<AdviceListBean> adviceList) {
            this.adviceList = adviceList;
        }



    }
}
