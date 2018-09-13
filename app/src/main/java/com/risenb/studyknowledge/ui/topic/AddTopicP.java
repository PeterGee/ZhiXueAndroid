package com.risenb.studyknowledge.ui.topic;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.flyco.tablayout.utils.FragmentChangeManager;
import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.topic.ActionManageBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by zhezhu on 2017/11/26.
 */

public class AddTopicP extends PresenterBase {


    private AddTopicFace addTopicFace;

    public AddTopicP(AddTopicFace addTopicFace,FragmentActivity activity) {
        this.addTopicFace = addTopicFace;
        setActivity(activity);
    }

    public void addTopic(String c, String collegeId, String topicName, String topicPayType, String topicType,
                                String topicIsTop, String topicUseyn, String topicImg, String s, String s1, String s2) {
        Utils.getUtils().showProgressDialog(getActivity(), null);

        NetworkUtils.getNetworkUtils().addTopic(c, collegeId, topicName, topicPayType, topicType,topicIsTop,
                topicUseyn, topicImg,s,s1, s2,new DataCallback<NetBaseBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                Utils.getUtils().dismissDialog();

            }

            @Override
            public void onSuccess(NetBaseBean result, int i) {
                Utils.getUtils().dismissDialog();

                addTopicFace.addTopicSuccess(result);

            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                Utils.getUtils().dismissDialog();

            }
        });

    }

    public void updateTopic(String c, String topicId, String topicName, String topicPayType, String topicType, String topicIsTop,
                            String topicUseyn, String topicImg, String s5, String s6, String s7) {

        Utils.getUtils().showProgressDialog(getActivity(), null);

        NetworkUtils.getNetworkUtils().updateTopic(c, topicId, topicName, topicPayType, topicType,topicIsTop,
                topicUseyn, topicImg,s5,s6, s7,new DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        makeText(activity.getResources().getString(R.string.network_error));
                        Utils.getUtils().dismissDialog();

                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        addTopicFace.updateTopicSuccess(result);

                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        makeText(errorMsg);
                        Utils.getUtils().dismissDialog();

                    }
                });
    }

    public interface AddTopicFace {
        void addTopicSuccess(NetBaseBean result);
        void updateTopicSuccess(NetBaseBean result);
    }
}
