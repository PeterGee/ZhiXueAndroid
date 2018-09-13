package com.risenb.studyknowledge.ui.topic;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.ActionManageBean;
import com.risenb.studyknowledge.beans.topic.ActionNeophyteBean;
import com.risenb.studyknowledge.beans.topic.ActivityListBean;
import com.risenb.studyknowledge.beans.topic.ActivityUserListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.List;

import okhttp3.Call;

import static android.R.attr.tag;

/**
 * Created by zhuzh on 2017/11/23.
 */

public class ActionNeophyteP extends PresenterBase {

    private ActionNeophyteFace actionNeophyteFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private List<ActivityUserListBean> list;

    public ActionNeophyteP(ActionNeophyteFace actionNeophyteFace, FragmentActivity activity) {
        this.actionNeophyteFace = actionNeophyteFace;
        setActivity(activity);
    }

    public void getActivityUserList(final String tag, String c, String activityId, String timestamp, String page, String limit) {

        NetworkUtils.getNetworkUtils().getActivityUserList(c, activityId, timestamp, page, limit, new DataCallback<ActionNeophyteBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                actionNeophyteFace.actionUserListFail();
            }

            @Override
            public void onSuccess(ActionNeophyteBean.DataBean result, int i) {
                String times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                if(TextUtils.equals(tag,REFRESH)){
                    list = result.getActivityUserList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getActivityUserList());
                    if(result.getActivityUserList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                actionNeophyteFace.actionUserListSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                actionNeophyteFace.actionUserListFail();
            }
        });
    }

    public interface ActionNeophyteFace{
        void actionUserListSuccess(String tag, String time, List<ActivityUserListBean> list);
        void actionUserListFail();
    }
}
