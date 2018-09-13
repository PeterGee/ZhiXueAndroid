package com.risenb.studyknowledge.ui.personal;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;
import com.risenb.studyknowledge.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 * 踢出的会员列表
 */

public class KickOutMemberP extends PresenterBase {

    private KickOutListFace kickOutListFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private String times;
    private List<AttendanceBean> list=new ArrayList<>();
    public KickOutMemberP(KickOutListFace face, FragmentActivity activity) {
        this.kickOutListFace = face;
        setActivity(activity);
    }

    public void getKickOutList(final String tag, String c, String collegeId, String timestamp, final String page, String limit) {
        NetworkUtils.getNetworkUtils().getKickOutList(c,collegeId,timestamp, page,limit,new
                DataCallback<KickOutMemberBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                kickOutListFace.getKickOutListFail();
            }

            @Override
            public void onSuccess(KickOutMemberBean.DataBean result, int i) {
                times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                //踢出会员列表信息
                if(TextUtils.equals(tag,REFRESH)){
                    list=result.getDelynVipList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getDelynVipList());
                    if(result.getDelynVipList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                kickOutListFace.getKickOutListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                kickOutListFace.getKickOutListFail();
            }
        });

    }

    /**
     * 邀请会员
     * @param c            登录标识
     * @param attendId    申请ID
     */
    public void getKickOutInvite(String c, String attendId, final int position) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().kickOutMemberInvite(c,attendId, new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        kickOutListFace.inviteKickMemberSuccess(position);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface KickOutListFace {
        //获取踢出会员列表成功的回调
            void getKickOutListSuccess(String tag,String timestamp,List<AttendanceBean> result);
        //获取踢出会员列表失败的回调
            void getKickOutListFail();
        //邀请会员成功的回调
            void inviteKickMemberSuccess(int position);

    }
}
