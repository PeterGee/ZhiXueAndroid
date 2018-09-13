package com.risenb.studyknowledge.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.MemberManagerAdapter;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.MemberLevelBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.InputMethodUtils;
import com.risenb.studyknowledge.utils.MaxTextLengthFilter;
import com.risenb.studyknowledge.utils.SPUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * C端会员管理
 */
@ContentView(R.layout.ui_member_manager)
public class MemberManagerUI extends BaseUI implements MyRefreshLayoutListener,MemberLevelFragment.OnMemberLevelListener,BaseQuickAdapter.OnItemChildClickListener, MemberManagerP.MemberManagerFace {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_member_manager)
    RecyclerView rv_member_manager;//C端会员管理列表
    @ViewInject(R.id.tv_member_level)
    TextView tv_member_level;//会员等级
    @ViewInject(R.id.iv_down_arrow)
    ImageView iv_down_arrow;
    @ViewInject(R.id.et_member_search)
    EditText et_member_search;//会员昵称搜索
    private MemberManagerAdapter mMemberManagerAdapter;
    private boolean isShowLevel=false;
    private MemberLevelFragment mMemberLevelFragment;
    public static String LEVEL="level";
    public static final String MEMBER_INFO="member_info";
    private MemberManagerP mMemberManagerP;
    private List<MemberLevelBean> mUserCollege;
    private List<AttendanceBean> mAttendanceList;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String NAME="";
    private String CollegeGradeId="";
    private String C="1643";
    private String CollegeId="45";
    private int itemCheckedPosition;
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
        setTitle(getResources().getString(R.string.C_member_manager));//C端会员管理
        LIMIT=getResources().getString(R.string.limit_10);

        mMemberManagerAdapter = new MemberManagerAdapter(R.layout.member_manager_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_member_manager.setAdapter(mMemberManagerAdapter);
        rv_member_manager.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mMemberManagerAdapter.setOnItemChildClickListener(this);
        mMemberManagerAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_member_manager.getParent());
        //清空首选项
        SPUtils.put(this,LEVEL,"");//清空等级首选相

        //编辑框，软键盘搜索按钮监听
        et_member_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    InputMethodUtils.hideInputMethod(v);//隐藏软键盘
                    NAME=et_member_search.getText().toString().trim();
                    Utils.getUtils().showProgressDialog(getActivity(), null);
                    search();//搜索
                    return true;
                }
                return false;
            }
        });
        //搜索内容超出20字提示
        et_member_search.setFilters(new InputFilter[]{new MaxTextLengthFilter(20,getResources().getString(R.string.et_length_tip))});

    }
    public static void start(Context context) {
        Intent starter = new Intent(context, MemberManagerUI.class);
        context.startActivity(starter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMemberManagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mMemberManagerP = new MemberManagerP(this, getActivity());
        mMemberManagerP.getMemberList(mMemberManagerP.REFRESH,C,CollegeId,NAME,CollegeGradeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.rl_member_level})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_member_level://会员等级
                isShowLevel=!isShowLevel;
                showFragment(isShowLevel);
                break;
            default:
                break;
        }
    }
    /**
     * 昵称或等级搜索
     */
    public void search() {
        PAGE=1;
        TIMESTAMP="";
        mMemberManagerP.getMemberList(mMemberManagerP.REFRESH,C,CollegeId,NAME,CollegeGradeId,
                TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        search();
    }

    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mMemberManagerP.getMemberList(mMemberManagerP.LOAD,C,CollegeId,NAME,CollegeGradeId, TIMESTAMP,
                PAGE+"",LIMIT);
    }
    /**
     * 获取C端会员列表成功的回调
     */
    @Override
    public void memberListSuccess(String tag,String timestamp,List<AttendanceBean>
            attendanceList,List<MemberLevelBean> userCollege) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mMemberManagerP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mMemberManagerP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;

        //会员等级列表
        this.mUserCollege=userCollege;
        //会员列表
        this.mAttendanceList=attendanceList;
        mMemberManagerAdapter.setNewData(mAttendanceList);
        mMemberManagerAdapter.notifyDataSetChanged();
    }

    /**
     * 获取C端会员列表失败的回调
     * @param userCollege
     */
    @Override
    public void memberListFail(List<MemberLevelBean> userCollege) {
        Utils.getUtils().dismissDialog();
        //会员等级列表
        this.mUserCollege=userCollege;
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
        mMemberManagerAdapter.notifyDataSetChanged();
    }

    /**
     * 踢出会员成功的回调
     */
    @Override
    public void kickOutMemberSuccess(int position) {
        mAttendanceList.remove(position);
        mMemberManagerAdapter.notifyDataSetChanged();
    }

    /**
     * 是否显示城市选择fm
     * @param show
     */
    private void showFragment(boolean show) {
        if (mMemberLevelFragment == null) {
            mMemberLevelFragment = new MemberLevelFragment();
            mMemberLevelFragment.setOnMemberLevelListener(this);
        }
        mMemberLevelFragment.setList(mUserCollege);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mMemberLevelFragment.isAdded()){
                return;
            }
            transaction.add(R.id.fl_contener,mMemberLevelFragment).commit();
            tv_member_level.setTextColor(getResources().getColor(R.color.blue));
            iv_down_arrow.setImageResource(R.mipmap.up_arrow_blue);
        }else {
            transaction.remove(mMemberLevelFragment).commit();
            tv_member_level.setTextColor(getResources().getColor(R.color.gray_3));
            iv_down_arrow.setImageResource(R.mipmap.down_arrow);
        }
    }
    public static final int REQUEST_CODE_EDIT=0;
    public static final int REQUEST_CODE_DETAIL=1;
    /**
     * 侧滑菜单“编辑”、“踢出”点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.tv_edit://编辑_跳转会员设置页面
                this.itemCheckedPosition=position;
                Intent starter = new Intent(this,MemberSettingUI.class);
                starter.putExtra(MEMBER_INFO,mAttendanceList.get(position));
                startActivityForResult(starter,REQUEST_CODE_EDIT);
                break;
            case R.id.tv_kick_out://踢出
                mMemberManagerP.kickOutVip(C,mAttendanceList.get(position).getAttendId()+"",CollegeId,position);
                break;
            case R.id.content://条目点击事件
                //跳转会员详情页面
                this.itemCheckedPosition=position;
                Intent starter2 = new Intent(this,MemberDetailUI.class);
                starter2.putExtra(MEMBER_INFO,mAttendanceList.get(position));
                startActivityForResult(starter2,REQUEST_CODE_DETAIL);
                break;
            default:
                break;
        }
    }

    /**
     * 关闭等级列表弹窗的回调
     * @param view
     * @param position
     */
    @Override
    public void closeLevelListListener(View view, int position) {
        String level = mUserCollege.get(position).getUserCollegegradeName();
        tv_member_level.setText(level);
        isShowLevel=false;
        showFragment(isShowLevel);
        SPUtils.put(this,LEVEL,position+"");
        //选中会员等级的id
        CollegeGradeId=mUserCollege.get(position).getUserCollegegradeId();
        //搜索显示选中等级的会员列表
        Utils.getUtils().showProgressDialog(getActivity(), null);
        search();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==MemberSettingUI.RESULT_CODE){
            if(requestCode==REQUEST_CODE_EDIT||requestCode==REQUEST_CODE_DETAIL) {
                AttendanceBean mMemberInfoBean = data.getParcelableExtra(MEMBER_INFO);
                mAttendanceList.set(itemCheckedPosition,mMemberInfoBean);
                mMemberManagerAdapter.setNewData(mAttendanceList);
                mMemberManagerAdapter.notifyDataSetChanged();
            }
        }
    }
}
