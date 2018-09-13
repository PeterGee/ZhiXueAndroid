package com.risenb.studyknowledge.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.login.LoginUI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yy on 2017/5/5.
 */

public class ToolUtils {

    /**
     * 获取图片全路径
     *
     * @param mContext
     * @param imageUrl
     * @return
     */
    public static String getPicLoad(Context mContext, String imageUrl) {
//        String picLoad = mContext.getResources().getString(R.string.service_host_image).concat(imageUrl);
//        return picLoad;

        if (imageUrl != null) {
            if (imageUrl.startsWith("http")) {
                return imageUrl;
            } else {
                return mContext.getString(R.string.service_host_image).concat(imageUrl);
            }
        } else {
            return "";
        }
    }

    /**
     *
     * @param context
     * @param imageView
     * @param delWidth  要减去的宽度
     * @param picWidth  图片尺寸宽度
     * @param picHeigth  图片尺寸高度
     */
    public static void setImageWidthHeigth(Context context,ImageView imageView,int delWidth,int picWidth,int picHeigth){
        //获取iv_dynamic_pic_one在屏幕中显示的宽度
        int width = ((Activity)context).getWindowManager().getDefaultDisplay().getWidth()-delWidth;
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width =width;
        params.height = picHeigth*width/picWidth;
        imageView.setLayoutParams(params);
    }
    public static void setImageWidthHeigth(ImageView imageView,int width,int picWidth,int picHeigth){
        //获取iv_dynamic_pic_one在屏幕中显示的宽度
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width =width;
        params.height = picHeigth*width/picWidth;
        imageView.setLayoutParams(params);
    }

    /**
     * 搜索关键字标红
     *
     * @param title
     * @param keyword
     * @return
     */
    public static SpannableString matcherSearchTitle(Context mContext, String title, String keyword) {
        SpannableString s = new SpannableString(title);
        Pattern p = Pattern.compile(keyword);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.red)), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    public static String imgStyleHtml(String html){
//        String imgStyle = "<style> img{width:100%; height:auto;}iframe{width:100%; height:auto;}</style>";
        String imgStyle = "<style> img{width:auto; height:auto;}iframe{width:auto; height:auto;}</style>";
        String img_html;
        if(TextUtils.isEmpty(html)){
            img_html ="";
        }else{
            img_html = html;
        }
        img_html = imgStyle+img_html;
        return img_html;
    }
    /**
     * 使用正则表达式 把html标签中的style属性全部替换成""
     */
    public static String replaceImgStyle(String html){
        String reg = "style=\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(html);
        return matcher.replaceAll("");
    }

    /**
     * 去掉html中的换行，缩进等
     * @param html
     * @return
     */
    public static String replaceEnter(String html){
        String reg = "\\\\s*|\\t|\\r|\\n";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(html);
        return matcher.replaceAll("");
    }

    /**
     * 去掉html中的所有的标签
     * @param html
     * @return
     */
    public static String replaceTag(String html){
        String reg = "<[^>]*>";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(html);
        return matcher.replaceAll("");
    }

    public static String getNoEmptyString(String text){
        if(TextUtils.isEmpty(text)){
            return "";
        }else {
            return text;
        }
    }
    public static String getJS(){
//        String js ="<script>\n" +
//                "function changeSize(height)\n" + "{\n" + "document.getElementsByTagName(\"iframe\")[0].style.height=height+\"px\";\n"+
//                "}\n" + "</script>\n";
        String js ="<script>\n" +
                "function changeSize(height)\n" +
                "{\n" +
                "document.getElementsByTagName(\"iframe\")[0].style.height=height+\"px\";\n" +
                "document.getElementsByTagName(\"iframe\")[0].style.width=100%;\n" +
                "}\n" +
                "</script>\n";
        return js;
    }
    /**
     * 判断网络是否连接
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            //如果仅仅是用来判断网络连接
            //则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void startLogin(final Context context){
        CustomDialogUtils.getInstance().createCustomDialog(context, context.getResources()
                .getString(R.string.login_out),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, LoginUI.class));
                    }
                });
    }
    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }


    /**
     * 改变字体颜色
     *
     * @param textView
     * @param text
     * @param start
     * @param end
     */
    public static void changeTextColor(TextView textView, String text, int start, int end) {
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        //设置指定位置文字的颜色
        style.setSpan(new ForegroundColorSpan(textView.getContext().getResources().getColor(R.color.blue)), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(style);
    }
}
