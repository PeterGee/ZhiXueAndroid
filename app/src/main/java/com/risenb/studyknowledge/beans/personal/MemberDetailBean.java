package com.risenb.studyknowledge.beans.personal;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/11/17.
 * 会员详情
 */

public class MemberDetailBean extends NetBaseBean{

    public static class DataBean {
        /**
         * postList : [{"postJoinNum":0,"postWriterId":80,"postCreationTime":"2017-07-15 13:10:43","postContent":"<p>&nbsp;\u201c卓有成效\u201d的管理是德鲁克管理的核心主题，也是所有人的共同追求。《卓有成效的管理者》是受到最广泛欢迎的著作。然而，如何才能使企业管理卓有成效？如何使德鲁克管理思想落实到每个人的工作中？如何能够使企业和个人在变幻莫测的市场中长期成功？仍然是每一位管理者最关注的问题。<br/><\/p><p><br/><\/p><p>&nbsp; \u201c卓有成效的企业管理\u201d论坛的目的，是探讨如何将德鲁克提供的工具和方法\u2014\u2014主要是企业X射线和经典五问\u2014\u2014运用于企业实践，以及这种实践如何能够从根本上改变企业管理的价值观和信念，如何使目标与自我控制的管理成为自然而然的企业实践。<\/p><p><br/><\/p><p>&nbsp; 管理是一种实践。然而从哪里入手改变却是棘手的难题。多数人把德鲁克管理落地都把注意力放在改变观念和行为上，做出很大努力，效果却难以衡量，更难以形成根本性的变革。观念和行为的改变是管理变革的结果，企业管理层需要的是创造能够改变观念和行为的环境，而信息的应用是创造这个环境最重要的要素。<\/p><p><br/><\/p><p>&nbsp; 德鲁克说，在信息时代，很少人懂得如何利用信息。至今仍是如此。依靠今天的ERP或其他财务系统是不可能实现德鲁克管理变革的，因为它本身就在误导管理层。如何能构建真正反映企业和市场现实的信息体系，将准确和变化的动态信息转化为企业的知识，应该成为德鲁克管理落地的切入口。<\/p><p><br/><\/p><p>&nbsp; 假如我们站在这个起点上，我们需要做什么才能使这些工具和方法有效地落地？如何与企业共创真正\u201c卓有成效的企业管理\u201d？希望邀请朋友们来共同探讨。<\/p><p><br/><\/p><p>&nbsp; 我发起在这个论坛讨论四个问题：<\/p><p>&nbsp; 1. 什么是绩效？如何能够从外部来看企业的绩效？<\/p><p>&nbsp; 2. 企业X射线工具到底是什么？如何运用？<\/p><p>&nbsp; 3. 什么才是企业战略？如何应用？<\/p><p>&nbsp; 4. \u201c卓有成效的企业管理\u201d背后有哪些价值观和信念？<\/p><p><br/><\/p><p>&nbsp; 欢迎朋友们共同探讨，并提出你自己在这方面想要探讨的问题。<\/p>","postName":"卓有成效的企业管理 论坛","postPrice":0,"postIsFree":1,"postTalkNum":1}]
         * totalPage : 0
         * isUpdated : false
         * totalRecord : 0
         * attendance : {"medalTypeMig":["http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170703/7821c2ecb99b787e4a0fd3de77963ca8.png","http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170703/2423e79589eeb6f9164c27adb8f97ad9.jpg","http://mpic.tiankong.com/a45/e04/a45e0429e5828421d9d08f780dccd5ac/640.jpg","http://mpic.tiankong.com/51e/23a/51e23a4d16222397e4908d183d83ca86/640.jpg@360h","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=350105340,3522980783&fm=27&gp=0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg"],"userImg":"http://wx.qlogo.cn/mmopen/SCug0ESSOHib7VNsNWJYg07LQlZmIfzexmspRzhzXBfBQcia5hqvMk0mVXiavLKNtev5q4YMicjaH6iczDQno4UjmoUYK42AZ5JgS/0","attendAllowYn":1,"attendType":2,"attendId":2270,"attendCollegeId":45,"updatetime":"2017-09-30 11:01:20","attendGradeImg":"http://1x9x.cn/user//res/img/vip01.png","attendTalkLimit":0,"attendUsername":"李洁老师"}
         * timestamp : 1510883663676
         */

        private int totalPage;
        private boolean isUpdated;
        private int totalRecord;
        private String timestamp;
        private List<MemberTopicListBean> postList;

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

        public List<MemberTopicListBean> getPostList() {
            return postList;
        }

        public void setPostList(List<MemberTopicListBean> postList) {
            this.postList = postList;
        }

    }
}
