package com.risenb.studyknowledge.ui.post;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.post.PostsCourseAdapter;
import com.risenb.studyknowledge.beans.posts.PostListBean;
import com.risenb.studyknowledge.ui.LazyLoadFragment;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;
import com.risenb.studyknowledge.views.DividerItemDecoration;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzh on 2017/9/26.
 * <p>
 * 帖子课程列表
 */

public class PostsCourseFragment extends LazyLoadFragment implements BaseQuickAdapter.OnItemClickListener, MyRefreshLayoutListener, PostsCourseFragmentP.PostsCourseFace {
    @ViewInject(R.id.mrl_posts_course)
    private MyRefreshLayout mrl_posts_course;
    @ViewInject(R.id.rv_posts_course)
    private RecyclerView rv_posts_course;

    private PostsCourseAdapter mAdapter;
    private List<PostListBean> listData = new ArrayList<>();
    private int postType;//1 课程   2 大家谈  3 有偿提问
    private String key = "";
    private PostsCourseFragmentP postsCourseP;
    private int PAGE = 1;
    private String LIMIT = "10";
    private String TIMESTAMP = "";

    private String postTopicId;
    private String type = "1"; //1 帖子列表  2 搜索
    private int sType;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.posts_course_fm, container, false);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setControlBasis() {
        EventBus.getDefault().register(this);
        key = TextUtils.isEmpty(getKey()) ? "" : getKey();
        postType = getPostType();

        mAdapter = new PostsCourseAdapter(R.layout.posts_list_item, listData);
        rv_posts_course.setAdapter(mAdapter);
        rv_posts_course.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv_posts_course.getParent());

        //条目点击
        mAdapter.setOnItemClickListener(this);
        //添加分隔线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), R.drawable.divider_activity_line, LinearLayoutManager.VERTICAL);
        rv_posts_course.addItemDecoration(itemDecoration);
        //刷新加载
        mrl_posts_course.setMyRefreshLayoutListener(this);
    }

    public void onFirstUserVisible() {
//        Utils.getUtils().showProgressDialog(getActivity(), null);
//        postsCourseP = new PostsCourseFragmentP(this, getActivity());
//        postsCourseP.getPostList(postsCourseP.REFRESH,C,"343",TIMESTAMP,PAGE+"",LIMIT,type,postType+"",key);
    }

    @Override
    public void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        postsCourseP = new PostsCourseFragmentP(this, getActivity());
        postsCourseP.getPostList(postsCourseP.REFRESH, application.getC(), postTopicId, TIMESTAMP, PAGE + "", LIMIT, type, postType + "", key);
    }

    /**
     * 搜索请求
     *
     * @param
     */
    public void startRequest(int searchType) {
        PAGE = 1;
        TIMESTAMP = "";
        type = "2";
        sType = searchType;
        Utils.getUtils().showProgressDialog(getActivity(), null);
        postsCourseP.getSearchPostList(postsCourseP.REFRESH, application.getC(), postTopicId, TIMESTAMP, PAGE + "", LIMIT, type, sType + "", key);
    }

    /**
     * 条目点击
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        int postType = listData.get(position).getPostType();
        if (postType == 1 || postType == 2) {
            PostsDetailsUI.start(getActivity(), listData.get(position));
        } else if (postType == 3) {
            PostsDetailsValueUI.start(getActivity(), listData.get(position));
        }
    }

    @Override
    public void onRefresh(View view) {
        PAGE = 1;
        type = "1";
        postsCourseP.getPostList(postsCourseP.REFRESH, application.getC(), postTopicId, TIMESTAMP, PAGE + "", LIMIT, type, postType + "", key);
    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        if ("1".equals(type)) {
            postsCourseP.getPostList(postsCourseP.LOAD, application.getC(), postTopicId, TIMESTAMP, PAGE + "", LIMIT, "1", postType + "", key);
        } else if ("2".equals(type)) {
            postsCourseP.getSearchPostList(postsCourseP.LOAD, application.getC(), postTopicId, TIMESTAMP, PAGE + "", LIMIT, "2", sType + "", key);
        }
    }


    /**
     * @return
     */
    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public void setPostTopicId(String postTopicId) {
        this.postTopicId = postTopicId;
    }

    /**
     * 搜索关键字
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 帖子列表请求成功
     *
     * @param tag
     * @param time
     * @param list
     */
    @Override
    public void postListSuccess(String tag, String time, List<PostListBean> list) {
        Log.i("list", list.size() + "");
        Utils.getUtils().dismissDialog();
        if (TextUtils.equals(tag, postsCourseP.REFRESH)) {
            mrl_posts_course.refreshComplete();
        } else if (TextUtils.equals(tag, postsCourseP.LOAD)) {
            mrl_posts_course.loadMoreComplete();
        }
        TIMESTAMP = time;
        listData = list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 帖子列表请求失败
     */
    @Override
    public void postListFail() {
        Utils.getUtils().dismissDialog();
        mrl_posts_course.refreshComplete();
        mrl_posts_course.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 搜索请求成功
     *
     * @param listSearch
     */
    @Override
    public void postSearchListSuccess(String tag, String time, List<PostListBean> listSearch) {
        Utils.getUtils().dismissDialog();
        if (TextUtils.equals(tag, postsCourseP.REFRESH)) {
            mrl_posts_course.refreshComplete();
        } else if (TextUtils.equals(tag, postsCourseP.LOAD)) {
            mrl_posts_course.loadMoreComplete();
        }
        TIMESTAMP = time;
        listData = listSearch;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }


    @Subscribe
    public void postEvent(PostEvent postEvent) {
        if (PostEvent.RELEASE_SUCCESS == postEvent.getEventType()) {
            PAGE = 1;
            type = "1";
            postsCourseP.getPostList(postsCourseP.REFRESH, application.getC(), postTopicId, TIMESTAMP, PAGE + "", LIMIT, type, postType + "", key);
        }
    }
}
