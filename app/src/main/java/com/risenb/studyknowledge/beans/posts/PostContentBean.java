package com.risenb.studyknowledge.beans.posts;

/**
 * Created by zhuzh on 2017/11/28.
 */

public class PostContentBean {
    /**
     * attentionNum : 5
     * userImg : http://wx.qlogo.cn/mmopen/ajNVdqHZLLCNt2nmyGqjPwZ9fJnXx5dMBzlLND35t8Ib66PlvpR1xibxkPghas6cpoFt6PBJn3wSvJ4EEpYmXZQ/0
     * postContent : <html>
     * <head></head>
     * <body>
     * <p style="text-align: left;"><strong>杨顺波院长精彩分享：</strong><br /></p>
     * <p style="text-align: left;"><strong><strong style="white-space: normal;"><span style="color: rgb(255, 0, 0);">【共19分钟，6分30秒一段，请耐心等待下一段自动播放】</span></strong></strong></p>
     * <p><iframe id="id1" height="498" width="510" src="http://player.youku.com/embed/XMzE1OTc0NTE2NA==" frameborder="0"></iframe></p>
     * <p><br /></p>
     * <p style="white-space: normal; text-align: center;"><br /></p>
     * <p style="white-space: normal; text-align: left;"><strong>杨顺波院长简介</strong></p>
     * <p style="white-space: normal; text-align: center;"><img src="http://bjzx2016.img-cn-shanghai.aliyuncs.com/20171116/3d27ab10ca530ee2165d833e848030d7.jpg" title="3d27ab10ca530ee2165d833e848030d7.jpg" alt="1510752639(1).jpg" /></p>
     * <p style="white-space: normal; text-align: center;"><img src="http://bjzx2016.img-cn-shanghai.aliyuncs.com/20171116/3d27ab10ca530ee2165d833e848030d7.jpg" title="3d27ab10ca530ee2165d833e848030d7.jpg" alt="1510752639(1).jpg" /></p>
     * <p style="white-space: normal; text-align: center;"><img src="http://bjzx2016.img-cn-shanghai.aliyuncs.com/20171116/3d27ab10ca530ee2165d833e848030d7.jpg" title="3d27ab10ca530ee2165d833e848030d7.jpg" alt="1510752639(1).jpg" /></p>
     * <p style="white-space: normal; text-align: center;"><img src="http://bjzx2016.img-cn-shanghai.aliyuncs.com/20171116/3d27ab10ca530ee2165d833e848030d7.jpg" title="3d27ab10ca530ee2165d833e848030d7.jpg" alt="1510752639(1).jpg" /></p>
     * <p style="white-space: normal; text-align: center;">彼得&middot;德鲁克管理学院院长、德友汇创始人</p>
     * <p style="white-space: normal; text-align: center;">系统学管理倡导者</p>
     * <p style="white-space: normal;"><br /></p>
     * <p style="white-space: normal;">以传播“<strong>真正的管理、负责任的管理、有价值的管理</strong>”为己任。学习的领域包括博雅管理（MLA）、创新与创业精神、企业战略思考与实践、组织发展等。</p>
     * <p><br /></p>
     * </body>
     * </html>
     * userName : 汪洋大海
     */

    private int attentionNum;//关注数
    private String userImg;
    private String postContent;
    private String userName;
    private int postReward;//赏金
    private int postPeepNum;//偷看人数

    public int getPostPeepNum() {
        return postPeepNum;
    }

    public void setPostPeepNum(int postPeepNum) {
        this.postPeepNum = postPeepNum;
    }

    public int getPostReward() {
        return postReward;
    }

    public void setPostReward(int postReward) {
        this.postReward = postReward;
    }



    public int getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(int attentionNum) {
        this.attentionNum = attentionNum;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
