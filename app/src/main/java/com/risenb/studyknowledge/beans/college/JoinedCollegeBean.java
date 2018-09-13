package com.risenb.studyknowledge.beans.college;

import java.util.List;

/**
 * Created by yjyvi on 2017/12/26.
 */

public class JoinedCollegeBean {


    /**
     * status : true
     * errorCode :
     * errorMsg :
     * data : {"data":[{"collegeLogo":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/e7f8c650-8f60-4b37-b100-387f8f5e525f.png","collegeName":"干活呵呵要学就学","collegeBanner":"碎片化学习作为一种获取知识交流情感的方式，已经成为人们日常生活中不可缺少的一项重要内容，尤其是在二十一世纪这个知识经济时代，自主学习已是人们不断满足自身需要、充实原有知识结构，获取有价值的信息，并最终取得成功的法宝。","collegeId":58},{"collegeLogo":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/e7f8c650-8f60-4b37-b100-387f8f5e525f.png","collegeName":"干活呵呵要学就学","collegeBanner":"碎片化学习作为一种获取知识交流情感的方式，已经成为人们日常生活中不可缺少的一项重要内容，尤其是在二十一世纪这个知识经济时代，自主学习已是人们不断满足自身需要、充实原有知识结构，获取有价值的信息，并最终取得成功的法宝。","collegeId":58},{"collegeLogo":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/20170613/deec5ad273df103a1aaae549bb572fc5.png","collegeName":"互动派小课堂","collegeBanner":"互动派《管理的沟通与决策》课前学习资料！","collegeId":60},{"collegeLogo":"http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/e7f8c650-8f60-4b37-b100-387f8f5e525f.png","collegeName":"干活呵呵要学就学","collegeBanner":"碎片化学习作为一种获取知识交流情感的方式，已经成为人们日常生活中不可缺少的一项重要内容，尤其是在二十一世纪这个知识经济时代，自主学习已是人们不断满足自身需要、充实原有知识结构，获取有价值的信息，并最终取得成功的法宝。","collegeId":58},{"collegeLogo":"http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170629/41f97914ace737be98d50c0c733578ea.jpg","collegeName":"儿童口腔学组","collegeBanner":"欢迎来到儿童口腔学组，这里是专业的儿童口腔信息、技术、机会的交流中心！","collegeId":66},{"collegeLogo":"http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170814/80b9834cdd192c8aa2fbd8ec76f3f258.jpg","collegeName":"博雅学院","collegeBanner":"管理：从控制到释放                                                               德鲁克在90岁的时候被问及如何总结他一生的贡献时提及，他把管理（management）这门学科作为一门真正的博雅艺术（Liberal Art，亦可译作\u201c自由技艺\u201d）。                                                             什么是 Liberal Art？                                                     Liberal Art 一词源自公元前的希腊和罗马的时代，泛指\u201c自由公民\u201d所从事的职业，包括音乐、美术、文学、设计等，以独立的人格和自由的心灵为前提条件的工作。相对于奴隶所的劳作，人更乐于从事 这类创造性的工作，这类工作能够发挥人独特的优势，让人在工作中获得满足感和成就感。工作过程本身成为了最好的激励和回报。显然，人类要维持生存和发展只完成这些\u201c创造性\u201d的工作是远远不够的，人类还必须完成与之相匹配的那些单调而辛苦的劳作。古希腊","collegeId":62},{"collegeLogo":"http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170814/80b9834cdd192c8aa2fbd8ec76f3f258.jpg","collegeName":"博雅学院","collegeBanner":"管理：从控制到释放                                                               德鲁克在90岁的时候被问及如何总结他一生的贡献时提及，他把管理（management）这门学科作为一门真正的博雅艺术（Liberal Art，亦可译作\u201c自由技艺\u201d）。                                                             什么是 Liberal Art？                                                     Liberal Art 一词源自公元前的希腊和罗马的时代，泛指\u201c自由公民\u201d所从事的职业，包括音乐、美术、文学、设计等，以独立的人格和自由的心灵为前提条件的工作。相对于奴隶所的劳作，人更乐于从事 这类创造性的工作，这类工作能够发挥人独特的优势，让人在工作中获得满足感和成就感。工作过程本身成为了最好的激励和回报。显然，人类要维持生存和发展只完成这些\u201c创造性\u201d的工作是远远不够的，人类还必须完成与之相匹配的那些单调而辛苦的劳作。古希腊","collegeId":62},{"collegeLogo":"http://1x9x.cn/college/res/img/logo_20161205174614.png","collegeName":"浩浩学院","collegeBanner":null,"collegeId":69},{"collegeLogo":"http://1x9x.cn/college/res/img/logo_20161205174614.png","collegeName":"武汉大学思博学院","collegeBanner":null,"collegeId":73}]}
     */

    private boolean status;
    private String errorCode;
    private String errorMsg;
    private DataBeanX data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * collegeLogo : http://bjzx2016.oss-cn-shanghai.aliyuncs.com/college/upload/image/e7f8c650-8f60-4b37-b100-387f8f5e525f.png
             * collegeName : 干活呵呵要学就学
             * collegeBanner : 碎片化学习作为一种获取知识交流情感的方式，已经成为人们日常生活中不可缺少的一项重要内容，尤其是在二十一世纪这个知识经济时代，自主学习已是人们不断满足自身需要、充实原有知识结构，获取有价值的信息，并最终取得成功的法宝。
             * collegeId : 58
             */

            private String collegeLogo;
            private String collegeName;
            private String collegeBanner;
            private int collegeId;

            public String getCollegeLogo() {
                return collegeLogo;
            }

            public void setCollegeLogo(String collegeLogo) {
                this.collegeLogo = collegeLogo;
            }

            public String getCollegeName() {
                return collegeName;
            }

            public void setCollegeName(String collegeName) {
                this.collegeName = collegeName;
            }

            public String getCollegeBanner() {
                return collegeBanner;
            }

            public void setCollegeBanner(String collegeBanner) {
                this.collegeBanner = collegeBanner;
            }

            public int getCollegeId() {
                return collegeId;
            }

            public void setCollegeId(int collegeId) {
                this.collegeId = collegeId;
            }
        }
    }
}
