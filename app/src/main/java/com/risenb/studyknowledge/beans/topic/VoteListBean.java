package com.risenb.studyknowledge.beans.topic;

import java.io.Serializable;

/**
 * Created by zhuzh on 2017/11/23.
 */

public class VoteListBean  implements Serializable{

    /**
     * voteImg : 话题图片地址nn
     * voteIsTop : 0
     * topicName : 109xiugai添加测试000
     * VoteName : 1010投票添加测试后台添加
     * startTime : 2017-10-04 10:58
     * endTime : 2017-10-17 10:58
     * voteId : 1801
     * userName : 阿帅
     * voteJoinNum : 0
     */

    private String voteImg;
    private int voteIsTop;
    private String topicName;
    private String VoteName;
    private String startTime;
    private String endTime;
    private int voteId;
    private String userName;
    private int voteJoinNum;
    private String activityType;//活动类型


    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }


    public String getVoteImg() {
        return voteImg;
    }

    public void setVoteImg(String voteImg) {
        this.voteImg = voteImg;
    }

    public int getVoteIsTop() {
        return voteIsTop;
    }

    public void setVoteIsTop(int voteIsTop) {
        this.voteIsTop = voteIsTop;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getVoteName() {
        return VoteName;
    }

    public void setVoteName(String VoteName) {
        this.VoteName = VoteName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getVoteJoinNum() {
        return voteJoinNum;
    }

    public void setVoteJoinNum(int voteJoinNum) {
        this.voteJoinNum = voteJoinNum;
    }
}
