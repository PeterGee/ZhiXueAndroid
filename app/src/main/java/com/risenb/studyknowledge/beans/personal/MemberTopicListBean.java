package com.risenb.studyknowledge.beans.personal;

/**
 * Created by yy on 2017/9/25.
 * 会员详情页话题列表
 */

public class MemberTopicListBean {
    /**
     * postWriterId : 李洁老师
     * postCreationTime : 2017-11-23 10:04
     * postReward : 50.0
     * postContent : <p>音频测试</p><p>			</p><div class="aAudioWrap"><audio>
     <source src="http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171027/7a376bfcdcec596a219fc936f66a2d38.mp3">			</audio><div class="goStop" id="0music1509066856512" ng-click="getTime('0music1509066856512')"><img src="http://1x9x.cn/college/res/img/Audiorun.png"></div><div class="audioTime"><span class="audioTimeStart">0:00</span>/<span class="audioTimeAll">4:33</span></div><div class="audioRangeWrap"><div class="hadPlayRange"><div class="audioNowPos"></div></div></div></div><p><br></p><p>音频测试</p><p>	</p><div class="aAudioWrap">
     <audio>
     <source src="http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171027/16993a841b6a83b8bab4c5399b0ea169.mp3">
     </audio>
     <div class="goStop" id="1509072754067" ng-click="getTime('1509072754067')">				<img src="http://1x9x.cn/college/res/img/Audiorun.png">
     </div>
     <div class="audioTime"><span class="audioTimeStart">0:00</span>/<span class="audioTimeAll">0:00</span></div><div class="audioRangeWrap"><div class="hadPlayRange"><div class="audioNowPos"></div></div></div>	</div><p><br></p>
     * postName : 音频测试
     * postIsFree : 1
     * postSeeNum : 0
     * postTalkNum : 11
     * postPeepNum : 0
     */

    private String postWriterId;
    private String postCreationTime;
    private double postReward;
    private String postContent;
    private String postName;
    private int postIsFree;//是否付费(1：免费、2：付费)
    private int postSeeNum;  //"浏览人数"
    private int postTalkNum;  //评论人数
    private int postPeepNum;  //偷看人数
    private int type;//0=大家谈，1=有偿提问

    public String getPostWriterId() {
        return postWriterId;
    }

    public void setPostWriterId(String postWriterId) {
        this.postWriterId = postWriterId;
    }

    public String getPostCreationTime() {
        return postCreationTime;
    }

    public void setPostCreationTime(String postCreationTime) {
        this.postCreationTime = postCreationTime;
    }

    public double getPostReward() {
        return postReward;
    }

    public void setPostReward(double postReward) {
        this.postReward = postReward;
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

    public int getPostIsFree() {
        return postIsFree;
    }

    public void setPostIsFree(int postIsFree) {
        this.postIsFree = postIsFree;
    }

    public int getPostSeeNum() {
        return postSeeNum;
    }

    public void setPostSeeNum(int postSeeNum) {
        this.postSeeNum = postSeeNum;
    }

    public int getPostTalkNum() {
        return postTalkNum;
    }

    public void setPostTalkNum(int postTalkNum) {
        this.postTalkNum = postTalkNum;
    }

    public int getPostPeepNum() {
        return postPeepNum;
    }

    public void setPostPeepNum(int postPeepNum) {
        this.postPeepNum = postPeepNum;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
