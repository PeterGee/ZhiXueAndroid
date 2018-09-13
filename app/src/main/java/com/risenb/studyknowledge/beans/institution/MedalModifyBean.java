package com.risenb.studyknowledge.beans.institution;

import com.risenb.studyknowledge.beans.NetBaseBean;

/**
 * Created by yy on 2017/11/28.
 * 勋章修改
 */

public class MedalModifyBean extends NetBaseBean{

    public static class DataBean {
        /**
         * medalType : {"medalTypeId":165,"medalTypeMig":"h","medalTypeInfo":"哈","medalTypeName":"哈"}
         */

        private MedalInfoBean medalType;

        public MedalInfoBean getMedalType() {
            return medalType;
        }

        public void setMedalType(MedalInfoBean medalType) {
            this.medalType = medalType;
        }

    }
}
