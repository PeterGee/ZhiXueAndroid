package com.risenb.studyknowledge.beans.posts;

import java.util.List;

/**
 * Created by zhuzh on 2017/11/28.
 */

public class PostCommentListBean {
        /**
         * floorInfo : {"floorId":685,"floorData":"好","userImg":"http://wx.qlogo.cn/mmopen/ZuiaDAak4ia2icEnz0SwzRSEJicCILwHmRwYOZ01OkI0mmaC9aGhNEMw6koZdgNGnWZ2eGDlqQR2pXHEbnKBTfoaKGzFEQGNmbL7/0","floorCreationtime":"2017-08-01","userName":"刘一"}
         * talkInfo : [{"talkStr":"刘一:1=talkId=566,commentUserId=1345"},{"talkStr":"阿帅:来来来=talkId=576,commentUserId=702"},{"talkStr":"阿帅:恐惧症=talkId=577,commentUserId=702"},{"talkStr":"阿帅回复 阿帅:居住证=talkId=578,commentUserId=702,beCommentUserId=702"},{"talkStr":"九天揽月回复 阿帅:健健康康=talkId=585,commentUserId=1315,beCommentUserId=702"}]
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

        public static class FloorInfoBean {
            /**
             * floorId : 685
             * floorData : 好
             * userImg : http://wx.qlogo.cn/mmopen/ZuiaDAak4ia2icEnz0SwzRSEJicCILwHmRwYOZ01OkI0mmaC9aGhNEMw6koZdgNGnWZ2eGDlqQR2pXHEbnKBTfoaKGzFEQGNmbL7/0
             * floorCreationtime : 2017-08-01
             * userName : 刘一
             */

            private int floorId;
            private String floorData;
            private String userImg;
            private String floorCreationtime;
            private String userName;

            public int getFloorId() {
                return floorId;
            }

            public void setFloorId(int floorId) {
                this.floorId = floorId;
            }

            public String getFloorData() {
                return floorData;
            }

            public void setFloorData(String floorData) {
                this.floorData = floorData;
            }

            public String getUserImg() {
                return userImg;
            }

            public void setUserImg(String userImg) {
                this.userImg = userImg;
            }

            public String getFloorCreationtime() {
                return floorCreationtime;
            }

            public void setFloorCreationtime(String floorCreationtime) {
                this.floorCreationtime = floorCreationtime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }

        public static class TalkInfoBean {
            /**
             * talkStr : 刘一:1=talkId=566,commentUserId=1345
             */

            private String talkStr;

            public String getTalkStr() {
                return talkStr;
            }

            public void setTalkStr(String talkStr) {
                this.talkStr = talkStr;
            }
        }
}
