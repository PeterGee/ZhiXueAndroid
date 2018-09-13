package com.risenb.studyknowledge.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.post.CostsListAdapter;
import com.risenb.studyknowledge.beans.live.TeacherListBean;
import com.risenb.studyknowledge.beans.posts.CostsListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.live.SelectLecturersUI;
import com.risenb.studyknowledge.utils.KeyboardUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;
import com.risenb.studyknowledge.views.CustomPopWindow;
import com.risenb.studyknowledge.views.SwitchButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhuzh on 2017/9/27.
 * <p>
 * 发布帖子
 */
@ContentView(R.layout.release_post_ui)
public class ReleasePostUI extends BaseUI implements BaseQuickAdapter.OnItemClickListener {

    @ViewInject(R.id.et_title)
    private EditText et_title;

    @ViewInject(R.id.tv_cost)
    private TextView tv_cost;

    @ViewInject(R.id.sb_stick)
    private SwitchButton sb_stick;

    @ViewInject(R.id.ll_release_post)
    private LinearLayout ll_release_post;

    @ViewInject(R.id.rl_issuer)
    private RelativeLayout rl_issuer;

    @ViewInject(R.id.tv_issuer)
    private TextView tv_issuer;
    private CustomPopWindow mCostPopWindow;
    private List<CostsListBean> list = new ArrayList<>();
    private String mCost = "";
    private CostsListAdapter mAdapter;
    private String[] costs;
    private int postType;//帖子类型
    private int postTopicId;
    private String postIsTop = "0";
    private int postWriterId;
    private String postIsFree = "0"; //1免费  2付费
    private String postId;
    private String postName;
    private String postPrice;
    private EditText et_cost;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    /**
     * 发布贴子界面跳转
     *
     * @param context
     * @param postType
     * @param postTopicId
     */
    public static void start(Context context, int postType, int postTopicId) {
        Intent starter = new Intent(context, ReleasePostUI.class);
        starter.putExtra("postType", postType);
        starter.putExtra("postTopicId", postTopicId);
        context.startActivity(starter);
    }


    /**
     * 编辑贴子界面跳转
     *
     * @param context
     * @param postId
     * @param postName
     * @param postIsFree
     * @param postPrice
     * @param postIsTop
     * @param postType
     */
    public static void start(
            Context context,
            String postId, String postName,
            String postIsFree, String postPrice,
            String postIsTop, String postType) {
        Intent starter = new Intent(context, ReleasePostUI.class);
        starter.putExtra("postType", Integer.parseInt(postType));
        starter.putExtra("postId", postId);
        starter.putExtra("postName", postName);
        starter.putExtra("postIsFree", postIsFree);
        starter.putExtra("postPrice", postPrice);
        starter.putExtra("postIsTop", postIsTop);
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


        postType = getIntent().getIntExtra("postType", postType);
        postTopicId = getIntent().getIntExtra("postTopicId", postTopicId);

        postId = getIntent().getStringExtra("postId");
        postName = getIntent().getStringExtra("postName");
        postIsFree = getIntent().getStringExtra("postIsFree");
        postPrice = getIntent().getStringExtra("postPrice");
        postIsTop = getIntent().getStringExtra("postIsTop");
        if (TextUtils.isEmpty(postId)) {
            setTitle(getResources().getString(R.string.release_post));
        } else {
            setTitle(getResources().getString(R.string.update_post));
            if (!TextUtils.isEmpty(postName)) {
                et_title.setText(postName);
                et_title.setSelection(postName.length());
            }
            if (!TextUtils.isEmpty(postPrice)) {
                tv_cost.setText("¥" + postPrice);
                mCost = "2";
            } else {
                mCost = "1";
            }
            sb_stick.setChecked("1".equals(postIsTop) ? true : false);
            rl_issuer.setVisibility(View.GONE);

        }


        //是否置顶
        sb_stick.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked)
                    postIsTop = "1";
                else
                    postIsTop = "0";
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

    @OnClick({R.id.tv_confirm, R.id.rl_cost, R.id.rl_issuer})
    public void onClick(View view) {
        KeyboardUtils.hideKeyBoard(view.getContext(), view);
        switch (view.getId()) {
            case R.id.tv_confirm:

                nextStep();
                break;
            case R.id.rl_cost:

                showCostPopWindow();
                break;
            case R.id.rl_issuer:
                SelectLecturersUI.start(view.getContext());
                break;
            default:
                break;
        }
    }

    private void nextStep() {

        String postName = et_title.getText().toString().trim();
        String mLecturer = tv_issuer.getText().toString().trim();
        String postPrice;
        if ("1".equals(postIsFree)) {
            if (et_cost != null) {
                postPrice = et_cost.getText().toString().trim();
            } else {
                postPrice = "0";
            }
        } else {
            postPrice = "0";
        }

        if (TextUtils.isEmpty(postName)) {
            makeText("标题不能为空");
            return;
        }

        if (TextUtils.isEmpty(mCost)) {
            makeText("收费类型不能为空");
            return;
        }

        if (TextUtils.isEmpty(postId)) {
            if (TextUtils.isEmpty(mLecturer)) {
                makeText("讲师不能为空");
                return;
            }
            ReleaseContentsUI.start(this, String.valueOf(postType), postName, String.valueOf(postTopicId), String.valueOf(postWriterId), postIsFree, postPrice, postIsTop);
        } else {
            ReleaseContentsUI.start(this, postId, postName, String.valueOf(postTopicId), postIsFree, postPrice, postIsTop);
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
            tv_issuer.setText(bean.getUserName());
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
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimUp)
                .create();
        mCostPopWindow.showAtLocation(ll_release_post, Gravity.BOTTOM, 0, 0);
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
                            if (postType == 3) {
                                makeText("不能选择免费");
                                return;
                            }
                            tv_cost.setText(mCost);
                            postIsFree = "1";
                        } else if (mCost.equals(costs[1])) {
                            String cost = et_cost.getText().toString();
                            if (cost.isEmpty()) {
                                makeText("金额不能为空");
                                return;
                            }
                            tv_cost.setText("¥" + cost);
                            postIsFree = "2";
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
        mCost = list.get(position).getContent();
        if (mCost.equals(costs[0])) {
            et_cost.setVisibility(View.INVISIBLE);
        } else if (mCost.equals(costs[1])) {
            et_cost.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
    }


    @Subscribe
    public void postEvent(PostEvent postEvent) {
        if (PostEvent.RELEASE_SUCCESS == postEvent.getEventType()) {
            finish();
        }
    }
}
