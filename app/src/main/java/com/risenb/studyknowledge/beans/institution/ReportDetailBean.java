package com.risenb.studyknowledge.beans.institution;

import java.util.List;

/**
 * Created by yy on 2017/10/20.
 */

public class ReportDetailBean {

    /**
     * complaint : {"complaintToId":686,"postWriterId":"李洁老师","complaintId":4,"topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/df8fb644-ddb2-4d10-8156-8d6b4002cbe7.png","postName":"情商领导力04\u2014\u2014自我管理","complaintContent":"自我管理使人们进步的基础！","complaintCreationTime":"2017-11-07 15:11","complaintCount":null}
     * totalPage : 0
     * complaintList : [{"userImg":"http://192.168.1.189:80/user/res/img/moren3.jpg","complaintId":4,"complaintInfo":"limin","userName":"JF","complaintInfoType":3},{"userImg":"http://192.168.1.189:80/user/res/img/moren3.jpg","complaintId":5,"complaintInfo":"limin","userName":"JF","complaintInfoType":3}]
     * isUpdated : false
     * totalRecord : 0
     * timestamp : 1512713694090
     */

    private ComplaintBean complaint;
    private int totalPage;
    private boolean isUpdated;
    private int totalRecord;
    private String timestamp;
    private List<ComplaintListBean> complaintList;

    public ComplaintBean getComplaint() {
        return complaint;
    }

    public void setComplaint(ComplaintBean complaint) {
        this.complaint = complaint;
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

    public List<ComplaintListBean> getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(List<ComplaintListBean> complaintList) {
        this.complaintList = complaintList;
    }

    public static class ComplaintBean {
        /**
         * complaintToId : 686
         * postWriterId : 李洁老师
         * complaintId : 4
         * topicImg : http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/df8fb644-ddb2-4d10-8156-8d6b4002cbe7.png
         * postName : 情商领导力04——自我管理
         * complaintContent : 自我管理使人们进步的基础！
         * complaintCreationTime : 2017-11-07 15:11
         * complaintCount : null
         */

        private int complaintToId;
        private String postWriterId;
        private int complaintId;
        private String topicImg;
        private String postName;
        private String complaintContent;
        private String complaintCreationTime;
        private Object complaintCount;

        public int getComplaintToId() {
            return complaintToId;
        }

        public void setComplaintToId(int complaintToId) {
            this.complaintToId = complaintToId;
        }

        public String getPostWriterId() {
            return postWriterId;
        }

        public void setPostWriterId(String postWriterId) {
            this.postWriterId = postWriterId;
        }

        public int getComplaintId() {
            return complaintId;
        }

        public void setComplaintId(int complaintId) {
            this.complaintId = complaintId;
        }

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getComplaintContent() {
            return complaintContent;
        }

        public void setComplaintContent(String complaintContent) {
            this.complaintContent = complaintContent;
        }

        public String getComplaintCreationTime() {
            return complaintCreationTime;
        }

        public void setComplaintCreationTime(String complaintCreationTime) {
            this.complaintCreationTime = complaintCreationTime;
        }

        public Object getComplaintCount() {
            return complaintCount;
        }

        public void setComplaintCount(Object complaintCount) {
            this.complaintCount = complaintCount;
        }
    }

    public static class ComplaintListBean {
        /**
         * userImg : http://192.168.1.189:80/user/res/img/moren3.jpg
         * complaintId : 4
         * complaintInfo : limin
         * userName : JF
         * complaintInfoType : 3
         */

        private String userImg;
        private int complaintId;
        private String complaintInfo;
        private String userName;
        private int complaintInfoType;

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public int getComplaintId() {
            return complaintId;
        }

        public void setComplaintId(int complaintId) {
            this.complaintId = complaintId;
        }

        public String getComplaintInfo() {
            return complaintInfo;
        }

        public void setComplaintInfo(String complaintInfo) {
            this.complaintInfo = complaintInfo;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getComplaintInfoType() {
            return complaintInfoType;
        }

        public void setComplaintInfoType(int complaintInfoType) {
            this.complaintInfoType = complaintInfoType;
        }
    }
}
