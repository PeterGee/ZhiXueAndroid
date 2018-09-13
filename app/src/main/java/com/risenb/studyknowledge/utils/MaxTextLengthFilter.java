package com.risenb.studyknowledge.utils;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yy on 2017/4/17.
 */

public class MaxTextLengthFilter implements InputFilter {
    private  String tips;
    private int maxLength;

    public MaxTextLengthFilter(int maxLength, String tips) {
        this.maxLength = maxLength;
        this.tips=tips;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (!filterEmoje(source)) return "";
        int mlength = getLength(dest.subSequence(dstart, dend).toString());// 修改字符串的长度
        int dlength = getLength(dest.toString());// 已有字符串的长度
        int slength = getLength(source.subSequence(start, end).toString());// 要增加的字符串的长度
        int keep = maxLength - (dlength - mlength);// 还差多少字符到最大长度

        if (keep <= 0) {
            // 已经到达最大长度
            ToastUtils.showToast(tips);
            return "";
        } else if (keep >= slength) {
            // 还没到达最大长度
            return null;
        } else {
            // 超出最大长度
            int tmp = 0;
            int index;
            for (index = start; index <= end; index++) {
                tmp += 1;
                if (tmp > keep) {
                    break;
                }
            }
            ToastUtils.showToast(tips);
            return source.subSequence(start, index);
        }
    }

    public boolean filterEmoje(CharSequence source){
        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(source);
        if(matcher.find()){
            ToastUtils.showToast("不允许输入emoji");
            return false;
        }
        return true;
    }



    /**
     * 判断字符串是否为空或空串
     *
     * @param str
     *            待判断的字符串
     * @return true：字符串为空或空串
     */
    public static boolean isNull(final String str) {
        if (null == str || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取字符串长度
     *
     * @param str
     *            字符串
     * @return 字符串长度
     */
    public static int getLength(final String str) {
        if (isNull(str)) {
            return 0;
        }
        int len = str.length();
        return len;
    }
}
