package com.risenb.studyknowledge.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.post.ReleaseContentsAdapter;
import com.risenb.studyknowledge.beans.posts.ReleaseContentsBean;
import com.risenb.studyknowledge.pop.PopIco;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.FileAddP;
import com.risenb.studyknowledge.ui.topic.UpdateActionP;
import com.risenb.studyknowledge.utils.AddImageUtils;
import com.risenb.studyknowledge.utils.FileStorage;
import com.risenb.studyknowledge.utils.SoftInputUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;
import com.risenb.studyknowledge.utils.record.RecordUtil;
import com.risenb.studyknowledge.utils.record.VoiceManager;
import com.risenb.studyknowledge.views.CustomPopWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzh on 2017/11/13.
 * <p>
 * 发布内容
 */
@ContentView(R.layout.release_contents_ui)
public class ReleaseContentsUI extends BaseUI implements ReleaseActionP.ReleaseActionListener, UpdateActionP.UpdateActionListener, ReleasePostP.ReleasePostListener, FileAddP.FileAddFace, UpdatePostP.UpdatePostListener {
    @ViewInject(R.id.rv_release_content)
    private RecyclerView rv_release_content;
    @ViewInject(R.id.et_content)
    private EditText et_content;
    @ViewInject(R.id.ll_content)
    private LinearLayout ll_content;

    @ViewInject(R.id.iv_picture)
    private ImageView iv_picture;

    @ViewInject(R.id.ll_release_contents)
    private LinearLayout ll_release_contents;

    @ViewInject(R.id.ll_upload)
    private LinearLayout ll_upload;

    private String IMG_PATH;
    private List<ReleaseContentsBean> listData = new ArrayList<>();//发布内容的Json数据
    private ReleaseContentsAdapter mAdapter;
    private boolean mIsFocus;
    private CustomPopWindow recordPopWindow;
    private VoiceManager voiceManager;
    private TextView tv_time_length;

    private String postType;//帖子类型
    private String postTopicId;
    private String postIsTop = "0";
    private String postWriterId;
    private String postIsFree; //1免费  2付费
    private String postName;
    private String postPrice;
    private String topicImg;
    private String startTime;
    private String endTime;
    private ReleaseActionP mReleaseActionP;
    private String activityId;
    public UpdateActionP mUpdateActionP;
    public ReleasePostP mReleasePostP;
    private PopIco popIco;
    private FileAddP mFileAddP;
    private Uri mOutputUri;
    private int fileType;
    private long voiceLength = 0;
    public File mFileCamera;
    public File mVoiceFile;
    private String voiceStrLength;
    private String postId;
    public UpdatePostP mUpdatePostP;


    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    /**
     * 发布贴子的
     *
     * @param context
     * @param postType
     * @param postName
     * @param postTopicId
     * @param postWriterId
     * @param postIsFree
     * @param postPrice
     * @param postIsTop
     */
    public static void start(Context context, String postType, String postName, String postTopicId,
                             String postWriterId, String postIsFree, String postPrice, String postIsTop) {
        Intent starter = new Intent(context, ReleaseContentsUI.class);
        starter.putExtra("postType", postType);
        starter.putExtra("postName", postName);
        starter.putExtra("postTopicId", postTopicId);
        starter.putExtra("postWriterId", postWriterId);
        starter.putExtra("postIsFree", postIsFree);
        starter.putExtra("postPrice", postPrice);
        starter.putExtra("postIsTop", postIsTop);
        context.startActivity(starter);
    }


    /**
     * 修改贴子
     *
     * @param context
     * @param postId
     * @param postName
     * @param postTopicId
     * @param postIsFree
     * @param postPrice
     * @param postIsTop
     */
    public static void start(Context context, String postId, String postName, String postTopicId,
                             String postIsFree, String postPrice, String postIsTop) {
        Intent starter = new Intent(context, ReleaseContentsUI.class);
        starter.putExtra("postId", postId);
        starter.putExtra("postName", postName);
        starter.putExtra("postTopicId", postTopicId);
        starter.putExtra("postIsFree", postIsFree);
        starter.putExtra("postPrice", postPrice);
        starter.putExtra("postIsTop", postIsTop);
        context.startActivity(starter);
    }

    /**
     * 发布活动的
     *
     * @param context
     * @param postType
     * @param postName
     * @param postTopicId
     * @param postWriterId
     * @param topicImg
     * @param startTime
     * @param endTime
     * @param postIsTop
     */
    public static void start(Context context,
                             String postType,
                             String postName,
                             String postTopicId,
                             String postWriterId,
                             String topicImg,
                             String startTime,
                             String endTime,
                             String postIsTop) {
        Intent starter = new Intent(context, ReleaseContentsUI.class);
        starter.putExtra("postType", postType);
        starter.putExtra("postName", postName);
        starter.putExtra("postTopicId", postTopicId);
        starter.putExtra("postWriterId", postWriterId);
        starter.putExtra("topicImg", topicImg);
        starter.putExtra("startTime", startTime);
        starter.putExtra("endTime", endTime);
        starter.putExtra("postIsTop", postIsTop);
        context.startActivity(starter);
    }


    /**
     * 修改活动
     *
     * @param context
     * @param postType
     * @param postName
     * @param postTopicId
     * @param postWriterId
     * @param topicImg
     * @param startTime
     * @param endTime
     * @param postIsTop
     * @param activityId
     */
    public static void start(Context context,
                             String postType,
                             String postName,
                             String postTopicId,
                             String postWriterId,
                             String topicImg,
                             String startTime,
                             String endTime,
                             String postIsTop,
                             String activityId) {
        Intent starter = new Intent(context, ReleaseContentsUI.class);
        starter.putExtra("postType", postType);
        starter.putExtra("postName", postName);
        starter.putExtra("postTopicId", postTopicId);
        starter.putExtra("postWriterId", postWriterId);
        starter.putExtra("topicImg", topicImg);
        starter.putExtra("startTime", startTime);
        starter.putExtra("endTime", endTime);
        starter.putExtra("postIsTop", postIsTop);
        starter.putExtra("activityId", activityId);
        context.startActivity(starter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setControlBasis() {

        EventBus.getDefault().register(this);

        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.release_content));
        voiceManager = VoiceManager.getInstance(this);
        postType = getIntent().getStringExtra("postType");
        postTopicId = getIntent().getStringExtra("postTopicId");
        postIsTop = getIntent().getStringExtra("postIsTop");
        postWriterId = getIntent().getStringExtra("postWriterId");
        postIsFree = getIntent().getStringExtra("postIsFree");
        postName = getIntent().getStringExtra("postName");
        postPrice = getIntent().getStringExtra("postPrice");

        topicImg = getIntent().getStringExtra("topicImg");
        startTime = getIntent().getStringExtra("startTime");
        endTime = getIntent().getStringExtra("endTime");
        activityId = getIntent().getStringExtra("activityId");

        postId = getIntent().getStringExtra("postId");

        mAdapter = new ReleaseContentsAdapter(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_release_content.setAdapter(mAdapter);
        rv_release_content.setLayoutManager(linearLayoutManager);
        institutionListener();

        et_content.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocus) {
                mIsFocus = isFocus;
            }
        });


    }

    /**
     * RecyclerView监听
     */
    private void institutionListener() {
        //文字改变的监听
        mAdapter.setChangInstitutionDataListener(new ReleaseContentsAdapter.ChangInstitutionDataListener() {
            @Override
            public void onChangInstitutionDataListener(int position, String data) {
//                if (position > listData.size()) {
//                    return;
//                }
            }

            @Override
            public void onDeleteItemListener(int position) {
                mAdapter.remove(position);
            }
        });
        //条目子控件的点击事件监听
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_delete:
                        mAdapter.remove(position);
                        break;
                    case R.id.iv_record_play:
                        PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(listData.get(position));
                        fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());
                        break;
                }
            }
        });

        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.remove(position);
                return false;
            }
        });
    }


    /**
     * 上传图片
     */
    private void addPic() {
        fileType = ReleaseContentsBean.IMG;
        popIco = new PopIco(iv_picture, getActivity());
        popIco.showAsDropDown(ll_release_contents);
        popIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_pop_ico_camera:
                        AddImageUtils.requestCamera(ReleaseContentsUI.this);
                        break;
                    case R.id.tv_pop_ico_photo:
                        AddImageUtils.requestPhotoAlbum(ReleaseContentsUI.this);
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
                            AddImageUtils.handleImageOnKitKat(data, ReleaseContentsUI.this);
                        } else {
                            AddImageUtils.handleImageBeforeKitKat(data, ReleaseContentsUI.this);
                        }
                        mOutputUri = AddImageUtils.cropPhoto(ReleaseContentsUI.this);
                    }
                    break;
                case AddImageUtils.REQUEST_CAPTURE://拍照
                    mOutputUri = AddImageUtils.cropPhoto(ReleaseContentsUI.this);
                    break;
                case AddImageUtils.REQUEST_PICTURE_CUT://裁剪完成
                    if (data != null) {
                        try {
                            mFileCamera = new File(mOutputUri.getPath());
                            //本地显示
                            addList(mOutputUri.getPath(), fileType, voiceStrLength, voiceLength);
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
     * 图片上传成功的回调
     */
    @Override
    public void addFileSuccess(String url) {
        String head = "http://";
        if (url.contains(head)) {
            url = url.substring(head.length(), url.length());
        }
        //发布时需要用到的去http
        listData.add(new ReleaseContentsBean(url, fileType, voiceStrLength, voiceLength));
        voiceStrLength = "";
        voiceLength = 0;

    }

    @OnClick({R.id.iv_picture, R.id.tv_confirm, R.id.tv_release, R.id.iv_voice, R.id.ll_upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_picture:
                addPic();
                break;
            case R.id.tv_confirm:
                fileType = ReleaseContentsBean.TEXT;
                if (!TextUtils.isEmpty(et_content.getText().toString().trim())) {
                    addList(et_content.getText().toString().trim(), fileType, null, 0);
                    listData.add(new ReleaseContentsBean(et_content.getText().toString().trim(), fileType, voiceStrLength, voiceLength));
                    et_content.setText("");
                }
                break;
            case R.id.tv_release:
                if (!TextUtils.isEmpty(startTime) && "0".equals(activityId)) {
                    //发布活动
                    mReleaseActionP.setReleaseAction(
                            postTopicId, topicImg, postName,
                            postType, postIsTop, postWriterId,
                            startTime, endTime, JSON.toJSONString(listData)

                    );
                } else if (!TextUtils.isEmpty(activityId) && !"0".equals(activityId)) {
                    //编辑更新活动
                    mUpdateActionP.setUpdateAction(
                            postTopicId, activityId, topicImg, postName,
                            postType, postIsTop, postWriterId,
                            startTime, endTime, JSON.toJSONString(listData)

                    );
                } else {
                    //发布贴子
                    if (TextUtils.isEmpty(postId)) {
                        mReleasePostP.setReleasePost(
                                postTopicId, postName, postType,
                                postWriterId, postIsFree, postPrice,
                                postIsTop, JSON.toJSONString(listData));
                    } else {
                        //修改贴子
                        mUpdatePostP.setUpdatePost(postId, postName, postIsFree, postPrice, postIsTop, JSON.toJSONString(listData));
                    }
                }
                Log.i("ReleaseContentsUI", listData.toString());
                break;
            case R.id.iv_voice:
                showRecordPopWindow();
                break;
            default:
                break;
        }
    }

    private void showRecordPopWindow() {
        fileType = ReleaseContentsBean.RECORD;
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_record_audio, null);
        handleLogic(contentView);
        recordPopWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(true)
                .setBgDarkAlpha(0.7f)
                .enableOutsideTouchableDissmiss(true)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();
        recordPopWindow.showAtLocation(ll_release_contents, Gravity.BOTTOM, 0, 0);

    }

    private void handleLogic(View contentView) {
        tv_time_length = (TextView) contentView.findViewById(R.id.tv_time_length);
        tv_time_length.setText("00:00:00");
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_record_delete:
                        if (voiceManager != null) {
                            voiceManager.cancelVoiceRecord();
                        }
                        recordPopWindow.dissmiss();
                        break;
                    case R.id.tv_record_start:
                        startRecord();
                        break;
                    case R.id.tv_record_conform:
                        if (voiceManager != null) {
                            voiceManager.stopVoiceRecord();
                        }
                        recordPopWindow.dissmiss();
                        break;
                }
            }
        };
        contentView.findViewById(R.id.tv_record_delete).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_record_start).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_record_conform).setOnClickListener(listener);

    }

    private void startRecord() {
        voiceManager.setVoiceRecordListener(new VoiceManager.VoiceRecordCallBack() {
            @Override
            public void recDoing(long time, String strTime) {
                tv_time_length.setText(strTime);
            }

            @Override
            public void recVoiceGrade(int grade) {
//                voicLine.setVolume(grade);
            }

            @Override
            public void recStart(boolean init) {
//                mIvPauseContinue.setImageResource(R.drawable.icon_pause);
//                voicLine.setContinue();
            }

            @Override
            public void recPause(String str) {
//                mIvPauseContinue.setImageResource(R.drawable.icon_continue);
//                voicLine.setPause();
            }


            @Override
            public void recFinish(long length, String strLength, String path) {
                mVoiceFile = new File(path);
                mFileAddP.fileAdd(new File(path));
                voiceStrLength = strLength;
                voiceLength = length;
                //本地显示
                addList(path, fileType, strLength, length);
            }
        });
        voiceManager.startVoiceRecord(RecordUtil.getAudioPath());
    }

    /**
     * 添加数据--本地显示
     *
     * @param content 数据内容
     * @param type    数据类型 0：文字；1：图片；2：录音
     */
    private void addList(String content, int type, String strLength, long length) {
        ReleaseContentsBean data = new ReleaseContentsBean(content, type, strLength, length);
        mAdapter.addData(data);
        mAdapter.notifyDataSetChanged();
        rv_release_content.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    protected void prepareData() {
        mUpdateActionP = new UpdateActionP(this, this);
        mUpdatePostP = new UpdatePostP(this, this);
        mReleaseActionP = new ReleaseActionP(this, this);
        mReleasePostP = new ReleasePostP(this, this);
        mFileAddP = new FileAddP(this, getActivity());
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (isHideSoftInput(view, (int) ev.getX(), (int) ev.getY())) {
            closeSoftInput();
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void closeSoftInput() {
        et_content.clearFocus();
        SoftInputUtils.closeSoftInput(this);
    }

    private boolean isHideSoftInput(View view, int x, int y) {
        if (view == null || !(view instanceof EditText) || !mIsFocus) {
            return false;
        }
        return x < ll_content.getLeft() ||
                x > ll_content.getRight() ||
                y < ll_content.getTop();
    }

    @Override
    public void releasePostSuccess() {
        ToastUtils.showToast("发布成功");
        //删除文件
        if (mFileCamera != null) {
            FileStorage.deleteFile(mFileCamera.getAbsolutePath());
        }
        if (mVoiceFile != null) {
            FileStorage.deleteFile(mVoiceFile.getAbsolutePath());
        }

        //发布贴子成功
        finish();
        EventBus.getDefault().post(new PostEvent().setEventType(PostEvent.RELEASE_SUCCESS));
    }

    @Override
    public void releaseActionSuccess() {
        //发布活动成功
        ToastUtils.showToast("发布成功");
        finish();
        EventBus.getDefault().post(new PostEvent().setEventType(PostEvent.RELEASE_SUCCESS));
    }

    @Override
    public void updateActionSuccess() {
        ToastUtils.showToast("编辑成功");
        //编辑活动成功
        finish();
        EventBus.getDefault().post(new PostEvent().setEventType(PostEvent.RELEASE_SUCCESS));
    }

    @Override
    public void updatePostSuccess() {
        ToastUtils.showToast("编辑成功");
        //编辑贴子成功
        finish();
        EventBus.getDefault().post(new PostEvent().setEventType(PostEvent.RELEASE_SUCCESS));
    }


    @Subscribe
    public void postEvent(PostEvent postEvent) {

    }

}
