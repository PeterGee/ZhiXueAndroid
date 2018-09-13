package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.AnnounceBean;
import com.risenb.studyknowledge.beans.institution.NoticeListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 * 删除勋章
 */

public class DeleteMedalP extends PresenterBase {

    private DeleteMedalFace deleteMedalFace;
    public DeleteMedalP(DeleteMedalFace face, FragmentActivity activity) {
        this.deleteMedalFace = face;
        setActivity(activity);
    }

    /**
     * 删除勋章
     * @param c              登录标识
     * @param collegeId     学院ID
     * @param medalTypeId   勋章类型ID
     */
    public void deleteMedal(String c, String collegeId,String medalTypeId, final int position) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().deleteMedal(c,collegeId,medalTypeId,new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        deleteMedalFace.deleteMedalSuccess(position);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface DeleteMedalFace {
        //删除勋章成功的回调
            void deleteMedalSuccess(int position);

    }
}
