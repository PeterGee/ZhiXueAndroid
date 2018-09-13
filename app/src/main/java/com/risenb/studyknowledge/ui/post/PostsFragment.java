package com.risenb.studyknowledge.ui.post;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.topic.TopicListAdapter;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.ui.LazyLoadFragment;
import com.risenb.studyknowledge.ui.topic.TopicListManageP;
import com.risenb.studyknowledge.utils.MyConfig;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;
import com.risenb.studyknowledge.views.DividerItemDecoration;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzh on 2017/9/13.
 * <p>
 * 帖子模块列表
 */

public class PostsFragment extends LazyLoadFragment implements MyRefreshLayoutListener,
//        , BaseQuickAdapter.OnItemChildClickListener,
        TopicListManageP.TopicListManageFace {
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
    private int itemCheckedPosition;


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.ui_post, container, false);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setControlBasis() {
        EventBus.getDefault().register(this);

        mAdapter = new TopicListAdapter(R.layout.topic_list_item, listData, true);
        rv_topic_list.setAdapter(mAdapter);
        rv_topic_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv_topic_list.getParent());


        //添加分隔线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), R.drawable.divider_activity_line, LinearLayoutManager.VERTICAL);
        rv_topic_list.addItemDecoration(itemDecoration);
        //刷新加载
        mrl_topic_list.setMyRefreshLayoutListener(this);

    }

    public void onFirstUserVisible() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        topicListManageP = new TopicListManageP(this, getActivity());
        topicListManageP.getTopicList(topicListManageP.REFRESH, application.getC(), "", TIMESTAMP, PAGE + "", LIMIT);
    }


    @Override
    public void prepareData() {

    }

    @Override
    public void onRefresh(View view) {
        PAGE = 1;
        topicListManageP.getTopicList(topicListManageP.REFRESH, application.getC(), "", TIMESTAMP, PAGE + "", LIMIT);


    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        topicListManageP.getTopicList(topicListManageP.LOAD, application.getC(), "", TIMESTAMP, PAGE + "", LIMIT);
    }

//    @Override
//    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        switch (view.getId()) {
//            case R.id.tv_menu_one:
//                this.itemCheckedPosition = position;
//                TopicListBean bean = listData.get(position);
//                ReleasePostUI.start(this, bean, AddTopicUI.FLAG_EDIT);
//                break;
//            case R.id.tv_menu_two:
//                makeText(position + "");
//                int topicId = listData.get(position).getTopicId();
//                int topicUseyn = listData.get(position).getTopicUseyn();
//                if (topicUseyn == 0) {
//                    topicListManageP.isUpOrDown(application.getC(), topicId + "", "1", position);
//
//                } else if (topicUseyn == 1) {
//                    topicListManageP.isUpOrDown(application.getC(), topicId + "", "0", position);
//
//                }
//                break;
//            default:
//                break;
//        }
//
//    }


    @Override
    public void topicListSuccess(String tag, String time, List<TopicListBean> list) {
        Utils.getUtils().dismissDialog();
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

    }

    @Override
    public void updateSortSuccess(NetBaseBean result) {

    }

    @Subscribe
    public void postEvent(PostEvent postEvent) {
        if (PostEvent.RELEASE_SUCCESS == postEvent.getEventType()) {
            PAGE = 1;
            topicListManageP.getTopicList(topicListManageP.REFRESH, application.getC(), "", TIMESTAMP, PAGE + "", LIMIT);
        }
    }
}
