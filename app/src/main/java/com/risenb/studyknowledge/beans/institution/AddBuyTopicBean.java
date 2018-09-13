package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

/**
 * Created by yjyvi on 2017/12/12.
 */

public class AddBuyTopicBean extends NetBaseBean {

    /**
     * buyTopic : {"buytopicBuyer":45,"buytopicBuyerCollegeid":"","buytopicCreattime":1513072588000,"buytopicDelyn":0,"buytopicEndtime":1515750988000,"buytopicId":114,"buytopicMonths":1,"buytopicSellor":45,"buytopicSellorCollegeid":"","buytopicStatus":0,"buytopicTopic":403,"buytopicTopicNew":407,"buytopicUpdatetime":1513072587000,"buytopicUpdator":"","collegeName":"李洁老师小课堂","newWriterId":"","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/57bff74f-4df1-48a3-a662-d04a85db4b0d.png","topicName":"微课\u2014\u2014德鲁克经典五问"}
     */

    private BuyTopicBean buyTopic;

    public BuyTopicBean getBuyTopic() {
        return buyTopic;
    }

    public void setBuyTopic(BuyTopicBean buyTopic) {
        this.buyTopic = buyTopic;
    }

    public static class BuyTopicBean {
        /**
         * buytopicBuyer : 45
         * buytopicBuyerCollegeid :
         * buytopicCreattime : 1513072588000
         * buytopicDelyn : 0
         * buytopicEndtime : 1515750988000
         * buytopicId : 114
         * buytopicMonths : 1
         * buytopicSellor : 45
         * buytopicSellorCollegeid :
         * buytopicStatus : 0
         * buytopicTopic : 403
         * buytopicTopicNew : 407
         * buytopicUpdatetime : 1513072587000
         * buytopicUpdator :
         * collegeName : 李洁老师小课堂
         * newWriterId :
         * topicImg : http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/57bff74f-4df1-48a3-a662-d04a85db4b0d.png
         * topicName : 微课——德鲁克经典五问
         */

        private int buytopicBuyer;
        private String buytopicBuyerCollegeid;
        private long buytopicCreattime;
        private int buytopicDelyn;
        private long buytopicEndtime;
        private int buytopicId;
        private int buytopicMonths;
        private int buytopicSellor;
        private String buytopicSellorCollegeid;
        private int buytopicStatus;
        private int buytopicTopic;
        private int buytopicTopicNew;
        private long buytopicUpdatetime;
        private String buytopicUpdator;
        private String collegeName;
        private String newWriterId;
        private String topicImg;
        private String topicName;

        public int getBuytopicBuyer() {
            return buytopicBuyer;
        }

        public void setBuytopicBuyer(int buytopicBuyer) {
            this.buytopicBuyer = buytopicBuyer;
        }

        public String getBuytopicBuyerCollegeid() {
            return buytopicBuyerCollegeid;
        }

        public void setBuytopicBuyerCollegeid(String buytopicBuyerCollegeid) {
            this.buytopicBuyerCollegeid = buytopicBuyerCollegeid;
        }

        public long getBuytopicCreattime() {
            return buytopicCreattime;
        }

        public void setBuytopicCreattime(long buytopicCreattime) {
            this.buytopicCreattime = buytopicCreattime;
        }

        public int getBuytopicDelyn() {
            return buytopicDelyn;
        }

        public void setBuytopicDelyn(int buytopicDelyn) {
            this.buytopicDelyn = buytopicDelyn;
        }

        public long getBuytopicEndtime() {
            return buytopicEndtime;
        }

        public void setBuytopicEndtime(long buytopicEndtime) {
            this.buytopicEndtime = buytopicEndtime;
        }

        public int getBuytopicId() {
            return buytopicId;
        }

        public void setBuytopicId(int buytopicId) {
            this.buytopicId = buytopicId;
        }

        public int getBuytopicMonths() {
            return buytopicMonths;
        }

        public void setBuytopicMonths(int buytopicMonths) {
            this.buytopicMonths = buytopicMonths;
        }

        public int getBuytopicSellor() {
            return buytopicSellor;
        }

        public void setBuytopicSellor(int buytopicSellor) {
            this.buytopicSellor = buytopicSellor;
        }

        public String getBuytopicSellorCollegeid() {
            return buytopicSellorCollegeid;
        }

        public void setBuytopicSellorCollegeid(String buytopicSellorCollegeid) {
            this.buytopicSellorCollegeid = buytopicSellorCollegeid;
        }

        public int getBuytopicStatus() {
            return buytopicStatus;
        }

        public void setBuytopicStatus(int buytopicStatus) {
            this.buytopicStatus = buytopicStatus;
        }

        public int getBuytopicTopic() {
            return buytopicTopic;
        }

        public void setBuytopicTopic(int buytopicTopic) {
            this.buytopicTopic = buytopicTopic;
        }

        public int getBuytopicTopicNew() {
            return buytopicTopicNew;
        }

        public void setBuytopicTopicNew(int buytopicTopicNew) {
            this.buytopicTopicNew = buytopicTopicNew;
        }

        public long getBuytopicUpdatetime() {
            return buytopicUpdatetime;
        }

        public void setBuytopicUpdatetime(long buytopicUpdatetime) {
            this.buytopicUpdatetime = buytopicUpdatetime;
        }

        public String getBuytopicUpdator() {
            return buytopicUpdator;
        }

        public void setBuytopicUpdator(String buytopicUpdator) {
            this.buytopicUpdator = buytopicUpdator;
        }

        public String getCollegeName() {
            return collegeName;
        }

        public void setCollegeName(String collegeName) {
            this.collegeName = collegeName;
        }

        public String getNewWriterId() {
            return newWriterId;
        }

        public void setNewWriterId(String newWriterId) {
            this.newWriterId = newWriterId;
        }

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }
    }
}
