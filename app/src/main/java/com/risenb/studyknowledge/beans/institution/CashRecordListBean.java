package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/11/24.
 * 提现列表
 */

public class CashRecordListBean extends NetBaseBean {

    public static class DataBean {
        /**
         * totalPage : 1
         * isUpdated : false
         * totalRecord : 1
         * cashRecordList : [{"cashCreationtime":"2017-11-16 15:49","cashValue":0.5,"updatetime":"2017-11-16 15:49","payStatus":0}]
         * timestamp : 1511513299617
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<CashDetailsBean> cashRecordList;

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

        public List<CashDetailsBean> getCashRecordList() {
            return cashRecordList;
        }

        public void setCashRecordList(List<CashDetailsBean> cashRecordList) {
            this.cashRecordList = cashRecordList;
        }

    }
}
