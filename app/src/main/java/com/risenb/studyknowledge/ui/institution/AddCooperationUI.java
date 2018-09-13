package com.risenb.studyknowledge.ui.institution;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.AddBuyTopicBean;
import com.risenb.studyknowledge.beans.live.TeacherListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.live.SelectLecturersUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.utils.ToastUtils;

/**
 * Created by yy on 2017/9/25.
 * 添加合作
 */
@ContentView(R.layout.ui_add_cooperation)
public class AddCooperationUI extends BaseUI implements AddTopicFragment.OnTopicListener, AddCooperationP.AddCooperationListener {
    @ViewInject(R.id.iv_topic_arrow)
    ImageView iv_topic_arrow;//所属话题向右箭头
    @ViewInject(R.id.tv_topic_content)
    TextView tv_topic_content;//选中话题内容
    @ViewInject(R.id.iv_teacher_arrow)
    ImageView iv_teacher_arrow;
    @ViewInject(R.id.tv_teacher_name)
    TextView tv_teacher_name;//选中讲师姓名

    @ViewInject(R.id.et_purchase_period)
    EditText et_purchase_period;//月份

    @ViewInject(R.id.et_institution_name)
    EditText et_institution_name;//学院名称


    private AddTopicFragment mAddTopicFragment;
    public AddCooperationP mAddCooperationP;
    private int newWriterId;
    private String topicId;

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
        setTitle(getResources().getString(R.string.add_cooperation));//添加合作
        mAddCooperationP = new AddCooperationP(this, this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddCooperationUI.class);
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

    @OnClick({R.id.tv_setting_save, R.id.rl_add_topic, R.id.rl_choose_teacher})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_setting_save://保存

                String months = et_purchase_period.getText().toString().trim();
                String institutionName = et_institution_name.getText().toString().trim();

                if (TextUtils.isEmpty(months)) {
                    ToastUtils.showToast("请输入购买期限");
                    return;
                }


                if (TextUtils.isEmpty(institutionName)) {
                    ToastUtils.showToast("请输入学院名称");
                    return;
                }

                mAddCooperationP.setAddcooperationData(topicId, "45", String.valueOf(newWriterId), months);

                break;
            case R.id.rl_add_topic://添加所属话题
                showTopicFragment(true);
                break;
            case R.id.rl_choose_teacher://选择发布人
                SelectLecturersUI.start(this);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelectLecturersUI.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                TeacherListBean bean = (TeacherListBean) data.getSerializableExtra(SelectLecturersUI.TEACHER_INFO);
                iv_teacher_arrow.setVisibility(View.GONE);
                tv_teacher_name.setVisibility(View.VISIBLE);
                tv_teacher_name.setText(bean.getUserName());
                newWriterId = bean.getTeacherId();
            }
        }
    }

    /**
     * 显示话题弹窗
     *
     * @param show
     */
    private void showTopicFragment(boolean show) {
        if (mAddTopicFragment == null) {
            mAddTopicFragment = new AddTopicFragment();
            mAddTopicFragment.setOnTopicListener(this);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            //防止快速点击
            if (mAddTopicFragment.isAdded()) {
                return;
            }
            transaction.add(R.id.fl_topic, mAddTopicFragment).commit();
        } else {
            transaction.remove(mAddTopicFragment).commit();
        }
    }

    /**
     * 关闭话题列表弹窗
     *
     * @param view
     */
    @Override
    public void closeTopicListener(View view) {
        showTopicFragment(false);
    }

    /**
     * 回显选中话题
     *
     * @param view
     * @param topic
     */
    @Override
    public void topicListener(View view, String topic, String topicName) {
        iv_topic_arrow.setVisibility(View.GONE);
        tv_topic_content.setVisibility(View.VISIBLE);
        tv_topic_content.setText(topicName);
        showTopicFragment(false);

        this.topicId = topic;
    }


    @Override
    public void addCooperationData(AddBuyTopicBean addBuyTopicBean) {
        if (!TextUtils.isEmpty(addBuyTopicBean.getErrorMsg())) {
            ToastUtils.showToast(addBuyTopicBean.getErrorMsg());
        }
        finish();
    }

    @Override
    public void addCooperationField() {

    }
}
