package com.risenb.studyknowledge.ui.topic;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.ActionManageBean;
import com.risenb.studyknowledge.beans.topic.ActivityListBean;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.beans.topic.TopicsListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;

import static android.R.attr.tag;

/**
 * Created by zhuzh on 2017/11/23.
 */

public class ActionManageP extends PresenterBase {

    private ActionManageFace actionManageFace;

    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private List<ActivityListBean> list;

    public ActionManageP(ActionManageFace actionManageFace, FragmentActivity activity) {
        this.actionManageFace = actionManageFace;
        setActivity(activity);
    }

    public void getActionList(final String tag, String c, String collegeId, String timestamp, String page, String limit) {
        NetworkUtils.getNetworkUtils().getActionList(c, collegeId, timestamp, page, limit, new DataCallback<ActionManageBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                actionManageFace.actionListFail();
            }

            @Override
            public void onSuccess(ActionManageBean.DataBean result, int i) {
                String times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                if(TextUtils.equals(tag,REFRESH)){
                    list = result.getActivityList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getActivityList());
                    if(result.getActivityList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                actionManageFace.actionListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                actionManageFace.actionListFail();
            }
        });
    }

    public interface ActionManageFace {
        void actionListSuccess(String tag, String time, List<ActivityListBean> list);
        void actionListFail();
    }


}
