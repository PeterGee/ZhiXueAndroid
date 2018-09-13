package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.BusinessInOutBean;
import com.risenb.studyknowledge.beans.institution.BusinessInfoBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 * 友商售出列表
 */

public class BusinessOutP extends PresenterBase {

    private BusinessOutFace businessOutFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private String times;
    private List<BusinessInfoBean> list=new ArrayList<>();
    public BusinessOutP(BusinessOutFace face, FragmentActivity activity) {
        this.businessOutFace = face;
        setActivity(activity);
    }

    public void getBusinessOutList(final String tag, String collegeId, String timestamp, final
    String page, String limit) {
        NetworkUtils.getNetworkUtils().getBusinessOutList(collegeId,timestamp, page,limit,new
                DataCallback<BusinessInOutBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                businessOutFace.getBusinessOutListFail();
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
                businessOutFace.getBusinessOutListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                businessOutFace.getBusinessOutListFail();
            }
        });

    }

    /**
     * 取消代理友商
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
                        businessOutFace.deleteBusinessTopicSuccess(position);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface BusinessOutFace {
        //获取友商售出列表成功的回调
            void getBusinessOutListSuccess(String tag, String timestamp, List<BusinessInfoBean> result);
        //获取友商售出列表失败的回调
            void getBusinessOutListFail();
        //取消代理友商成功的回调
            void deleteBusinessTopicSuccess(int position);

    }
}
