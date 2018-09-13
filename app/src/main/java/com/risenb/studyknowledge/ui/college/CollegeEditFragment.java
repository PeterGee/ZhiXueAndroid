package com.risenb.studyknowledge.ui.college;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.college.CollegeBean;
import com.risenb.studyknowledge.beans.college.CollegeInfoBean;
import com.risenb.studyknowledge.pop.PopIco;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.FileAddP;
import com.risenb.studyknowledge.utils.AddImageUtils;
import com.risenb.studyknowledge.utils.ToastUtils;
import com.risenb.studyknowledge.views.CustomPopWindow;

import java.io.File;

/**
 * Created by zhuzh on 2017/11/3.
 */

public class CollegeEditFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener, CollegeInfoP.CollegeInfoFace, FileAddP.FileAddFace {

    @ViewInject(R.id.et_college_name)
    private EditText et_college_name;//学院名称
    @ViewInject(R.id.et_registrant)
    private EditText et_registrant;//注册人
    @ViewInject(R.id.et_bank_name)
    private EditText et_bank_name;//开户行
    @ViewInject(R.id.et_card_number)
    private EditText et_card_number;//银行卡号
    @ViewInject(R.id.et_college_sort)
    private TextView et_college_sort;//学院类别
    @ViewInject(R.id.iv_college_back_img)
    private ImageView iv_college_back_img;//学院背景
    @ViewInject(R.id.et_address_detail)
    private EditText et_address_detail;//学院简介

    public static final String COLLEGE_INFO = "college_info";
    @ViewInject(R.id.college_seek_bar)
    private SeekBar college_seek_bar;
    @ViewInject(R.id.rg_group_setting)
    private RadioGroup rg_group_setting;
    @ViewInject(R.id.ll_edit_college)
    private LinearLayout ll_edit_college;

    private CollegeBean collegeBean;
    public CollegeInfoP mCollegeInfoP;
    public String mCollegeName;//学院名称
    public String mCollegeUser;//注册人
    public String mCollegeAccBankinfo;//开户银行
    public String mCollegeAccBank;//银行账号
    public String mCollegeBackimg;//学院背景图
    public int mCollegeType;//入群类型---1-开放  2-付费  3-审核
    public int mScale;//学院与用户分成比例
    public int mCollegeDelYn;//学院状态   0-正常  1-删除  2-私密
    public String mCollegeInfo; //学院简介
    private String mCollegePrice = "";//学院价格
    private Uri mOutputUri;
    private int fileType;
    private File mFileCamera;
    private FileAddP mFileAddP;
    private CustomPopWindow mAddVotePop;


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.college_edit_fm, container, false);

    }

    public static CollegeEditFragment newInstance(CollegeBean collegeBean) {
        CollegeEditFragment fragment = new CollegeEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(COLLEGE_INFO, collegeBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setControlBasis() {
        mCollegeInfoP = new CollegeInfoP(this, getActivity());
        mFileAddP = new FileAddP(this, getActivity());
        collegeBean = (CollegeBean) getArguments().getSerializable(COLLEGE_INFO);
        if (collegeBean != null) {


            mCollegeName = collegeBean.getCollegeName();
            mCollegeUser = collegeBean.getCollegeUser();
            mCollegeAccBankinfo = collegeBean.getCollegeAccBankinfo();
            mCollegeAccBank = collegeBean.getCollegeAccBank();
            mCollegeBackimg = collegeBean.getCollegeBackimg();

            mCollegeType = collegeBean.getCollegeType();
            mScale = collegeBean.getScale();
            mCollegeDelYn = collegeBean.getCollegeDelYn();
            mCollegeInfo = collegeBean.getCollegeInfo();

            et_college_name.setText(collegeBean.getCollegeName());
            et_registrant.setText(collegeBean.getCollegeUser());
            college_seek_bar.setOnSeekBarChangeListener(this);
            rg_group_setting.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case R.id.item_option1:
                            mCollegeType = 1;
                            break;
                        case R.id.item_option2:
                            mCollegeType = 2;
                            break;
                        case R.id.item_option3:
                            mCollegeType = 3;
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void prepareData() {

    }

    /**
     * 添加投票项弹框
     */
    private void showAddVotePop() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_add_college_money, null);
        handleAddVote(contentView);
        mAddVotePop = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(true)
                .setBgDarkAlpha(0.7f)
                .enableOutsideTouchableDissmiss(false)
                .create();
        mAddVotePop.showAtLocation(ll_edit_college, Gravity.CENTER, 0, 0);
    }


    private void handleAddVote(View contentView) {
        final EditText et_vote = (EditText) contentView.findViewById(R.id.et_vote);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.tv_preserve:
                        String content = et_vote.getText().toString().trim();
                        if (content.isEmpty()) {
                            makeText("请输入付费金额");
                        } else {
                            mCollegePrice = content;
                            mAddVotePop.dissmiss();
                        }
                        break;
                    case R.id.tv_cancel:
                        mAddVotePop.dissmiss();
                        break;
                }
            }
        };
        contentView.findViewById(R.id.tv_preserve).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_cancel).setOnClickListener(listener);

    }


    @OnClick({R.id.tv_commit, R.id.tv_cancel, R.id.ll_selected_pic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:

                mCollegeName = et_college_name.getText().toString().trim();
                mCollegeUser = et_registrant.getText().toString().trim();
                mCollegeAccBankinfo = et_bank_name.getText().toString().trim();
                mCollegeAccBank = et_card_number.getText().toString().trim();
                mCollegeInfo = et_address_detail.getText().toString().trim();

                if (mCollegeType == 1) {
                    showAddVotePop();
                } else {
                    mCollegePrice = "0";
                    mCollegeInfoP.getEditCollegeInfo(
                            String.valueOf(collegeBean.getCollegeId()),
                            mCollegeName, mCollegeUser,
                            mCollegeAccBankinfo, mCollegeAccBank,
                            mCollegeBackimg, String.valueOf(mScale),
                            String.valueOf(mCollegeType), mCollegePrice,
                            String.valueOf(mCollegeDelYn), mCollegeInfo
                    );
                }


                break;
            case R.id.tv_cancel:
                getFragmentManager().popBackStack();
                break;

            case R.id.ll_selected_pic:
                addPic();
                break;
            default:
                break;
        }
    }


    /**
     * 上传图片
     */
    private void addPic() {
        PopIco popIco = new PopIco(et_college_name, getActivity());
        popIco.showAsDropDown(et_college_name);
        popIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_pop_ico_camera:
                        AddImageUtils.requestCamera(getActivity());
                        break;
                    case R.id.tv_pop_ico_photo:
                        AddImageUtils.requestPhotoAlbum(getActivity());
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
                            AddImageUtils.handleImageOnKitKat(data, getActivity());
                        } else {
                            AddImageUtils.handleImageBeforeKitKat(data, getActivity());
                        }
                        mOutputUri = AddImageUtils.cropPhoto(getActivity());
                    }
                    break;
                case AddImageUtils.REQUEST_CAPTURE://拍照
                    mOutputUri = AddImageUtils.cropPhoto(getActivity());
                    break;
                case AddImageUtils.REQUEST_PICTURE_CUT://裁剪完成
                    if (data != null) {
                        try {
                            mFileCamera = new File(mOutputUri.getPath());
                            //本地显示
                            mFileAddP.fileAdd(mFileCamera);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

        }
    }

    /**
     * 进度条监听
     *
     * @param seekBar
     * @param i
     * @param b
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        ToastUtils.showToast("当前进度值" + i);
        mScale = i;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void collegeInfoSuccess(CollegeInfoBean.DataBean result) {

    }

    @Override
    public void editCollegeInfoSuccess() {

    }

    @Override
    public void addFileSuccess(String url) {
        this.mCollegeBackimg = url;
        Glide.with(getActivity()).load(url).into(iv_college_back_img);
    }
}
