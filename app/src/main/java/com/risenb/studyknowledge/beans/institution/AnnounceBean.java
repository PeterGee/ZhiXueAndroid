package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/9/29.
 * 公告列表
 */

public class AnnounceBean extends NetBaseBean{

    public static class DataBean {
        /**
         * noticeList : [{"noticeCreationtime":"2017-09-26 09:09","noticeInfo":"北京知学科技有限公司，碎片化学习，让你学出精彩的明天","noticeTitle":"平台简介（请勿删除，勿修改！！！）","noticeId":0},{"noticeCreationtime":"2017-06-25 19:01","noticeInfo":"创建群空间目的是为小区居民提供一个方便的交流平台，欢迎小区的业主和居民，以及关心小区事务的机构包括物业公司人员加入。申请加入本群需申报房号或身份说明，请群友们自觉遵守网络公共秩序，文明发言，未经管理员许可，不可擅自发布商业广告。欢迎举报，违规者开除群。","noticeTitle":"群策群力共建美好家园","noticeId":45}]
         * totalPage : 4
         * isUpdated : false
         * totalRecord : 7
         * timestamp : 1511493860606
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<NoticeListBean> noticeList;

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

        public List<NoticeListBean> getNoticeList() {
            return noticeList;
        }

        public void setNoticeList(List<NoticeListBean> noticeList) {
            this.noticeList = noticeList;
        }

    }
}
