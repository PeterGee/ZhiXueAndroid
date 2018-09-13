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
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.BusinessInAdapter;
import com.risenb.studyknowledge.beans.institution.BusinessInOutBean;
import com.risenb.studyknowledge.beans.institution.BusinessInfoBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.personal.BlacklistP;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/15.
 * 友商购进
 */
@ContentView(R.layout.ui_business_in_out)
public class BusinessInUI  extends BaseUI implements MyRefreshLayoutListener, BusinessInP.BusinessInFace, BaseQuickAdapter.OnItemChildClickListener {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_business_list)
    RecyclerView rv_businessIn_list;//友商购进列表
    private List<BusinessInfoBean> mBusinessInList;
    private BusinessInAdapter mBusinessInAdapter;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String CollegeId="45";
    private BusinessInP mBusinessInP;

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
        setTitle(getResources().getString(R.string.friendly_business_in));//友商管理(购进)
        LIMIT=getResources().getString(R.string.limit_10);

        mBusinessInAdapter = new BusinessInAdapter(R.layout.business_in_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_businessIn_list.setAdapter(mBusinessInAdapter);
        rv_businessIn_list.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mBusinessInAdapter.setOnItemChildClickListener(this);
        mBusinessInAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_businessIn_list.getParent());
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, BusinessInUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mBusinessInP = new BusinessInP(this, getActivity());
        mBusinessInP.getBusinessInList(mBusinessInP.REFRESH,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
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
        mBusinessInP.getBusinessInList(mBusinessInP.REFRESH,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mBusinessInP.getBusinessInList(mBusinessInP.LOAD,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    /**
     * 获取友商购进列表成功的回调
     * @param tag
     * @param timestamp
     * @param result
     */
    @Override
    public void getBusinessInListSuccess(String tag, String timestamp, List<BusinessInfoBean> result) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mBusinessInP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mBusinessInP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;

        this.mBusinessInList=result;
        mBusinessInAdapter.setNewData(mBusinessInList);
        mBusinessInAdapter.notifyDataSetChanged();
    }

    /**
     * 获取友商购进列表失败的回调
     */
    @Override
    public void getBusinessInListFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }

    /**
     * 删除友商成功的回调
     * @param position
     */
    @Override
    public void deleteBusinessTopicSuccess(int position) {
        mBusinessInList.remove(position);
        mBusinessInAdapter.notifyDataSetChanged();
    }

    /**
     * 侧滑删除
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.tv_delete://删除
                mBusinessInP.deleteBusiness(mBusinessInList.get(position).getBuytopicId()+"",position);
                break;
            default:
                break;
        }
    }
}
