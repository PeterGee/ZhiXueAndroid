package com.risenb.studyknowledge.utils;

import android.text.TextUtils;
import android.util.Log;

import com.lidroid.mutils.MUtils;
import com.lidroid.mutils.network.HttpBack;
import com.lidroid.mutils.network.NetMethod;
import com.lidroid.mutils.network.ReqParams;
import com.mzhy.http.okhttp.OkHttpUtils;
import com.risenb.studyknowledge.MyApplication;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.network.NetUtils;

import java.io.File;


/**
 * 调用接口
 */
public class NetworkUtils {
    private static NetworkUtils networkUtils;
    protected MyApplication application;
    private String PAGE = "page";
    private String LIMIT = "limit";
    private String TIME = "timestamp";

    public static NetworkUtils getNetworkUtils() {
        if (networkUtils == null) {
            networkUtils = new NetworkUtils();
        }
        return networkUtils;
    }

    public void setApplication(MyApplication application) {
        this.application = application;
    }

    private String getUrl(int id) {
        return application.getResources().getString(R.string.service_host_address).concat(application.getString(id).concat(".do"));
    }

    public String getUrl1(int id) {
        return application.getResources().getString(R.string.service_test_address).concat(application.getString(id).concat(".do"));
    }

    private ReqParams getReqParams() {
        ReqParams param = new ReqParams();
        if (!TextUtils.isEmpty(application.getC())) {
            param.addParam("c", application.getC());
        }
        return param;
    }

    /**
     * 登录
     *
     * @param name
     * @param password
     * @param httpBack
     */
    public void login(String name, String password, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.userLogin))
                .addParams("mobile", name)
                .addParams("pwd", password)
                .build().execute(httpBack);
    }

    /**
     * 注册
     *
     * @param telephone
     * @param password
     * @param code
     * @param httpBack
     */
    public void register(String telephone, String password, String code, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.userRegister))
                .addParams("mobile", telephone)
                .addParams("code", code)
                .addParams("pwd", password)
                .build().execute(httpBack);
    }

    /**
     * 上传文件
     *
     * @param file
     * @param httpBack
     */
    public void addFile(File file, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.uploadFiles))
                .addFile("files", file.getName(), file)
                .build().execute(httpBack);
    }

    /**
     * 获取验证码
     *
     * @param phone
     * @param httpBack type  是否需要验证手机号是否存在注册，0-不需要，1-需要 2-忘记密码
     */
    public void getCode(String phone, String type, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.authcode))
                .addParams("mobile", phone)
                .addParams("type", type)
                .build().execute(httpBack);
    }

    /**
     * 忘记密码
     *
     * @param telephone
     * @param password
     * @param code
     * @param httpBack
     */
    public void forgetPwd(String telephone, String code, String password, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.forgetPassword))
                .addParams("mobile", telephone)
                .addParams("code", code)
                .addParams("newPwd", password)
                .build().execute(httpBack);
    }

    /**
     * 修改用信息
     *
     * @param userName
     * @param httpBack
     */
    public void modifyUsernameInfo(String userName, String mobile, String email, String code, String userIntro, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.updateInfo))
                .addParams("userName", userName)
                .addParams("mobile", mobile)
                .addParams("email", email)
                .addParams("code", code)
                .addParams("userIntro", userIntro)
                .build().execute(httpBack);
    }

    /**
     * 获取C端会员管理接口
     *
     * @param c         登录标识
     * @param collegeId 学院ID
     * @param name      会员昵称
     * @param level     会员等级
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getMemberList(String c, String collegeId, String name, String level, String
            timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getVipList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("attendUsername", name)
                .addParams("userCollegegradeId", level)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
        ReqParams reqParams = getReqParams();
        Log.d("NetworkUtils", reqParams.toString());
    }

    /**
     * 获取会员详情
     *
     * @param c         登录标识
     * @param attendId  会员ID
     * @param postType  帖子分类(2：大家谈、3：有偿提问)
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void memberDetail(String c, String attendId, String postType, String
            timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getMemberDetail))
                .addParams("c", c)
                .addParams("attendId", attendId)
                .addParams("postType", postType)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 获取用户信息
     *
     * @param c
     * @param httpBack
     */
    public void getPersonalInfo(String c, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getUserInfo))

                .build().execute(httpBack);
    }


    /**
     * 获取直播预告列表
     *
     * @param c
     * @param collegeId
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getLiveList(String c, String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getLiveList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 删除直播预告
     *
     * @param c
     * @param postId
     * @param httpBack
     */
    public void deleteLive(String c, String postId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.deleteLive))
                .addParams("c", c)
                .addParams("postId", postId)
                .build().execute(httpBack);
    }

    /**
     * 获取话题列表
     *
     * @param c
     * @param collegeId
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getTopicList(String c, String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getTopicList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 保存会员信息
     *
     * @param c               登录标识
     * @param attendId        会员ID
     * @param attendUsername  会员名称
     * @param attendType      会员身份(0：学生、1：管理、2：老师、默认为0
     * @param attendAllowYn   是否拉黑(是否允许再加入：1：是、2：否)
     * @param attendTalkLimit 是否禁言(0：否、1：是)
     * @param attendTalkTime  禁言时间
     * @param medalTypeIds    勋章类型ID
     * @param httpBack
     */
    public void saveMemberInfo(String c, String attendId, String attendUsername, String
            attendType, String attendAllowYn, String attendTalkLimit, String attendTalkTime, String
                                       medalTypeIds, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.saveMemberInfo))
                .addParams("c", c)
                .addParams("attendId", attendId)
                .addParams("attendUsername", attendUsername)
                .addParams("attendType", attendType)
                .addParams("attendAllowYn", attendAllowYn)
                .addParams("attendTalkLimit", attendTalkLimit)
                .addParams("attendTalkTime", attendTalkTime)
                .addParams("medalTypeIds", medalTypeIds)
                .build().execute(httpBack);
    }

    /**
     * 踢出会员
     *
     * @param c         登录标识
     * @param attendId  会员ID
     * @param collegeId 学院ID
     * @param httpBack
     */
    public void kickOutMember(String c, String attendId, String collegeId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.kickOutVip))
                .addParams("c", c)
                .addParams("attendId", attendId)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .build().execute(httpBack);
    }

    /**
     * 获取踢出会员列表
     *
     * @param c         登录标识
     * @param collegeId 学院ID
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getKickOutList(String c, String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getDelynVipList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 邀请会员
     *
     * @param c        登录标识
     * @param attendId 申请ID
     * @param httpBack
     */
    public void kickOutMemberInvite(String c, String attendId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.kickOutVipInvite))
                .addParams("c", c)
                .addParams("attendId", attendId)
                .build().execute(httpBack);
    }

    /**
     * 获取黑名单列表
     *
     * @param c         登录标识
     * @param collegeId 学院ID
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getBlackList(String c, String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getBlackList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 取消拉黑
     *
     * @param c        登录标识
     * @param attendId 申请ID
     * @param httpBack
     */
    public void cancelBlackList(String c, String attendId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.removeBlackList))
                .addParams("c", c)
                .addParams("attendId", attendId)
                .build().execute(httpBack);
    }

    /**
     * 获取申请会员列表
     *
     * @param c         登录标识
     * @param collegeId 学院ID
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getApplyVipList(String c, String collegeId, String timestamp, String page, String
            limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getApplyVipList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 拒绝/通过会员申请
     *
     * @param c            登录标识
     * @param attendIds    申请ID(多个会员申请时用，号隔开)
     * @param attendPassYn 申请是否通过(1：通过、2：拒绝)
     * @param httpBack
     */
    public void applyVipPass(String c, String attendIds, String attendPassYn, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.applyVipPass))
                .addParams("c", c)
                .addParams("attendIds", attendIds)
                .addParams("attendPassYn", attendPassYn)
                .build().execute(httpBack);
    }

    /**
     * 签到管理
     *
     * @param c           登录标识
     * @param collegeId   学院ID
     * @param searchMonth 查询年月份 格式xx年xx月
     * @param httpBack
     */
    public void getSignin(String c, String collegeId, String searchMonth, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getSignin))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("searchMonth", searchMonth)
                .build().execute(httpBack);
    }

    /**
     * 会员等级设置列表
     *
     * @param c         登录标识
     * @param collegeId 学院ID
     * @param httpBack
     */
    public void vipLevelSettingList(String c, String collegeId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.updateVipGrade))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .build().execute(httpBack);
    }

    /**
     * 保存会员等级设置
     *
     * @param c                      登录标识
     * @param collegeId              学院ID
     * @param userCollegegradeId     学院会员等级ID
     * @param userCollegegradeName   学院会员等级名称
     * @param userCollegegradePoints 学院会员等级所需积分
     * @param httpBack
     */
    public void saveLevelSetting(String c, String collegeId, String userCollegegradeId, String
            userCollegegradeName, String userCollegegradePoints, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.saveVipGrade))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("userCollegegradeId", userCollegegradeId)
                .addParams("userCollegegradeName", userCollegegradeName)
                .addParams("userCollegegradePoints", userCollegegradePoints)
                .build().execute(httpBack);
    }

    /**
     * 获取勋章列表
     *
     * @param c         登录标识
     * @param collegeId 学院ID
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getMedalList(String c, String collegeId, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getMedalList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 添加或修改勋章
     *
     * @param c             登录标识
     * @param collegeId     学院ID
     * @param medalTypeId   勋章类型ID
     * @param medalTypeName 勋章类型标题
     * @param medalTypeInfo 勋章类型描述
     * @param medalTypeMig  勋章类型图片
     * @param httpBack
     */
    public void addModifyMedal(String c, String collegeId, String medalTypeId, String
            medalTypeName, String medalTypeInfo, String medalTypeMig, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.saveMedalType))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("medalTypeId", medalTypeId)
                .addParams("medalTypeName", medalTypeName)
                .addParams("medalTypeInfo", medalTypeInfo)
                .addParams("medalTypeMig", medalTypeMig)
                .build().execute(httpBack);
    }

    /**
     * 删除勋章
     *
     * @param c           登录标识
     * @param collegeId   学院ID
     * @param medalTypeId 勋章类型ID
     * @param httpBack
     */
    public void deleteMedal(String c, String collegeId, String medalTypeId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.delMedalType))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("medalTypeId", medalTypeId)
                .build().execute(httpBack);
    }

    /**
     * 获取vip申请明细列表
     *
     * @param c         登录标识
     * @param collegeId 学院ID
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getVIPApplyDetailList(String c, String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getVipDetail))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 获取友商购进列表
     *
     * @param collegeId 学院ID
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getBusinessInList(String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getBuyInList))
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 获取友商售出列表
     *
     * @param collegeId 学院ID
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getBusinessOutList(String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getBuyOutList))
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 删除/取消代理友商
     *
     * @param buyTopicId 友商购买id
     * @param httpBack
     */
    public void deleteBusinessTopic(String buyTopicId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.delBuyTopic))
                .addParams("buyTopicId", buyTopicId)
                .build().execute(httpBack);
    }

    /**
     * 获取提现明细列表
     *
     * @param c         登录标识
     * @param collegeId 学院ID
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getCashRecordList(String c, String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getCashRecordList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 获取公告列表
     *
     * @param c         登录标识
     * @param collegeId 学院ID
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getAnnounceList(String c, String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getNoticeList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .tag(getReqParams())
                .build().execute(httpBack);

    }

    /**
     * 删除公告
     *
     * @param c        登录标识
     * @param noticeId 公告id
     * @param httpBack
     */
    public void deleteAnnounce(String c, String noticeId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.delNotice))
                .addParams("c", c)
                .addParams("noticeId", noticeId)
                .build().execute(httpBack);
    }

    /**
     * 添加学院公告信息
     *
     * @param c           登录标识
     * @param collegeId   学院id
     * @param noticeTitle 公告标题
     * @param noticeInfo  公告内容
     * @param httpBack
     */
    public void addAnnounce(String c, String collegeId, String noticeTitle, String noticeInfo, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.addNotice))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("noticeTitle", noticeTitle)
                .addParams("noticeInfo", noticeInfo)
                .build().execute(httpBack);
    }

    /**
     * 修改学院公告信息
     *
     * @param c           登录标识
     * @param noticeId    公告id
     * @param noticeTitle 公告标题
     * @param noticeInfo  公告内容
     * @param httpBack
     */
    public void updateAnnounce(String c, String noticeId, String noticeTitle, String noticeInfo, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.updateNotice))
                .addParams("c", c)
                .addParams("noticeId", noticeId)
                .addParams("noticeTitle", noticeTitle)
                .addParams("noticeInfo", noticeInfo)
                .build().execute(httpBack);
    }

    /**
     * 获取意见反馈列表
     *
     * @param c         登录标识
     * @param collegeId 学院id
     * @param key       key=0 未读  key=1已读(为空时则展示全部列表)
     * @param timestamp 查询时间戳
     * @param page      第几页
     * @param limit     每页显示多少条
     * @param httpBack
     */
    public void getAdviceList(String c, String collegeId, String key, String timestamp,
                              String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getAdviceList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("key", key)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 意见反馈是否已读
     *
     * @param c        登录标识
     * @param adviceId 意见反馈id
     * @param httpBack
     */
    public void isRead(String c, String adviceId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.isRead))
                .addParams("c", c)
                .addParams("adviceId", adviceId)
                .build().execute(httpBack);
    }

    /**
     * 意见反馈至平台
     *
     * @param c             登录标识
     * @param collegeId     学院id
     * @param adviceContent 意见反馈内容
     * @param httpBack
     */
    public void addAdvice(String c, String collegeId, String adviceContent, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.addAdvice))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("adviceContent", adviceContent)
                .build().execute(httpBack);
    }


    /**
     * 获取活动管理列表
     *
     * @param c
     * @param collegeId
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getActionList(String c, String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getActionList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 获取活动参与人列表
     *
     * @param c
     * @param activityId
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getActivityUserList(String c, String activityId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getActivityUserList))
                .addParams("c", c)
                .addParams("activityId", activityId)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 获取投票列表
     *
     * @param c
     * @param collegeId
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getVoteList(String c, String collegeId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getVoteList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 获取用户信息
     *
     * @param c
     * @param httpBack
     */
    public void getCollegeInfo(String c, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getCollegeInfo))
                .addParams("c", c)
                .build().execute(httpBack);
    }

    /**
     * 获取VIP列表
     *
     * @param httpBack
     */
    public void getCollegeVip(DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getCollegeVip))
                .build().execute(httpBack);
    }

    /**
     * 获取帖子列表
     *
     * @param c
     * @param postTopicId
     * @param timestamp
     * @param page
     * @param limit
     * @param type
     * @param postType
     * @param key
     * @param httpBack
     */
    public void getPostList(String c, String postTopicId, String timestamp, String page,
                            String limit, String type, String postType, String key, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getPostList))
                .addParams("c", c)
                .addParams("postTopicId", postTopicId)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .addParams("type", type)
                .addParams("postType", postType)
                .addParams("key", key)
                .build().execute(httpBack);
    }


    /**
     * 发布贴子
     *
     * @param c
     * @param postTopicId
     * @param postName
     * @param postType
     * @param postWriterId
     * @param postIsFree
     * @param postPrice
     * @param postIsTop
     * @param postContent
     * @param httpBack
     */
    public void addPost(String c, String postTopicId, String postName, String postType,
                        String postWriterId, String postIsFree,
                        String postPrice, String postIsTop,
                        String postContent, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.addPost))
                .addParams("c", c)
                .addParams("postType", postType)
                .addParams("postName", postName)
                .addParams("postTopicId", postTopicId)
                .addParams("postWriterId", postWriterId)
                .addParams("postIsFree", postIsFree)
                .addParams("postPrice", postPrice)
                .addParams("postIsTop", postIsTop)
                .addParams("postContent", postContent)
                .build().execute(httpBack);
    }


    /**
     * 修改贴子
     *
     * @param c
     * @param postId
     * @param postName
     * @param postIsFree
     * @param postPrice
     * @param postIsTop
     * @param postContent
     * @param httpBack
     */
    public void updatePost(String c, String postId, String postName, String postIsFree,
                           String postPrice, String postIsTop,
                           String postContent, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.updatePost))
                .addParams("c", c)
                .addParams("postId", postId)
                .addParams("postName", postName)
                .addParams("postIsFree", postIsFree)
                .addParams("postPrice", postPrice)
                .addParams("postIsTop", postIsTop)
                .addParams("postContent", postContent)
                .build().execute(httpBack);
    }


    /**
     * 评论贴子
     *
     * @param c
     * @param postId
     * @param type
     * @param floorData
     * @param httpBack
     */
    public void commentPost(String c, String postId, String type, String floorData, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.commentPost))
                .addParams("c", c)
                .addParams("postId", postId)
                .addParams("type", type)
                .addParams("floorData", floorData)

                .build().execute(httpBack);
    }


    /**
     * 评论楼层回复（评论贴子）
     *
     * @param c
     * @param postId
     * @param floorId
     * @param commentUserId
     * @param beCommentUserId
     * @param talkInfo
     * @param httpBack
     */
    public void commentReply(String c,
                             String postId, String floorId,
                             String commentUserId, String beCommentUserId, String talkInfo, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.commentReply))
                .addParams("c", c)
                .addParams("postId", postId)
                .addParams("floorId", floorId)
                .addParams("commentUserId", commentUserId)
                .addParams("beCommentUserId", beCommentUserId)
                .addParams("talkInfo", talkInfo)

                .build().execute(httpBack);
    }


    /**
     * 投票详情列表
     *
     * @param c
     * @param voteId
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getVoteDetailList(String c, String voteId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getVoteDetailList))
                .addParams("c", c)
                .addParams("postId", voteId)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 添加话题
     *
     * @param c
     * @param collegeId
     * @param topicName
     * @param topicPayType
     * @param topicType
     * @param topicIsTop
     * @param topicUseyn
     * @param topicImg
     * @param topicPrice
     * @param topicVipName
     * @param ids
     * @param httpBack
     */
    public void addTopic(String c, String collegeId, String topicName, String topicPayType, String topicType,
                         String topicIsTop, String topicUseyn, String topicImg,
                         String topicPrice, String topicVipName, String ids, DataCallback httpBack) {

        OkHttpUtils.getInstance().post().url(getUrl(R.string.addTopic))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("topicName", topicName)
                .addParams("topicPayType", topicPayType)
                .addParams("topicType", topicType)
                .addParams("topicIsTop", topicIsTop)
                .addParams("topicUseyn", topicUseyn)
                .addParams("topicImg", topicImg)
                .addParams("topicPrice", topicPrice)
                .addParams("topicVipName", topicVipName)
                .addParams("ids", ids)
                .build().execute(httpBack);
    }

    /**
     * 修改话题
     *
     * @param c
     * @param topicId
     * @param topicName
     * @param topicPayType
     * @param topicType
     * @param topicIsTop
     * @param topicUseyn
     * @param topicImg
     * @param topicPrice
     * @param topicVipName
     * @param ids
     * @param httpBack
     */
    public void updateTopic(String c, String topicId, String topicName, String topicPayType, String topicType,
                            String topicIsTop, String topicUseyn, String topicImg,
                            String topicPrice, String topicVipName, String ids, DataCallback httpBack) {

        OkHttpUtils.getInstance().post().url(getUrl(R.string.updateTopic))
                .addParams("c", c)
                .addParams("topicId", topicId)
                .addParams("topicName", topicName)
                .addParams("topicPayType", topicPayType)
                .addParams("topicType", topicType)
                .addParams("topicIsTop", topicIsTop)
                .addParams("topicUseyn", topicUseyn)
                .addParams("topicImg", topicImg)
                .addParams("topicPrice", topicPrice)
                .addParams("topicVipName", topicVipName)
                .addParams("ids", ids)
                .build().execute(httpBack);
    }

    /**
     * 话题上下架
     *
     * @param c
     * @param topicId
     * @param topicUseyn
     * @param httpBack
     */
    public void isUpOrDown(String c, String topicId, String topicUseyn, DataCallback<NetBaseBean> httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.isUpOrDown))
                .addParams("c", c)
                .addParams("topicId", topicId)
                .addParams("topicUseyn", topicUseyn)
                .build().execute(httpBack);
    }

    /**
     * 话题排序
     *
     * @param c
     * @param topicId1
     * @param topicId2
     * @param httpBack
     */
    public void updateSort(String c, String topicId1, String topicId2, DataCallback<NetBaseBean> httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.updateSort))
                .addParams("c", c)
                .addParams("topicId1", topicId1)
                .addParams("topicId2", topicId2)
                .build().execute(httpBack);
    }

    /**
     * 添加直播预告
     *
     * @param c
     * @param collegeId
     * @param postName
     * @param postTopicId
     * @param postWriterId
     * @param postLivetime
     * @param postIsFree
     * @param postPrice
     * @param postIsTop
     * @param postInfo
     * @param httpBack
     */
    public void addPostLive(String c, String collegeId, String postName, String postTopicId, String postWriterId, String postLivetime,
                            String postIsFree, String postPrice, String postIsTop, String postInfo, DataCallback<NetBaseBean> httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.addPostLive))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("postName", postName)
                .addParams("postTopicId", postTopicId)
                .addParams("postWriterId", postWriterId)
                .addParams("postLivetime", postLivetime)
                .addParams("postIsFree", postIsFree)
                .addParams("postPrice", postPrice)
                .addParams("postIsTop", postIsTop)
                .addParams("postInfo", postInfo)
                .build().execute(httpBack);
    }

    /**
     * 讲师列表
     *
     * @param c
     * @param collegeId
     * @param timestamp
     * @param page
     * @param limit
     * @param key
     * @param httpBack
     */
    public void getTeacherList(String c, String collegeId, String timestamp, String page, String limit, String key,
                               DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getTeacherList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .addParams("key", key)
                .build().execute(httpBack);
    }

    /**
     * 帖子详情
     *
     * @param c
     * @param postId
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getPostDetail(String c, String postId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getPostDetail))
                .addParams("c", c)
                .addParams("postId", postId)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }


    /**
     * 获取举报列表
     *
     * @param c
     * @param collegeId
     * @param complaintType
     * @param orderBy
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getReportList(String c, String collegeId, String complaintType, String orderBy, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getComplaintList))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("complaintType", complaintType)
                .addParams("orderBy", orderBy)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }


    /**
     * 获取举报明细
     *
     * @param c
     * @param collegeId
     * @param complaintId
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getReportDes(String c, String collegeId, String complaintId, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getComplaintDes))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("complaintId", complaintId)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);

    }


    /**
     * 删除举报
     *
     * @param c
     * @param collegeId
     * @param complaintType
     * @param complaintToId
     * @param httpBack
     */
    public void delReport(String c, String collegeId, String complaintType, String complaintToId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.delComplaintDes))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("complaintType", complaintType)
                .addParams("complaintToId", complaintToId)

                .build().execute(httpBack);
    }


    /**
     * 近期收益
     *
     * @param collegeId
     * @param httpBack
     */
    public void getAccount(String c, String collegeId, String startDate, String endDate, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getAccount))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("startDate", startDate)
                .addParams("endDate", endDate)
                .build().execute(httpBack);
    }


    /**
     * 人群收益
     *
     * @param c
     * @param collegeId
     * @param startDate
     * @param endDate
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getCollegeAccount(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getCollegeAccount))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("startDate", startDate)
                .addParams("endDate", endDate)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 话题收益
     *
     * @param c
     * @param collegeId
     * @param startDate
     * @param endDate
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getTopicAccount(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getTopicAccount))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("startDate", startDate)
                .addParams("endDate", endDate)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 贴子收益
     *
     * @param c
     * @param collegeId
     * @param startDate
     * @param endDate
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getPostAccount(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getPostAccount))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("startDate", startDate)
                .addParams("endDate", endDate)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }

    /**
     * 打赏收益记录
     *
     * @param c
     * @param collegeId
     * @param startDate
     * @param endDate
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getGiveAccount(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getGiveAccount))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("startDate", startDate)
                .addParams("endDate", endDate)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }


    /**
     * 打赏分成收益记录
     *
     * @param c
     * @param collegeId
     * @param startDate
     * @param endDate
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getGiveScalAccount(String c, String collegeId, String startDate, String endDate, String timestamp, String page, String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getGiveScalAccount))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("startDate", startDate)
                .addParams("endDate", endDate)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }


    /**
     * 获取提现信息
     *
     * @param c
     * @param collegeId
     * @param httpBack
     */
    public void getCashInfo(String c, String collegeId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getCashInfo))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))

                .build().execute(httpBack);
    }

    /**
     * 申请提现
     *
     * @param c
     * @param collegeId
     * @param cashValue
     * @param httpBack
     */
    public void addCashRecord(String c, String collegeId, String cashValue, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.addCashRecord))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("cashValue", cashValue)

                .build().execute(httpBack);
    }


    /**
     * 添加友商售出
     *
     * @param topicId
     * @param collegeId
     * @param newWriterId
     * @param months
     * @param httpBack
     */
    public void addBuyTopic(String topicId, String collegeId, String newWriterId, String months, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.addBuyTopic))
                .addParams("topicId", topicId)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("newWriterId", newWriterId)
                .addParams("months", months)
                .build().execute(httpBack);
    }


    /**
     * 修改友商售出信息
     *
     * @param buyTopicId
     * @param months
     * @param httpBack
     */
    public void updateBuyTopic(String buyTopicId, String months, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.updateBuyTopic))
                .addParams("buyTopicId", buyTopicId)
                .addParams("months", months)
                .build().execute(httpBack);
    }


    /**
     * 修改密码
     *
     * @param pwd
     * @param newPwd
     * @param httpBack
     */
    public void updatePwd(String pwd, String newPwd, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.updatePwd))
                .addParams("pwd", pwd)
                .addParams("newPwd", newPwd)
                .build().execute(httpBack);
    }


    /**
     * 获取邮箱验证码
     *
     * @param email
     * @param type
     * @param httpBack
     */
    public void getEmailCode(String email, String type, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getEmailCode))
                .addParams("email", email)
                .addParams("type", type)
                .build().execute(httpBack);
    }


    /**
     * 发布活动
     *
     * @param c
     * @param topicId
     * @param activityImg
     * @param activityName
     * @param activityType
     * @param activityIsTop
     * @param activityWriterId
     * @param startTime
     * @param endTime
     * @param activityContent
     * @param httpBack
     */
    public void addActivity(
            String c, String topicId,
            String activityImg, String activityName,
            String activityType, String activityIsTop,
            String activityWriterId, String startTime,
            String endTime, String activityContent, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.addActivity))
                .addParams("c", c)
                .addParams("topicId", topicId)
                .addParams("activityImg", activityImg)
                .addParams("activityName", activityName)
                .addParams("activityType", activityType)
                .addParams("activityIsTop", activityIsTop)
                .addParams("activityWriterId", activityWriterId)
                .addParams("startTime", startTime)
                .addParams("endTime", endTime)
                .addParams("activityContent", activityContent)
                .build().execute(httpBack);
    }

    /**
     * 删除活动
     *
     * @param c
     * @param activityId
     * @param httpBack
     */
    public void delActivity(String c, String activityId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.delActivity))
                .addParams("c", c)
                .addParams("activityId", activityId)
                .build().execute(httpBack);
    }

    /**
     * 编辑活动
     *
     * @param c
     * @param topicId
     * @param activityId
     * @param activityImg
     * @param activityName
     * @param activityType
     * @param activityIsTop
     * @param activityWriterId
     * @param startTime
     * @param endTime
     * @param activityContent
     * @param httpBack
     */
    public void updateActivity(String c, String topicId, String activityId,
                               String activityImg, String activityName,
                               String activityType, String activityIsTop,
                               String activityWriterId, String startTime,
                               String endTime, String activityContent, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.updateActivity))
                .addParams("c", c)
                .addParams("topicId", topicId)
                .addParams("activityId", activityId)
                .addParams("activityImg", activityImg)
                .addParams("activityName", activityName)
                .addParams("activityType", activityType)
                .addParams("activityIsTop", activityIsTop)
                .addParams("activityWriterId", activityWriterId)
                .addParams("startTime", startTime)
                .addParams("endTime", endTime)
                .addParams("activityContent", activityContent)
                .build().execute(httpBack);
    }


    /**
     * 添加投票
     *
     * @param c
     * @param topicId
     * @param voteName
     * @param topicType
     * @param voteIsTop
     * @param voteWriterId
     * @param startTime
     * @param endTime
     * @param voteSecNames
     * @param isMultipleChoice
     * @param httpBack
     */
    public void addVote(String c, String topicId, String voteName, String topicType, String voteIsTop,
                        String voteWriterId, String startTime, String endTime, String voteSecNames,
                        boolean isMultipleChoice, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.addVote))
                .addParams("c", c)
                .addParams("topicId", topicId)
                .addParams("voteName", voteName)
                .addParams("topicType", topicType)
                .addParams("voteIsTop", voteIsTop)
                .addParams("voteWriterId", voteWriterId)
                .addParams("startTime", startTime)
                .addParams("endTime", endTime)
                .addParams("voteSecNames", voteSecNames)
                .addParams("isMultipleChoice", String.valueOf(isMultipleChoice))
                .build().execute(httpBack);
    }

    /**
     * 删除投票
     *
     * @param c
     * @param voteId
     * @param httpBack
     */
    public void delVote(String c, String voteId, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.delVote))
                .addParams("c", c)
                .addParams("voteId", voteId)
                .build().execute(httpBack);
    }

    /**
     * 编辑学院
     *
     * @param c
     * @param collegeId
     * @param collegeName
     * @param collegeUser
     * @param collegeAccBankinfo
     * @param collegeAccBank
     * @param collegeBackimg
     * @param scale
     * @param collegeType
     * @param collegePrice
     * @param collegeDelYn
     * @param collegeInfo
     * @param httpBack
     */
    public void editCollege(
            String c, String collegeId,
            String collegeName, String collegeUser,
            String collegeAccBankinfo, String collegeAccBank,
            String collegeBackimg, String scale,
            String collegeType, String collegePrice,
            String collegeDelYn, String collegeInfo,
            DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.editCollege))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("collegeName", collegeName)
                .addParams("collegeUser", collegeUser)
                .addParams("collegeAccBankinfo", collegeAccBankinfo)
                .addParams("collegeAccBank", collegeAccBank)
                .addParams("collegeBackimg", collegeBackimg)
                .addParams("scale", scale)
                .addParams("collegeType", collegeType)
                .addParams("collegePrice", collegePrice)
                .addParams("collegeDelYn", collegeDelYn)
                .addParams("collegeInfo", collegeInfo)
                .build().execute(httpBack);
    }


    /**
     * 获取有偿收益明细
     *
     * @param c
     * @param collegeId
     * @param startDate
     * @param endDate
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getYouChangAccount(
            String c, String collegeId,
            String startDate, String endDate,
            String timestamp, String page,
            String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getYouChangAccount))
                .addParams("c", c)
                .addParams("collegeId", MUtils.getMUtils().getMem("collegeId","45"))
                .addParams("startDate", startDate)
                .addParams("endDate", endDate)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }


    /**
     * 有偿贴子详情
     *
     * @param c
     * @param postId
     * @param timestamp
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getYouChangDetail(
            String c, String postId,
            String timestamp, String page,
            String limit, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.getYouChangDetail))
                .addParams("c", c)
                .addParams("postId", postId)
                .addParams(TIME, timestamp)
                .addParams(PAGE, page)
                .addParams(LIMIT, limit)
                .build().execute(httpBack);
    }


    /**
     * 偷看有偿贴子
     * @param c
     * @param postId
     * @param floorId
     * @param peepUserId
     * @param peepBeUserId
     * @param peepPrice
     * @param httpBack
     */
    public void peepAnswer(
            String c, String postId,
            String floorId, String peepUserId,
            String peepBeUserId, String peepPrice, DataCallback httpBack) {
        OkHttpUtils.getInstance().post().url(getUrl(R.string.peepAnswer))
                .addParams("c", c)
                .addParams("postId", postId)
                .addParams("floorId", floorId)
                .addParams("peepUserId", peepUserId)
                .addParams("peepBeUserId", peepBeUserId)
                .addParams("peepPrice", peepPrice)
                .build().execute(httpBack);
    }

    /**
     * 获取可加入的学院列表
     *
     * @param c
     * @param httpBack
     */
    public void getMoreUserCollege(
            String c, HttpBack<String > httpBack) {

        ReqParams reqParams = new ReqParams();
        reqParams.addParam("c", c);
        NetUtils.getNetUtils().send(getUrl(R.string.getMoreUserCollege), reqParams, NetMethod.POST, httpBack);
    }

}
