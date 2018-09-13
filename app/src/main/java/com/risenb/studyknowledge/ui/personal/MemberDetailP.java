package com.risenb.studyknowledge.ui.personal;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.personal.MemberDetailBean;
import com.risenb.studyknowledge.beans.personal.MemberTopicListBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by zhuzh on 2017/4/18.
 * 会员详情
 */

public class MemberDetailP extends PresenterBase {

    private MemberDetailFace memberDetailFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    public static final String TYPE_TALK_ABOUT="2";//大家谈
    public static final String TYPE_PAY_QUESTION="3";//有偿
    private String times;
    private List<MemberTopicListBean> list=new ArrayList<>();
    public MemberDetailP(MemberDetailFace face, FragmentActivity activity) {
        this.memberDetailFace = face;
        setActivity(activity);
    }

    /**
     * 获取会员详情
     * @param c            登录标识
     * @param attendId    会员ID
     * @param postType    帖子分类(2：大家谈、3：有偿提问)
     * @param timestamp   查询时间戳
     * @param page         第几页
     * @param limit        每页显示多少条
     */
    public void getMemberDetail(final String tag, String c, String attendId, String postType, String
            timestamp, String page, final String limit) {
        NetworkUtils.getNetworkUtils().memberDetail(c,attendId,postType,timestamp,page,limit, new
                DataCallback<MemberDetailBean.DataBean>() {
            @Override
            public void onError(Call call, Exception e, int i) {
                makeText(activity.getResources().getString(R.string.network_error));
                memberDetailFace.memberDetailFail();
            }

            @Override
            public void onSuccess(MemberDetailBean.DataBean result, int i) {
                times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                //帖子列表信息
                if(TextUtils.equals(tag,REFRESH)){
                    list=result.getPostList();
                }else if(TextUtils.equals(tag,LOAD)){
                    list.addAll(result.getPostList());
                    if(result.getPostList().size()<=0){
                        makeText(activity.getResources().getString(R.string.no_more_data));
                    }
                }
                memberDetailFace.memberDetailSuccess(tag,times,list);
            }

            @Override
            public void onStatusError(String errorCode, String errorMsg) {
                makeText(errorMsg);
                memberDetailFace.memberDetailFail();
            }
        });

    }
    public interface MemberDetailFace {
        void memberDetailSuccess(String tag,String timestamp,List<MemberTopicListBean> postList);
        void memberDetailFail();
    }
}
