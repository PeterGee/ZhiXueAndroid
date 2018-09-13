package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yy on 2017/11/15.
 * 新增公告编辑
 */

public class AddAnnounceP extends PresenterBase {
    private AddAnnounceFace addAnnounceFace;
    public AddAnnounceP(AddAnnounceFace face, FragmentActivity activity) {
        this.addAnnounceFace = face;
        setActivity(activity);
    }

    /**
     * 新增公告
     * @param c                登录标识
     * @param collegeId       学院id
        noticeTitle   公告标题
        noticeInfo    公告内容
     */
    public void addAnnounce(String c, String collegeId) {
        if(TextUtils.isEmpty(addAnnounceFace.getAnnounceTitle())){
            makeText(getActivity().getResources().getString(R.string.announce_title_null));
            return;
        }
        if(TextUtils.isEmpty(addAnnounceFace.getAnnounceInfo())){
            makeText(getActivity().getResources().getString(R.string.announce_content_null));
            return;
        }
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().addAnnounce(c,collegeId,addAnnounceFace.getAnnounceTitle()
                ,addAnnounceFace.getAnnounceInfo(), new DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        addAnnounceFace.addAnnounceSuccess();
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }

    public interface AddAnnounceFace {
        //新增公告成功的回调
        void addAnnounceSuccess();
        //获取公告标题
        String getAnnounceTitle();
        //获取公告内容
        String getAnnounceInfo();
    }
}
