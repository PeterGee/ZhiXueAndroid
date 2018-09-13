package com.risenb.studyknowledge.beans.topic;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuzh on 2017/8/13.
 */

public class TopicsListBean extends NetBaseBean {
    public static class DataBean {
        /**
         * totalPage : 2
         * isUpdated : false
         * totalRecord : 12
         * topicList : [{"topicId":259,"creationTime":"2017-07-11 13:07","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/57bff74f-4df1-48a3-a662-d04a85db4b0d.png","topicUseyn":1,"topicName":"微课\u2014\u2014德鲁克经典五问","topicPayType":1,"topicType":1},{"topicId":263,"creationTime":"2017-04-20 00:59","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/df8fb644-ddb2-4d10-8156-8d6b4002cbe7.png","topicUseyn":1,"topicName":"微课\u2014\u2014情商领导力","topicPayType":1,"topicType":1},{"topicId":264,"creationTime":"2017-04-20 00:20","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/abd450b8-894c-46c7-9d2b-15f96c4dea4e.png","topicUseyn":1,"topicName":"微课\u2014\u2014领导力开发--提升视野","topicPayType":1,"topicType":1},{"topicId":266,"creationTime":"2017-04-20 00:07","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/834d8c2d-3455-4545-b849-4461e68fe612.png","topicUseyn":1,"topicName":"教练微课\u2014\u2014赋能领导力","topicPayType":1,"topicType":1},{"topicId":267,"creationTime":"2017-04-20 00:05","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/749f17d6-6690-4666-bccd-4dde230c53d2.png","topicUseyn":1,"topicName":"教练微课\u2014\u20145R教练领导力","topicPayType":1,"topicType":1},{"topicId":269,"creationTime":"2017-07-05 18:07","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/d4213099-06fb-406e-8227-17b4a0139912.png","topicUseyn":1,"topicName":"大家谈","topicPayType":1,"topicType":2},{"topicId":288,"creationTime":"2017-05-24 07:46","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/2c34b669-af4b-451f-9a7f-ccc55bd642d5.jpg","topicUseyn":1,"topicName":"天天读书分享","topicPayType":1,"topicType":1},{"topicId":260,"creationTime":"2017-04-20 00:38","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/082bb1dc-7d26-43d4-a406-7ee18e5624f4.png","topicUseyn":1,"topicName":"读德鲁克\u2014\u201421世纪的管理挑战","topicPayType":1,"topicType":1},{"topicId":265,"creationTime":"2017-04-19 23:47","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/352803d7-4cfb-47e1-9017-6cd0cd20bc4f.png","topicUseyn":1,"topicName":"互联网微课\u2014\u2014新经济新规则","topicPayType":1,"topicType":1},{"topicId":289,"creationTime":"2017-06-12 22:12","topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/20170611/662c2538ce56e26e41b8f5e572fab2a2.jpg","topicUseyn":1,"topicName":"劲霸男装读书分享会","topicPayType":1,"topicType":1}]
         * timestamp : 1511340481598
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<TopicListBean> topicList;

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

        public List<TopicListBean> getTopicList() {
            return topicList;
        }

        public void setTopicList(List<TopicListBean> topicList) {
            this.topicList = topicList;
        }


    }

//    String topicName;
//
//    String addStatus;
//
//    public String getTopicName() {
//        return topicName;
//    }
//
//    public void setTopicName(String topicName) {
//        this.topicName = topicName;
//    }
//
//    public String getAddStatus() {
//        return addStatus;
//    }
//
//    public void setAddStatus(String addStatus) {
//        this.addStatus = addStatus;
//    }


}
