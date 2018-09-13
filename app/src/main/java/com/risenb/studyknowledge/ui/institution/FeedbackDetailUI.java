package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.AdviceListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.personal.MemberManagerUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;

/**
 * Created by yy on 2017/9/30.
 * 意见反馈详情
 */
@ContentView(R.layout.ui_feedback_detail)
public class FeedbackDetailUI extends BaseUI{
    @ViewInject(R.id.tv_feedback_name)
    TextView tv_feedback_name;//反馈人姓名
    @ViewInject(R.id.tv_feedback_time)
    TextView tv_feedback_time;//反馈时间,
    @ViewInject(R.id.tv_feedback_type)
    TextView tv_feedback_type;//反馈来源（0：用户，1：学院）
    @ViewInject(R.id.tv_feedback_isRead)
    TextView tv_feedback_isRead;//是否已读（0：否，1：是）
    @ViewInject(R.id.tv_feedback_phone)
    TextView tv_feedback_phone;//反馈人电话号码,
    @ViewInject(R.id.tv_feedback_email)
    TextView tv_feedback_email;//反馈人邮箱
    @ViewInject(R.id.tv_feedback_content)
    TextView tv_feedback_content;//反馈内容

    public static final String FEEDBACK_INFO="feedback_info";
    private AdviceListBean mFeedbackBean;

    @Override
    protected void back() {
        finish();
    }
    @Override
    protected boolean isStatusBar() {
        return true;
    }
    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.feedback));//意见反馈
        tv_feedback_content.setMaxLines(500);

        mFeedbackBean = getIntent().getParcelableExtra(FEEDBACK_INFO);
        //初始化数据
        init();
    }
    public static void start(Context context, AdviceListBean adviceListBean) {
        Intent starter = new Intent(context, FeedbackDetailUI.class);
        starter.putExtra(FEEDBACK_INFO,adviceListBean);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    /**
     * 初始化操作
     */
    public void init(){
        tv_feedback_name.setText(mFeedbackBean.getUserName());//反馈人姓名,
        tv_feedback_time.setText(mFeedbackBean.getAdviceCreationTime());//反馈时间,
        int type = mFeedbackBean.getAdviceType();//反馈来源（0：用户，1：学院）
        String[] feedback_type = getResources().getStringArray(R.array.feedback_type);
        tv_feedback_type.setText(feedback_type[type]);
        int adviceReadyn = mFeedbackBean.getAdviceReadyn();//是否已读（0：否，1：是）
        String[] adviceReadyn_type = getResources().getStringArray(R.array.adviceReadyn);
        tv_feedback_isRead.setText(adviceReadyn_type[adviceReadyn]);
        tv_feedback_phone.setText(mFeedbackBean.getUserPhone());//反馈人电话号码,
        tv_feedback_email.setText(mFeedbackBean.getUserEmail());//反馈人邮箱
        tv_feedback_content.setText(mFeedbackBean.getAdviceContent());//反馈内容
    }
}
