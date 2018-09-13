package com.risenb.studyknowledge.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuzh on 2017/4/18.
 */
public class PhoneUtils {
    public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }

    public static boolean isPwd(String pwd) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        //假设有一个字符串
        for (int i = 0; i < pwd.length(); i++) { //循环遍历字符串
            if (Character.isDigit(pwd.charAt(i))) {     //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }
            if (Character.isLetter(pwd.charAt(i))) {   //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        if (isDigit && isLetter) {
            return true;
        } else {
            return false;
        }
    }


    //验证邮箱
    public static boolean isEmail(String strEmail) {
        String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        if (m.matches()) {
            return true;
        }else {
            return false;
        }
    }
}
