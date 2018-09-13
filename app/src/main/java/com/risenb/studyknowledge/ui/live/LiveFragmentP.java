package com.risenb.studyknowledge.ui.live;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.live.LiveNoticeBean;
import com.risenb.studyknowledge.beans.live.PostLiveListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;

import static android.R.id.list;

/**
 * Created by zhuzh on 2017/11/22.
 */

public class LiveFragmentP extends PresenterBase {

    private LiveFragmentFace liveFragmentFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private List<PostLiveListBean> list;

    public LiveFragmentP(LiveFragmentFace liveFragmentFace, FragmentActivity activity) {
        this.liveFragmentFace = liveFragmentFace;
        setActivity(activity);
    }

    /**
     * 获取直播预告列表
     * @param tag
     * @param c
     * @param collegeId
     * @param timestamp
     * @param page
     * @param limit
     */
    public void getLiveList(final String tag, String c, String collegeId, String timestamp, String page, String limit) {

        NetworkUtils.getNetworkUtils().getLiveList(c, collegeId, timestamp, page, limit, new DataCallback<LiveNoticeBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                liveFragmentFace.liveListFail();
            }

            @Override
            public void onSuccess(LiveNoticeBean.DataBean result, int i) {
                String times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                if(TextUtils.equals(tag,REFRESH)){
                    list = result.getPostLiveList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getPostLiveList());
                    if(result.getPostLiveList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                liveFragmentFace.liveListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                liveFragmentFace.liveListFail();
            }
        });
    }

    public void deleteLive(String c, int postId, final int position) {
        NetworkUtils.getNetworkUtils().deleteLive(c, postId+"", new DataCallback<NetBaseBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));

            }

            @Override
            public void onSuccess(NetBaseBean result, int i) {
                liveFragmentFace.deleteLiveSuccess(position);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);

            }
        });
    }

    public interface LiveFragmentFace {
        void liveListSuccess(String tag, String time, List<PostLiveListBean> list);
        void liveListFail();
        void deleteLiveSuccess(int position);
    }
}
