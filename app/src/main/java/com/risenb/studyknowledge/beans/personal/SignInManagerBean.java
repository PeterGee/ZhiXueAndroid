package com.risenb.studyknowledge.beans.personal;

import com.risenb.studyknowledge.beans.NetBaseBean;

import java.util.List;

/**
 * Created by yy on 2017/11/23.
 * 签到管理
 */

public class SignInManagerBean extends NetBaseBean {


    public static class DataBean {
        /**
         * searchMonth : 2017年06月
         * totalNum : [{"num":0,"day":1},{"num":0,"day":2},{"num":0,"day":3},{"num":0,"day":4},{"num":0,"day":5},{"num":0,"day":6},{"num":0,"day":7},{"num":0,"day":8},{"num":0,"day":9},{"num":0,"day":10},{"num":0,"day":11},{"num":0,"day":12},{"num":0,"day":13},{"num":0,"day":14},{"num":1,"day":15},{"num":0,"day":16},{"num":0,"day":17},{"num":2,"day":18},{"num":1,"day":19},{"num":0,"day":20},{"num":3,"day":21},{"num":0,"day":22},{"num":3,"day":23},{"num":1,"day":24},{"num":0,"day":25},{"num":0,"day":26},{"num":3,"day":27},{"num":0,"day":28},{"num":0,"day":29},{"num":1,"day":30}]
         */

        private String searchMonth;
        private List<TotalNumBean> totalNum;

        public String getSearchMonth() {
            return searchMonth;
        }

        public void setSearchMonth(String searchMonth) {
            this.searchMonth = searchMonth;
        }

        public List<TotalNumBean> getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(List<TotalNumBean> totalNum) {
            this.totalNum = totalNum;
        }

        public static class TotalNumBean {
            /**
             * num : 0
             * day : 1
             */

            private int num;
            private int day;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }
        }
    }
}
