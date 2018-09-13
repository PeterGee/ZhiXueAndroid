package com.risenb.studyknowledge.beans.institution;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yy on 2017/11/23.
 * 意见反馈对象
 */

public class AdviceListBean implements Parcelable{
    /**
     * adviceContent : 阿大是大非的
     * adviceId : 118
     * adviceCreationTime : 2017-06-19 09:10
     * adviceReadyn : 1
     * userPhone : 18671562516
     * userEmail : 183866896@qq.com
     * userName : 阿帅
     * adviceType : 0
     */

    private String adviceContent;
    private int adviceId;
    private String adviceCreationTime;
    private int adviceReadyn;
    private String userPhone;
    private String userEmail;
    private String userName;
    private int adviceType;

    public AdviceListBean() {
    }

    public String getAdviceContent() {
        return adviceContent;
    }

    public void setAdviceContent(String adviceContent) {
        this.adviceContent = adviceContent;
    }

    public int getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(int adviceId) {
        this.adviceId = adviceId;
    }

    public String getAdviceCreationTime() {
        return adviceCreationTime;
    }

    public void setAdviceCreationTime(String adviceCreationTime) {
        this.adviceCreationTime = adviceCreationTime;
    }

    public int getAdviceReadyn() {
        return adviceReadyn;
    }

    public void setAdviceReadyn(int adviceReadyn) {
        this.adviceReadyn = adviceReadyn;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAdviceType() {
        return adviceType;
    }

    public void setAdviceType(int adviceType) {
        this.adviceType = adviceType;
    }
    protected AdviceListBean(Parcel in) {
        adviceContent = in.readString();
        adviceId = in.readInt();
        adviceCreationTime = in.readString();
        adviceReadyn = in.readInt();
        userPhone = in.readString();
        userEmail = in.readString();
        userName = in.readString();
        adviceType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adviceContent);
        dest.writeInt(adviceId);
        dest.writeString(adviceCreationTime);
        dest.writeInt(adviceReadyn);
        dest.writeString(userPhone);
        dest.writeString(userEmail);
        dest.writeString(userName);
        dest.writeInt(adviceType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdviceListBean> CREATOR = new Creator<AdviceListBean>() {
        @Override
        public AdviceListBean createFromParcel(Parcel in) {
            return new AdviceListBean(in);
        }

        @Override
        public AdviceListBean[] newArray(int size) {
            return new AdviceListBean[size];
        }
    };

}
