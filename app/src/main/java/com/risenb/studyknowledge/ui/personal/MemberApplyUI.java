package com.risenb.studyknowledge.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.MemberApplyAdapter;
import com.risenb.studyknowledge.beans.institution.ReportBean;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.MemberApplyBean;
import com.risenb.studyknowledge.enums.EnumTAB;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.institution.InstitutionManageUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.VibratorUtil;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/18.
 * 会员申请
 */
@ContentView(R.layout.ui_member_apply)
public class MemberApplyUI extends BaseUI implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemClickListener, MemberApplyP.MemberApplyFace {
    @ViewInject(R.id.rv_member_apply)
    RecyclerView rv_member_apply;//会员申请列表
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.ll_bottom_choose)
    LinearLayout ll_bottom_choose;
    @ViewInject(R.id.iv_all_checked)
    ImageView iv_all_checked;
    private MemberApplyAdapter mMemberApplyAdapter;
    private boolean isShowCheck=false;
    private boolean isAllChecked=false;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String C="1643";
    private String CollegeId="45";
    private List<AttendanceBean> mApplyVipList;
    private MemberApplyP mMemberApplyP;

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
        setTitle(getResources().getString(R.string.member_application));//会员申请
        rightVisible(getResources().getString(R.string.choose));
        LIMIT=getResources().getString(R.string.limit_10);

        mMemberApplyAdapter = new MemberApplyAdapter(R.layout.member_apply_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_member_apply.setAdapter(mMemberApplyAdapter);
        rv_member_apply.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mMemberApplyAdapter.setOnItemClickListener(this);
        mMemberApplyAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_member_apply.getParent());

    }
    public static void start(Context context) {
        Intent starter = new Intent(context, MemberApplyUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mMemberApplyP = new MemberApplyP(this, getActivity());
        mMemberApplyP.getMemberApplyList(mMemberApplyP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",
                LIMIT,isShowCheck);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_right,R.id.ll_all_check,R.id.ll_delete,R.id.ll_cancel})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_right://选择
                isShowCheck=!isShowCheck;
                if(isShowCheck){
                    rightVisible(getResources().getString(R.string.cancel));
                    ll_bottom_choose.setVisibility(View.VISIBLE);
                }else {
                    rightVisible(getResources().getString(R.string.choose));
                    ll_bottom_choose.setVisibility(View.GONE);
                }
               isShowChoose(isShowCheck);
                break;
            case R.id.ll_all_check://全选
                isAllChecked=!isAllChecked;
                if(isAllChecked){
                    iv_all_checked.setImageResource(R.mipmap.blue_check);
                }else {
                    iv_all_checked.setImageResource(R.mipmap.gray_uncheck);
                }
                for (int i = 0; i < mApplyVipList.size(); i++) {
                    mApplyVipList.get(i).setChecked(isAllChecked);
                }
                mMemberApplyAdapter.notifyDataSetChanged();
                break;
            case R.id.ll_delete://拒绝
                mMemberApplyP.applyVipPass(C,getCheckedMemberID(),MemberApplyP.APPLY_REFUSE);
                break;
            case R.id.ll_cancel://通过
                mMemberApplyP.applyVipPass(C,getCheckedMemberID(),MemberApplyP.APPLY_PASS);
                break;
            default:
                break;
        }
    }

    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        PAGE=1;
        TIMESTAMP="";
        mMemberApplyP.getMemberApplyList(mMemberApplyP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",
                LIMIT,isShowCheck);
    }

    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mMemberApplyP.getMemberApplyList(mMemberApplyP.LOAD,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT,isShowCheck);
    }
    /**
     * 是否显示勾选圈
     */
    public void isShowChoose(boolean isShowCheck){
        for (int i = 0; i <mApplyVipList.size() ; i++) {
            mApplyVipList.get(i).setShow(isShowCheck);
        }
        mMemberApplyAdapter.notifyDataSetChanged();
    }
    /**
     * 获取选中申请的会员attendId组拼
     * @return
     */
    public String getCheckedMemberID(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mApplyVipList.size(); i++) {
            if(mApplyVipList.get(i).isChecked()){
                stringBuilder.append(mApplyVipList.get(i).getAttendId());
                stringBuilder.append(",");
            }
        }
        String attendIds = stringBuilder.substring(0, stringBuilder.length() - 1);
        return attendIds;
    }
    /**
     * 条目点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        boolean checked = mApplyVipList.get(position).isChecked();
        mApplyVipList.get(position).setChecked(!checked);
        mMemberApplyAdapter.notifyDataSetChanged();
    }

    /**
     * 获取会员申请列表成功的回调
     * @param tag
     * @param timestamp
     * @param result
     */
    @Override
    public void getApplyVipListSuccess(String tag, String timestamp, List<AttendanceBean> result) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mMemberApplyP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mMemberApplyP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;

        //黑名单列表
        this.mApplyVipList=result;
        mMemberApplyAdapter.setNewData(mApplyVipList);
        mMemberApplyAdapter.notifyDataSetChanged();
    }

    /**
     * 获取会员申请列表失败的回调
     */
    @Override
    public void getApplyVipListFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }

    /**
     * 拒绝/通过会员申请成功的回调
     */
    @Override
    public void applyVipPassSuccess() {
        //将选中的对象从列表中剔除
        List<AttendanceBean> deleteList=new ArrayList<>();
        for (int i = 0; i < mApplyVipList.size(); i++) {
            if(mApplyVipList.get(i).isChecked()){
                deleteList.add(mApplyVipList.get(i));
            }
        }
        mApplyVipList.removeAll(deleteList);
        mMemberApplyAdapter.notifyDataSetChanged();
    }
}
