package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.VIPApplyBean;
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
 * vip申请明细列表
 */

public class VIPApplyDetailP extends PresenterBase {

    private VIPApplyDetailFace vipApplyDetailFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private String times;
    private List<VIPApplyBean.DataBean.VipDetailListBean> list=new ArrayList<>();
    public VIPApplyDetailP(VIPApplyDetailFace face, FragmentActivity activity) {
        this.vipApplyDetailFace = face;
        setActivity(activity);
    }

    public void getVIPApplyDetailList(final String tag, String c, String collegeId, String
            timestamp, final String page, String limit) {
        NetworkUtils.getNetworkUtils().getVIPApplyDetailList(c,collegeId,timestamp, page,limit,new
                DataCallback<VIPApplyBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                vipApplyDetailFace.getVIPApplyDetailListFail();
            }

            @Override
            public void onSuccess(VIPApplyBean.DataBean result, int i) {
                times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                if(TextUtils.equals(tag,REFRESH)){
                    list=result.getVipDetailList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getVipDetailList());
                    if(result.getVipDetailList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                vipApplyDetailFace.getVIPApplyDetailListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                vipApplyDetailFace.getVIPApplyDetailListFail();
            }
        });

    }
    public interface VIPApplyDetailFace {
        //获取踢出会员列表成功的回调
            void getVIPApplyDetailListSuccess(String tag, String timestamp, List<VIPApplyBean.DataBean.VipDetailListBean> result);
        //获取踢出会员列表失败的回调
            void getVIPApplyDetailListFail();
    }
}
