package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.AdviceListBean;
import com.risenb.studyknowledge.beans.institution.FeedbackBean;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.MemberLevelBean;
import com.risenb.studyknowledge.beans.personal.MemberManagerBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by yy on 2017/11/15.
 * 获取意见反馈列表
 */

public class FeedbackListP extends PresenterBase {
    private FeedbackListFace feedbackListFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private String times;
    private List<AdviceListBean> list=new ArrayList<>();
    public FeedbackListP(FeedbackListFace face, FragmentActivity activity) {
        this.feedbackListFace = face;
        setActivity(activity);
    }

    /**
     * 获取意见反馈列表
     * @param tag
     * @param c             登录标识
     * @param collegeId    学院id
     * @param key           key=0 未读  key=1已读(为空时则展示全部列表)
     * @param timestamp     查询时间戳
     * @param page           第几页
     * @param limit         每页显示多少条
     */
    public void getFeedbackList(final String tag, String c, String collegeId,String key, String
            timestamp, String page, String limit){
        NetworkUtils.getNetworkUtils().getAdviceList(c,collegeId,key,timestamp,page,limit, new
                DataCallback<FeedbackBean.DataBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        makeText(activity.getResources().getString(R.string.network_error));
                        feedbackListFace.getAdviceListFail();
                    }

                    @Override
                    public void onSuccess(FeedbackBean.DataBean result, int i) {
                        times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                        //会员列表信息
                        if(TextUtils.equals(tag,REFRESH)){
                            list=result.getAdviceList();
                        }else if(TextUtils.equals(tag,LOAD)){
                            list.addAll(result.getAdviceList());
                            if(result.getAdviceList().size()<=0){
                                makeText(activity.getResources().getString(R.string.no_more_data));
                            }
                        }
                        feedbackListFace.getAdviceListSuccess(tag,times,list);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        makeText(errorMsg);
                        feedbackListFace.getAdviceListFail();
                    }
                });

    }
    /**
     * 意见反馈是否已读
     * @param c            登录标识
     * @param adviceId    意见反馈id
     */
    public void isReadFeedback(String c, String adviceId, final int position) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().isRead(c,adviceId, new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        feedbackListFace.isReadFeedbackSuccess(position);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface FeedbackListFace {
        //获取意见反馈列表成功的回调
        void getAdviceListSuccess(String tag, String timestamp, List<AdviceListBean> result);
        void getAdviceListFail();

        //意见反馈已读成功的回调
        void isReadFeedbackSuccess(int position);
    }
}
