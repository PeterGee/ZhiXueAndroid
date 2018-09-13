package com.risenb.studyknowledge.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.UUID;


public class FileStorage {
    private File cropIconDir;
    private File iconDir;

    public FileStorage() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File external = Environment.getExternalStorageDirectory();
            String rootDir = "/" + "zhixue";
            cropIconDir = new File(external, rootDir + "/crop");
            if (!cropIconDir.exists()) {
                cropIconDir.mkdirs();

            }
            iconDir = new File(external, rootDir + "/icon");
            if (!iconDir.exists()) {
                iconDir.mkdirs();

            }
        }
    }

    public File createCropFile() {
        String fileName = "";
        if (cropIconDir != null) {
            fileName = UUID.randomUUID().toString() + ".png";
        }
        return new File(cropIconDir, fileName);
    }

    public File createIconFile() {
        String fileName = "";
        if (iconDir != null) {
            fileName = UUID.randomUUID().toString() + ".png";
        }
        return new File(iconDir, fileName);
    }

    public static String getFileName(String url){
        //例如：url="http://img4.duitang.com/uploads/item/201404/16/20140416232354_VFTyY.jpeg";
        //获取后缀名
        int end= url.lastIndexOf(".");
        String endName = url.substring(end);//.jpeg
        int name = url.lastIndexOf("/");
        String fileName = url.substring(name+1, end);//20140416232354_VFTyY
        return TextUtils.concat(fileName,endName).toString();
    }

    /**
     * TODO<删除指定地址的文件夹>
     *
     * @return void
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists())
            return file.delete();
        return false;
    }



}
