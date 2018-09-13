package com.risenb.studyknowledge.beans.topic;

import java.io.Serializable;

/**
 * Created by zhuzh on 2017/11/22.
 */

public class TopicListBean implements Serializable {
    /**
     * topicId : 259
     * creationTime : 2017-07-11 13:07
     * topicImg : http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/57bff74f-4df1-48a3-a662-d04a85db4b0d.png
     * topicUseyn : 1
     * topicName : 微课——德鲁克经典五问
     * topicPayType : 1
     * topicType : 1
     */

    private int topicId;
    private String creationTime;
    private String topicImg;
    private int topicUseyn;
    private String topicName;
    private int topicPayType;
    private int topicType;
    private int topicPrice;
    private String topicVipName;
    private int topicIsTop;


    public int getTopicIsTop() {
        return topicIsTop;
    }

    public void setTopicIsTop(int topicIsTop) {
        this.topicIsTop = topicIsTop;
    }



    public int getTopicPrice() {
        return topicPrice;
    }

    public void setTopicPrice(int topicPrice) {
        this.topicPrice = topicPrice;
    }


    public String getTopicVipName() {
        return topicVipName;
    }

    public void setTopicVipName(String topicVipName) {
        this.topicVipName = topicVipName;
    }


    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public int getTopicUseyn() {
        return topicUseyn;
    }

    public void setTopicUseyn(int topicUseyn) {
        this.topicUseyn = topicUseyn;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTopicPayType() {
        return topicPayType;
    }

    public void setTopicPayType(int topicPayType) {
        this.topicPayType = topicPayType;
    }

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }
}
