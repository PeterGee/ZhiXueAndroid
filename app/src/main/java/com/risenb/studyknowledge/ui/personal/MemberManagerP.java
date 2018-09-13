package com.risenb.studyknowledge.ui.personal;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.personal.AttendanceBean;
import com.risenb.studyknowledge.beans.personal.MemberLevelBean;
import com.risenb.studyknowledge.beans.personal.MemberManagerBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;
import com.risenb.studyknowledge.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by yy on 2017/11/15.
 * C端会员管理
 */

public class MemberManagerP extends PresenterBase {
    private MemberManagerFace memberManagerFace;
    public static final String REFRESH="refresh";
    public static final String LOAD="load";
    private String times;
    private List<AttendanceBean> list=new ArrayList<>();
    public MemberManagerP(MemberManagerFace face, FragmentActivity activity) {
        this.memberManagerFace = face;
        setActivity(activity);
    }
    /**
     * 获取C端会员管理列表
     */
    public void getMemberList(final String tag, String c, String collegeId, String name, String level, String
            timestamp, String page, String limit){
        NetworkUtils.getNetworkUtils().getMemberList(c,collegeId,name,level,timestamp,page,limit, new
                DataCallback<MemberManagerBean.DataBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        makeText(activity.getResources().getString(R.string.network_error));
                        List<MemberLevelBean> userCollege = new ArrayList<MemberLevelBean>();
                        MemberLevelBean bean= new MemberLevelBean();
                        bean.setUserCollegegradeName("会员等级");
                        bean.setUserCollegegradeId("");
                        userCollege.add(0,bean);
                        memberManagerFace.memberListFail(userCollege);
                    }

                    @Override
                    public void onSuccess(MemberManagerBean.DataBean result, int i) {
                        times= TextUtils.isEmpty(result.getTimestamp())?"":result.getTimestamp();
                        //会员等级列表
                        List<MemberLevelBean> userCollege = result.getUserCollege();
                        MemberLevelBean bean= new MemberLevelBean();
                        bean.setUserCollegegradeName("会员等级");
                        bean.setUserCollegegradeId("");
                        userCollege.add(0,bean);
                        //会员列表信息
                        if(TextUtils.equals(tag,REFRESH)){
                            list=result.getAttendanceList();
                        }else if(TextUtils.equals(tag,LOAD)){
                            list.addAll(result.getAttendanceList());
                            if(result.getAttendanceList().size()<=0){
                                makeText(activity.getResources().getString(R.string.no_more_data));
                            }
                        }
                        memberManagerFace.memberListSuccess(tag,times,list,userCollege);

                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        makeText(errorMsg);
                        List<MemberLevelBean> userCollege = new ArrayList<MemberLevelBean>();
                        MemberLevelBean bean= new MemberLevelBean();
                        bean.setUserCollegegradeName("会员等级");
                        bean.setUserCollegegradeId("");
                        userCollege.add(0,bean);
                        memberManagerFace.memberListFail(userCollege);
                    }
                });

    }

    /**
     * 踢出会员
     * @param c             登录标识
     * @param attendId     会员ID
     * @param collegeId    学院ID
     */
    public void kickOutVip(String c, String attendId, String collegeId, final int position) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().kickOutMember(c,attendId,collegeId, new
                DataCallback<NetBaseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(NetBaseBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        memberManagerFace.kickOutMemberSuccess(position);
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }

    public interface MemberManagerFace {
        //获取C端会员列表成功的回调
        void memberListSuccess(String tag,String timestamp,List<AttendanceBean>
                attendanceList,List<MemberLevelBean> userCollege);
        void memberListFail(List<MemberLevelBean> userCollege);

        //踢出会员成功的回调
        void kickOutMemberSuccess(int position);
    }
}
