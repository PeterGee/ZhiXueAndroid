package com.risenb.studyknowledge.beans.live;

import java.io.Serializable;

/**
 * Created by zhuzh on 2017/11/22.
 */

public class PostLiveListBean implements Serializable {
    /**
     * userImg : http://wx.qlogo.cn/mmopen/qzOlKibbqGuuRrfgLyaShzleChjDeoRHibMXjeDqBevkb4CCGN4ibvKic4wdtYwN1OoWSQ1Pssg7ricg9PhJBapQMek1pLn9KSicys/0
     * postLivetime : 2017-11-09 17:31
     * topicId : 289
     * postInfo : 山东分公司地方
     * postName : 浩浩直播间
     * topicName : 劲霸男装读书分享会
     * postId : 1795
     * userName : YuangYuang圓圓
     */

    private String userImg;
    private String postLivetime;
    private int topicId;
    private String postInfo;
    private String postName;
    private String topicName;
    private int postId;
    private String userName;

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPostLivetime() {
        return postLivetime;
    }

    public void setPostLivetime(String postLivetime) {
        this.postLivetime = postLivetime;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(String postInfo) {
        this.postInfo = postInfo;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
