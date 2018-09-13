package com.risenb.studyknowledge.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.topic.ActionManageAdapter;
import com.risenb.studyknowledge.beans.topic.ActivityListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.post.ReleaseActionUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;
import com.risenb.studyknowledge.views.DividerItemDecoration;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.risenb.studyknowledge.R.id.ll_right;


/**
 * Created by zhuzh on 2017/9/20.
 * <p>
 * 活动管理
 */

@ContentView(R.layout.action_manage_ui)
public class ActionManageUI extends BaseUI implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemChildClickListener, ActionManageP.ActionManageFace, DeleteActionP.DeleteActionListener {
    @ViewInject(R.id.mrl_action_manage)
    private MyRefreshLayout mrl_action_manage;
    @ViewInject(R.id.rv_action_manage)
    private RecyclerView rv_action_manage;

    private ActionManageAdapter mAdapter;
    private List<ActivityListBean> listData = new ArrayList<>();
    private ActionManageP actionManageP;
    private int PAGE = 1;
    private String LIMIT = "10";
    private String TIMESTAMP = "";
    private String CollegeId = "45";
    public DeleteActionP mDeleteActionP;
    private int mCurrentPosition;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ActionManageUI.class);
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
        setTitle(getResources().getString(R.string.action_manage));
        rightVisible(R.mipmap.add_title_iv);

        mAdapter = new ActionManageAdapter(R.layout.action_manage_item, listData);
        rv_action_manage.setAdapter(mAdapter);
        rv_action_manage.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv_action_manage.getParent());

        //添加分隔线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, R.drawable.divider_activity_line, LinearLayoutManager.VERTICAL);
        rv_action_manage.addItemDecoration(itemDecoration);
        mrl_action_manage.setMyRefreshLayoutListener(this);//刷新加载
        mAdapter.setOnItemChildClickListener(this);//侧滑菜单监听

    }

    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        actionManageP = new ActionManageP(this, getActivity());

        mDeleteActionP = new DeleteActionP(this, this);
        actionManageP.getActionList(actionManageP.REFRESH, application.getC(), CollegeId, TIMESTAMP, PAGE + "", LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({ll_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case ll_right:
                ReleaseActionUI.start(this, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh(View view) {
        PAGE = 1;
        actionManageP.getActionList(actionManageP.REFRESH, application.getC(), CollegeId, TIMESTAMP, PAGE + "", LIMIT);

    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        actionManageP.getActionList(actionManageP.LOAD, application.getC(), CollegeId, TIMESTAMP, PAGE + "", LIMIT);

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        this.mCurrentPosition = position;
        switch (view.getId()) {
            //编辑
            case R.id.tv_menu_one:
                ActivityListBean activityListBean = listData.get(position);
                ReleaseActionUI.start(this, activityListBean);
                break;
            //删除
            case R.id.tv_menu_two:
                mDeleteActionP.setDeleteAction(application.getC(), String.valueOf(listData.get(position).getActivityId()));
                break;
            default:
                break;
        }
    }


    @Override
    public void actionListSuccess(String tag, String time, List<ActivityListBean> list) {
        Utils.getUtils().dismissDialog();
        if (TextUtils.equals(tag, actionManageP.REFRESH)) {
            mrl_action_manage.refreshComplete();
        } else if (TextUtils.equals(tag, actionManageP.LOAD)) {
            mrl_action_manage.loadMoreComplete();
        }
        TIMESTAMP = time;
        listData = list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void actionListFail() {
        Utils.getUtils().dismissDialog();
        mrl_action_manage.refreshComplete();
        mrl_action_manage.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteActionSuccess() {
        ToastUtils.showToast("删除成功");
        listData.remove(mCurrentPosition);
        mAdapter.notifyDataSetChanged();
    }


    @Subscribe
    public void postEvent(PostEvent postEvent) {
        if (PostEvent.RELEASE_SUCCESS == postEvent.getEventType()) {
            PAGE = 1;
            actionManageP.getActionList(actionManageP.REFRESH, application.getC(), CollegeId, TIMESTAMP, PAGE + "", LIMIT);

        }
    }


}
