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
import com.risenb.studyknowledge.adapter.personal.KickOutMemberAdapter;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;
import com.risenb.studyknowledge.beans.personal.MemberApplyBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 踢出的会员
 */
@ContentView(R.layout.ui_kick_out_member)
public class KickOutMemberUI extends BaseUI implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemChildClickListener, KickOutMemberP.KickOutListFace {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_kick_out_member)
    RecyclerView rv_kick_out_member;//踢出的会员列表
    private KickOutMemberAdapter mKickOutMemberAdapter;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String C="1643";
    private String CollegeId="45";
    private KickOutMemberP mKickOutMemberP;
    private List<AttendanceBean> kickOutList;
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
        setTitle(getResources().getString(R.string.kick_out_member));//踢出的会员
        LIMIT=getResources().getString(R.string.limit_10);

        mKickOutMemberAdapter = new KickOutMemberAdapter(R.layout.kick_out_member_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_kick_out_member.setAdapter(mKickOutMemberAdapter);
        rv_kick_out_member.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mKickOutMemberAdapter.setOnItemChildClickListener(this);
        mKickOutMemberAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_kick_out_member.getParent());
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, KickOutMemberUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mKickOutMemberP = new KickOutMemberP(this, getActivity());
        mKickOutMemberP.getKickOutList(mKickOutMemberP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
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
        mKickOutMemberP.getKickOutList(mKickOutMemberP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mKickOutMemberP.getKickOutList(mKickOutMemberP.LOAD,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    /**
     * 侧滑菜单“邀请”点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mKickOutMemberP.getKickOutInvite(C,kickOutList.get(position).getAttendId()+"",position);
    }

    /**
     * 踢出会员列表成功的回调
     * @param tag
     * @param timestamp
     * @param result
     */
    @Override
    public void getKickOutListSuccess(String tag, String timestamp, List<AttendanceBean> result) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mKickOutMemberP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mKickOutMemberP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;

        //踢出会员列表
        this.kickOutList=result;
        mKickOutMemberAdapter.setNewData(kickOutList);
        mKickOutMemberAdapter.notifyDataSetChanged();
    }

    /**
     * 踢出会员列表失败的回调
     */
    @Override
    public void getKickOutListFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }

    /**
     * 邀请会员成功的回调
     */
    @Override
    public void inviteKickMemberSuccess(int position) {
        kickOutList.remove(position);
        mKickOutMemberAdapter.notifyDataSetChanged();
    }
}
