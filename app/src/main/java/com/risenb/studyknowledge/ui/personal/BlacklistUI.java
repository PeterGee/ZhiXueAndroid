package com.risenb.studyknowledge.ui.personal;

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
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.BlackListAdapter;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 黑名单
 */
@ContentView(R.layout.ui_black_list)
public class BlacklistUI extends BaseUI implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemChildClickListener, BlacklistP.BlackListFace {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_black_list)
    RecyclerView rv_black_list;//黑名单列表
    private BlackListAdapter mBlackListAdapter;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String C="1643";
    private String CollegeId="45";
    private BlacklistP mBlacklistP;
    private List<AttendanceBean> mBlackList;
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
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.black_list));//黑名单
        LIMIT=getResources().getString(R.string.limit_10);


        mBlackListAdapter = new BlackListAdapter(R.layout.black_list_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_black_list.setAdapter(mBlackListAdapter);
        rv_black_list.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mBlackListAdapter.setOnItemChildClickListener(this);
        mBlackListAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_black_list.getParent());
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, BlacklistUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mBlacklistP = new BlacklistP(this, getActivity());
        mBlacklistP.getBlackList(mBlacklistP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        PAGE=1;
        TIMESTAMP="";
        mBlacklistP.getBlackList(mBlacklistP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mBlacklistP.getBlackList(mBlacklistP.LOAD,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 侧滑菜单“取消拉黑”点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mBlacklistP.cancelBlackList(C,mBlackList.get(position).getAttendId()+"",position);
    }

    /**
     * 获取黑名单列表成功的回调
     * @param tag
     * @param timestamp
     * @param result
     */
    @Override
    public void getBlackListSuccess(String tag, String timestamp, List<AttendanceBean> result) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mBlacklistP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mBlacklistP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;

        //黑名单列表
        this.mBlackList=result;
        mBlackListAdapter.setNewData(mBlackList);
        mBlackListAdapter.notifyDataSetChanged();
    }

    /**
     * 获取黑名单列表失败的回调
     */
    @Override
    public void getBlackListFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }

    /**
     * 取消拉黑成功的回调
     * @param position
     */
    @Override
    public void cancelBlackSuccess(int position) {
        mBlackList.remove(position);
        mBlackListAdapter.notifyDataSetChanged();
    }
}
