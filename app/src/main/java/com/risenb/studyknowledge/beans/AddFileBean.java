package com.risenb.studyknowledge.beans;

import java.util.List;

/**
 * Created by yy on 2017/10/10.
 * 文件上传
 */

public class AddFileBean extends NetBaseBean{

    public static class DataBean {
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;

    }
}
