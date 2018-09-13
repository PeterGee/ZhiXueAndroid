package com.risenb.studyknowledge.beans.personal;

/**
 * Created by yy on 2017/9/21.
 * 会员等级bean
 */

public class MemberLevelBean {
    /**
     * userCollegegradeName : VIP1
     * userCollegegradeId : 170
     */

    private String userCollegegradeName;
    private String  userCollegegradeId;
    private boolean isChecked;

    public MemberLevelBean() {
    }
    public String getUserCollegegradeName() {
        return userCollegegradeName;
    }

    public void setUserCollegegradeName(String userCollegegradeName) {
        this.userCollegegradeName = userCollegegradeName;
    }

    public String getUserCollegegradeId() {
        return userCollegegradeId;
    }

    public void setUserCollegegradeId(String userCollegegradeId) {
        this.userCollegegradeId = userCollegegradeId;
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
