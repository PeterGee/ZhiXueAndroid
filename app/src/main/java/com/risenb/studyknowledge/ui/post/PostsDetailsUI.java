package com.risenb.studyknowledge.ui.post;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
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

import java.util.ArrayList;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/10/9.
 * 无偿贴子类型
 */
@ContentView(R.layout.posts_details_ui)
public class PostsDetailsUI extends BaseUI implements PostsDetailsP.PostsDetailsFace, CommentPostP.CommentPostListener, ReplyCommentP.ReplyCommentListener {

    @ViewInject(R.id.tv_comment)
    private TextView tv_comment;


    @ViewInject(R.id.et_comment_content)
    private EditText et_comment_content;

    @ViewInject(R.id.ll_comment)
    private LinearLayout ll_comment;


    @ViewInject(R.id.iv_head)
    private ImageView iv_head;
    @ViewInject(R.id.tv_nickname)
    private TextView tv_nickname;
    @ViewInject(R.id.tv_attention_num)
    private TextView tv_attention_num;
    @ViewInject(R.id.wv_post_content)
    private WebView wv_post_content;

    @ViewInject(R.id.tab_posts_details)
    private SlidingTabLayout tab_posts_details;
    @ViewInject(R.id.vp_content)
    private ViewPager vp_content;
    @ViewInject(R.id.ll_post_details_head)
    private LinearLayout ll_post_details_head;

    private boolean isShow = false;
    private PostsDetailsP postsDetailsP;
    private int PAGE = 1;
    private String LIMIT = "10";
    private String TIMESTAMP = "";
    private PostsDetailsBean contentBean;
    public CommentPostP mCommentPostP;
    private ReplyCommentP mReplyCommentP;
    public String mFloorUserName;
    private String mFloorId;
    private PostListBean postListBean;
    private String postType;
    public String mFloorUserId;
    private boolean isFloorComment;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context, PostListBean postListBean) {
        Intent starter = new Intent(context, PostsDetailsUI.class);
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
        setTitle("");
        rightVisible(R.mipmap.edit_iv);

        postListBean = (PostListBean) getIntent().getSerializableExtra("postListBean");
        postType = String.valueOf(postListBean.getPostType());
        //设置头部随着滚动
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) ll_post_details_head.getLayoutParams();
        layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);


        String[] strings = {"讨论", "作业"};
        ArrayList<Fragment> fragmentList = new ArrayList<>();

        PostsDetailsTaskFragment postsFragment1 = new PostsDetailsTaskFragment();
        postsFragment1.setType(1);
        postsFragment1.setPostId(String.valueOf(postListBean.getPostId()));
        fragmentList.add(postsFragment1);

        WorksListDetailsFragment postsFragment2 = new WorksListDetailsFragment();
        postsFragment2.setType(2);
        postsFragment2.setPostId(String.valueOf(postListBean.getPostId()));
        fragmentList.add(postsFragment2);


        tab_posts_details.setViewPager(vp_content, strings, this, fragmentList);

        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                postType = String.valueOf(position + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        postsDetailsP = new PostsDetailsP(this, getActivity());
        mCommentPostP = new CommentPostP(this, this);
        mReplyCommentP = new ReplyCommentP(this, getActivity());
        postsDetailsP.getPostDetail(String.valueOf(postListBean.getPostId()), TIMESTAMP, PAGE + "", LIMIT);


    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @OnClick({R.id.tv_comment, R.id.ll_comment, R.id.tv_commit, R.id.ll_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_comment:
                tv_comment.setVisibility(View.GONE);
                ll_comment.setVisibility(View.VISIBLE);
                isFloorComment = false;
                break;
            case R.id.tv_commit:
                commentPostOrWork();
                break;
            //编辑贴子
            case R.id.ll_right:
                ReleasePostUI.start(
                        view.getContext(),
                        String.valueOf(postListBean.getPostId()),
                        postListBean.getPostName(),
                        String.valueOf(postListBean.getPostIsFree()),
                        postListBean.getPostReward(),
                        String.valueOf(postListBean.getPostIsTop()), postType);
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
     * 帖子详情请求成功
     *
     * @param postsDetailsBean
     */
    @Override
    public void postsDetailsSuccess(PostsDetailsBean postsDetailsBean) {
        Utils.getUtils().dismissDialog();
        contentBean = postsDetailsBean;
        GlideImageLoader.create(iv_head).loadCircleImage(contentBean.getPostContent().getUserImg(), R.mipmap.unify_circle_head);
        tv_nickname.setText(contentBean.getPostContent().getUserName());
        tv_attention_num.setText(contentBean.getPostContent().getAttentionNum() + "");

        //帖子内容
        String html = ToolUtils.imgStyleHtml(contentBean.getPostContent().getPostContent());
        wv_post_content.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        WebSettings settings = wv_post_content.getSettings();
        settings.setJavaScriptEnabled(true);
        wv_post_content.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

    }


    @Override
    public void postsDetailsListFail() {

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


    /**
     * 评论成功后初始化
     */
    private void initComment() {
        ll_comment.setVisibility(View.GONE);
        tv_comment.setVisibility(View.VISIBLE);
        et_comment_content.setText("");
        mFloorUserId = "";
        isFloorComment = false;
        KeyboardUtils.hideKeyBoard(this, tv_comment);
    }


    @Subscribe
    public void replyCommentEvent(PostEvent postEvent) {

        switch (postEvent.getEventType()) {
            case PostEvent.REPLY_POST:

                initFloorComment();

                PostsDetailsBean.PostCommentListBean postData = (PostsDetailsBean.PostCommentListBean) postEvent.getData();
                mFloorId = String.valueOf(postData.getFloorInfo().getFloorId());
                mFloorUserName = postData.getFloorInfo().getUserName();
                mFloorUserId = postData.getFloorInfo().getFloorUserId();
                break;

            case PostEvent.REPLY_WORK:
                initFloorComment();
                PostsDetailsBean.WorkCommentListBean workData = (PostsDetailsBean.WorkCommentListBean) postEvent.getData();
                mFloorId = String.valueOf(workData.getFloorInfo().getFloorId());
                mFloorUserName = workData.getFloorInfo().getUserName();
                mFloorUserId = workData.getFloorInfo().getFloorUserId();
                break;

            case PostEvent.REPLY_WORK_COMMENT:
                initFloorComment();
                String workDataSt = (String) postEvent.getData();
                String[] split = workDataSt.split("=");
                mFloorUserId = split[0];
                mFloorUserName = split[1];
                mFloorId = split[2];
                break;

            case PostEvent.REPLY_POST_COMMENT:
                initFloorComment();
                String postDataSt = (String) postEvent.getData();
                String[] split2 = postDataSt.split("=");
                mFloorUserId = split2[0];
                mFloorUserName = split2[1];
                mFloorId = split2[2];
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
