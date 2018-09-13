package com.risenb.studyknowledge.ui.info;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.info.PersonalBgAdapter;
import com.risenb.studyknowledge.beans.info.PersonalBgBean;
import com.risenb.studyknowledge.beans.personal.KickOutMemberBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/11/7.
 * 个性背景
 */
@ContentView(R.layout.ui_personal_bg)
public class PersonalBgUI extends BaseUI {
    @ViewInject(R.id.rv_personal_bg_list)
    RecyclerView rv_personal_bg_list;
    private List<PersonalBgBean> list=new ArrayList<>();
    private PersonalBgAdapter mPersonalBgAdapter;

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
        setTitle(getResources().getString(R.string.personal_bg));//个性背景

        for (int i = 0; i < 9; i++) {
            PersonalBgBean bean = new PersonalBgBean();
            bean.setInUse(false);
            list.add(bean);
        }
        list.get(0).setInUse(true);

        mPersonalBgAdapter = new PersonalBgAdapter(R.layout.personal_bg_item, list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv_personal_bg_list.setLayoutManager(gridLayoutManager);
        rv_personal_bg_list.setAdapter(mPersonalBgAdapter);

    }
    public static void start(Context context) {
        Intent starter = new Intent(context, PersonalBgUI.class);
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

}
