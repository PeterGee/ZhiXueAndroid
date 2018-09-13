package com.risenb.studyknowledge.ui.personal;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.MemberSettingBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 * 会员设置，修改会员信息
 */

public class MemberSettingP extends PresenterBase {

    private MemberSettingFace memberSettingFace;
    public MemberSettingP(MemberSettingFace face, FragmentActivity activity) {
        this.memberSettingFace = face;
        setActivity(activity);
    }

    /**
     * 保存会员信息
     * @param c                  登录标识
     * @param attendId          会员ID
     * @param attendType        会员身份(0：学生、1：管理、2：老师、默认为0
     * @param attendAllowYn     是否拉黑(是否允许再加入：1：是、2：否)
     * @param attendTalkLimit   是否禁言(0：否、1：是)
     * @param attendTalkTime    禁言时间
     * @param medalTypeIds      勋章类型ID
     */
    public void getMemberInfo(String c, String attendId,String
            attendType,String attendAllowYn,String attendTalkLimit,String attendTalkTime,String
                                medalTypeIds) {
        if (TextUtils.isEmpty(memberSettingFace.getMemberName())) {
            makeText(getActivity().getResources().getString(R.string.member_name_tip));
            return;
        }
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().saveMemberInfo(c,attendId,memberSettingFace.getMemberName(),attendType,
                attendAllowYn,attendTalkLimit,attendTalkTime, medalTypeIds,new
                DataCallback<MemberSettingBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Utils.getUtils().dismissDialog();
                makeText(activity.getResources().getString(R.string.network_error));
            }

            @Override
            public void onSuccess(MemberSettingBean.DataBean result, int i) {
                Utils.getUtils().dismissDialog();
                memberSettingFace.saveMemberInfoSuccess(result.getAttendance());
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                Utils.getUtils().dismissDialog();
                makeText(errorMsg);
            }
        });

    }
    public interface MemberSettingFace {
        String getMemberName();
        void saveMemberInfoSuccess(AttendanceBean result);
    }
}
