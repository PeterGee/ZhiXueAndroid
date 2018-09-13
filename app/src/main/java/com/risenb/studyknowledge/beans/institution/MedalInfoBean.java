package com.risenb.studyknowledge.beans.institution;

import android.os.Parcel;
import android.os.Parcelable;

import com.risenb.studyknowledge.beans.NetBaseBean;

/**
 * Created by yy on 2017/11/27.
 */

public class MedalInfoBean implements Parcelable{
    /**
     * medalTypeId : 145
     * medalTypeMig : http://mpic.tiankong.com/a45/e04/a45e0429e5828421d9d08f780dccd5ac/640.jpg
     * medalTypeInfo : 被赞数超过200
     * medalTypeName : 宝宝最赞
     */

    private int medalTypeId;
    private String medalTypeMig;
    private String medalTypeInfo;
    private String medalTypeName;
    private boolean isChecked;//是否选中

    public MedalInfoBean() {
    }

    public int getMedalTypeId() {
        return medalTypeId;
    }

    public void setMedalTypeId(int medalTypeId) {
        this.medalTypeId = medalTypeId;
    }

    public String getMedalTypeMig() {
        return medalTypeMig;
    }

    public void setMedalTypeMig(String medalTypeMig) {
        this.medalTypeMig = medalTypeMig;
    }

    public String getMedalTypeInfo() {
        return medalTypeInfo;
    }

    public void setMedalTypeInfo(String medalTypeInfo) {
        this.medalTypeInfo = medalTypeInfo;
    }

    public String getMedalTypeName() {
        return medalTypeName;
    }

    public void setMedalTypeName(String medalTypeName) {
        this.medalTypeName = medalTypeName;
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(medalTypeId);
        dest.writeString(medalTypeMig);
        dest.writeString(medalTypeInfo);
        dest.writeString(medalTypeName);
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }
    protected MedalInfoBean(Parcel in) {
        medalTypeId = in.readInt();
        medalTypeMig = in.readString();
        medalTypeInfo = in.readString();
        medalTypeName = in.readString();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<MedalInfoBean> CREATOR = new Creator<MedalInfoBean>() {
        @Override
        public MedalInfoBean createFromParcel(Parcel in) {
            return new MedalInfoBean(in);
        }

        @Override
        public MedalInfoBean[] newArray(int size) {
            return new MedalInfoBean[size];
        }
    };
}
