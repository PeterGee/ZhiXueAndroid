package com.risenb.studyknowledge.ui.institution;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.MedalInfoBean;
import com.risenb.studyknowledge.pop.PopIco;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.FileAddP;
import com.risenb.studyknowledge.ui.personal.MemberManagerUI;
import com.risenb.studyknowledge.ui.personal.MemberSettingUI;
import com.risenb.studyknowledge.utils.AddImageUtils;
import com.risenb.studyknowledge.utils.InputMethodUtils;
import com.risenb.studyknowledge.utils.MaxTextLengthFilter;
import com.risenb.studyknowledge.utils.StatusBarUtils;

import java.io.File;

import glideimageview.GlideImageLoader;


/**
 * Created by yy on 2017/10/10.
 * 新增勋章
 */
@ContentView(R.layout.ui_add_medal)
public class AddMedalUI extends BaseUI implements AddMedalP.AddModifyMedalFace, FileAddP.FileAddFace {
    @ViewInject(R.id.iv_add_pic)
    ImageView iv_add_pic;//添加勋章图片
    @ViewInject(R.id.et_medal_title)
    EditText et_medal_title;//勋章标题
    @ViewInject(R.id.et_medal_info)
    EditText et_medal_info;//勋章描述
    @ViewInject(R.id.tv_medalInfo_length)
    TextView tv_medalInfo_length;//字数监听
    @ViewInject(R.id.ll_title)
    LinearLayout ll_title;

    private PopIco popIco;
    private Uri mOutputUri;
    private String C="1643";
    private String CollegeId="45";
    private String medalTypeId;
    private String medalImg="";
    private AddMedalP mAddMedalP;
    private FileAddP mFileAddP;
    private String mMedalType;
    private MedalInfoBean mMedalInfoBean;
    public static final int RESULT_CODE=10;

    @Override
    protected void back() {
        finish();
    }
    @Override
    protected boolean isStatusBar() {
        return true;
    }
    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        if(TextUtils.equals(mMedalType,MedalManagerUI.MEDAL_ADD)){
            setTitle(getResources().getString(R.string.add_medal));//新增勋章
        }else {
            setTitle(getResources().getString(R.string.edit_medal));//编辑勋章
        }

        mMedalInfoBean = getIntent().getParcelableExtra(MedalManagerUI.MEDAL_INFO);
        medalTypeId=mMedalInfoBean==null?"":mMedalInfoBean.getMedalTypeId()+"";
        mMedalType = getIntent().getStringExtra(MedalManagerUI.MEDAL_TYPE);//区分是编辑还是新增

        et_medal_title.setFilters(new InputFilter[]{new MaxTextLengthFilter(20,getResources()
                .getString(R.string.et_length_medalTitle_tip))});
        et_medal_info.setFilters(new InputFilter[]{new MaxTextLengthFilter(30,getResources()
                .getString(R.string.et_length_medalInfo_tip))});

        //编辑文字改变的监听
        et_medal_info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = et_medal_info.getText().toString();
                tv_medalInfo_length.setText(content.length()+"/30");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        init();//编辑修改页面初始化
    }
    @Override
    protected void prepareData() {
        mAddMedalP = new AddMedalP(this, getActivity());
        mFileAddP = new FileAddP(this, getActivity());
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.iv_add_pic,R.id.tv_setting_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_add_pic://上传勋章图片
                InputMethodUtils.hideInputMethod(view);//隐藏软键盘
                addPic();
                break;
            case R.id.tv_setting_save://保存
                mAddMedalP.addModifyMedal(C,CollegeId,medalTypeId);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化
     */
    public void init(){
        if(TextUtils.equals(mMedalType,MedalManagerUI.MEDAL_MODIFY)){
            et_medal_info.setText(mMedalInfoBean.getMedalTypeInfo());
            et_medal_title.setText(mMedalInfoBean.getMedalTypeName());
            et_medal_title.requestFocus();
            et_medal_title.setSelection(mMedalInfoBean.getMedalTypeName().length());//将光标移至文字末尾
            medalImg=mMedalInfoBean.getMedalTypeMig();
            GlideImageLoader.create(iv_add_pic).loadImage(medalImg,R.mipmap.unify_image_ing);
        }
    }
    /**
     * 上传图片
     */
    private void addPic(){
        popIco = new PopIco(iv_add_pic, getActivity());
        popIco.showAsDropDown(ll_title);
        popIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_pop_ico_camera:
                        AddImageUtils.requestCamera(AddMedalUI.this);
                        break;
                    case R.id.tv_pop_ico_photo:
                        AddImageUtils.requestPhotoAlbum(AddMedalUI.this);
                        break;
                }
            }
        });
    }


    //相机和相册选择图片的回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode){
                case AddImageUtils.REQUEST_PICK_IMAGE://从相册选择
                    if (data != null) {
                        if (Build.VERSION.SDK_INT >= 19) {
                            AddImageUtils.handleImageOnKitKat(data,AddMedalUI.this);
                        } else {
                            AddImageUtils.handleImageBeforeKitKat(data,AddMedalUI.this);
                        }
                        mOutputUri=AddImageUtils.cropPhoto(AddMedalUI.this);
                    }
                    break;
                case AddImageUtils.REQUEST_CAPTURE://拍照
                    mOutputUri = AddImageUtils.cropPhoto(AddMedalUI.this);
                    break;
                case AddImageUtils.REQUEST_PICTURE_CUT://裁剪完成
                    if (data != null){
                        try {
                            File fileCamera = new File(mOutputUri.getPath());
                            mFileAddP.fileAdd(fileCamera);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

        }
    }

    /**
     * 新增或修改勋章成功的回调
     */
    @Override
    public void addModifyMedalSuccess(MedalInfoBean result) {
        Intent mIntent = new Intent();
        mIntent.putExtra(MedalManagerUI.MEDAL_INFO,result);
        setResult(RESULT_CODE,mIntent);
        finish();
    }

    /**
     * 获取勋章标题
     * @return
     */
    @Override
    public String getMedalTitle() {
        return et_medal_title.getText().toString().trim();
    }

    /**
     * 获取勋章描述
     * @return
     */
    @Override
    public String getMedalInfo() {
        return et_medal_info.getText().toString().trim();
    }

    /**
     * 获取勋章图片
     * @return
     */
    @Override
    public String getMedalImg() {
        return medalImg;
    }

    /**
     * 图片上传成功的回调
     */
    @Override
    public void addFileSuccess(String url) {
        this.medalImg=url;
        GlideImageLoader.create(iv_add_pic).loadImage(url,R.mipmap
                .unify_image_ing);
    }
}
