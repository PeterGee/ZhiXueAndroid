package com.risenb.studyknowledge.ui.institution;

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
import com.risenb.studyknowledge.adapter.institution.BusinessInAdapter;
import com.risenb.studyknowledge.adapter.institution.BusinessOutAdapter;
import com.risenb.studyknowledge.beans.institution.BusinessInOutBean;
import com.risenb.studyknowledge.beans.institution.BusinessInfoBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/15.
 * 友商售出
 */
@ContentView(R.layout.ui_business_in_out)
public class BusinessOutUI extends BaseUI implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemChildClickListener, BusinessOutP.BusinessOutFace {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_business_list)
    RecyclerView rv_businessOut_list;//友商售出列表
    private List<BusinessInfoBean> mBusinessOutList;
    private BusinessOutAdapter mBusinessOutAdapter;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String CollegeId="45";
    private BusinessOutP mBusinessOutP;

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
        setTitle(getResources().getString(R.string.friendly_business_out));//友商管理(售出)
        rightVisible(getResources().getString(R.string.add_cooperation));//添加合作
        LIMIT=getResources().getString(R.string.limit_10);

        mBusinessOutAdapter = new BusinessOutAdapter(R.layout.business_out_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_businessOut_list.setAdapter(mBusinessOutAdapter);
        rv_businessOut_list.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mBusinessOutAdapter.setOnItemChildClickListener(this);
        mBusinessOutAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_businessOut_list.getParent());
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, BusinessOutUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mBusinessOutP = new BusinessOutP(this, getActivity());
        mBusinessOutP.getBusinessOutList(mBusinessOutP.REFRESH,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right://添加合作
                AddCooperationUI.start(this);
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
        mBusinessOutP.getBusinessOutList(mBusinessOutP.REFRESH,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mBusinessOutP.getBusinessOutList(mBusinessOutP.LOAD,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    /**
     * 侧滑点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.tv_delete://取消代理
                mBusinessOutP.deleteBusiness(mBusinessOutList.get(position).getBuytopicId()+"",position);
                break;
            case R.id.tv_edit://编辑代理
                break;
            default:
                break;
        }
    }

    /**
     * 获取友商售出列表成功的回调
     * @param tag
     * @param timestamp
     * @param result
     */
    @Override
    public void getBusinessOutListSuccess(String tag, String timestamp, List<BusinessInfoBean> result) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mBusinessOutP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mBusinessOutP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;

        this.mBusinessOutList=result;
        mBusinessOutAdapter.setNewData(mBusinessOutList);
        mBusinessOutAdapter.notifyDataSetChanged();
    }

    /**
     * 获取友商售出列表失败的回调
     */
    @Override
    public void getBusinessOutListFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }

    /**
     * 取消代理成功的回调
     * @param position
     */
    @Override
    public void deleteBusinessTopicSuccess(int position) {
        mBusinessOutList.remove(position);
        mBusinessOutAdapter.notifyDataSetChanged();
    }
}
