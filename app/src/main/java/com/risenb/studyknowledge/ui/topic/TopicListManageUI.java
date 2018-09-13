package com.risenb.studyknowledge.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.lidroid.mutils.utils.Log;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.topic.TopicListAdapter;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.VibratorUtil;
import com.risenb.studyknowledge.utils.evntBusBean.TopicEvent;
import com.risenb.studyknowledge.views.DividerItemDecoration;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhuzh on 2017/9/16.
 * <p>
 * 话题列表
 */
@ContentView(R.layout.topic_list_ui)
public class TopicListManageUI extends BaseUI implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemChildClickListener, OnItemDragListener, TopicListManageP.TopicListManageFace {
    @ViewInject(R.id.mrl_topic_list)
    private MyRefreshLayout mrl_topic_list;
    @ViewInject(R.id.rv_topic_list)
    private RecyclerView rv_topic_list;

    private TopicListAdapter mAdapter;
    private List<TopicListBean> listData = new ArrayList<>();
    private TopicListManageP topicListManageP;
    private int PAGE = 1;
    private String LIMIT = "10";
    private String TIMESTAMP = "";
    private String C = "702";
    private String CollegeId = "69";
    public static final String TOPIC_INFO = "topic_info";
    public static final int REQUEST_CODE_EDIT = 0;
    private int itemCheckedPosition;
    private int startPosition;


    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, TopicListManageUI.class);
        context.startActivity(starter);
    }

    @Override
    public void onResume() {
        super.onResume();
        PAGE = 1;
        topicListManageP.getTopicList(topicListManageP.REFRESH, C, CollegeId, TIMESTAMP, PAGE + "", LIMIT);
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.topic_list));
        rightVisible(R.mipmap.add_title_iv);

//        ArrayList<TopicsListBean> items = (ArrayList<TopicsListBean>) ACache.get(getActivity()).getAsObject("items");
//        if (items != null && !items.isEmpty())
//            listData.addAll(items);

//        for (int i = 0; i < 20; i++) {
//            TopicsListBean bean = new TopicsListBean();
//            bean.setTopicName("艺术博物馆学校" + i);
//            if(i%2==0){
//                bean.setAddStatus("已上架");
//            } else {
//                bean.setAddStatus("已下架");
//            }
//            listData.add(bean);
//        }

        mAdapter = new TopicListAdapter(R.layout.topic_list_item, listData,false);
        rv_topic_list.setAdapter(mAdapter);
        rv_topic_list.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv_topic_list.getParent());

        //添加分隔线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, R.drawable.divider_activity_line, LinearLayoutManager.VERTICAL);
        rv_topic_list.addItemDecoration(itemDecoration);
        mrl_topic_list.setMyRefreshLayoutListener(this);//刷新加载
        mAdapter.setOnItemChildClickListener(this);//侧滑菜单监听
//        mAdapter.setOnItemListener(this);//条目监听
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                makeText(listData.get(position).getActionName());
//            }
//        });

        //拖拽
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemDragAndSwipeCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(rv_topic_list);
        mAdapter.enableDragItem(itemTouchHelper, R.id.content, true);
        mAdapter.setOnItemDragListener(this);//拖拽监听


    }

    @OnClick({R.id.ll_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_right:
                AddTopicUI.start(this, null, AddTopicUI.FLAG_ADD);
                break;
            default:
                break;
        }
    }

    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        topicListManageP = new TopicListManageP(this, getActivity());
        if (PAGE != 1) {
            topicListManageP.getTopicList(topicListManageP.REFRESH, C, CollegeId, TIMESTAMP, PAGE + "", LIMIT);
        }
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
        topicListManageP.getTopicList(topicListManageP.REFRESH, C, CollegeId, TIMESTAMP, PAGE + "", LIMIT);

    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        topicListManageP.getTopicList(topicListManageP.LOAD, C, CollegeId, TIMESTAMP, PAGE + "", LIMIT);

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tv_menu_one:
                this.itemCheckedPosition = position;
                TopicListBean bean = listData.get(position);
                AddTopicUI.start(this, bean, AddTopicUI.FLAG_EDIT);
                break;
            case R.id.tv_menu_two:
                makeText(position + "");
                int topicId = listData.get(position).getTopicId();
                int topicUseyn = listData.get(position).getTopicUseyn();
                if (topicUseyn == 0) {
                    topicListManageP.isUpOrDown(C, topicId + "", "1", position);

                } else if (topicUseyn == 1) {
                    topicListManageP.isUpOrDown(C, topicId + "", "0", position);

                }
                break;
//            case R.id.content:
//                makeText(listData.get(position).getTopicName());
//                break;
            default:
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==RESULT_OK){
//            if(requestCode==REQUEST_CODE_EDIT) {
//                TopicListBean topicListBean = (TopicListBean) data.getSerializableExtra(TOPIC_INFO);
//                listData.set(itemCheckedPosition,topicListBean);
//                mAdapter.setNewData(listData);
//                mAdapter.notifyDataSetChanged();
//            }
//        }
//    }

    @Override
    public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
        BaseViewHolder holder = ((BaseViewHolder) viewHolder);
        holder.setBackgroundColor(R.id.content, getResources().getColor(R.color.gray));
        VibratorUtil.Vibrate(getActivity(), 70);   //震动70ms

        startPosition = pos;
    }

    @Override
    public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder viewHolder, int to) {
        BaseViewHolder holder = ((BaseViewHolder) viewHolder);
    }

    @Override
    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
        BaseViewHolder holder = ((BaseViewHolder) viewHolder);
        holder.setBackgroundColor(R.id.content, Color.WHITE);

        int endPosition = pos;
        int topicId1 = listData.get(startPosition).getTopicId();
        int topicId2 = listData.get(endPosition).getTopicId();

        Log.i("topicId1", topicId1 + "--" + topicId2);
        topicListManageP.updateSort(C, topicId1 + "", topicId2 + "");

        //存入缓存
//        ACache.get(getActivity()).put("items", (ArrayList<TopicsListBean>) listData);

    }

    /**
     * 话题列表请求成功
     *
     * @param tag
     * @param time
     * @param list
     */
    @Override
    public void topicListSuccess(String tag, String time, List<TopicListBean> list) {
        Utils.getUtils().dismissDialog();
        //关闭刷新或者加载更多动画
        if (TextUtils.equals(tag, topicListManageP.REFRESH)) {
            mrl_topic_list.refreshComplete();
        } else if (TextUtils.equals(tag, topicListManageP.LOAD)) {
            mrl_topic_list.loadMoreComplete();
        }
        TIMESTAMP = time;
        listData = list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void topicListFail() {
        Utils.getUtils().dismissDialog();
        mrl_topic_list.refreshComplete();
        mrl_topic_list.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void isUpOrDownSuccess(NetBaseBean result, String s, int position) {
        if (s.equals("1")) {
            listData.get(position).setTopicUseyn(1);
        } else if (s.equals("0")) {
            listData.get(position).setTopicUseyn(0);
        }
        mAdapter.notifyItemChanged(position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateSortSuccess(NetBaseBean result) {
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void topicEvent(TopicEvent topicEvent) {
        if (TopicEvent.UPDATE_TOPIC_LIST == topicEvent.getEventType()) {
//            PAGE = 1;
//            topicListManageP.getTopicList(topicListManageP.REFRESH, C, CollegeId, TIMESTAMP, PAGE + "", LIMIT);
        }
    }
}
