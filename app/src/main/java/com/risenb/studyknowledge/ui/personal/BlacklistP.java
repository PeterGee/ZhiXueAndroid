package com.risenb.studyknowledge.ui.personal;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.BlackListBean;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 * 黑名单列表
 */

public class BlacklistP extends PresenterBase {

    private BlackListFace blackListFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private String times;
    private List<AttendanceBean> list=new ArrayList<>();
    public BlacklistP(BlackListFace face, FragmentActivity activity) {
        this.blackListFace = face;
        setActivity(activity);
    }

    public void getBlackList(final String tag, String c, String collegeId, String timestamp, final String page, String limit) {
        NetworkUtils.getNetworkUtils().getBlackList(c,collegeId,timestamp, page,limit,new
                DataCallback<BlackListBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                blackListFace.getBlackListFail();
            }

            @Override
            public void onSuccess(BlackListBean.DataBean result, int i) {
                times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                //踢出会员列表信息
                if(TextUtils.equals(tag,REFRESH)){
                    list=result.getBlackList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getBlackList());
                    if(result.getBlackList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                blackListFace.getBlackListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                blackListFace.getBlackListFail();
            }
        });

    }

    /**
     * 取消拉黑
     * @param c            登录标识
     * @param attendId    申请ID
     */
    public void cancelBlackList(String c, String attendId, final int position) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().cancelBlackList(c,attendId, new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        blackListFace.cancelBlackSuccess(position);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface BlackListFace {
        //获取踢出会员列表成功的回调
            void getBlackListSuccess(String tag, String timestamp, List<AttendanceBean> result);
        //获取踢出会员列表失败的回调
            void getBlackListFail();
        //取消拉黑成功的回调
            void cancelBlackSuccess(int position);

    }
}
