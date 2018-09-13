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
import com.risenb.studyknowledge.adapter.institution.AnnounceAdapter;
import com.risenb.studyknowledge.beans.institution.AnnounceBean;
import com.risenb.studyknowledge.beans.institution.CashDetailsBean;
import com.risenb.studyknowledge.beans.institution.MedalInfoBean;
import com.risenb.studyknowledge.beans.institution.NoticeListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.personal.BlacklistP;
import com.risenb.studyknowledge.ui.personal.MemberDetailUI;
import com.risenb.studyknowledge.ui.personal.MemberSettingUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/29.
 * 公告编辑
 */
@ContentView(R.layout.ui_announce_list)
public class AnnounceEditUI extends BaseUI implements MyRefreshLayoutListener, AnnounceEditListP.AnnounceEditListFace, BaseQuickAdapter.OnItemChildClickListener {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_announce_list)
    RecyclerView rv_announce_list;//公告列表
    private List<NoticeListBean> mAnnounceList;
    private AnnounceAdapter mAnnounceAdapter;
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String C="1643";
    private String CollegeId="45";
    private AnnounceEditListP mAnnounceEditListP;
    public static final String ANNOUNCE_INFO="announce_info";
    public static final int REQUEST_CODE_ADD=0;
    public static final int REQUEST_CODE_MODIFY=1;
    public static final int RESULT_CODE=10;
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
        setTitle(getResources().getString(R.string.announcement_edit));//公告编辑
        rightVisible(getResources().getString(R.string.new_add));//新增
        LIMIT=getResources().getString(R.string.limit_10);

        mAnnounceAdapter = new AnnounceAdapter(R.layout.announce_edit_item);
        mLinearLayoutManager = new LinearLayoutManager(this);
        rv_announce_list.setAdapter(mAnnounceAdapter);
        rv_announce_list.setLayoutManager(mLinearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mAnnounceAdapter.setOnItemChildClickListener(this);
        mAnnounceAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_announce_list.getParent());
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, AnnounceEditUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mAnnounceEditListP = new AnnounceEditListP(this, getActivity());
        mAnnounceEditListP.getAnnounceList(mAnnounceEditListP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_right://新增
                Intent starter = new Intent(this,AddAnnounceUI.class);
                startActivityForResult(starter,REQUEST_CODE_ADD);
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
        onPageOneDate();
    }
    public void onPageOneDate(){
        PAGE=1;
        TIMESTAMP="";
        mAnnounceEditListP.getAnnounceList(mAnnounceEditListP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mAnnounceEditListP.getAnnounceList(mAnnounceEditListP.LOAD,C,CollegeId,TIMESTAMP,PAGE+"",
                LIMIT);
    }

    /**
     * 公告列表成功的回调
     * @param tag
     * @param timestamp
     * @param result
     */
    @Override
    public void getAnnounceListSuccess(String tag, String timestamp, List<NoticeListBean> result) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mAnnounceEditListP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mAnnounceEditListP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;

        //公告列表
        this.mAnnounceList=result;
        mAnnounceAdapter.setNewData(mAnnounceList);
        mAnnounceAdapter.notifyDataSetChanged();
    }

    /**
     * 公告列表失败的回调
     */
    @Override
    public void getAnnounceListFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }

    /**
     * 删除公告成功的回调
     */
    @Override
    public void deleteAnnounceSuccess(int position) {
        mAnnounceList.remove(position);
        mAnnounceAdapter.notifyDataSetChanged();
    }

    /**
     * 侧滑及条目点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.tv_delete://删除
                mAnnounceEditListP.deleteAnnounce(C,mAnnounceList.get(position).getNoticeId()+"",position);
                break;
            case R.id.content://条目点击事件
                this.checkedEditPosition=position;
                Intent starter = new Intent(this,AnnounceDetailUI.class);
                starter.putExtra(ANNOUNCE_INFO,mAnnounceList.get(position));
                startActivityForResult(starter,REQUEST_CODE_MODIFY);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_CODE){
            if(requestCode==REQUEST_CODE_ADD) {
                mLinearLayoutManager.scrollToPosition(0);
                onPageOneDate();
            }else if(requestCode==REQUEST_CODE_MODIFY){
                NoticeListBean noticeListBean = data.getParcelableExtra(ANNOUNCE_INFO);
                mAnnounceList.set(checkedEditPosition,noticeListBean);
                mAnnounceAdapter.setNewData(mAnnounceList);
                mAnnounceAdapter.notifyDataSetChanged();
            }
        }
    }
}
