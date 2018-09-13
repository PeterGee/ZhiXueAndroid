package com.risenb.studyknowledge.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wen on 2017/1/6.
 */

public class SPUtils {

    public static final String SP_KEY_LONGITUDE = "Longitude";
    public static final String SP_KEY_LATITUDE = "Latitude";

    /**
     * 万能的put方法     (能存储String/int/boolean类型的值)
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            //JDK1.7之后可以把引用数据类型转为基本数据类型
            edit.putInt(key, (int) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (boolean) value);
        }
        edit.commit();
    }

    /**
     * 获取String
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
    /**
     * 获取int
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }
    /**
     * 获取Boolean
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static String getCurrentLongitude(Context context){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(SP_KEY_LONGITUDE,"");
    }

    public static String getCurrentLatitude(Context context){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(SP_KEY_LATITUDE,"");
    }

    /**
     * 清空首选项
     *
     * */
    public static void clearData(Context context){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
