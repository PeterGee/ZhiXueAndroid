package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.MedalManagerAdapter;
import com.risenb.studyknowledge.beans.institution.MedalBean;
import com.risenb.studyknowledge.beans.institution.MedalInfoBean;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.personal.MemberSettingUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/10/10.
 * 勋章管理
 */
@ContentView(R.layout.ui_medal_list)
public class MedalManagerUI extends BaseUI implements  MedalManagerP.MedalListFace, BaseQuickAdapter.OnItemChildClickListener, DeleteMedalP.DeleteMedalFace {
    @ViewInject(R.id.rv_medal_list)
    RecyclerView rv_medal_list;//勋章列表
    private List<MedalInfoBean> medalManagerList;
    private MedalManagerAdapter mMedalManagerAdapter;
    private int PAGE=1;
    private String LIMIT;
    private String C="1643";
    private String collegeId="45";
    private MedalManagerP mMedalManagerP;
    private DeleteMedalP mDeleteMedalP;
    public static final String MEDAL_INFO="medal_info";
    public static final String MEDAL_TYPE="medal_type";
    public static final String MEDAL_ADD="medal_add";
    public static final String MEDAL_MODIFY="medal_modify";
    public static final int REQUEST_CODE_ADD=0;
    public static final int REQUEST_CODE_MODIFY=1;
    private int checkedEditPosition;
    private LinearLayoutManager mLinearLayoutManager;

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
        setTitle(getResources().getString(R.string.medal_manage));//勋章管理
        rightVisible(R.mipmap.add);
        LIMIT=getResources().getString(R.string.limit_10);


        mMedalManagerAdapter = new MedalManagerAdapter(R.layout.medal_manager_item);
        mLinearLayoutManager = new LinearLayoutManager(this);
        rv_medal_list.setAdapter(mMedalManagerAdapter);
        rv_medal_list.setLayoutManager(mLinearLayoutManager);
        mMedalManagerAdapter.setOnItemChildClickListener(this);
        mMedalManagerAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_medal_list.getParent());
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, MedalManagerUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        mMedalManagerP = new MedalManagerP(this, getActivity());
        mDeleteMedalP = new DeleteMedalP(this, getActivity());
        mMedalManagerP.getMedalList(C,collegeId,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMedalManagerAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.iv_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_right://新增勋章
                if(medalManagerList.size()<10){
                    Intent starter = new Intent(this,AddMedalUI.class);
                    starter.putExtra(MEDAL_TYPE,MEDAL_ADD);
                    starter.putExtra(MEDAL_INFO,"");
                    startActivityForResult(starter,REQUEST_CODE_ADD);
                }else {
                    makeText(getResources().getString(R.string.medal_num_tip));
                }
                break;
            default:
                break;
        }
    }
    /**
     * 获取勋章列表成功的回调
     * @param medalTypeList
     */
    @Override
    public void medalListSuccess(List<MedalInfoBean> medalTypeList) {
        this.medalManagerList=medalTypeList;
        mMedalManagerAdapter.setNewData(medalManagerList);
        mMedalManagerAdapter.notifyDataSetChanged();
    }
    /**
     * 删除勋章成功的回调
     * @param position
     */
    @Override
    public void deleteMedalSuccess(int position) {
        medalManagerList.remove(position);
        mMedalManagerAdapter.notifyDataSetChanged();
    }
    /**
     * 侧滑删除及条目点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.tv_delete://删除
                mDeleteMedalP.deleteMedal(C,collegeId,medalManagerList.get(position).getMedalTypeId()+"",position);
                break;
            case R.id.tv_edit://编辑
                checkedEditPosition=position;
                Intent starter = new Intent(this,AddMedalUI.class);
                starter.putExtra(MEDAL_TYPE,MEDAL_MODIFY);
                starter.putExtra(MEDAL_INFO,medalManagerList.get(position));
                startActivityForResult(starter,REQUEST_CODE_MODIFY);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==AddMedalUI.RESULT_CODE){
            if(requestCode==REQUEST_CODE_ADD) {
                mMedalManagerP.getMedalList(C,collegeId,PAGE+"",LIMIT);
            }else if(requestCode==REQUEST_CODE_MODIFY){
                MedalInfoBean medalInfoBean = data.getParcelableExtra(MEDAL_INFO);
                medalManagerList.set(checkedEditPosition,medalInfoBean);
                mMedalManagerAdapter.setNewData(medalManagerList);
                mMedalManagerAdapter.notifyDataSetChanged();
                //mMedalManagerP.getMedalList(C,collegeId,PAGE+"",LIMIT);
            }
        }
    }

}
