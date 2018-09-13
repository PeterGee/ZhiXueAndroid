package com.risenb.studyknowledge.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.CustomListener;
import com.lidroid.mutils.screen.ScreenUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.live.TeacherListBean;
import com.risenb.studyknowledge.beans.topic.ActivityListBean;
import com.risenb.studyknowledge.pop.PopIco;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.FileAddP;
import com.risenb.studyknowledge.ui.institution.AddTopicFragment;
import com.risenb.studyknowledge.ui.live.SelectLecturersUI;
import com.risenb.studyknowledge.utils.AddImageUtils;
import com.risenb.studyknowledge.utils.KeyboardUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;
import com.risenb.studyknowledge.views.CustomPopWindow;
import com.risenb.studyknowledge.views.SwitchButton;
import com.risenb.studyknowledge.views.lib.TimePickerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/9/29.
 * <p>
 * 发布活动
 */
@ContentView(R.layout.release_action_ui)
public class ReleaseActionUI extends BaseUI implements AddTopicFragment.OnTopicListener, FileAddP.FileAddFace {

    @ViewInject(R.id.tv_issuer)
    private TextView tv_issuer;//发布人

    @ViewInject(R.id.tv_start_time)
    private TextView tv_start_time;

    @ViewInject(R.id.tv_end_time)
    private TextView tv_end_time;

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.sb_stick)
    private SwitchButton sb_stick;

    @ViewInject(R.id.tv_topic_type)
    private TextView tv_topic_type;

    @ViewInject(R.id.ll_add_action)
    private LinearLayout ll_add_action;

    @ViewInject(R.id.iv_right_topic)
    private ImageView iv_right_topic;

    @ViewInject(R.id.tv_topic)
    private TextView tv_topic;

    @ViewInject(R.id.iv_add_picture)
    private ImageView iv_add_picture;

    private int topicType;// 活动类型
    private TimePickerView pvCustomTime;
    private CustomPopWindow mTopicTypePop;
    public String mItemViewType;
    public String mStartTime;
    public String mEndTime;
    public String mIsTop;
    private AddTopicFragment mAddTopicFragment;
    private String topicId;
    private String topicImg = "https://pic4.zhimg.com/02685b7a5f2d8cbf74e1fd1ae61d563b_xll.jpg";
    private int activityWriterId;
    public ActivityListBean mActivityListBean;
    private int mActivityId;
    private Uri mOutputUri;
    private PopIco popIco;
    private FileAddP mFileAddP;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context, ActivityListBean activityListBean) {
        Intent starter = new Intent(context, ReleaseActionUI.class);
        starter.putExtra("activityListBean", activityListBean);
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
        setTitle(getResources().getString(R.string.release_action));

        initCustomTimePicker();

        mActivityListBean = (ActivityListBean) getIntent().getSerializableExtra("activityListBean");

        //是否置顶
        sb_stick.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked)
                    mIsTop = "是";
                else
                    mIsTop = "否";
                makeText(mIsTop);
            }
        });


    }

    @Override
    protected void prepareData() {
        mFileAddP = new FileAddP(this, this);
        if (mActivityListBean != null) {
            mItemViewType = mActivityListBean.getActivityType();
            //设置回显示
            if (!"0".equals(mItemViewType)) {
                switch (mItemViewType) {
                    case "1":
                        tv_topic_type.setText("课程");
                        break;
                    case "2":
                        tv_topic_type.setText("大家谈");
                        break;
                }
            }

            mActivityId = mActivityListBean.getActivityId();
            tv_title.setText(mActivityListBean.getActivityName());
//            sb_stick.setChecked(mActivityListBean.);
            tv_topic.setText(mActivityListBean.getTopicName());
            tv_start_time.setText(mActivityListBean.getStartTime());
            tv_end_time.setText(mActivityListBean.getEndTime());
//            tv_issuer.setText(mActivityListBean.getActivityId());
        }


    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({R.id.rl_issuer, R.id.rl_start_time, R.id.rl_end_time, R.id.tv_confirm, R.id.rl_action_type, R.id.rl_topic, R.id.iv_add_picture})
    public void onClick(View view) {
        KeyboardUtils.hideKeyBoard(view.getContext(), view);
        switch (view.getId()) {
            case R.id.rl_issuer:
                SelectLecturersUI.start(this);
                break;
            case R.id.rl_start_time:
                pvCustomTime.show(tv_start_time);
                break;
            case R.id.rl_end_time:
                pvCustomTime.show(tv_end_time);
                break;
            case R.id.tv_confirm:
                //创建
                if (inputReal()) {
                    ReleaseContentsUI.start(
                            view.getContext(),
                            String.valueOf(topicType),
                            tv_title.getText().toString().trim(),
                            topicId,
                            String.valueOf(activityWriterId),
                            topicImg,
                            mStartTime,
                            mEndTime,
                            "是".equals(mIsTop) ? "0" : "1",
                            String.valueOf(mActivityId)

                    );
                }

                break;
            case R.id.rl_action_type:
                showActionTypePop();
                break;
            case R.id.rl_topic:
                showTopicFragment(true);
                break;
            case R.id.iv_add_picture:
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
        popIco = new PopIco(iv_add_picture, getActivity());
        popIco.showAsDropDown(ll_add_action);
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
                            File fileCamera = new File(mOutputUri.getPath());
                            mFileAddP.fileAdd(fileCamera);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // ImageGlideUtils.GlideCommonImg(this,mOutputUri,R.mipmap.unify_image_ing,iv_add_pic);
                        GlideImageLoader.create(iv_add_picture).loadImage(mOutputUri.toString(), R.mipmap.unify_image_ing);
                    }
                    break;
                case SelectLecturersUI.REQUEST_CODE:
                    TeacherListBean bean = (TeacherListBean) data.getSerializableExtra(SelectLecturersUI.TEACHER_INFO);
                    tv_issuer.setText(bean.getUserName());
                    activityWriterId = bean.getTeacherId();
                    break;
            }

        }
    }

    private boolean inputReal() {
        if (TextUtils.isEmpty(tv_title.getText().toString().trim())) {
            ToastUtils.showToast("请输入标题");
            return false;
        }


        if (TextUtils.isEmpty(String.valueOf(activityWriterId))) {
            ToastUtils.showToast("请选择发布人");
            return false;
        }


        if (TextUtils.isEmpty(topicImg)) {
            ToastUtils.showToast("请上传活动图片");
            return false;
        }

        if (TextUtils.isEmpty(mStartTime)) {
            ToastUtils.showToast("请选择开始时间");
            return false;
        }

        if (TextUtils.isEmpty(mEndTime)) {
            ToastUtils.showToast("请选择结束时间");
            return false;
        }
        return true;
    }

    /**
     * 显示话题弹窗
     *
     * @param show
     */
    private void showTopicFragment(boolean show) {
        if (mAddTopicFragment == null) {
            mAddTopicFragment = new AddTopicFragment();
            mAddTopicFragment.setOnTopicListener(this);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mAddTopicFragment.isAdded()) {
                return;
            }
            transaction.add(R.id.fl_topic, mAddTopicFragment).commit();
        } else {
            transaction.remove(mAddTopicFragment).commit();
        }
    }

    /**
     * 话题类型弹框
     */
    private void showActionTypePop() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_topic_type, null);
        handleTopicType(contentView);
        mTopicTypePop = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(true)
                .setBgDarkAlpha(0.7f)
                .enableOutsideTouchableDissmiss(true)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimUp)
                .create();
        mTopicTypePop.showAtLocation(ll_add_action, Gravity.BOTTOM, 0, 0);
    }

    private void handleTopicType(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopicTypePop != null) {
                    mTopicTypePop.dissmiss();
                }
                switch (v.getId()) {

                    case R.id.tv_course:
                        tv_topic_type.setText("课程");
                        topicType = 1;
                        break;
                    case R.id.tv_voices:
                        tv_topic_type.setText("大家谈");
                        topicType = 2;
                        break;
                }
            }
        };
        contentView.findViewById(R.id.tv_all).setVisibility(View.GONE);
        contentView.findViewById(R.id.tv_course).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_voices).setOnClickListener(listener);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != Activity.RESULT_OK) {
//            return;
//        }
//        if (requestCode == REQUEST_CODE) {
//
//        }
//    }

    /**
     * 初始化时间选择
     */
    private void initCustomTimePicker() {

        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2001, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2049, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (v == tv_start_time) {
                    mStartTime = getTime(date);
                    tv_start_time.setText(mStartTime);
                } else if (v == tv_end_time) {
                    mEndTime = getTime(date);
                    tv_end_time.setText(mEndTime);
                }
            }
        }).setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        TextView v_bottom = (TextView) v.findViewById(R.id.v_bottom);
                        if (v_bottom != null) {
                            ViewGroup.MarginLayoutParams bottomParams = (ViewGroup.MarginLayoutParams) v_bottom.getLayoutParams();
                            bottomParams.height = ScreenUtils.getScreenUtils().getBottomStatusHeight(getActivity());
                            v_bottom.setLayoutParams(bottomParams);
                        }
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(getResources().getColor(R.color.gray))
                .setTextColorCenter(getResources().getColor(R.color.gray_3))
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    @Override
    public void closeTopicListener(View view) {
        showTopicFragment(false);
    }

    @Override
    public void topicListener(View view, String topic, String topicName) {
//        iv_right_topic.setVisibility(View.GONE);
        tv_topic.setText(topicName);
        showTopicFragment(false);

        this.topicId = topic;
    }


    @Subscribe
    public void postEvent(PostEvent postEvent) {
        if (PostEvent.RELEASE_SUCCESS == postEvent.getEventType()) {
            finish();
        }
    }

    @Override
    public void addFileSuccess(String url) {
        topicImg = url;
    }
}
