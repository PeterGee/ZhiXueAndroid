package com.risenb.studyknowledge.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.io.File;

/**
 * Created by yy on 2017/10/17.
 * 上传图片
 */

public class AddImageUtils {
    private static Uri imageUri;//原图保存地址
    private static Uri outputUri;//裁剪后地址
    private static String imagePath;
    public static final int REQUEST_PICK_IMAGE = 1; //相册选取
    public static final int REQUEST_CAPTURE = 2;  //拍照
    public static final int REQUEST_PICTURE_CUT = 3;  //剪裁图片
    /**
     * 相册的权限请求
     */
    public static void requestPhotoAlbum(final Context context) {
        if (PermissionsUtil.hasPermission((Activity)context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            selectFromAlbum(context);
        } else {
            PermissionsUtil.requestPermission((Activity)context, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                    selectFromAlbum(context);
                }
                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    Toast.makeText((Activity)context, "用户拒绝了读写文件", Toast.LENGTH_LONG).show();
                }
            }, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }
    /**
     * 拍照的权限请求
     */
    public static void requestCamera(final Context context) {
        if (PermissionsUtil.hasPermission((Activity)context, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            openCamera(context);
        } else {
            PermissionsUtil.requestPermission((Activity)context, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                    openCamera(context);
                }
                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    Toast.makeText((Activity)context, "用户拒绝了访问摄像头", Toast.LENGTH_LONG).show();
                }
            }, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }
    /**
     * 从相册选择
     */
    public static void selectFromAlbum(Context context) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        ((Activity)context).startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }
    /**
     * 打开系统相机
     */
    public static void openCamera(Context context) {
        File file = new FileStorage().createIconFile();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(context, "com.risenb.studyknowledge.provider", file);//通过FileProvider创建一个content类型的Uri
        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        ((Activity)context).startActivityForResult(intent, REQUEST_CAPTURE);
    }
    /**
     * 裁剪
     */
    public static Uri cropPhoto(Context context) {
        File file = new FileStorage().createCropFile();
        //缩略图保存地址
        outputUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 480);
        intent.putExtra("outputY", 480);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        ((Activity)context).startActivityForResult(intent, REQUEST_PICTURE_CUT);
        return outputUri;
    }
    /**
     * 从相册选择后的操作
     * @param data
     */
    @TargetApi(19)
    public static void handleImageOnKitKat(Intent data, Context context) {
        imagePath = null;
        imageUri = data.getData();

        if (DocumentsContract.isDocumentUri(context, imageUri)) {
            //如果是document类型的uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(imageUri);
            if ("com.android.providers.media.documents".equals(imageUri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context,MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection);
            } else if ("com.android.downloads.documents".equals(imageUri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(context,contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            //如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(context,imageUri, null);
        } else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            //如果是file类型的Uri,直接获取图片路径即可
            imagePath = imageUri.getPath();
        }

    }

    public static void handleImageBeforeKitKat(Intent intent,Context context) {
        imageUri = intent.getData();
        imagePath = getImagePath(context,imageUri, null);

    }

    private static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        //通过Uri和selection老获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
