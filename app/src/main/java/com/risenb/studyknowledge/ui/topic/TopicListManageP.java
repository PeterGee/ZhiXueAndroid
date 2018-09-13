package com.risenb.studyknowledge.ui.topic;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.live.LiveNoticeBean;
import com.risenb.studyknowledge.beans.live.PostLiveListBean;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.beans.topic.TopicsListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;

import static android.R.attr.tag;

/**
 * Created by zhuzh on 2017/11/22.
 */

public class TopicListManageP extends PresenterBase{

    private TopicListManageFace topicListManageFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private List<TopicListBean> list;

    public TopicListManageP(TopicListManageFace topicListManageFace, FragmentActivity activity) {
        this.topicListManageFace = topicListManageFace;
        setActivity(activity);
    }

    public void getTopicList(final String tag, String c, String collegeId, String timestamp, String page, String limit) {
        NetworkUtils.getNetworkUtils().getTopicList(c, collegeId, timestamp, page, limit, new DataCallback<TopicsListBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                topicListManageFace.topicListFail();
            }

            @Override
            public void onSuccess(TopicsListBean.DataBean result, int i) {
                String times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                if(TextUtils.equals(tag,REFRESH)){
                    list = result.getTopicList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getTopicList());
                    if(result.getTopicList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                topicListManageFace.topicListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                topicListManageFace.topicListFail();
            }
        });
    }

    /**
     * 话题上下架
     * @param c
     * @param topicId
     * @param s
     * @param position
     */
    public void isUpOrDown(String c, String topicId, final String s, final int position) {
        Utils.getUtils().showProgressDialog(getActivity(), null);

        NetworkUtils.getNetworkUtils().isUpOrDown(c, topicId, s, new DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        makeText(activity.getResources().getString(R.string.network_error));
                        Utils.getUtils().dismissDialog();

                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        topicListManageFace.isUpOrDownSuccess(result,s, position);

                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        makeText(errorMsg);
                        Utils.getUtils().dismissDialog();

                    }
                });

    }

    public void updateSort(String c, String topicId1, String topicId2) {
        Utils.getUtils().showProgressDialog(getActivity(), null);

        NetworkUtils.getNetworkUtils().updateSort(c, topicId1, topicId2, new DataCallback<NetBaseBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                Utils.getUtils().dismissDialog();

            }

            @Override
            public void onSuccess(NetBaseBean result, int i) {
                Utils.getUtils().dismissDialog();
                topicListManageFace.updateSortSuccess(result);

            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                Utils.getUtils().dismissDialog();

            }
        });
    }

    public interface TopicListManageFace {
        void topicListSuccess(String tag, String time, List<TopicListBean> list);
        void topicListFail();
        void isUpOrDownSuccess(NetBaseBean result, String s, int position);
        void updateSortSuccess(NetBaseBean result);
    }
}
