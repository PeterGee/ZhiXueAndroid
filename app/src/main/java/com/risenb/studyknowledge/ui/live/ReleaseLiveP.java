package com.risenb.studyknowledge.ui.live;

import android.support.v4.app.FragmentActivity;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by zhuzh on 2017/11/27.
 */

public class ReleaseLiveP extends PresenterBase {

    private ReleaseLiveFace releaseLiveFace;

    public ReleaseLiveP(ReleaseLiveFace releaseLiveFace, FragmentActivity activity) {
        this.releaseLiveFace = releaseLiveFace;
        setActivity(activity);
    }

    public void addPostLive(String c, String collegeId, String postName, String postTopicId, String postWriterId, String postLivetime, String postIsFree,
                            String postPrice, String postIsTop, String postInfo) {
        NetworkUtils.getNetworkUtils().addPostLive(c, collegeId, postName, postTopicId, postWriterId,postLivetime,
                postIsFree, postPrice,postIsTop,postInfo,new DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        makeText(activity.getResources().getString(R.string.network_error));
                        Utils.getUtils().dismissDialog();

                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();

                        releaseLiveFace.addPostLiveSuccess(result);

                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        makeText(errorMsg);
                        Utils.getUtils().dismissDialog();

                    }
                });

    }

    public interface ReleaseLiveFace{

        void addPostLiveSuccess(NetBaseBean result);
    }
}
