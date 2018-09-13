package com.risenb.studyknowledge.ui.post;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.post.PostsTaskAdapter;
import com.risenb.studyknowledge.beans.posts.PostListBean;
import com.risenb.studyknowledge.beans.posts.PostsDetailsBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.KeyboardUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;
import com.risenb.studyknowledge.utils.ToolUtils;
import com.risenb.studyknowledge.utils.evntBusBean.PostEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/11/28.
 * 有偿贴子类型
 */
@ContentView(R.layout.posts_details_value)
public class PostsDetailsValueUI extends BaseUI implements PostsDetailsP.PostsDetailsFace, CommentPostP.CommentPostListener, BaseQuickAdapter.OnItemChildClickListener, ReplyCommentP.ReplyCommentListener {

    @ViewInject(R.id.iv_head)
    private ImageView iv_head;
    @ViewInject(R.id.tv_nickname)
    private TextView tv_nickname;
    @ViewInject(R.id.tv_attention_num)
    private TextView tv_attention_num;
    @ViewInject(R.id.wv_post_content)
    private WebView wv_post_content;
    @ViewInject(R.id.tv_money_reward)
    private TextView tv_money_reward;
    @ViewInject(R.id.tv_peep_num)
    private TextView tv_peep_num;


    @ViewInject(R.id.tv_comment)
    private TextView tv_comment;

    @ViewInject(R.id.et_comment_content)
    private EditText et_comment_content;

    @ViewInject(R.id.ll_comment)
    private LinearLayout ll_comment;


    @ViewInject(R.id.rv_posts_task)
    private RecyclerView rv_posts_task;

    private String postType = "1";
    private String postId;
    private PostsDetailsP postsDetailsP;
    private int PAGE = 1;
    private String LIMIT = "10";
    private String TIMESTAMP = "";
    private PostsDetailsBean.PostContentBean contentBean;
    private CommentPostP mCommentPostP;
    private PostListBean postListBean;
    public PostsTaskAdapter mAdapter;
    private boolean isFloorComment;
    private ReplyCommentP mReplyCommentP;
    private String mFloorId;
    private String mFloorUserId;
    private String mFloorUserName;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context, PostListBean postListBean) {
        Intent starter = new Intent(context, PostsDetailsValueUI.class);
        starter.putExtra("postListBean", postListBean);
        context.startActivity(starter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setControlBasis() {
        EventBus.getDefault().register(this);
        StatusBarUtils.transparencyBar(this);
        rightVisible(R.mipmap.edit_iv);
        postListBean = (PostListBean) getIntent().getSerializableExtra("postListBean");
        postId = String.valueOf(postListBean.getPostId());
    }

    @Override
    protected void prepareData() {
        postsDetailsP = new PostsDetailsP(this, getActivity());
        mCommentPostP = new CommentPostP(this, this);
        mReplyCommentP = new ReplyCommentP(this, getActivity());
        postsDetailsP.getYouChangDetail(postId, TIMESTAMP, String.valueOf(PAGE), LIMIT);

        rv_posts_task.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({R.id.tv_comment, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_comment:
                tv_comment.setVisibility(View.GONE);
                ll_comment.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_commit:
                commentPostOrWork();
                break;
        }
    }


    /**
     * 评论贴子
     */
    private void commentPostOrWork() {
        String commentContent = et_comment_content.getText().toString().trim();
        if (TextUtils.isEmpty(commentContent)) {
            ToastUtils.showToast("请输入评论内容");
            return;
        }

        //回复贴子
        if (!isFloorComment) {
            mCommentPostP.setCommentPost(String.valueOf(postListBean.getPostId()), postType, commentContent);
        } else {
            //回复楼层
            mReplyCommentP.setReplyComment(String.valueOf(postListBean.getPostId())
                    , mFloorId, application.getC(), mFloorUserId, commentContent);
        }
    }

    /**
     * 评论成功后初始化
     */
    private void initComment() {
        ll_comment.setVisibility(View.GONE);
        tv_comment.setVisibility(View.VISIBLE);
        et_comment_content.setText("");
        KeyboardUtils.hideKeyBoard(this, tv_comment);
    }

    @Override
    public void postsDetailsSuccess(PostsDetailsBean postsDetailsBean) {
        Utils.getUtils().dismissDialog();
        contentBean = postsDetailsBean.getPostContent();
        GlideImageLoader.create(iv_head).loadCircleImage(contentBean.getUserImg(), R.mipmap.unify_circle_head);
        tv_nickname.setText(contentBean.getUserName());
        tv_attention_num.setText(String.valueOf(contentBean.getAttentionNum()));
        tv_money_reward.setText("赏金" + contentBean.getPostReward());
        tv_peep_num.setText(String.valueOf(contentBean.getPostPeepNum()));

        //没有人偷看贴子。则不显示评论，
        if (postsDetailsBean.isIsUpdated()) {
            //评论区域
            mAdapter = new PostsTaskAdapter(R.layout.posts_task_item, postsDetailsBean.getPostCommentList());
            rv_posts_task.setAdapter(mAdapter);
            mAdapter.setOnItemChildClickListener(this);
        }

        //帖子内容
        String html = ToolUtils.imgStyleHtml(contentBean.getPostContent());
        wv_post_content.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        wv_post_content.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }


    @Override
    public void postsDetailsListFail() {
        Utils.getUtils().dismissDialog();
    }

    @Override
    public void commentPostSuccess() {
        EventBus.getDefault().post(new PostEvent().setEventType(PostEvent.COMMENT_SUCCESS));
        initComment();
    }

    @Override
    public void replyCommentSuccess() {
        EventBus.getDefault().post(new PostEvent().setEventType(PostEvent.COMMENT_SUCCESS));
        initComment();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        //回复楼层贴子
        PostsDetailsBean.PostCommentListBean item = (PostsDetailsBean.PostCommentListBean) adapter.getData().get(position);
        tv_comment.setVisibility(View.GONE);
        ll_comment.setVisibility(View.VISIBLE);
        mFloorId = String.valueOf(item.getFloorInfo().getFloorId());
        mFloorUserId = item.getFloorInfo().getFloorUserId();
    }

    @Subscribe
    public void replyCommentEvent(PostEvent postEvent) {

        switch (postEvent.getEventType()) {
            case PostEvent.REPLY_POST_COMMENT:
                initFloorComment();
                String postDataSt = (String) postEvent.getData();
                String[] split2 = postDataSt.split("=");
                mFloorUserId = split2[0];
                mFloorUserName = split2[1];
                mFloorId = split2[2];
                break;

            case PostEvent.COMMENT_SUCCESS:
                PAGE = 1;
                postsDetailsP.getYouChangDetail(postId, TIMESTAMP, String.valueOf(PAGE), LIMIT);
                break;
        }
    }

    private void initFloorComment() {
        isFloorComment = true;
        ll_comment.setVisibility(View.VISIBLE);
        tv_comment.setVisibility(View.GONE);
        et_comment_content.setText("");
    }


}
