package com.risenb.studyknowledge.beans.posts;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/11/28.
 */

public class PostsDetailsBean extends NetBaseBean {


    /**
     * isUpdated : false
     * postCommentList : [{"floorInfo":{"floorCreationtime":"2017-12-18","floorData":"你哪次你猜你猜你","floorId":720,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"},"talkInfo":[]},{"floorInfo":{"floorCreationtime":"2017-12-18","floorData":"好想好想你想你想那些","floorId":721,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"},"talkInfo":[]},{"floorInfo":{"floorCreationtime":"2017-12-18","floorData":"好想好想你想你想那些","floorId":722,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"},"talkInfo":[]},{"floorInfo":{"floorCreationtime":"2017-12-18","floorData":"那些想你想你","floorId":723,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"},"talkInfo":[]},{"floorInfo":{"floorCreationtime":"2017-12-18","floorData":"九转金身决","floorId":724,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"},"talkInfo":[]},{"floorInfo":{"floorCreationtime":"2017-12-18","floorData":"德克萨斯","floorId":725,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"},"talkInfo":[]}]
     * postContent : {"attentionNum":1,"postContent":"","userImg":"http://wx.qlogo.cn/mmopen/SCug0ESSOHib7VNsNWJYg07OkR477M7l4sysVgHHWvTOn8kdTOAOdX7QK1OtmLznMHvZa2ywpvN12jUxUsDalqR2Bt2peIMVh/0","userName":"闫铮"}
     * timestamp : 1513585318893
     * totalPage : 1
     * totalRecord : 6
     * workCommentList : [{"floorInfo":{"floorCreationtime":"2017-12-18","floorData":"呵呵呵","floorId":726,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"},"talkInfo":[]},{"floorInfo":{"floorCreationtime":"2017-12-18","floorData":"呵呵","floorId":727,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"},"talkInfo":[]}]
     */

    private boolean isUpdated;
    private PostContentBean postContent;
    private String timestamp;
    private int totalPage;
    private int totalRecord;
    private List<PostCommentListBean> postCommentList;
    private List<WorkCommentListBean> workCommentList;

    public boolean isIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public PostContentBean getPostContent() {
        return postContent;
    }

    public void setPostContent(PostContentBean postContent) {
        this.postContent = postContent;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<PostCommentListBean> getPostCommentList() {
        return postCommentList;
    }

    public void setPostCommentList(List<PostCommentListBean> postCommentList) {
        this.postCommentList = postCommentList;
    }

    public List<WorkCommentListBean> getWorkCommentList() {
        return workCommentList;
    }

    public void setWorkCommentList(List<WorkCommentListBean> workCommentList) {
        this.workCommentList = workCommentList;
    }

    public static class PostContentBean {
        /**
         * attentionNum : 1
         * postContent :
         * userImg : http://wx.qlogo.cn/mmopen/SCug0ESSOHib7VNsNWJYg07OkR477M7l4sysVgHHWvTOn8kdTOAOdX7QK1OtmLznMHvZa2ywpvN12jUxUsDalqR2Bt2peIMVh/0
         * userName : 闫铮
         * postReward : 998.00
         * postPeepNum : 0
         */

        private int attentionNum;
        private String postContent;
        private String userImg;
        private String userName;
        private String postReward;
        private String postPeepNum;

        public String getPostReward() {
            return postReward;
        }

        public void setPostReward(String postReward) {
            this.postReward = postReward;
        }

        public String getPostPeepNum() {
            return postPeepNum;
        }

        public void setPostPeepNum(String postPeepNum) {
            this.postPeepNum = postPeepNum;
        }

        public int getAttentionNum() {
            return attentionNum;
        }

        public void setAttentionNum(int attentionNum) {
            this.attentionNum = attentionNum;
        }

        public String getPostContent() {
            return postContent;
        }

        public void setPostContent(String postContent) {
            this.postContent = postContent;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class PostCommentListBean {
        /**
         * floorInfo : {"floorCreationtime":"2017-12-18","floorData":"你哪次你猜你猜你","floorId":720,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"}
         * talkInfo : []
         */

        private FloorInfoBean floorInfo;
        private List<TalkInfoBean> talkInfo;

        public FloorInfoBean getFloorInfo() {
            return floorInfo;
        }

        public void setFloorInfo(FloorInfoBean floorInfo) {
            this.floorInfo = floorInfo;
        }

        public List<TalkInfoBean> getTalkInfo() {
            return talkInfo;
        }

        public void setTalkInfo(List<TalkInfoBean> talkInfo) {
            this.talkInfo = talkInfo;
        }

        public static  class  TalkInfoBean{
            private String talkStr;

            public String getTalkStr() {
                return talkStr;
            }

            public void setTalkStr(String talkStr) {
                this.talkStr = talkStr;
            }
        }

        public static class FloorInfoBean {
            /**
             * floorCreationtime : 2017-12-18
             * floorData : 你哪次你猜你猜你
             * floorId : 720
             * userImg : 就象训觉辛苦辛苦
             * userName : 几点到家时间
             */

            private String floorCreationtime;
            private String floorData;
            private int floorId;
            private String userImg;
            private String userName;
            private String floorUserId;


            public String getFloorUserId() {
                return floorUserId;
            }

            public void setFloorUserId(String floorUserId) {
                this.floorUserId = floorUserId;
            }

            public String getFloorCreationtime() {
                return floorCreationtime;
            }

            public void setFloorCreationtime(String floorCreationtime) {
                this.floorCreationtime = floorCreationtime;
            }

            public String getFloorData() {
                return floorData;
            }

            public void setFloorData(String floorData) {
                this.floorData = floorData;
            }

            public int getFloorId() {
                return floorId;
            }

            public void setFloorId(int floorId) {
                this.floorId = floorId;
            }

            public String getUserImg() {
                return userImg;
            }

            public void setUserImg(String userImg) {
                this.userImg = userImg;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }

    public static class WorkCommentListBean {
        /**
         * floorInfo : {"floorCreationtime":"2017-12-18","floorData":"呵呵呵","floorId":726,"userImg":"就象训觉辛苦辛苦","userName":"几点到家时间"}
         * talkInfo : []
         */

        private FloorInfoBeanX floorInfo;
        private List<TalkInfoBean> talkInfo;

        public FloorInfoBeanX getFloorInfo() {
            return floorInfo;
        }

        public void setFloorInfo(FloorInfoBeanX floorInfo) {
            this.floorInfo = floorInfo;
        }

        public List<TalkInfoBean> getTalkInfo() {
            return talkInfo;
        }

        public void setTalkInfo(List<TalkInfoBean> talkInfo) {
            this.talkInfo = talkInfo;
        }

        public static  class  TalkInfoBean{
            private String workTalkStr;

            public String getWorkTalkStr() {
                return workTalkStr;
            }

            public void setWorkTalkStr(String workTalkStr) {
                this.workTalkStr = workTalkStr;
            }
        }

        public static class FloorInfoBeanX {
            /**
             * floorCreationtime : 2017-12-18
             * floorData : 呵呵呵
             * floorId : 726
             * userImg : 就象训觉辛苦辛苦
             * userName : 几点到家时间
             */

            private String floorCreationtime;
            private String floorData;
            private int floorId;
            private String userImg;
            private String userName;
            private String floorUserId;

            public String getFloorUserId() {
                return floorUserId;
            }

            public void setFloorUserId(String floorUserId) {
                this.floorUserId = floorUserId;
            }

            public String getFloorCreationtime() {
                return floorCreationtime;
            }

            public void setFloorCreationtime(String floorCreationtime) {
                this.floorCreationtime = floorCreationtime;
            }

            public String getFloorData() {
                return floorData;
            }

            public void setFloorData(String floorData) {
                this.floorData = floorData;
            }

            public int getFloorId() {
                return floorId;
            }

            public void setFloorId(int floorId) {
                this.floorId = floorId;
            }

            public String getUserImg() {
                return userImg;
            }

            public void setUserImg(String userImg) {
                this.userImg = userImg;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
