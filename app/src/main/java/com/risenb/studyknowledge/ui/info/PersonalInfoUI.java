package com.risenb.studyknowledge.ui.info;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.mutils.utils.UIManager;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.MyApplication;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.UserBean;
import com.risenb.studyknowledge.pop.PopIco;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.login.LoginUI;
import com.risenb.studyknowledge.utils.AddImageUtils;
import com.risenb.studyknowledge.utils.CustomDialogUtils;
import com.risenb.studyknowledge.utils.SPUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;
import com.risenb.studyknowledge.utils.evntBusBean.UserEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import glideimageview.GlideImageLoader;

/**
 * Created by yy on 2017/11/7.
 * 个人信息
 */
@ContentView(R.layout.ui_personal_info)
public class PersonalInfoUI extends BaseUI implements PersonalInfoP.PersonalInfoListener {
    @ViewInject(R.id.ll_title)
    LinearLayout ll_title;
    @ViewInject(R.id.iv_head_icon)
    ImageView iv_head_icon;//用户头像
    @ViewInject(R.id.tv_username)
    TextView tv_username;//用户名
    @ViewInject(R.id.tv_personal_sign)
    TextView tv_personal_sign;//个性签名

    private PopIco popIco;
    private Uri mOutputUri;
    public PersonalInfoP mPersonalInfoP;

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
        EventBus.getDefault().register(this);
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.personal_info));//个人信息
        ll_title.setBackgroundResource(R.mipmap.bg_info_title);
        mPersonalInfoP = new PersonalInfoP(this, this);

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PersonalInfoUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void prepareData() {
        mPersonalInfoP.getPersonalInfo();
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }


    @OnClick({R.id.iv_head_icon, R.id.rl_modify_phone, R.id.rl_modify_mailbox, R.id.rl_modify_pwd, R
            .id.rl_user_name, R.id.rl_sign, R.id.rl_personal_bg, R.id.tv_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head_icon://更换头像
                addPic();
                break;
            case R.id.rl_modify_phone://修改绑定手机
                ModifyPhoneUI.start(this);
                break;
            case R.id.rl_modify_mailbox://修改绑定邮箱
                ModifyNewMailboxUI.start(this);
                break;
            case R.id.rl_modify_pwd://修改密码
                ModifyPwdUI.start(this);
                break;
            case R.id.rl_user_name://用户名
                EditUsernameUI.start(this);
                break;
            case R.id.rl_sign://签名
                EditSignUI.start(this);
                break;
            case R.id.rl_personal_bg://个性背景
                PersonalBgUI.start(this);
                break;
            case R.id.tv_login_out://退出登录
                CustomDialogUtils.getInstance().createCustomDialog(this, getResources().getString(R.string.is_login_out),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyApplication.cleanUserInfo();
                                //清空登录记录
                                SPUtils.put(PersonalInfoUI.this, LoginUI.LOGIN_FLAG, LoginUI.LOGIN_OUT);
                                UIManager.getInstance().popAllActivity();
                                LoginUI.start(PersonalInfoUI.this);
//                                finish();
                            }
                        });
                break;
            default:
                break;

        }
    }

    /**
     * 上传图片
     */
    private void addPic() {
        popIco = new PopIco(iv_head_icon, getActivity());
        popIco.showAsDropDown(ll_title);
        popIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_pop_ico_camera:
                        AddImageUtils.requestCamera(PersonalInfoUI.this);
                        break;
                    case R.id.tv_pop_ico_photo:
                        AddImageUtils.requestPhotoAlbum(PersonalInfoUI.this);
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
            switch (requestCode) {
                case AddImageUtils.REQUEST_PICK_IMAGE://从相册选择
                    if (data != null) {
                        if (Build.VERSION.SDK_INT >= 19) {
                            AddImageUtils.handleImageOnKitKat(data, PersonalInfoUI.this);
                        } else {
                            AddImageUtils.handleImageBeforeKitKat(data, PersonalInfoUI.this);
                        }
                        mOutputUri = AddImageUtils.cropPhoto(PersonalInfoUI.this);
                    }
                    break;
                case AddImageUtils.REQUEST_CAPTURE://拍照
                    mOutputUri = AddImageUtils.cropPhoto(PersonalInfoUI.this);
                    break;
                case AddImageUtils.REQUEST_PICTURE_CUT://裁剪完成
                    if (data != null) {
//                        try {
//                            File fileCamera = new File(outputUri.getPath());
//                            mPresenter.getFiles(token, fileCamera);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                        // ImageGlideUtils.GlideCircleImg(this,mOutputUri,iv_head_icon);
                        GlideImageLoader.create(iv_head_icon).loadCircleImage(mOutputUri.toString(), R.mipmap.unify_circle_head);
                    }
                    break;
            }

        }
    }

    @Override
    public void personalInfoData(UserBean userBean) {

        //获取空数据时，重新登录
        if (userBean == null) {
            ToastUtils.showToast("登录超时，请重新登录");
            LoginUI.start(this);
            finish();
            return;
        }


        tv_username.setText(userBean.getUserName());
        if (!TextUtils.isEmpty(userBean.getUserIntro())) {
            tv_personal_sign.setText(userBean.getUserIntro());
        } else {
            tv_personal_sign.setText(getResources().getString(R.string.personal_sign));
        }
    }


    @Subscribe
    public void updateUserInfo(UserEvent userEvent) {

        String result;
        switch (userEvent.getEventType()) {
            case UserEvent.UPDATE_USERNAME:
                result = (String) userEvent.getData();
                tv_username.setText(result);
                break;
            case UserEvent.UPDATE_USER_SIGN:
                result = (String) userEvent.getData();
                tv_personal_sign.setText(result);
        }

    }
}
