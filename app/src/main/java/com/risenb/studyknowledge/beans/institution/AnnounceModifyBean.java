package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

/**
 * Created by yy on 2017/11/28.
 * 修改公告
 */

public class AnnounceModifyBean extends NetBaseBean {

    public static class DataBean {
        /**
         * notice : {"noticeCreationtime":"2017-09-26 09:09","noticeInfo":"北京知学科技有限公司，碎片化学习，让你学出精彩的明天","noticeId":0,"noticeTitle":"平台简介（请勿删除，勿修改！！！）"}
         */

        private NoticeListBean notice;

        public NoticeListBean getNotice() {
            return notice;
        }

        public void setNotice(NoticeListBean notice) {
            this.notice = notice;
        }

    }
}
