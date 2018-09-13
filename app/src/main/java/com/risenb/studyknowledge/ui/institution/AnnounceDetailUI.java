package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.AnnounceBean;
import com.risenb.studyknowledge.beans.institution.NoticeListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.personal.MemberManagerUI;
import com.risenb.studyknowledge.utils.MaxTextLengthFilter;
import com.risenb.studyknowledge.utils.StatusBarUtils;

/**
 * Created by yy on 2017/11/24.
 * 公告详情页（含修改）
 */
@ContentView(R.layout.ui_announce_detail)
public class AnnounceDetailUI extends BaseUI implements UpdateAnnounceP.UpdateAnnounceFace {
    @ViewInject(R.id.et_announce_title)
    EditText et_announce_title;//公告标题
    @ViewInject(R.id.et_announce_info)
    EditText et_announce_info;//公告内容
    @ViewInject(R.id.tv_save)
    TextView tv_save;//保存编辑
    private NoticeListBean mAnnounceBean;
    private boolean isEditState=false;//是否是编辑状态
    private UpdateAnnounceP mUpdateAnnounceP;
    private String C="1643";
    @Override
    protected void back() {
        Intent mIntent = new Intent();
        mIntent.putExtra(AnnounceEditUI.ANNOUNCE_INFO,mAnnounceBean);
        setResult(AnnounceEditUI.RESULT_CODE,mIntent);
        finish();
    }
    @Override
    protected boolean isStatusBar() {
        return true;
    }
    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.announce_content));//公告内容
        rightVisible(getResources().getString(R.string.edit));

        et_announce_title.setFilters(new InputFilter[]{new MaxTextLengthFilter(50,getResources().getString
                (R.string.et_length_announce_title_tip))});
        et_announce_info.setFilters(new InputFilter[]{new MaxTextLengthFilter(500,getResources().getString
                (R.string.et_length_content_tip))});
        //公告基本信息
        mAnnounceBean = getIntent().getParcelableExtra(AnnounceEditUI.ANNOUNCE_INFO);
        //初始化公告基本信息
        isEditEnable(isEditState);

    }
    public static void start(Context context, NoticeListBean noticeListBean) {
        Intent starter = new Intent(context, AnnounceDetailUI.class);
        starter.putExtra(AnnounceEditUI.ANNOUNCE_INFO,noticeListBean);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        mUpdateAnnounceP = new UpdateAnnounceP(this, getActivity());
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    public void isEditEnable(boolean flag){
        et_announce_title.setFocusable(flag);
        et_announce_title.setFocusableInTouchMode(flag);
        et_announce_info.setFocusable(flag);
        et_announce_info.setFocusableInTouchMode(flag);
        if(flag){//可编辑状态
            et_announce_title.requestFocus();
            et_announce_title.setSelection(et_announce_title.getText().length());//将光标移至文字末尾
            rightVisible(getResources().getString(R.string.cancel));
            tv_save.setVisibility(View.VISIBLE);
        }else {
            et_announce_title.setText(mAnnounceBean.getNoticeTitle());
            et_announce_info.setText(mAnnounceBean.getNoticeInfo());
            rightVisible(getResources().getString(R.string.edit));
            tv_save.setVisibility(View.GONE);
        }
    }
    @OnClick({R.id.tv_right,R.id.tv_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_right://编辑/取消
                isEditState=!isEditState;
                isEditEnable(isEditState);
                break;
            case R.id.tv_save://保存
                mUpdateAnnounceP.updateAnnounce(C,mAnnounceBean.getNoticeId()+"");
                break;
            default:
                break;
        }
    }

    /**
     * 修改公告成功的回调
     */
    @Override
    public void updateAnnounceSuccess(NoticeListBean result) {
       this.mAnnounceBean=result;
        isEditState=false;
        isEditEnable(isEditState);
    }

    /**
     * 获取公告标题
     * @return
     */
    @Override
    public String getAnnounceTitle() {
        return et_announce_title.getText().toString().trim();
    }

    /**
     * 获取公告内容
     * @return
     */
    @Override
    public String getAnnounceInfo() {
        return et_announce_info.getText().toString().trim();
    }
}
