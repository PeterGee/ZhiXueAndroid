package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.AnnounceBean;
import com.risenb.studyknowledge.beans.institution.NoticeListBean;
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
 * 获取公告列表
 */

public class AnnounceEditListP extends PresenterBase {

    private AnnounceEditListFace announceEditListFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private String times;
    private List<NoticeListBean> list=new ArrayList<>();
    public AnnounceEditListP(AnnounceEditListFace face, FragmentActivity activity) {
        this.announceEditListFace = face;
        setActivity(activity);
    }

    /**
     * 获取公告列表
     * @param tag
     * @param c             登录标识
     * @param collegeId    学院ID
     * @param timestamp    查询时间戳
     * @param page          第几页
     * @param limit         每页显示多少条
     */
    public void getAnnounceList(final String tag, String c, String collegeId, String timestamp, final String page, String limit) {
        NetworkUtils.getNetworkUtils().getAnnounceList(c,collegeId,timestamp, page,limit,new
                DataCallback<AnnounceBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                announceEditListFace.getAnnounceListFail();
            }

            @Override
            public void onSuccess(AnnounceBean.DataBean result, int i) {
                times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                //踢出会员列表信息
                if(TextUtils.equals(tag,REFRESH)){
                    list=result.getNoticeList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getNoticeList());
                    if(result.getNoticeList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                announceEditListFace.getAnnounceListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                announceEditListFace.getAnnounceListFail();
            }
        });

    }

    /**
     * 删除公告
     * @param c           登录标识
     * @param noticeId   公告id
     */
    public void deleteAnnounce(String c, String noticeId, final int position) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().deleteAnnounce(c,noticeId, new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        announceEditListFace.deleteAnnounceSuccess(position);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface AnnounceEditListFace {
        //获取公告列表成功的回调
            void getAnnounceListSuccess(String tag, String timestamp, List<NoticeListBean> result);
        //获取公告列表失败的回调
            void getAnnounceListFail();
        //删除公告成功的回调
            void deleteAnnounceSuccess(int position);

    }
}
