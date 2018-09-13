package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.BusinessInOutBean;
import com.risenb.studyknowledge.beans.institution.BusinessInfoBean;
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
 * 友商购进列表
 */

public class BusinessInP extends PresenterBase {

    private BusinessInFace businessInFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private String times;
    private List<BusinessInfoBean> list=new ArrayList<>();
    public BusinessInP(BusinessInFace face, FragmentActivity activity) {
        this.businessInFace = face;
        setActivity(activity);
    }

    public void getBusinessInList(final String tag, String collegeId, String timestamp, final String page, String limit) {
        NetworkUtils.getNetworkUtils().getBusinessInList(collegeId,timestamp, page,limit,new
                DataCallback<BusinessInOutBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                businessInFace.getBusinessInListFail();
            }

            @Override
            public void onSuccess(BusinessInOutBean.DataBean result, int i) {
                times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                //踢出会员列表信息
                if(TextUtils.equals(tag,REFRESH)){
                    list=result.getList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getList());
                    if(result.getList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                businessInFace.getBusinessInListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                businessInFace.getBusinessInListFail();
            }
        });

    }

    /**
     * 删除友商
     * @param buyTopicId   友商购买id
     */
    public void deleteBusiness(String buyTopicId, final int position) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().deleteBusinessTopic(buyTopicId, new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        businessInFace.deleteBusinessTopicSuccess(position);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface BusinessInFace {
        //获取友商购进列表成功的回调
            void getBusinessInListSuccess(String tag, String timestamp, List<BusinessInfoBean> result);
        //获取友商购进列表失败的回调
            void getBusinessInListFail();
        //删除友商购进成功的回调
            void deleteBusinessTopicSuccess(int position);

    }
}
