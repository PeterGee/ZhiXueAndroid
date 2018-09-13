package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.AnnounceModifyBean;
import com.risenb.studyknowledge.beans.institution.NoticeListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yy on 2017/11/15.
 * 修改学院公告信息
 */

public class UpdateAnnounceP extends PresenterBase {
    private UpdateAnnounceFace updateAnnounceFace;
    public UpdateAnnounceP(UpdateAnnounceFace face, FragmentActivity activity) {
        this.updateAnnounceFace = face;
        setActivity(activity);
    }

    /**
     * 修改学院公告信息
     * @param c                登录标识
     * @param noticeId       公告id
        noticeTitle   公告标题
        noticeInfo    公告内容
     */
    public void updateAnnounce(String c, String noticeId) {
        if(TextUtils.isEmpty(updateAnnounceFace.getAnnounceTitle())){
            makeText(getActivity().getResources().getString(R.string.announce_title_null));
            return;
        }
        if(TextUtils.isEmpty(updateAnnounceFace.getAnnounceInfo())){
            makeText(getActivity().getResources().getString(R.string.announce_content_null));
            return;
        }
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().updateAnnounce(c,noticeId,updateAnnounceFace.getAnnounceTitle()
                ,updateAnnounceFace.getAnnounceInfo(), new DataCallback<AnnounceModifyBean.DataBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(AnnounceModifyBean.DataBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        updateAnnounceFace.updateAnnounceSuccess(result.getNotice());
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }

    public interface UpdateAnnounceFace {
        //修改公告成功的回调
        void updateAnnounceSuccess(NoticeListBean result);
        //获取公告标题
        String getAnnounceTitle();
        //获取公告内容
        String getAnnounceInfo();
    }
}
