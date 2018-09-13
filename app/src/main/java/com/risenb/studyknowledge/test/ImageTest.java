package com.risenb.studyknowledge.test;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.mutils.camera.CameraCallBack;
import com.lidroid.mutils.camera.ImgUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.pop.PopIco;
import com.risenb.studyknowledge.ui.BaseUI;

/**
 * @author 作者: wangjian
 * @version 创建时间：2015年7月14日 下午6:54:45
 * @类说明
 */
public class ImageTest extends BaseUI {

    /**
     * 头像
     */
    @ViewInject(R.id.back)
    private ImageView back;

    private PopIco popIco;
    private ImgUtils imgUtils;

    @Override
    protected void back() {

    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {
        Intent intentUtils = new Intent();
        // aspectX aspectY 是宽高的比例
        intentUtils.putExtra("aspectX", 1);
        intentUtils.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intentUtils.putExtra("outputX", 300);
        intentUtils.putExtra("outputY", 300);
        imgUtils = new ImgUtils(getActivity(), true, intentUtils);
        imgUtils.setCameraCallBack(new CameraCallBack() {
            @Override
            public void onCameraCallBack(String path) {
                File file = new File(path);
                MediaType mediaType = MediaType.parse("application/octet-stream");
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.addFormDataPart("ico", file.getName(), RequestBody.create(mediaType, new File(path)));
            }
        });
        popIco = new PopIco(back, getActivity());
        popIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_pop_ico_camera:
                        imgUtils.openCamera();
                        break;
                    case R.id.tv_pop_ico_photo:
                        imgUtils.openPhotoAlbum();
                        break;
                }
            }
        });
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @Override
    protected void onActivityResult(int thisCode, int toCode, Intent data) {
        super.onActivityResult(thisCode, toCode, data);
        imgUtils.onActivityResult(thisCode, toCode, data);
    }

}
