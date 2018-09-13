package com.risenb.studyknowledge.ui.personal;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.BlackListBean;
import com.risenb.studyknowledge.beans.personal.MemberApplyBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 * 会员申请列表
 */

public class MemberApplyP extends PresenterBase {

    private MemberApplyFace memberApplyFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    public static final String APPLY_PASS="1";//会员申请通过
    public static final String APPLY_REFUSE="2";//拒绝
    private String times;
    private List<AttendanceBean> list=new ArrayList<>();
    public MemberApplyP(MemberApplyFace face, FragmentActivity activity) {
        this.memberApplyFace = face;
        setActivity(activity);
    }

    /**
     * 获取会员申请列表
     * @param tag
     * @param c
     * @param collegeId
     * @param timestamp
     * @param page
     * @param limit
     */
    public void getMemberApplyList(final String tag, String c, String collegeId, String timestamp,
                                   final String page, String limit, final boolean isShow) {
        NetworkUtils.getNetworkUtils().getApplyVipList(c,collegeId,timestamp, page,limit,new
                DataCallback<MemberApplyBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                memberApplyFace.getApplyVipListFail();
            }

            @Override
            public void onSuccess(MemberApplyBean.DataBean result, int i) {
                times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                //踢出会员列表信息
                if(TextUtils.equals(tag,REFRESH)){
                    list=result.getApplyVipList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getApplyVipList());
                    if(result.getApplyVipList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                /**
                 * 是否显示勾选圈
                 */
                for (int m = 0; m <list.size() ; m++) {
                    list.get(m).setShow(isShow);
                }
                memberApplyFace.getApplyVipListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                memberApplyFace.getApplyVipListFail();
            }
        });

    }

    /**
     * 拒绝/通过会员申请
     * @param c                 登录标识
     * @param attendIds        申请ID(多个会员申请时用，号隔开)
     * @param attendPassYn     申请是否通过(1：通过、2：拒绝)
     */
    public void applyVipPass(String c, String attendIds,String attendPassYn) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().applyVipPass(c,attendIds,attendPassYn, new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        memberApplyFace.applyVipPassSuccess();
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface MemberApplyFace {
        //获取会员申请列表成功的回调
            void getApplyVipListSuccess(String tag, String timestamp, List<AttendanceBean> result);
        //获取会员申请列表失败的回调
            void getApplyVipListFail();
        //拒绝/通过会员申请成功的回调
            void applyVipPassSuccess();
    }
}
