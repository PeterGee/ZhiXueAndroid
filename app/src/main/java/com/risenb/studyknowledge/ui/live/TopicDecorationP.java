package com.risenb.studyknowledge.ui.live;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.beans.topic.TopicsListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/11/27.
 */

public class TopicDecorationP extends PresenterBase {

    private TopicDecorationFace topicDecorationFace;
    public static final String REFRESH = "refresh";
    public static final String LOAD = "load";
    private List<TopicListBean> list;

    public TopicDecorationP(TopicDecorationFace topicDecorationFace, FragmentActivity activity) {
        this.topicDecorationFace = topicDecorationFace;
        setActivity(activity);
    }

    public void getTopicList(String c, String collegeId, String timestamp, String page, String limit) {

        NetworkUtils.getNetworkUtils().getTopicList(c, collegeId, timestamp, page, limit, new DataCallback<TopicsListBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                topicDecorationFace.topicListFail();
            }

            @Override
            public void onSuccess(TopicsListBean.DataBean result, int i) {
                list = result.getTopicList();
                if (result.getTopicList().size() <= 0) {
                    makeText(activity.getResources().getString(R.string.no_more_data));
                }
                topicDecorationFace.topicListSuccess(list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                topicDecorationFace.topicListFail();
            }
        });
    }

    public interface TopicDecorationFace {
        void topicListSuccess(List<TopicListBean> list);

        void topicListFail();
    }
}
