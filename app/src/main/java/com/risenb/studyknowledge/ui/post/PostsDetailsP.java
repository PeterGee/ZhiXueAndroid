package com.risenb.studyknowledge.ui.post;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.posts.PostsDetailsBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by zhuzh on 2017/11/28.
 */

public class PostsDetailsP extends PresenterBase {

    private PostsDetailsFace postsDetailsFace;

    public static final String REFRESH = "refresh";
    public static final String LOAD = "load";

    public PostsDetailsP(PostsDetailsFace postsDetailsFace, FragmentActivity activity) {
        this.postsDetailsFace = postsDetailsFace;
        setActivity(activity);
    }

    public void getPostDetail( String postId, String timestamp, String page, String limit) {

        NetworkUtils.getNetworkUtils().getPostDetail(application.getC(), postId, timestamp, page, limit, new DataCallback<PostsDetailsBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
            }

            @Override
            public void onSuccess(PostsDetailsBean result, int i) {
                postsDetailsFace.postsDetailsSuccess(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                postsDetailsFace.postsDetailsListFail();
            }
        });
    }


    public void getYouChangDetail( String postId, String timestamp, String page, String limit) {
        NetworkUtils.getNetworkUtils().getYouChangDetail(application.getC(), postId, timestamp, page, limit, new DataCallback<PostsDetailsBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
            }

            @Override
            public void onSuccess(PostsDetailsBean result, int i) {
                postsDetailsFace.postsDetailsSuccess(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                postsDetailsFace.postsDetailsListFail();
            }
        });
    }


    public interface PostsDetailsFace {
        void postsDetailsSuccess(PostsDetailsBean postsDetailsBean);
        void postsDetailsListFail();
    }
}
