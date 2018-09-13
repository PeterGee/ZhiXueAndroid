package com.risenb.studyknowledge.ui.live;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.listener.CustomListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.screen.ScreenUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.post.CostsListAdapter;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.live.LiveNoticeBean;
import com.risenb.studyknowledge.beans.live.PostLiveListBean;
import com.risenb.studyknowledge.beans.live.TeacherListBean;
import com.risenb.studyknowledge.beans.posts.CostsListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.personal.MemberApplyUI;
import com.risenb.studyknowledge.ui.post.ReleasePostUI;
import com.risenb.studyknowledge.utils.DateUtils;
import com.risenb.studyknowledge.utils.InputMethodUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.CustomPopWindow;
import com.risenb.studyknowledge.views.SwitchButton;
import com.risenb.studyknowledge.views.lib.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.risenb.studyknowledge.R.id.et_cost;
import static com.risenb.studyknowledge.R.id.fl_release_live;
import static com.risenb.studyknowledge.R.id.tv_release;
import static com.risenb.studyknowledge.R.id.tv_toll_mode;
import static com.risenb.studyknowledge.R.id.tv_topic_type;


/**
 * Created by zhuzh on 2017/9/22.
 *
 * 发布直播预告
 */
@ContentView(R.layout.release_live_ui)
public class ReleaseLiveUI extends BaseUI implements TopicDecorationFragment.OnDecorationListener, BaseQuickAdapter.OnItemClickListener, ReleaseLiveP.ReleaseLiveFace {

    @ViewInject(R.id.tv_cost)
    private TextView tv_cost;
    @ViewInject(R.id.tv_lecturer)
    private TextView tv_lecturer;
    @ViewInject(R.id.ll_release_live)
    private LinearLayout ll_release_live;
    @ViewInject(R.id.tv_topic)
    private TextView tv_topic;
    @ViewInject(R.id.tv_live_time)
    private TextView tv_live_time;
    @ViewInject(R.id.et_title)
    private EditText et_title;
    @ViewInject(R.id.sb_stick)
    private SwitchButton sb_stick;
    @ViewInject(R.id.et_live_detail)
    private EditText et_live_detail;
    private PostLiveListBean bean;
    private TopicDecorationFragment mTopicFragment;

    private TimePickerView pvCustomTime;
    private CustomPopWindow mCostPopWindow;
    //private int REQUEST_CODE = 1;
    private List<CostsListBean> list=new ArrayList<>();
    private String mCost = "";
    private CostsListAdapter mAdapter;
    private EditText et_cost;
    private String[] costs;
    private ReleaseLiveP releaseLiveP;
    private int postTopicId;//话题id
    private String C="702";
    private String CollegeId="45";
    private int postWriterId;//讲师id
    private int postIsTop;//是否置顶
    private int postIsFree;//是否免费

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context, PostLiveListBean bean) {
        Intent starter = new Intent(context, ReleaseLiveUI.class);
        starter.putExtra("bean", bean);
        context.startActivity(starter);
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.release_live));

        releaseLiveP = new ReleaseLiveP(this, getActivity());

        bean = (PostLiveListBean) getIntent().getSerializableExtra("bean");
        if (bean != null) {
            et_title.setText(bean.getPostName());
            sb_stick.setChecked(false);
        }

        initCustomTimePicker();

        //是否置顶
        sb_stick.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked)
                    postIsTop = 1;
                else
                    postIsTop = 0;
            }
        });
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

    @OnClick({R.id.rl_topic,R.id.rl_lecturer,R.id.rl_live_time,R.id.rl_cost,R.id.tv_confirm})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_topic:
                showFragment(true);
                break;
            case R.id.rl_lecturer:
//                Intent intent = new Intent(this, SelectLecturersUI.class);
//                startActivityForResult(intent, REQUEST_CODE);
                SelectLecturersUI.start(this);
                break;
            case R.id.rl_live_time:
                pvCustomTime.show();
                break;
            case R.id.rl_cost:
                showCostPopWindow();
                break;
            case R.id.tv_confirm:
                liveConfirm();
                break;
            default:
                break;
        }
    }


    private void liveConfirm() {

        String postName = et_title.getText().toString().trim();
        String topicName = tv_topic.getText().toString().trim();
        String mLecturer = tv_lecturer.getText().toString().trim();
        String postLivetime = tv_live_time.getText().toString().trim();
        String postPrice = tv_cost.getText().toString().trim();
        String postInfo = et_live_detail.getText().toString().trim();

        if (TextUtils.isEmpty(postName)) {
            makeText("直播标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(topicName)) {
            makeText("所属话题不能为空");
            return;
        }
        if (TextUtils.isEmpty(mLecturer)) {
            makeText("讲师不能为空");
            return;
        }
        if (TextUtils.isEmpty(postLivetime)){
            makeText("直播时间不能为空");
            return;
        }
        if (TextUtils.isEmpty(mCost)){
            makeText("收费类型不能为空");
            return;
        }
        if (TextUtils.isEmpty(postInfo)){
            makeText("直播简介不能为空");
            return;
        }

        if (postIsFree==1){
            releaseLiveP.addPostLive(C, CollegeId, postName, postTopicId+"", postWriterId+"", postLivetime, postIsFree+"","",postIsTop+"",postInfo);
        } else if (postIsFree ==2){
            releaseLiveP.addPostLive(C, CollegeId, postName, postTopicId+"", postWriterId+"", postLivetime, postIsFree+"",postPrice,postIsTop+"",postInfo);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == SelectLecturersUI.REQUEST_CODE) {
            TeacherListBean bean = (TeacherListBean) data.getSerializableExtra(SelectLecturersUI.TEACHER_INFO);
            tv_lecturer.setText(bean.getUserName());
            postWriterId = bean.getTeacherId();
        }
    }


    /**
     * 是否付费的弹框
     */
    private void showCostPopWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_cost_menu, null);
        handleLogic(contentView);
        mCostPopWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(true)
                .setBgDarkAlpha(0.7f)
                .enableOutsideTouchableDissmiss(true)
                .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimUp)
                .create();
        mCostPopWindow.showAtLocation(ll_release_live, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        list.clear();
        costs = getResources().getStringArray(R.array.release_cost);
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
                if (mCostPopWindow != null) {
                    mCostPopWindow.dissmiss();
                }
                switch (v.getId()) {
                    case R.id.tv_cancel:
                        mCostPopWindow.dissmiss();
                        break;
                    case R.id.tv_confirm:
                        if (mCost.equals(costs[0])) {
                            tv_cost.setText(mCost);
                            postIsFree = 1;
                        } else if (mCost.equals(costs[1])) {
                            String cost = et_cost.getText().toString();
                            if (cost.isEmpty()){
                                makeText("金额不能为空");
                                return;
                            }
                            tv_cost.setText("¥"+cost);
                            postIsFree = 2;
                        }
                        mCostPopWindow.dissmiss();
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
        mCost =list.get(position).getContent();
        if (mCost.equals(costs[0])) {
            et_cost.setVisibility(View.INVISIBLE);
        } else if (mCost.equals(costs[1])) {
            et_cost.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
    }


    /**
     * 话题弹框
     * @param b
     */
    private void showFragment(boolean b) {

        if (mTopicFragment ==null) {
            mTopicFragment = new TopicDecorationFragment();
            mTopicFragment.setDecorationListener(this);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (b) {
            //防止快速点击
            if (mTopicFragment.isAdded()){
                return;
            }
            transaction.add(R.id.fl_release_live,mTopicFragment).commit();
        }else {
            transaction.remove(mTopicFragment).commit();
        }
    }

    /**
     * 关闭话题弹窗的回调
     * @param view
     * @param topic
     */
    @Override
    public void closeDecorationListener(View view, String topic, int topicId) {
        showFragment(false);
        if (topic != null){
            tv_topic.setText(topic);
        }
        postTopicId = topicId;
    }

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
            public void onTimeSelect(Date date, View v) {
                tv_live_time.setText(DateUtils.getTime(date));
            }
        })
                .setDate(selectedDate)
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
                .setLabel("年","月","日","时","分","")
                .isCenterLabel(false)
                .setDividerColor(getResources().getColor(R.color.gray))
                .setTextColorCenter(getResources().getColor(R.color.gray_3))
//                .setLineSpacingMultiplier(2.0f)
                .build();
    }


    /**
     * 添加直播成功回调
     * @param result
     */
    @Override
    public void addPostLiveSuccess(NetBaseBean result) {
        finish();
    }
}
