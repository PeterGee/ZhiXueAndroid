package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.CashDetailsBean;
import com.risenb.studyknowledge.beans.institution.CashRecordListBean;
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
 * 提现明细列表
 */

public class CashRecordListP extends PresenterBase {

    private CashRecordListFace cashRecordListFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private String times;
    private List<CashDetailsBean> list=new ArrayList<>();
    public CashRecordListP(CashRecordListFace face, FragmentActivity activity) {
        this.cashRecordListFace = face;
        setActivity(activity);
    }

    /**
     * 获取提现明细列表
     * @param tag
     * @param c             登录标识
     * @param collegeId    学院ID
     * @param timestamp    查询时间戳
     * @param page          第几页
     * @param limit         每页显示多少条
     */
    public void getCashRecordList(final String tag, String c, String collegeId, String timestamp, final String page, String limit) {
        NetworkUtils.getNetworkUtils().getCashRecordList(c,collegeId,timestamp, page,limit,new
                DataCallback<CashRecordListBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                cashRecordListFace.getCashRecordListFail();
            }

            @Override
            public void onSuccess(CashRecordListBean.DataBean result, int i) {
                times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                //踢出会员列表信息
                if(TextUtils.equals(tag,REFRESH)){
                    list=result.getCashRecordList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getCashRecordList());
                    if(result.getCashRecordList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                cashRecordListFace.getCashRecordListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                cashRecordListFace.getCashRecordListFail();
            }
        });

    }

    public interface CashRecordListFace {
        //获取踢出会员列表成功的回调
            void getCashRecordListSuccess(String tag, String timestamp, List<CashDetailsBean> result);
        //获取踢出会员列表失败的回调
            void getCashRecordListFail();
    }
}
