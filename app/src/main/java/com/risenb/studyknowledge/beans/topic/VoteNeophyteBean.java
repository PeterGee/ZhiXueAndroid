package com.risenb.studyknowledge.beans.topic;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/9/30.
 */

public class VoteNeophyteBean extends NetBaseBean {
    public static class DataBean {
        /**
         * voteDetailList : [{"voteSsecName":"","voteCreattime":"2017-11-23 14:51","postType":421,"userPhone":"13638622846","VoteName":"1123投票添加测试","userEmail":"","userName":"九天揽月"},{"voteSsecName":"","voteCreattime":"2017-11-23 14:51","postType":421,"userPhone":"13638622846","VoteName":"1123投票添加测试","userEmail":"","userName":"九天揽月"}]
         * totalPage : 1
         * isUpdated : false
         * totalRecord : 2
         * timestamp : 1511491867157
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<VoteDetailListBean> voteDetailList;

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

        public List<VoteDetailListBean> getVoteDetailList() {
            return voteDetailList;
        }

        public void setVoteDetailList(List<VoteDetailListBean> voteDetailList) {
            this.voteDetailList = voteDetailList;
        }


    }

//    private String name;
//
//    public List<VoteOptionBean> getVoteOptionBean() {
//        return voteOptionBeen;
//    }
//
//    public void setVoteOptionBean(List<VoteOptionBean> voteOptionBeen) {
//        this.voteOptionBeen = voteOptionBeen;
//    }
//
//    private List<VoteOptionBean> voteOptionBeen;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public static class VoteOptionBean {
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//
//        private String content;
//
//
//    }


}
