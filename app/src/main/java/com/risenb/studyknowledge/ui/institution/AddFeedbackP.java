package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.AdviceListBean;
import com.risenb.studyknowledge.beans.institution.FeedbackBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by yy on 2017/11/15.
 * 意见反馈至平台
 */

public class AddFeedbackP extends PresenterBase {
    private AddFeedbackFace addFeedbackFace;
    public AddFeedbackP(AddFeedbackFace face, FragmentActivity activity) {
        this.addFeedbackFace = face;
        setActivity(activity);
    }

    /**
     * 意见反馈至平台
     * @param c                登录标识
     * @param collegeId       学院id
        adviceContent   意见反馈内容
     */
    public void adviceToPlatform(String c, String collegeId) {
        if(TextUtils.isEmpty(addFeedbackFace.getAdviceContent())){
            makeText(getActivity().getResources().getString(R.string.feedback_null));
            return;
        }
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().addAdvice(c,collegeId,addFeedbackFace.getAdviceContent(), new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        addFeedbackFace.adviceToPlatformSuccess();
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }

    public interface AddFeedbackFace {
        //意见反馈至平台成功的回调
        void adviceToPlatformSuccess();
        //获取意见反馈内容
        String getAdviceContent();
    }
}
