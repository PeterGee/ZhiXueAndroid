package com.risenb.studyknowledge.ui.post;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.post.PostsTaskAdapter;
import com.risenb.studyknowledge.beans.posts.PostsDetailsBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;
import com.risenb.studyknowledge.views.DividerItemDecoration;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * Created by zhuzh on 2017/10/9.
 */

public class PostsDetailsTaskFragment extends BaseFragment implements MyRefreshLayoutListener, PostsDetailsP.PostsDetailsFace, BaseQuickAdapter.OnItemClickListener {

    @ViewInject(R.id.mrl_posts_task)
    private MyRefreshLayout mrl_posts_task;
    @ViewInject(R.id.rv_posts_task)
    private RecyclerView rv_posts_task;

    private PostsTaskAdapter mAdapter;
    private PostsDetailsP postsDetailsP;
    private String postId;
    private int PAGE = 1;
    private String LIMIT = "10";
    private String TIMESTAMP = "";
    private int type;//1讨论  2作业


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.posts_task_fm, container, false);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setControlBasis() {


        rv_posts_task.setLayoutManager(new LinearLayoutManager(getActivity()));

        //添加分隔线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), R.drawable.divider_activity_line, LinearLayoutManager.VERTICAL);
        rv_posts_task.addItemDecoration(itemDecoration);
        //刷新加载
        mrl_posts_task.setMyRefreshLayoutListener(this);
    }

    @Override
    public void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        postsDetailsP = new PostsDetailsP(this, getActivity());
        postsDetailsP.getPostDetail(postId, TIMESTAMP, PAGE + "", LIMIT);
    }


    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void onRefresh(View view) {
        PAGE = 1;
        postsDetailsP.getPostDetail(postId, TIMESTAMP, PAGE + "", LIMIT);
    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        postsDetailsP.getPostDetail(postId, TIMESTAMP, PAGE + "", LIMIT);
    }

    @Override
    public void postsDetailsSuccess(PostsDetailsBean postContentBean) {
        Utils.getUtils().dismissDialog();

        TIMESTAMP = postContentBean.getTimestamp();
        if (PAGE == 1) {
            mrl_posts_task.refreshComplete();
            mAdapter = new PostsTaskAdapter(R.layout.posts_task_item, postContentBean.getPostCommentList());
            rv_posts_task.setAdapter(mAdapter);
            //条目点击
            mAdapter.setOnItemClickListener(this);
        } else {
            mrl_posts_task.loadMoreComplete();
            mAdapter.addData(postContentBean.getPostCommentList());
        }
    }


    @Override
    public void postsDetailsListFail() {
        Utils.getUtils().dismissDialog();
        mrl_posts_task.refreshComplete();
        mrl_posts_task.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //回复楼层贴子
        PostsDetailsBean.PostCommentListBean item = (PostsDetailsBean.PostCommentListBean) adapter.getData().get(position);

        EventBus.getDefault().post(new PostEvent().setEventType(PostEvent.REPLY_POST).setData(item));
    }

    @Subscribe
    public void replyCommentEvent(PostEvent postEvent) {
        if (PostEvent.COMMENT_SUCCESS == postEvent.getEventType()) {
            PAGE = 1;
            postsDetailsP.getPostDetail(postId, TIMESTAMP, PAGE + "", LIMIT);
        }
    }

}
