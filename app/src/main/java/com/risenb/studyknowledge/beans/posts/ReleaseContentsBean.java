package com.risenb.studyknowledge.beans.posts;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zhuzh on 2017/11/14.
 */

public class ReleaseContentsBean implements MultiItemEntity, Parcelable {
    public static final int TEXT = 0;
    public static final int IMG = 1;
    public static final int RECORD = 2;

    private String content;
    private int type;//type  0--文字；1--图片;  2--录音

    protected ReleaseContentsBean(Parcel in) {
        content = in.readString();
        type = in.readInt();
        timeLength = in.readLong();
        strLength = in.readString();
    }

    public static final Creator<ReleaseContentsBean> CREATOR = new Creator<ReleaseContentsBean>() {
        @Override
        public ReleaseContentsBean createFromParcel(Parcel in) {
            return new ReleaseContentsBean(in);
        }

        @Override
        public ReleaseContentsBean[] newArray(int size) {
            return new ReleaseContentsBean[size];
        }
    };

    public long getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(long timeLength) {
        this.timeLength = timeLength;
    }

    private long timeLength; //录音时间
    private String strLength;

    public String getStrLength() {
        return strLength;
    }

    public void setStrLength(String strLength) {
        this.strLength = strLength;
    }

    public ReleaseContentsBean(String content, int type, String strLength, long timeLength) {
        this.content = content;
        this.type = type;
        this.strLength = strLength;
        this.timeLength = timeLength;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int itemType) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public String toString() {
        return "ReleaseContentsBean{" +
                "content='" + content + '\'' +
                ", itemType=" + type +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
        parcel.writeInt(type);
        parcel.writeLong(timeLength);
        parcel.writeString(strLength);
    }
}
