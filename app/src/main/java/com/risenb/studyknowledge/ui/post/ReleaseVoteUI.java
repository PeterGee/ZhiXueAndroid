package com.risenb.studyknowledge.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.listener.CustomListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.screen.ScreenUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.post.AddVoteAdapter;
import com.risenb.studyknowledge.beans.live.TeacherListBean;
import com.risenb.studyknowledge.beans.posts.AddVoteBean;
import com.risenb.studyknowledge.beans.topic.VoteListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.institution.AddTopicFragment;
import com.risenb.studyknowledge.ui.live.SelectLecturersUI;
import com.risenb.studyknowledge.ui.topic.ReleaseVoteP;
import com.risenb.studyknowledge.utils.KeyboardUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.CustomPopWindow;
import com.risenb.studyknowledge.views.SwitchButton;
import com.risenb.studyknowledge.views.lib.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuzh on 2017/9/29.
 * <p>
 * 发布投票
 */
@ContentView(R.layout.release_vote_ui)
public class ReleaseVoteUI extends BaseUI implements SwitchButton.OnCheckedChangeListener, BaseQuickAdapter.OnItemChildClickListener, ReleaseVoteP.ReleaseVoteListener, AddTopicFragment.OnTopicListener {

    @ViewInject(R.id.ll_release_vote)
    private LinearLayout ll_release_vote;

    @ViewInject(R.id.tv_issuer)
    private TextView tv_issuer;//发布人

    @ViewInject(R.id.tv_start_time)
    private TextView tv_start_time;

    @ViewInject(R.id.tv_end_time)
    private TextView tv_end_time;

    @ViewInject(R.id.et_title)
    private EditText et_title;

    @ViewInject(R.id.sb_stick)
    private SwitchButton sb_stick;

    @ViewInject(R.id.sb_select)
    private SwitchButton sb_select;

    @ViewInject(R.id.rv_vote)
    private RecyclerView rv_vote;

    @ViewInject(R.id.tv_vote_type)
    private TextView tv_vote_type;

    @ViewInject(R.id.tv_topic)
    private TextView tv_topic;

    private AddVoteAdapter mAdapter;
    private List<AddVoteBean> list = new ArrayList<>();


    private TimePickerView pvCustomTime;
    private CustomPopWindow mAddVotePop;
    public ReleaseVoteP mReleaseVoteP;
    public String mIsTop = "1";
    private boolean mIsMultiple;
    private AddTopicFragment mAddTopicFragment;
    private CustomPopWindow mTopicTypePop;
    private int activityWriterId;
    private String topicId;
    private int topicType;
    public String mStartTime;
    public String mEndTime;
    public VoteListBean mVoteListBean;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context, VoteListBean voteListBean) {
        Intent starter = new Intent(context, ReleaseVoteUI.class);
        starter.putExtra("voteListBean", voteListBean);
        context.startActivity(starter);
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.release_vote));

        mVoteListBean = (VoteListBean) getIntent().getSerializableExtra("voteListBean");

        initCustomTimePicker();
        sb_stick.setOnCheckedChangeListener(this);
        sb_select.setOnCheckedChangeListener(this);

        mAdapter = new AddVoteAdapter(R.layout.add_vote_item, list);
        rv_vote.setLayoutManager(new LinearLayoutManager(this));
        rv_vote.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);//侧滑菜单监听
    }

    @Override
    protected void prepareData() {
        mReleaseVoteP = new ReleaseVoteP(this, this);
        if (mVoteListBean != null) {
            et_title.setText(mVoteListBean.getVoteName());
            tv_start_time.setText(mVoteListBean.getStartTime());
            tv_end_time.setText(mVoteListBean.getEndTime());
        }
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        switch (view.getId()) {
            case R.id.sb_stick:
                //是否置顶
                if (isChecked)
                    mIsTop = "1";
                else
                    mIsTop = "0";
                makeText(mIsTop);
                break;
            case R.id.sb_select:
                //是否多选
                mIsMultiple = isChecked;
                break;
        }
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

    @OnClick({R.id.rl_issuer, R.id.rl_start_time, R.id.rl_end_time, R.id.tv_add_vote, R.id.tv_confirm, R.id.rl_topic, R.id.rl_vote_type})
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
            case R.id.tv_add_vote:
                showAddVotePop();
                break;
            case R.id.tv_confirm:
                String voteName = et_title.getText().toString().trim();
                if (TextUtils.isEmpty(voteName)) {
                    makeText("请输入标题");
                    return;
                }
                mReleaseVoteP.setReleaseVote(
                        topicId,
                        voteName,
                        String.valueOf(topicType),
                        mIsTop, String.valueOf(activityWriterId), mStartTime, mEndTime, JSON.toJSONString(list),
                        mIsMultiple

                );
                break;
            case R.id.rl_vote_type:
                showVoteTypePop();
                break;

            case R.id.rl_topic:
                showTopicFragment(true);
                break;

            default:
                break;
        }
    }

    /**
     * 添加投票项弹框
     */
    private void showAddVotePop() {

        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_add_vote, null);
        handleAddVote(contentView);
        mAddVotePop = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .enableBackgroundDark(true)
                .setBgDarkAlpha(0.7f)
                .enableOutsideTouchableDissmiss(false)
                .create();
        mAddVotePop.showAtLocation(ll_release_vote, Gravity.CENTER, 0, 0);
    }


    /**
     * 话题类型弹框
     */
    private void showVoteTypePop() {
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
        mTopicTypePop.showAtLocation(ll_release_vote, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 选择类型
     *
     * @param contentView
     */
    private void handleTopicType(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopicTypePop != null) {
                    mTopicTypePop.dissmiss();
                }
                switch (v.getId()) {

                    case R.id.tv_course:
                        tv_vote_type.setText("课程");
                        topicType = 1;
                        break;
                    case R.id.tv_voices:
                        tv_vote_type.setText("大家谈");
                        topicType = 2;
                        break;
                }
            }
        };
        contentView.findViewById(R.id.tv_all).setVisibility(View.GONE);
        contentView.findViewById(R.id.tv_course).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_voices).setOnClickListener(listener);
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
                            makeText("请添加内容");
                        } else {
                            AddVoteBean bean = new AddVoteBean();
                            bean.setContent(content);
                            list.add(bean);
                            mAdapter.setList(list);
                            mAdapter.notifyDataSetChanged();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == SelectLecturersUI.REQUEST_CODE) {
            TeacherListBean bean = (TeacherListBean) data.getSerializableExtra(SelectLecturersUI.TEACHER_INFO);
            tv_issuer.setText(bean.getUserName());
            activityWriterId = bean.getTeacherId();
        }
    }

    /**
     * 侧滑删除
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tv_menu:
//                makeText("删除");
//                list.remove(position);
//                mAdapter.notifyDataSetChanged();

                break;
            default:
                break;
        }
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
    public void releaseVoteSuccess() {
        makeText("发布成功");
        finish();
    }

    @Override
    public void closeTopicListener(View view) {
        showTopicFragment(false);
    }

    @Override
    public void topicListener(View view, String topic, String topicName) {
        tv_topic.setText(topicName);
        showTopicFragment(false);

        this.topicId = topic;
    }
}
