package com.risenb.studyknowledge.beans.posts;

import java.io.Serializable;

/**
 * Created by zhezhu on 2017/11/23.
 */

public class PostListBean implements Serializable{
    /**
     * postCollection : 0
     * postCreationTime : 2017-11-08 10:55
     * postReward : 0.00
     * postContentApp :
     * postContent : <p>
     <img src="http://img.baidu.com/hi/jx2/j_0024.gif"><img src="http://img.baidu.com/hi/ldw/w_0003.gif">
     </p>
     <p>
     <img src="http://img.baidu.com/hi/jx2/j_0034.gif">
     </p>
     <p>
     <img src="http://img.baidu.com/hi/jx2/j_0066.gif">
     </p>
     <p></p>
     <div class="aAudioWrap">
     <audio>
     <source src="http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171108/fd65dda904d6bdec6fe334e196fe2da3.mp3">
     </audio>
     <div class="goStop" id="0music1510109741610" ng-click="getTime('0music1510109741610')">
     <img src="http://1x9x.cn/college/res/img/Audiorun.png">
     </div>
     <div class="audioTime">
     <span class="audioTimeStart">0:00</span>/<span class="audioTimeAll">0:0</span>
     </div>
     </div>
     <p>
     <br>
     </p>
     <p>
     <br>
     </p>
     <p></p>
     <div class="aAudioWrap">
     <audio>
     <source src="http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171108/a0f56b4554dff0a2c3b94a87eb8e180f.mp3">
     </audio>
     <div class="goStop" id="1music1510109741610" ng-click="getTime('1music1510109741610')">
     <img src="http://1x9x.cn/college/res/img/Audiorun.png">
     </div>
     <div class="audioTime">
     <span class="audioTimeStart">0:00</span>/<span class="audioTimeAll">0:0</span>
     </div>
     </div>
     <p>
     <br>
     </p>
     <p>
     音频测试
     </p>
     <p>
     <img src="http://bjzxtest.img-cn-beijing.aliyuncs.com/20171108/160dcece4f60aa532ffde535f07538d4.png" title="160dcece4f60aa532ffde535f07538d4.png" alt="700x395.png">
     </p>
     * postName : 118音频测试
     * postIsTop : 1
     * postIsFree : 1
     * postId : 1821
     * postTalkNum : 0
     * userName : 阿帅
     * postPeepNum :
     */

    private int postCollection;
    private String postCreationTime;
    private String postReward;
    private String postContentApp;
    private String postContent;
    private String postName;
    private int postIsTop;
    private int postIsFree;
    private int postId;
    private int postTalkNum;
    private String userName;
    private String postPeepNum;
    private int postType;

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }


    public int getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(int postCollection) {
        this.postCollection = postCollection;
    }

    public String getPostCreationTime() {
        return postCreationTime;
    }

    public void setPostCreationTime(String postCreationTime) {
        this.postCreationTime = postCreationTime;
    }

    public String getPostReward() {
        return postReward;
    }

    public void setPostReward(String postReward) {
        this.postReward = postReward;
    }

    public String getPostContentApp() {
        return postContentApp;
    }

    public void setPostContentApp(String postContentApp) {
        this.postContentApp = postContentApp;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public int getPostIsTop() {
        return postIsTop;
    }

    public void setPostIsTop(int postIsTop) {
        this.postIsTop = postIsTop;
    }

    public int getPostIsFree() {
        return postIsFree;
    }

    public void setPostIsFree(int postIsFree) {
        this.postIsFree = postIsFree;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostTalkNum() {
        return postTalkNum;
    }

    public void setPostTalkNum(int postTalkNum) {
        this.postTalkNum = postTalkNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostPeepNum() {
        return postPeepNum;
    }

    public void setPostPeepNum(String postPeepNum) {
        this.postPeepNum = postPeepNum;
    }
}
