package com.risenb.studyknowledge.beans.institution;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yy on 2017/11/24.
 * 公告信息
 */

public class NoticeListBean implements Parcelable{
    /**
     * noticeCreationtime : 2017-09-26 09:09
     * noticeInfo : 北京知学科技有限公司，碎片化学习，让你学出精彩的明天
     * noticeTitle : 平台简介（请勿删除，勿修改！！！）
     * noticeId : 0
     */

    private String noticeCreationtime;
    private String noticeInfo;
    private String noticeTitle;
    private int noticeId;

    public NoticeListBean() {
    }

    public String getNoticeCreationtime() {
        return noticeCreationtime;
    }

    public void setNoticeCreationtime(String noticeCreationtime) {
        this.noticeCreationtime = noticeCreationtime;
    }

    public String getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(String noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }
    protected NoticeListBean(Parcel in) {
        noticeCreationtime = in.readString();
        noticeInfo = in.readString();
        noticeTitle = in.readString();
        noticeId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noticeCreationtime);
        dest.writeString(noticeInfo);
        dest.writeString(noticeTitle);
        dest.writeInt(noticeId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NoticeListBean> CREATOR = new Creator<NoticeListBean>() {
        @Override
        public NoticeListBean createFromParcel(Parcel in) {
            return new NoticeListBean(in);
        }

        @Override
        public NoticeListBean[] newArray(int size) {
            return new NoticeListBean[size];
        }
    };
}
