package com.risenb.studyknowledge.ui.college;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.college.CollegeVipBean;
import com.risenb.studyknowledge.beans.live.PostLiveListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;

import static android.R.attr.tag;

/**
 * Created by zhuzh on 2017/11/23.
 */

public class CollegeVipP extends PresenterBase {

    private CollegeVipFace collegeVipFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private List<CollegeVipBean.DataBean.CollegeGradeListBean> list;

    public CollegeVipP(CollegeVipFace collegeVipFace, FragmentActivity activity) {
        this.collegeVipFace = collegeVipFace;
        setActivity(activity);
    }

    public void getCollegeVip(final String tag) {
        NetworkUtils.getNetworkUtils().getCollegeVip(new DataCallback<CollegeVipBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                collegeVipFace.liveListFail();

            }

            @Override
            public void onSuccess(CollegeVipBean.DataBean result, int i) {
                if(TextUtils.equals(tag,REFRESH)){
                    list = result.getCollegeGradeList();
                }else if(TextUtils.equals(tag,LOAD)){
                    makeText(activity.getResources().getString(R.string.no_more_data));
                }
                collegeVipFace.vipListSuccess(tag,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                collegeVipFace.liveListFail();

            }
        });
    }

    public interface CollegeVipFace{
        void vipListSuccess(String tag, List<CollegeVipBean.DataBean.CollegeGradeListBean> list);
        void liveListFail();

    }
}
