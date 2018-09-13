package com.risenb.studyknowledge.beans.personal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by yy on 2017/11/16.
 * 会员基本信息
 */

public class AttendanceBean implements Parcelable {
    /**
     * medalTypeMig : ["http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170703/7821c2ecb99b787e4a0fd3de77963ca8.png","http://bjzx2016.img-cn-shanghai.aliyuncs.com/20170703/2423e79589eeb6f9164c27adb8f97ad9.jpg","http://mpic.tiankong.com/a45/e04/a45e0429e5828421d9d08f780dccd5ac/640.jpg","http://mpic.tiankong.com/51e/23a/51e23a4d16222397e4908d183d83ca86/640.jpg@360h","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=350105340,3522980783&fm=27&gp=0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510831786679&di=012f4ae319cce21299f1092bc1a86905&imgtype=0&src=http%3A%2F%2Fpic.47473.com%2Fupload%2Farticle%2F2016%2F01%2F0920535430.jpg"]
     * userImg : http://wx.qlogo.cn/mmopen/SCug0ESSOHib7VNsNWJYg07LQlZmIfzexmspRzhzXBfBQcia5hqvMk0mVXiavLKNtev5q4YMicjaH6iczDQno4UjmoUYK42AZ5JgS/0
     * attendAllowYn : 1
     * attendType : 2
     * attendId : 2270
     * attendCollegeId : 45
     * updatetime : 2017-09-30 11:01:20
     * attendGradeImg : http://1x9x.cn/user//res/img/vip01.png
     * attendTalkLimit : 0
     * attendUsername : 李洁老师
     */

    private String userImg;
    private int attendAllowYn;
    private int attendType;
    private int attendId;
    private int attendCollegeId;
    private String updatetime;
    private String attendGradeImg;
    private int attendTalkLimit;
    private String attendUsername;
    private List<String> medalTypeMig;
    private String attendTalkTime;
    private boolean isShow;//是否显示勾选圈
    private boolean isChecked;//是否被选中
    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public AttendanceBean() {
    }



    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getAttendAllowYn() {
        return attendAllowYn;
    }

    public void setAttendAllowYn(int attendAllowYn) {
        this.attendAllowYn = attendAllowYn;
    }

    public int getAttendType() {
        return attendType;
    }

    public void setAttendType(int attendType) {
        this.attendType = attendType;
    }

    public int getAttendId() {
        return attendId;
    }

    public void setAttendId(int attendId) {
        this.attendId = attendId;
    }

    public int getAttendCollegeId() {
        return attendCollegeId;
    }

    public void setAttendCollegeId(int attendCollegeId) {
        this.attendCollegeId = attendCollegeId;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getAttendGradeImg() {
        return attendGradeImg;
    }

    public void setAttendGradeImg(String attendGradeImg) {
        this.attendGradeImg = attendGradeImg;
    }

    public int getAttendTalkLimit() {
        return attendTalkLimit;
    }

    public void setAttendTalkLimit(int attendTalkLimit) {
        this.attendTalkLimit = attendTalkLimit;
    }

    public String getAttendUsername() {
        return attendUsername;
    }

    public void setAttendUsername(String attendUsername) {
        this.attendUsername = attendUsername;
    }

    public List<String> getMedalTypeMig() {
        return medalTypeMig;
    }

    public void setMedalTypeMig(List<String> medalTypeMig) {
        this.medalTypeMig = medalTypeMig;
    }
    public String getAttendTalkTime() {
        return attendTalkTime;
    }

    public void setAttendTalkTime(String attendTalkTime) {
        this.attendTalkTime = attendTalkTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userImg);
        dest.writeInt(attendAllowYn);
        dest.writeInt(attendType);
        dest.writeInt(attendId);
        dest.writeInt(attendCollegeId);
        dest.writeString(updatetime);
        dest.writeString(attendGradeImg);
        dest.writeInt(attendTalkLimit);
        dest.writeString(attendUsername);
        dest.writeStringList(medalTypeMig);
        dest.writeString(attendTalkTime);
    }
    protected AttendanceBean(Parcel in) {
        userImg = in.readString();
        attendAllowYn = in.readInt();
        attendType = in.readInt();
        attendId = in.readInt();
        attendCollegeId = in.readInt();
        updatetime = in.readString();
        attendGradeImg = in.readString();
        attendTalkLimit = in.readInt();
        attendUsername = in.readString();
        medalTypeMig = in.createStringArrayList();
        attendTalkTime = in.readString();
    }

    public static final Creator<AttendanceBean> CREATOR = new Creator<AttendanceBean>() {
        @Override
        public AttendanceBean createFromParcel(Parcel in) {
            return new AttendanceBean(in);
        }

        @Override
        public AttendanceBean[] newArray(int size) {
            return new AttendanceBean[size];
        }
    };
}
