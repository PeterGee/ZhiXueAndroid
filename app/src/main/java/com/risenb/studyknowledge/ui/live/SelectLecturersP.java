package com.risenb.studyknowledge.ui.live;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.live.SelectLecturersBean;
import com.risenb.studyknowledge.beans.live.TeacherListBean;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.beans.topic.TopicsListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.io.Serializable;
import java.util.List;
import java.util.logging.StreamHandler;

import okhttp3.Call;

import static android.R.attr.tag;

/**
 * Created by zhuzh on 2017/11/27.
 */

public class SelectLecturersP extends PresenterBase {

    private SelectLecturersFace selectLecturersFace;

    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private List<TeacherListBean> list;

    public SelectLecturersP(SelectLecturersFace selectLecturersFace, FragmentActivity activity) {
        this.selectLecturersFace = selectLecturersFace;
        setActivity(activity);
    }

    public void getTeacherList(final String tag, String c, String collegeId, String timestamp, String page, String limit, String key) {

        NetworkUtils.getNetworkUtils().getTeacherList(c, collegeId, timestamp, page, limit,key, new DataCallback<SelectLecturersBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                selectLecturersFace.teacherListFail();
            }

            @Override
            public void onSuccess(SelectLecturersBean.DataBean result, int i) {
                String times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                if(TextUtils.equals(tag,REFRESH)){
                    list = result.getTeacherList();
                }else if(TextUtils.equals(tag,LOAD)){
//                    list.addAll(result.getTeacherList());
//                    if(result.getTeacherList().size()<=0){
//                        makeText(activity.getResources().getString(R.string.no_more_data));
//                    }
                }
                selectLecturersFace.teacherListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                selectLecturersFace.teacherListFail();
            }
        });
    }

    public interface SelectLecturersFace {
        void teacherListSuccess(String tag, String time, List<TeacherListBean> list);
        void teacherListFail();
    }
}

