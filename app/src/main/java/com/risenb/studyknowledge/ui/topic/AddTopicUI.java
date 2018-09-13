package com.risenb.studyknowledge.ui.topic;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.post.CostsListAdapter;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.posts.CostsListBean;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.pop.PopIco;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.FileAddP;
import com.risenb.studyknowledge.utils.AddImageUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.evntBusBean.TopicEvent;
import com.risenb.studyknowledge.views.CustomPopWindow;
import com.risenb.studyknowledge.views.SwitchButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import glideimageview.GlideImageLoader;


/**
 * Created by zhuzh on 2017/9/27.
 * <p>
 * 添加话题
 */
@ContentView(R.layout.add_topic_ui)
public class AddTopicUI extends BaseUI implements BaseQuickAdapter.OnItemClickListener, AddTopicP.AddTopicFace, FileAddP.FileAddFace {
    @ViewInject(R.id.et_title)
    private EditText et_title;
    @ViewInject(R.id.tv_toll_mode)
    private TextView tv_toll_mode;
    @ViewInject(R.id.tv_topic_type)
    private TextView tv_topic_type;
    @ViewInject(R.id.ll_add_topic)
    private LinearLayout ll_add_topic;
    @ViewInject(R.id.sb_put_away)
    private SwitchButton sb_put_away;
    @ViewInject(R.id.iv_add_picture)
    private ImageView iv_add_picture;
    @ViewInject(R.id.sb_stick)
    private SwitchButton sb_stick;

    private CustomPopWindow mTollModePop;
    private TopicListBean bean;
    private CustomPopWindow mTopicTypePop;
    private List<CostsListBean> list = new ArrayList<>();
    private String mCost = "";
    private CostsListAdapter mAdapter;
    private EditText et_cost;
    private String[] costs;
    private PopIco popIco;
    private Uri mOutputUri;
    private String C = "702";
    private String CollegeId = "69";
    private int topicIsTop; //是否置顶
    private int topicUseyn; // 是否上架
    private int payType; //收费方式
    private int topicType;// 话题类型
    private String topicImg = "https://pic4.zhimg.com/02685b7a5f2d8cbf74e1fd1ae61d563b_xll.jpg";
    private AddTopicP addTopicP;
    private String type;   //1 添加 2 编辑
    public static final String FLAG_ADD = "1"; //添加
    public static final String FLAG_EDIT = "2";//编辑
    private int topicId;
    private FileAddP mFileAddP;


    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context, TopicListBean bean, String type) {
        Intent starter = new Intent(context, AddTopicUI.class);
        starter.putExtra(TopicListManageUI.TOPIC_INFO, bean);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }


    @Override
    protected void setControlBasis() {

        EventBus.getDefault().register(this);

        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.add_topic));

        addTopicP = new AddTopicP(this, getActivity());
        mFileAddP = new FileAddP(this,this);
        type = getIntent().getStringExtra("type");
        bean = (TopicListBean) getIntent().getSerializableExtra(TopicListManageUI.TOPIC_INFO);
        if (bean != null) {
            initData();
        }

        //是否置顶
        sb_stick.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked)
                    topicIsTop = 1;
                else
                    topicIsTop = 0;
            }
        });

        //是否上下架
        sb_put_away.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked)
                    topicUseyn = 1;
                else
                    topicUseyn = 0;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initData() {

        topicId = bean.getTopicId();
        et_title.setText(bean.getTopicName());

        //收费方式
        payType = bean.getTopicPayType();
        if (payType == 1) {
            tv_toll_mode.setText("免费");
        } else if (payType == 2) {
            tv_toll_mode.setText(bean.getTopicPrice() + "");
        } else if (payType == 3) {
            tv_toll_mode.setText(bean.getTopicVipName());
        } else if (payType == 4) {
            tv_toll_mode.setText("权限");
        }

        topicType = bean.getTopicType();
        if (topicType == 1) {
            tv_topic_type.setText("课程");
        } else if (topicType == 2) {
            tv_topic_type.setText("大家谈");
        } else if (topicType == 3) {
            tv_topic_type.setText("全部");
        }

        topicUseyn = bean.getTopicUseyn();
        if (topicUseyn == 0) {
            sb_put_away.setChecked(false);
        } else {
            sb_put_away.setChecked(true);
        }

        topicIsTop = bean.getTopicIsTop();
        if (topicIsTop == 0) {
            sb_stick.setChecked(false);
        } else {
            sb_stick.setChecked(true);
        }

        GlideImageLoader.create(iv_add_picture).loadImage(bean.getTopicImg().toString(), R.mipmap.unify_image_ing);


    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({R.id.rl_toll_mode, R.id.rl_topic_type, R.id.tv_confirm, R.id.iv_add_picture})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_toll_mode:
                showTollModePop();
                break;
            case R.id.rl_topic_type:
                showTopicTypePop();
                break;
            case R.id.tv_confirm:
                topicConfirm();
                break;
            case R.id.iv_add_picture:
                addPic();
                break;
            default:
                break;
        }
    }

    private void topicConfirm() {

        String topicName = et_title.getText().toString().trim();
        String tollMode = tv_toll_mode.getText().toString().trim();
        String topicTypes = tv_topic_type.getText().toString().trim();

        if (TextUtils.isEmpty(topicName)) {
            makeText("话题名称不能为空");
            return;
        }
        if (TextUtils.isEmpty(tollMode)) {
            makeText("收费方式不能为空");
            return;
        }
        if (TextUtils.isEmpty(topicTypes)) {
            makeText("话题类型不能为空");
            return;
        }

        if (type.equals(FLAG_ADD)) {
            if (payType == 1) {
                addTopicP.addTopic(C, CollegeId, topicName, payType + "", topicType + "", topicIsTop + "", topicUseyn + "", topicImg, "", "", "");
            } else if (payType == 2) {
                addTopicP.addTopic(C, CollegeId, topicName, payType + "", topicType + "", topicIsTop + "", topicUseyn + "", topicImg, tollMode, "", "");
            } else if (payType == 3) {
                addTopicP.addTopic(C, CollegeId, topicName, payType + "", topicType + "", topicIsTop + "", topicUseyn + "", topicImg, "", tollMode, "");
            }
        } else if (type.equals(FLAG_EDIT)) {
            if (payType == 1) {
                addTopicP.updateTopic(C, topicId + "", topicName, payType + "", topicType + "", topicIsTop + "", topicUseyn + "", topicImg, "", "", "");
            } else if (payType == 2) {
                addTopicP.updateTopic(C, topicId + "", topicName, payType + "", topicType + "", topicIsTop + "", topicUseyn + "", topicImg, tollMode, "", "");
            } else if (payType == 3) {
                addTopicP.updateTopic(C, topicId + "", topicName, payType + "", topicType + "", topicIsTop + "", topicUseyn + "", topicImg, "", tollMode, "");
            }
        }


    }

    /**
     * 收费方式弹框
     */
    private void showTollModePop() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_cost_menu, null);
        //处理popWindow 显示内容
        handleTollMode(contentView);
        //创建并显示popWindow
        mTollModePop = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(true) //弹出popWind ow时，背景是否变暗
                .setBgDarkAlpha(0.7f) // 控制亮度
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e("TAG", "onDismiss");
                    }
                })
                .enableOutsideTouchableDissmiss(true)// 设置点击PopupWindow之外的地方，popWindow不关闭，如果不设置这个属性或者为true，则关闭
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .setAnimationStyle(R.style.AnimUp)
                .create();
        mTollModePop.showAtLocation(ll_add_topic, Gravity.BOTTOM, 0, 0);
    }

    private void handleTollMode(View contentView) {
        list.clear();
        costs = getResources().getStringArray(R.array.add_topic);
        for (int i = 0; i < costs.length; i++) {
            CostsListBean bean = new CostsListBean();
            bean.setContent(costs[i]);
            list.add(bean);
        }
        et_cost = (EditText) contentView.findViewById(R.id.et_cost);
        RecyclerView rv_cost_list = (RecyclerView) contentView.findViewById(R.id.rv_cost_list);
        rv_cost_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CostsListAdapter(R.layout.cost_list_item, list);
        rv_cost_list.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_cancel:
                        mTollModePop.dissmiss();
                        break;
                    case R.id.tv_confirm:
                        if (mCost.equals(costs[0])) {
                            tv_toll_mode.setText(mCost);
                            payType = 1;
                        } else if (mCost.equals(costs[1])) {
                            tv_toll_mode.setText(et_cost.getText().toString());
                            payType = 2;
                        } else if (mCost.equals(costs[2])) {
                            tv_toll_mode.setText(et_cost.getText().toString());
                            payType = 3;
                        } else if (mCost.equals(costs[3])) {
                            tv_toll_mode.setText(mCost);
                            payType = 4;
                        }
                        mTollModePop.dissmiss();
                        break;
                }
            }
        };
        contentView.findViewById(R.id.tv_cancel).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_confirm).setOnClickListener(listener);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChecked(false);
        }
        list.get(position).setChecked(true);
        mCost = list.get(position).getContent();
        if (mCost.equals(costs[0])) {
            et_cost.setVisibility(View.INVISIBLE);
        } else if (mCost.equals(costs[1])) {
            et_cost.setVisibility(View.VISIBLE);
            et_cost.setHint("金额");
        } else if (mCost.equals(costs[2])) {
            et_cost.setVisibility(View.VISIBLE);
            et_cost.setHint("VIP1-7");
        } else if (mCost.equals(costs[3])) {
            ChargePrivilegeUI.start(this);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 话题类型弹框
     */
    private void showTopicTypePop() {
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
        mTopicTypePop.showAtLocation(ll_add_topic, Gravity.BOTTOM, 0, 0);
    }

    private void handleTopicType(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopicTypePop != null) {
                    mTopicTypePop.dissmiss();
                }
                switch (v.getId()) {
                    case R.id.tv_all:
                        tv_topic_type.setText("全部");
                        topicType = 3;
                        break;
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
        contentView.findViewById(R.id.tv_all).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_course).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_voices).setOnClickListener(listener);
    }


    /**
     * 上传图片
     */
    private void addPic() {
        popIco = new PopIco(iv_add_picture, getActivity());
        popIco.showAsDropDown(ll_add_topic);
        popIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_pop_ico_camera:
                        AddImageUtils.requestCamera(AddTopicUI.this);
                        break;
                    case R.id.tv_pop_ico_photo:
                        AddImageUtils.requestPhotoAlbum(AddTopicUI.this);
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
                            AddImageUtils.handleImageOnKitKat(data, AddTopicUI.this);
                        } else {
                            AddImageUtils.handleImageBeforeKitKat(data, AddTopicUI.this);
                        }
                        mOutputUri = AddImageUtils.cropPhoto(AddTopicUI.this);
                    }
                    break;
                case AddImageUtils.REQUEST_CAPTURE://拍照
                    mOutputUri = AddImageUtils.cropPhoto(AddTopicUI.this);
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
            }

        }
    }

    @Override
    public void addFileSuccess(String url) {
        topicImg = url;
    }


    @Override
    public void addTopicSuccess(NetBaseBean result) {
        finish();
        EventBus.getDefault().post(new TopicEvent().setEventType(TopicEvent.UPDATE_TOPIC_LIST));
    }

    @Override
    public void updateTopicSuccess(NetBaseBean result) {
        finish();
    }


    @Subscribe
    public void topicEvent(TopicEvent topicEvent) {

    }
}
