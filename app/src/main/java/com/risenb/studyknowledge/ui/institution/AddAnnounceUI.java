package com.risenb.studyknowledge.ui.institution;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.AnnounceAdapter;
import com.risenb.studyknowledge.beans.institution.AnnounceBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.MaxTextLengthFilter;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/29.
 * 新增公告编辑
 */
@ContentView(R.layout.ui_add_announce)
public class AddAnnounceUI extends BaseUI implements AddAnnounceP.AddAnnounceFace {
    @ViewInject(R.id.et_announce_title)
    EditText et_announce_title;//公告标题
    @ViewInject(R.id.et_announce_info)
    EditText et_announce_info;//公告内容
    private AddAnnounceP mAddAnnounceP;
    private String C="1643";
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
        setTitle(getResources().getString(R.string.announcement_edit));//公告编辑

        et_announce_title.setFilters(new InputFilter[]{new MaxTextLengthFilter(50,getResources().getString
                (R.string.et_length_announce_title_tip))});
        et_announce_info.setFilters(new InputFilter[]{new MaxTextLengthFilter(500,getResources().getString
                (R.string.et_length_content_tip))});
        et_announce_title.requestFocus();
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, AddAnnounceUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        mAddAnnounceP = new AddAnnounceP(this, getActivity());
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
                mAddAnnounceP.addAnnounce(C,CollegeId);
                break;
            default:
                break;
        }
    }

    /**
     * 新增公告成功的回调
     */
    @Override
    public void addAnnounceSuccess() {
        Intent mIntent = new Intent();
        setResult(AnnounceEditUI.RESULT_CODE,mIntent);
        finish();
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
