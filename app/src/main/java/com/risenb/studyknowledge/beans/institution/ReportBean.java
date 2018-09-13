package com.risenb.studyknowledge.beans.institution;

import java.util.List;

/**
 * Created by yy on 2017/10/18.
 * 举报bean
 */

public class ReportBean {

    /**
     * totalPage : 0
     * complaintList : [{"complaintToId":1477,"postWriterId":"李洁老师","complaintId":7,"topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/57bff74f-4df1-48a3-a662-d04a85db4b0d.png","postName":"德鲁克经典五问01\u2014\u2014课程介绍","complaintContent":null,"complaintCreationTime":"2017-11-07 15:12","complaintCount":1},{"complaintToId":1349,"postWriterId":"李洁老师","complaintId":6,"topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/abd450b8-894c-46c7-9d2b-15f96c4dea4e.png","postName":"提升视野14\u2014\u20145R赋能领导力与目标管理","complaintContent":null,"complaintCreationTime":"2017-11-07 15:12","complaintCount":1},{"complaintToId":1464,"postWriterId":"李洁老师","complaintId":3,"topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/df8fb644-ddb2-4d10-8156-8d6b4002cbe7.png","postName":"情商领导力04\u2014\u2014自我管理","complaintContent":null,"complaintCreationTime":"2017-11-07 15:11","complaintCount":1},{"complaintToId":1476,"postWriterId":"李洁老师","complaintId":2,"topicImg":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/57bff74f-4df1-48a3-a662-d04a85db4b0d.png","postName":"德鲁克经典五问02\u2014\u2014战略思考","complaintContent":null,"complaintCreationTime":"2017-11-07 15:10","complaintCount":1}]
     * isUpdated : false
     * totalRecord : 0
     * timestamp : 1512702405228
     */

    private int totalPage;
    private boolean isUpdated;
    private int totalRecord;
    private String timestamp;
    private List<ComplaintListBean> complaintList;



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

    public static class ComplaintListBean {
        /**
         * complaintToId : 1477
         * postWriterId : 李洁老师
         * complaintId : 7
         * topicImg : http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/57bff74f-4df1-48a3-a662-d04a85db4b0d.png
         * postName : 德鲁克经典五问01——课程介绍
         * complaintContent : null
         * complaintCreationTime : 2017-11-07 15:12
         * complaintCount : 1
         */


        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        private boolean isChecked;

        private boolean isShow;


        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        private int complaintToId;
        private String postWriterId;
        private int complaintId;
        private String topicImg;
        private String postName;
        private String complaintContent;
        private String complaintCreationTime;
        private int complaintCount;

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

        public int getComplaintCount() {
            return complaintCount;
        }

        public void setComplaintCount(int complaintCount) {
            this.complaintCount = complaintCount;
        }
    }
}
