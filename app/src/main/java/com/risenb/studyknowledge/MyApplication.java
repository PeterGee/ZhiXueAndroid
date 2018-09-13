package com.risenb.studyknowledge;

import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.mutils.MUtils;
import com.lidroid.mutils.gif.DimenUtils;
import com.lidroid.mutils.utils.Log;
import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.beans.login.UserInfoBean;
import com.risenb.studyknowledge.db.ChatHelper;
import com.risenb.studyknowledge.utils.ErrorUtils;
import com.risenb.studyknowledge.utils.MyConfig;
import com.risenb.studyknowledge.utils.NetworkUtils;

public class MyApplication extends MultiDexApplication {
    /**
     * 根目录
     */
    public static String path;

    private int webSize = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        path = MUtils.getMUtils().getPath(this);
        com.lidroid.mutils.MUtils.getMUtils().machineInformation();
        Log.setDebug(!MyConfig.SIGN.equals(MUtils.getMUtils().getSignature()));
        Log.e("path = " + path);
        Log.e(MUtils.getMUtils().getSignature());
        NetworkUtils.getNetworkUtils().setApplication(this);
       // ImageLoaderUtils.initImageLoader(this);
        ErrorUtils.info();
        Utils.getUtils().setCapbWidth(R.dimen.dm088);
        com.lidroid.mutils.utils.Utils.getUtils().info(R.layout.common_waiting_dialog, R.id.capb_dialog, R.id
                .tv_dialog, R.style.custom_dialog_style);
        ChatHelper.getChatHelper(this);
    }

    public void setWebSize(int webSize) {
        this.webSize = webSize;
    }

    public int getWebSize() {
        return webSize;
    }

    public int getWebSize(int dimen) {
        return (int) (dimen * webSize / DimenUtils.WIDTH);
    }

    public String getPath() {
        return path;
    }

    public String getC() {
        return MUtils.getMUtils().getShared("c");
    }

    public void setC(String c) {
        MUtils.getMUtils().setShared("c", c);
    }

    /**
     * 包含用户信息和用户加入的学院信息
     * @param userInfoBean
     */
    public static void setUserInfoBean(UserInfoBean.DataBean userInfoBean) {
        MUtils.getMUtils().setShared("UserInfoBean", JSONObject.toJSONString(userInfoBean));
    }
    public static UserInfoBean.DataBean getUserInfoBean() {
        String str = MUtils.getMUtils().getShared("UserInfoBean");
        if (!TextUtils.isEmpty(str)) {
            try {
                return JSONObject.parseObject(str, UserInfoBean.DataBean.class);
            } catch (Exception e) {

            }
        }
        return null;
    }
    public static void cleanUserInfo(){
        UserInfoBean.DataBean userInfoBean = null;
        MUtils.getMUtils().setShared("UserInfoBean", JSONObject.toJSONString(userInfoBean));
    }
    public boolean getOne() {
        String tag = "one" + Utils.getUtils().getVersionCode(getApplicationContext());
        boolean isOne = "".equals(MUtils.getMUtils().getShared(tag));
        MUtils.getMUtils().setShared(tag, "false");
        return isOne;
    }
}
