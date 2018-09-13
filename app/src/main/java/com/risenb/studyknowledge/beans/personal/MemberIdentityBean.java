package com.risenb.studyknowledge.beans.personal;

/**
 * Created by yy on 2017/9/21.
 * 会员身份bean
 */

public class MemberIdentityBean {
    private String identity;
    private boolean isChecked;

    public MemberIdentityBean() {
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
