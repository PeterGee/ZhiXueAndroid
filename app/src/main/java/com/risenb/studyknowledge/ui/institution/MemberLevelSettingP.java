package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.LevelSettingBean;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.BlackListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 * 会员等级设置
 */

public class MemberLevelSettingP extends PresenterBase {

    private MemberLevelSettingFace memberLevelSettingFace;
    public MemberLevelSettingP(MemberLevelSettingFace face, FragmentActivity activity) {
        this.memberLevelSettingFace = face;
        setActivity(activity);
    }

    /**
     * 获取会员等级设置列表
     * @param c
     * @param collegeId
     */
    public void getLevelSettingList(String c, String collegeId) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().vipLevelSettingList(c,collegeId,new
                DataCallback<LevelSettingBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Utils.getUtils().dismissDialog();
                makeText(activity.getResources().getString(R.string.network_error));
            }

            @Override
            public void onSuccess(LevelSettingBean.DataBean result, int i) {
                Utils.getUtils().dismissDialog();
                memberLevelSettingFace.getLevelSettingListSuccess(result.getUserCollegeList());
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                Utils.getUtils().dismissDialog();
                makeText(errorMsg);
            }
        });

    }

    /**
     * 保存会员等级设置
     * @param c                            登录标识
     * @param collegeId                   学院ID
     * @param userCollegegradeId         学院会员等级ID
     * @param userCollegegradeName       学院会员等级名称
     * @param userCollegegradePoints     学院会员等级所需积分
     */
    public void saveMemberLevelSetting(String c, String collegeId,String userCollegegradeId,String
            userCollegegradeName,String userCollegegradePoints) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().saveLevelSetting(c,collegeId,userCollegegradeId,
                userCollegegradeName,userCollegegradePoints, new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        memberLevelSettingFace.saveLevelSettingSuccess();
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface MemberLevelSettingFace {
        //获取会员等级列表成功的回调
            void getLevelSettingListSuccess(List<LevelSettingBean.DataBean.UserCollegeListBean> result);
        //保存会员等级设置成功的回调
            void saveLevelSettingSuccess();

    }
}
