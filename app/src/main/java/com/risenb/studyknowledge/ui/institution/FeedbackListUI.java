package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.FeedbackAdapter;
import com.risenb.studyknowledge.beans.institution.AdviceListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.SPUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.List;

/**
 * Created by yy on 2017/9/29.
 * 意见反馈列表
 */
@ContentView(R.layout.ui_feedback_list)
public class FeedbackListUI extends BaseUI implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemClickListener, FeedbackSelectFragment.OnFeedbackSelectListener, FeedbackListP.FeedbackListFace {
    @ViewInject(R.id.refresh_layout)
    MyRefreshLayout refresh_layout;
    @ViewInject(R.id.rv_feedback_list)
    RecyclerView rv_feedback_list;//意见反馈列表
    @ViewInject(R.id.iv_right)
    ImageView iv_right;
    @ViewInject(R.id.tv_right)
    TextView tv_right;
    private List<AdviceListBean> mAdviceList;
    private FeedbackAdapter mFeedbackAdapter;
    private FeedbackSelectFragment mSelectFragment;
    private boolean isShowRead=false;
    public static String SELECT="select";
    private int PAGE=1;
    private String LIMIT;
    private String TIMESTAMP="";
    private String KEY="";
    private String C="1651";
    private String CollegeId="45";
    private FeedbackListP mFeedbackListP;
    public static final int IS_READ=1;//已读
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
        setTitle(getResources().getString(R.string.feedback));//意见反馈
        rightVisible(getResources().getString(R.string.whole),R.mipmap.down_arrow_white);
        LIMIT=getResources().getString(R.string.limit_10);
        //清空首选项
        SPUtils.put(this,SELECT,"");//清空排序首选相

        mFeedbackAdapter = new FeedbackAdapter(R.layout.feedback_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_feedback_list.setAdapter(mFeedbackAdapter);
        rv_feedback_list.setLayoutManager(linearLayoutManager);
        refresh_layout.setMyRefreshLayoutListener(this);
        mFeedbackAdapter.setOnItemClickListener(this);
        mFeedbackAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) rv_feedback_list.getParent());
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, FeedbackListUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        mFeedbackListP = new FeedbackListP(this, getActivity());
        mFeedbackListP.getFeedbackList(mFeedbackListP.REFRESH,C,CollegeId,KEY,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_feedback_platform,R.id.ll_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_right://全部/已读/未读
                isShowRead=!isShowRead;
                showFragment(isShowRead);
                break;
            case R.id.tv_feedback_platform://反馈至平台
                AddFeedbackUI.start(this);
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
        search();
    }
    public void search(){
        PAGE=1;
        TIMESTAMP="";
        mFeedbackListP.getFeedbackList(mFeedbackListP.REFRESH,C,CollegeId,KEY,TIMESTAMP,PAGE+"",LIMIT);
    }
    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        PAGE++;
        mFeedbackListP.getFeedbackList(mFeedbackListP.LOAD,C,CollegeId,KEY,TIMESTAMP,PAGE+"",
                LIMIT);
    }

    /**
     * 条目点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if(mAdviceList.get(position).getAdviceReadyn()!=IS_READ){
            mFeedbackListP.isReadFeedback(C,mAdviceList.get(position).getAdviceId()+"",position);
        }
        FeedbackDetailUI.start(this,mAdviceList.get(position));
    }
    /**
     * 显示查询时间弹窗
     * @param show
     */
    private void showFragment(boolean show) {
        if (mSelectFragment == null) {
            mSelectFragment = new FeedbackSelectFragment();
            mSelectFragment.setOnFeedbackSelectListener(this);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mSelectFragment.isAdded()){
                return;
            }
            transaction.add(R.id.fl_whole,mSelectFragment).commit();
            iv_right.setImageResource(R.mipmap.up_arrow_white);
        }else {
            transaction.remove(mSelectFragment).commit();
            iv_right.setImageResource(R.mipmap.down_arrow_white);
        }
    }

    @Override
    public void closeSelectListener(View view, String selet,String key) {
        tv_right.setText(selet);
        isShowRead=false;
        showFragment(isShowRead);
        SPUtils.put(this,SELECT,selet);

        this.KEY=key;
        //搜索选中类型的反馈列表
        Utils.getUtils().showProgressDialog(getActivity(), null);
        search();
    }

    /**
     * 获取意见反馈列表成功的回调
     * @param tag
     * @param timestamp
     * @param result
     */
    @Override
    public void getAdviceListSuccess(String tag, String timestamp, List<AdviceListBean> result) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if(TextUtils.equals(tag,mFeedbackListP.REFRESH)){
            refresh_layout.refreshComplete();
        }else if(TextUtils.equals(tag,mFeedbackListP.LOAD)){
            refresh_layout.loadMoreComplete();
        }
        TIMESTAMP=timestamp;
        //意见反馈列表
        this.mAdviceList=result;
        mFeedbackAdapter.setNewData(mAdviceList);
        mFeedbackAdapter.notifyDataSetChanged();
    }

    /**
     * 获取意见反馈失败的回调
     */
    @Override
    public void getAdviceListFail() {
        Utils.getUtils().dismissDialog();
        refresh_layout.refreshComplete();
        refresh_layout.loadMoreComplete();
    }

    /**
     * 意见反馈已读成功的回调
     * @param position
     */
    @Override
    public void isReadFeedbackSuccess(int position) {
        mAdviceList.get(position).setAdviceReadyn(IS_READ);//已读
        mFeedbackAdapter.notifyDataSetChanged();
    }
}
