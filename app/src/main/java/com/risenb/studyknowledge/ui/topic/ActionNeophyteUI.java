package com.risenb.studyknowledge.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.topic.ActionManageAdapter;
import com.risenb.studyknowledge.adapter.topic.ActionNeophyteAdapter;
import com.risenb.studyknowledge.beans.topic.ActionManageBean;
import com.risenb.studyknowledge.beans.topic.ActionNeophyteBean;
import com.risenb.studyknowledge.beans.topic.ActivityListBean;
import com.risenb.studyknowledge.beans.topic.ActivityUserListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.DividerItemDecoration;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import static com.risenb.studyknowledge.R.id.mrl_action_manage;
import static com.risenb.studyknowledge.R.id.rv_action_manage;
import static java.sql.Types.TIMESTAMP;

/**
 * Created by zhuzh on 2017/9/22.
 *
 * 活动参与者
 */
@ContentView(R.layout.action_neophyte_ui)
public class ActionNeophyteUI extends BaseUI implements MyRefreshLayoutListener, ActionNeophyteP.ActionNeophyteFace {
    @ViewInject(R.id.mrl_action_neophyte)
    private MyRefreshLayout mrl_action_neophyte;
    @ViewInject(R.id.rv_action_neophyte)
    private RecyclerView rv_action_neophyte;

    private ActionNeophyteAdapter mAdapter;
    private List<ActivityUserListBean> listData = new ArrayList<>();
    private String activityId;
    private ActionNeophyteP actionNeophyteP;
    private int PAGE=1;
    private String LIMIT = "2";
    private String TIMESTAMP="";
    private String C="702";
    private String CollegeId="69";
    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context, String activityId) {
        Intent starter = new Intent(context, ActionNeophyteUI.class);
        starter.putExtra("activityId", activityId);
        context.startActivity(starter);
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.action_neophyte));
        activityId = getIntent().getStringExtra("activityId");

        mAdapter = new ActionNeophyteAdapter(R.layout.action_neophyte_item, listData);
        rv_action_neophyte.setAdapter(mAdapter);
        rv_action_neophyte.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_action_neophyte.getParent());

        mrl_action_neophyte.setMyRefreshLayoutListener(this);//刷新加载
    }

    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        actionNeophyteP = new ActionNeophyteP(this, getActivity());
        actionNeophyteP.getActivityUserList(actionNeophyteP.REFRESH,C,activityId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @Override
    public void onRefresh(View view) {
        PAGE = 1;
        actionNeophyteP.getActivityUserList(actionNeophyteP.REFRESH,C,activityId,TIMESTAMP,PAGE+"",LIMIT);

    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        actionNeophyteP.getActivityUserList(actionNeophyteP.LOAD,C,activityId,TIMESTAMP,PAGE+"",LIMIT);

    }


    @Override
    public void actionUserListSuccess(String tag, String time, List<ActivityUserListBean> list) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,actionNeophyteP.REFRESH)){
            mrl_action_neophyte.refreshComplete();
        }else if(TextUtils.equals(tag,actionNeophyteP.LOAD)){
            mrl_action_neophyte.loadMoreComplete();
        }
        TIMESTAMP=time;
        listData=list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void actionUserListFail() {
        Utils.getUtils().dismissDialog();
        mrl_action_neophyte.refreshComplete();
        mrl_action_neophyte.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }
}
