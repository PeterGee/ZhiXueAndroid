package com.risenb.studyknowledge.beans.topic;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/9/21.
 */

public class VoteManageBean extends NetBaseBean{
    public static class DataBean {
        /**
         * totalPage : 1
         * isUpdated : false
         * voteList : [{"voteImg":"话题图片地址nn","voteIsTop":0,"topicName":"109xiugai添加测试000","VoteName":"1010投票添加测试后台添加","startTime":"2017-10-04 10:58","endTime":"2017-10-17 10:58","voteId":1801,"userName":"阿帅","voteJoinNum":0},{"voteImg":"话题图片地址nn","voteIsTop":0,"topicName":"109xiugai添加测试000","VoteName":"10101740投票添加测试","startTime":"2017-08-29 10:50","endTime":"2017-09-14 08:06","voteId":1802,"userName":"杨戉","voteJoinNum":0},{"voteImg":"话题图片地址nn","voteIsTop":0,"topicName":"109xiugai添加测试000","VoteName":"10101740投票添加测试","startTime":"2017-08-29 10:50","endTime":"2017-09-14 08:06","voteId":1803,"userName":"杨戉","voteJoinNum":0},{"voteImg":"话题图片地址nn","voteIsTop":0,"topicName":"109xiugai添加测试000","VoteName":"1111111投票添加测试","startTime":"2017-08-29 10:50","endTime":"2017-09-14 08:06","voteId":1804,"userName":"杨戉","voteJoinNum":0},{"voteImg":"话题图片地址nn","voteIsTop":0,"topicName":"109xiugai添加测试000","VoteName":"10111011投票添加测试","startTime":"2017-08-29 10:50","endTime":"2017-09-14 08:06","voteId":1805,"userName":"杨戉","voteJoinNum":0}]
         * totalRecord : 5
         * timestamp : 1511408201202
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<VoteListBean> voteList;

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

        public List<VoteListBean> getVoteList() {
            return voteList;
        }

        public void setVoteList(List<VoteListBean> voteList) {
            this.voteList = voteList;
        }


    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    private String name;


}
