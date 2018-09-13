package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.MaxTextLengthFilter;
import com.risenb.studyknowledge.utils.StatusBarUtils;

/**
 * Created by yy on 2017/9/30.
 * 添加意见反馈
 */
@ContentView(R.layout.ui_add_feedback)
public class AddFeedbackUI extends BaseUI implements AddFeedbackP.AddFeedbackFace {
    @ViewInject(R.id.et_feedback_content)
    EditText et_feedback_content;
    private AddFeedbackP mAddFeedbackP;
    private String C="1651";
    private String CollegeId="45";
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

        et_feedback_content.setFilters(new InputFilter[]{new MaxTextLengthFilter(500,getResources().getString
                (R.string.et_length_content_tip))});

    }
    public static void start(Context context) {
        Intent starter = new Intent(context, AddFeedbackUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        mAddFeedbackP = new AddFeedbackP(this, getActivity());
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }
    @OnClick({R.id.tv_commit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_commit://提交
                mAddFeedbackP.adviceToPlatform(C,CollegeId);
                break;
            default:
                break;
        }
    }

    /**
     * 意见反馈至平台成功的回调
     */
    @Override
    public void adviceToPlatformSuccess() {
        finish();
    }

    /**
     * 获取意见反馈内容
     * @return
     */
    @Override
    public String getAdviceContent() {
        return et_feedback_content.getText().toString().trim();
    }
}
