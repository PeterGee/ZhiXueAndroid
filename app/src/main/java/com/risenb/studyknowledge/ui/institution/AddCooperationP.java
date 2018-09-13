package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.AddBuyTopicBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yjyvi on 2017/12/12.
 */

public class AddCooperationP extends PresenterBase {
    private  AddCooperationListener mAddCooperationListener;

    public AddCooperationP(AddCooperationListener addCooperationListener, FragmentActivity fragmentActivity) {
        setActivity(fragmentActivity);
        this.mAddCooperationListener = addCooperationListener;
    }

    public void setAddcooperationData(String topicId, String collegeId, String newWriterId, String months) {
        NetworkUtils.getNetworkUtils().addBuyTopic(topicId, collegeId, newWriterId, months, new DataCallback<AddBuyTopicBean>() {
            @Override
            public void onSuccess(AddBuyTopicBean result, int i) {
                mAddCooperationListener.addCooperationData(result);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                mAddCooperationListener.addCooperationField();
            }

            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                mAddCooperationListener.addCooperationField();
            }


        });
    }

    public interface AddCooperationListener {
        void addCooperationData(AddBuyTopicBean addBuyTopicBean);

        void addCooperationField();
    }
}
