package com.risenb.studyknowledge.ui.post;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.live.PostLiveListBean;
import com.risenb.studyknowledge.beans.posts.PostListBean;
import com.risenb.studyknowledge.beans.posts.PostsCourseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.ui.institution.FeedbackDetailUI;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by zhezhu on 2017/11/23.
 */

public class PostsCourseFragmentP extends PresenterBase {

    private PostsCourseFace postsCourseFace;

    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private List<PostListBean> list;
    private List<PostListBean> listSearch;


    public PostsCourseFragmentP(PostsCourseFace postsCourseFace, FragmentActivity activity) {
        this.postsCourseFace = postsCourseFace;
        setActivity(activity);
    }

    public void getPostList(final String tag, String c, String postTopicId, String timestamp,
                            String page, String limit, String type, String postType, String key) {

        NetworkUtils.getNetworkUtils().getPostList(c,postTopicId,timestamp,page,limit,type,postType,key, new DataCallback<PostsCourseBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                postsCourseFace.postListFail();
            }

            @Override
            public void onSuccess(PostsCourseBean.DataBean result, int i) {
                String times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                if(TextUtils.equals(tag,REFRESH)){
                    list = result.getPostList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getPostList());
                    if(result.getPostList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                postsCourseFace.postListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                postsCourseFace.postListFail();
            }
        });
    }

    public void getSearchPostList(final String tag, String c, String postTopicId, String timestamp, String page
            , String limit, String type, String searchType, String key) {
        NetworkUtils.getNetworkUtils().getPostList(c,postTopicId,timestamp,page,limit,type,searchType,key, new DataCallback<PostsCourseBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                Utils.getUtils().dismissDialog();

            }

            @Override
            public void onSuccess(PostsCourseBean.DataBean result, int i) {
                String times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();

                if(TextUtils.equals(tag,REFRESH)){
                    listSearch = result.getPostList();
                }else if(TextUtils.equals(tag,LOAD)){
                    listSearch.addAll(result.getPostList());
                    if(result.getPostList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                postsCourseFace.postSearchListSuccess(tag,times,listSearch);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                Utils.getUtils().dismissDialog();

            }
        });
    }

    public interface PostsCourseFace{
        void postListSuccess(String tag, String time, List<PostListBean> list);
        void postListFail();
        void postSearchListSuccess(String tag, String time, List<PostListBean> listSearch);
    }
}
