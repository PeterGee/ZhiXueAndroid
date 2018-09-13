package com.risenb.studyknowledge.beans.posts;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/9/26.
 */

public class PostsCourseBean extends NetBaseBean{

//    private String name;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    private String status;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    public static class DataBean {
        /**
         * postList : [{"postCollection":0,"postCreationTime":"2017-11-08 10:55","postReward":"0.00","postContentApp":"","postContent":"<p>\n    <img src=\"http://img.baidu.com/hi/jx2/j_0024.gif\"><img src=\"http://img.baidu.com/hi/ldw/w_0003.gif\">\n<\/p>\n<p>\n    <img src=\"http://img.baidu.com/hi/jx2/j_0034.gif\">\n<\/p>\n<p>\n    <img src=\"http://img.baidu.com/hi/jx2/j_0066.gif\">\n<\/p>\n<p><\/p>\n<div class=\"aAudioWrap\">\n    <audio>\n        <source src=\"http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171108/fd65dda904d6bdec6fe334e196fe2da3.mp3\">\n    <\/audio>\n    <div class=\"goStop\" id=\"0music1510109741610\" ng-click=\"getTime('0music1510109741610')\">\n        <img src=\"http://1x9x.cn/college/res/img/Audiorun.png\">\n    <\/div>\n    <div class=\"audioTime\">\n        <span class=\"audioTimeStart\">0:00<\/span>/<span class=\"audioTimeAll\">0:0<\/span>\n    <\/div>\n<\/div>\n<p>\n    <br>\n<\/p>\n<p>\n    <br>\n<\/p>\n<p><\/p>\n<div class=\"aAudioWrap\">\n    <audio>\n        <source src=\"http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171108/a0f56b4554dff0a2c3b94a87eb8e180f.mp3\">\n    <\/audio>\n    <div class=\"goStop\" id=\"1music1510109741610\" ng-click=\"getTime('1music1510109741610')\">\n        <img src=\"http://1x9x.cn/college/res/img/Audiorun.png\">\n    <\/div>\n    <div class=\"audioTime\">\n        <span class=\"audioTimeStart\">0:00<\/span>/<span class=\"audioTimeAll\">0:0<\/span>\n    <\/div>\n<\/div>\n<p>\n    <br>\n<\/p>\n<p>\n    音频测试\n<\/p>\n<p>\n    <img src=\"http://bjzxtest.img-cn-beijing.aliyuncs.com/20171108/160dcece4f60aa532ffde535f07538d4.png\" title=\"160dcece4f60aa532ffde535f07538d4.png\" alt=\"700x395.png\">\n<\/p>","postName":"118音频测试","postIsTop":1,"postIsFree":1,"postId":1821,"postTalkNum":0,"userName":"阿帅","postPeepNum":""},{"postCollection":0,"postCreationTime":"2017-10-27 09:14","postReward":"0.00","postContentApp":"","postContent":"<p>音频测试<\/p><p>\t\t\t<\/p><div class=\"aAudioWrap\"><audio>\n\t\t\t\t<source src=\"http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171027/7a376bfcdcec596a219fc936f66a2d38.mp3\">\t\t\t<\/audio><div class=\"goStop\" id=\"0music1509066856512\" ng-click=\"getTime('0music1509066856512')\"><img src=\"http://1x9x.cn/college/res/img/Audiorun.png\"><\/div><div class=\"audioTime\"><span class=\"audioTimeStart\">0:00<\/span>/<span class=\"audioTimeAll\">4:33<\/span><\/div><div class=\"audioRangeWrap\"><div class=\"hadPlayRange\"><div class=\"audioNowPos\"><\/div><\/div><\/div><\/div><p><br><\/p><p>音频测试<\/p><p>\t<\/p><div class=\"aAudioWrap\">\n\t\t<audio>\n\t\t\t<source src=\"http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171027/16993a841b6a83b8bab4c5399b0ea169.mp3\">\n\t\t\t<\/audio>\n\t\t\t<div class=\"goStop\" id=\"1509072754067\" ng-click=\"getTime('1509072754067')\">\t\t\t\t<img src=\"http://1x9x.cn/college/res/img/Audiorun.png\">\n\t\t\t<\/div>\n\t\t\t<div class=\"audioTime\"><span class=\"audioTimeStart\">0:00<\/span>/<span class=\"audioTimeAll\">0:00<\/span><\/div><div class=\"audioRangeWrap\"><div class=\"hadPlayRange\"><div class=\"audioNowPos\"><\/div><\/div><\/div>\t<\/div><p><br><\/p>","postName":"音频测试","postIsTop":0,"postIsFree":1,"postId":1810,"postTalkNum":0,"userName":"阿帅","postPeepNum":""},{"postCollection":0,"postCreationTime":"2017-10-23 13:06","postReward":"0.00","postContentApp":"","postContent":"<p><a href=\"http://192.168.1.195/user/college/tocollegelist.html\" target=\"_self\" title=\"超链接\" onclick=\"jump(this.href);\">超链接<\/a><br><\/p><p><br><\/p><p>哈哈哈哈<\/p>","postName":"超链接测试","postIsTop":0,"postIsFree":1,"postId":1809,"postTalkNum":0,"userName":"阿帅","postPeepNum":""},{"postCollection":0,"postCreationTime":"2017-10-23 11:45","postReward":"0.00","postContentApp":"","postContent":"<p><a href=\"http://192.168.1.195/user/college/tocollegelist.html\" target=\"_self\" title=\"学院首页\" onclick=\"jump(this.href);\">学院首页<\/a><br><\/p><p>测试链接1<\/p><p><a href=\"http://192.168.1.195/user/college/tocollegelist.html\" target=\"_self\" onclick=\"jump(this.href);\">首页<\/a><br><\/p><p><a href=\"http://192.168.1.195/user/college/tocollegelist.html\" target=\"_self\" title=\"输液\" onclick=\"jump(this.href);\">http://192.168.1.195/user/college/tocollegelist.html<\/a><br><\/p>","postName":"话题2","postIsTop":0,"postIsFree":1,"postId":1808,"postTalkNum":0,"userName":"阿帅","postPeepNum":""},{"postCollection":0,"postCreationTime":"2017-10-23 11:19","postReward":"0.00","postContentApp":"","postContent":"<p>帖子测试<\/p><p><br><\/p><div class=\"aAudioWrap\"><audio><source src=\"http://bjzxtest.oss-cn-beijing.aliyuncs.com/20171023/152472228e4aaf3702406bb16f41148a.mp3\"><\/audio><div class=\"goStop\" id=\"0music1508728780619\" ng-click=\"getTime('0music1508728780619')\"><img src=\"http://1x9x.cn/college/res/img/Audiorun.png\">\n <\/div><div class=\"audioTime\"><span class=\"audioTimeStart\">0:00<\/span>/<span class=\"audioTimeAll\">4:33<\/span><\/div><div class=\"audioRangeWrap\"><div class=\"hadPlayRange\"><div class=\"audioNowPos\"><\/div><\/div><\/div><\/div><p><br><\/p><p><br><a onclick=\"jump(this.href);\" href=\"http://192.168.1.195/user/college/tocollegelist.html\" target=\"_self\" title=\"学院列表\">学院列表<\/a><\/p>","postName":"帖子1","postIsTop":0,"postIsFree":1,"postId":1807,"postTalkNum":0,"userName":"阿帅","postPeepNum":""}]
         * totalPage : 1
         * isUpdated : false
         * totalRecord : 5
         * timestamp : 1511428887826
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<PostListBean> postList;

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

        public List<PostListBean> getPostList() {
            return postList;
        }

        public void setPostList(List<PostListBean> postList) {
            this.postList = postList;
        }


    }
}