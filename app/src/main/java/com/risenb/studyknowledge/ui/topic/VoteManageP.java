package com.risenb.studyknowledge.ui.topic;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.topic.VoteListBean;
import com.risenb.studyknowledge.beans.topic.VoteManageBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by zhuzh on 2017/11/23.
 */

public class VoteManageP extends PresenterBase {

    private VoteManageFace voteManageFace;

    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private List<VoteListBean> list;

    public VoteManageP(VoteManageFace voteManageFace, FragmentActivity activity) {
        this.voteManageFace = voteManageFace;
        setActivity(activity);
    }

    public void getVoteList(final String tag, String c, String collegeId, String timestamp, String page, String limit) {

        NetworkUtils.getNetworkUtils().getVoteList(c, collegeId, timestamp, page, limit, new DataCallback<VoteManageBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                voteManageFace.voteListFail();
            }

            @Override
            public void onSuccess(VoteManageBean.DataBean result, int i) {
                String times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                if(TextUtils.equals(tag,REFRESH)){
                    list = result.getVoteList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getVoteList());
                    if(result.getVoteList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                voteManageFace.voteListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                voteManageFace.voteListFail();
            }
        });
    }


    public  void deleteVote(String voteId){
        NetworkUtils.getNetworkUtils().delVote(application.getC(), voteId, new DataCallback<NetBaseBean>() {
            @Override
            public void onSuccess(NetBaseBean result, int i) {
                voteManageFace.delVoteSuccess();
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {

            }

            @Override
            public void onError(Call call, Exception e, int i) {

            }


        });
    }

    public interface VoteManageFace{
        void voteListSuccess(String tag, String time, List<VoteListBean> list);
        void voteListFail();
        void delVoteSuccess();
    }
}
