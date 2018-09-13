package com.risenb.studyknowledge.ui.topic;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.VoteDetailListBean;
import com.risenb.studyknowledge.beans.topic.VoteListBean;
import com.risenb.studyknowledge.beans.topic.VoteManageBean;
import com.risenb.studyknowledge.beans.topic.VoteNeophyteBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by zhuzh on 2017/11/24.
 */

public class VoteNeophyteP extends PresenterBase {

    private VoteNeophyteFace voteNeophyteFace;

    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private List<VoteDetailListBean> list;

    public VoteNeophyteP(VoteNeophyteFace voteNeophyteFace, FragmentActivity activity) {
        this.voteNeophyteFace = voteNeophyteFace;
        setActivity(activity);
    }

    public void getVoteDetailList(final String tag, String c, String voteId, String timestamp, String page, String limit) {
        NetworkUtils.getNetworkUtils().getVoteDetailList(c, "1864", timestamp, page, limit, new DataCallback<VoteNeophyteBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                voteNeophyteFace.voteDetailListFail();
            }

            @Override
            public void onSuccess(VoteNeophyteBean.DataBean result, int i) {
                String times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                if(TextUtils.equals(tag,REFRESH)){
                    list = result.getVoteDetailList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getVoteDetailList());
                    if(result.getVoteDetailList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                voteNeophyteFace.voteDetailListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                voteNeophyteFace.voteDetailListFail();
            }
        });
    }

    public interface VoteNeophyteFace {
        void voteDetailListSuccess(String tag, String time, List<VoteDetailListBean> list);
        void voteDetailListFail();
    }
}
