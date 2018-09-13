package com.risenb.studyknowledge.beans.live;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuzh on 2017/9/22.
 */

public class LiveNoticeBean extends NetBaseBean {
    public static class DataBean {
        /**
         * postLiveList : [{"userImg":"http://wx.qlogo.cn/mmopen/qzOlKibbqGuuRrfgLyaShzleChjDeoRHibMXjeDqBevkb4CCGN4ibvKic4wdtYwN1OoWSQ1Pssg7ricg9PhJBapQMek1pLn9KSicys/0","postLivetime":"2017-11-09 17:31","topicId":289,"postInfo":"山东分公司地方","postName":"浩浩直播间","topicName":"劲霸男装读书分享会","postId":1795,"userName":"YuangYuang圓圓"},{"userImg":"http://wx.qlogo.cn/mmopen/qzOlKibbqGuuRrfgLyaShzleChjDeoRHibMXjeDqBevkb4CCGN4ibvKic4wdtYwN1OoWSQ1Pssg7ricg9PhJBapQMek1pLn9KSicys/0","postLivetime":"2017-09-25 14:37","topicId":289,"postInfo":"山东分公司地方","postName":"好好直播间2","topicName":"劲霸男装读书分享会","postId":1796,"userName":"YuangYuang圓圓"},{"userImg":"http://wx.qlogo.cn/mmopen/qzOlKibbqGuuRrfgLyaShzleChjDeoRHibMXjeDqBevkb4CCGN4ibvKic4wdtYwN1OoWSQ1Pssg7ricg9PhJBapQMek1pLn9KSicys/0","postLivetime":"2017-09-25 15:07","topicId":289,"postInfo":"预告简介","postName":"添加测试","topicName":"劲霸男装读书分享会","postId":1797,"userName":"YuangYuang圓圓"},{"userImg":"http://wx.qlogo.cn/mmopen/qzOlKibbqGuuRrfgLyaShzleChjDeoRHibMXjeDqBevkb4CCGN4ibvKic4wdtYwN1OoWSQ1Pssg7ricg9PhJBapQMek1pLn9KSicys/0","postLivetime":"2017-09-25 15:07","topicId":289,"postInfo":"预告简介1331615616","postName":"修改测试","topicName":"劲霸男装读书分享会","postId":1798,"userName":"YuangYuang圓圓"},{"userImg":"http://wx.qlogo.cn/mmopen/qzOlKibbqGuuRrfgLyaShzleChjDeoRHibMXjeDqBevkb4CCGN4ibvKic4wdtYwN1OoWSQ1Pssg7ricg9PhJBapQMek1pLn9KSicys/0","postLivetime":"2017-09-25 15:07","topicId":289,"postInfo":"预告简介","postName":"1115添加测试","topicName":"劲霸男装读书分享会","postId":1860,"userName":"YuangYuang圓圓"}]
         * totalPage : 1
         * isUpdated : false
         * totalRecord : 5
         * timestamp : 1511330273634
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<PostLiveListBean> postLiveList;

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

        public List<PostLiveListBean> getPostLiveList() {
            return postLiveList;
        }

        public void setPostLiveList(List<PostLiveListBean> postLiveList) {
            this.postLiveList = postLiveList;
        }

    }


}
