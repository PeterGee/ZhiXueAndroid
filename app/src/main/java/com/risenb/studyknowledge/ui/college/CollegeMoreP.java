package com.risenb.studyknowledge.ui.college;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.lidroid.mutils.network.HttpBack;
import com.risenb.studyknowledge.beans.college.JoinedCollegeBean;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

/**
 * Created by yjyvi on 2017/12/26.
 */

public class CollegeMoreP extends PresenterBase {

    private CollegeMoreListener mCollegeMoreListener;

    public CollegeMoreP(CollegeMoreListener collegeMoreListener, FragmentActivity fragmentActivity) {
        setActivity(fragmentActivity);
        this.mCollegeMoreListener = collegeMoreListener;

    }

    public void setCollegeMore() {
        NetworkUtils.getNetworkUtils().getMoreUserCollege(application.getC(), new HttpBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);


            }


            @Override
            public void onString(String result) {
                super.onString(result);

                JoinedCollegeBean joinedCollegeBean = JSON.parseObject(result, JoinedCollegeBean.class);

                mCollegeMoreListener.collegeMoreSuccess(joinedCollegeBean.getData().getData());
            }
        });
    }

    public interface CollegeMoreListener {
        void collegeMoreSuccess(List<JoinedCollegeBean.DataBeanX.DataBean> dataBean);
    }
}
