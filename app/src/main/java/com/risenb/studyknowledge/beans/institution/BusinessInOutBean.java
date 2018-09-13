package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/9/25.
 * 友商购进
 */

public class BusinessInOutBean extends NetBaseBean{

    public static class DataBean {
        /**
         * totalPage : 1
         * isUpdated : false
         * list : [{"buytopicBuyer":45,"buytopicBuyerCollegeid":"","buytopicCreattime":1511595586000,"buytopicDelyn":0,"buytopicEndtime":1543131586000,"buytopicId":96,"buytopicMonths":12,"buytopicSellor":45,"buytopicSellorCollegeid":"","buytopicStatus":0,"buytopicTopic":289,"buytopicTopicNew":368,"buytopicUpdatetime":1511595585000,"buytopicUpdator":"","collegeName":"李洁老师小课堂","newWriterId":"","topicImg":"","topicName":""},{"buytopicBuyer":64,"buytopicBuyerCollegeid":"","buytopicCreattime":1499252891000,"buytopicDelyn":0,"buytopicEndtime":1530788891000,"buytopicId":88,"buytopicMonths":12,"buytopicSellor":45,"buytopicSellorCollegeid":"","buytopicStatus":0,"buytopicTopic":289,"buytopicTopicNew":303,"buytopicUpdatetime":"","buytopicUpdator":"","collegeName":"劲霸学堂","newWriterId":"","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/20170611/662c2538ce56e26e41b8f5e572fab2a2.jpg","topicName":"劲霸男装读书分享会"}]
         * totalRecord : 2
         * timestamp : 1511595784413
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<BusinessInfoBean> list;

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

        public List<BusinessInfoBean> getList() {
            return list;
        }

        public void setList(List<BusinessInfoBean> list) {
            this.list = list;
        }

    }
}
