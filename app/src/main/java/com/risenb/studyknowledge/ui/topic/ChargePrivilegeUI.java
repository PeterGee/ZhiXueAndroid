package com.risenb.studyknowledge.ui.topic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.live.SelectLecturersAdapter;
import com.risenb.studyknowledge.adapter.topic.ChargePrivilegeAdapter;
import com.risenb.studyknowledge.beans.institution.ReportBean;
import com.risenb.studyknowledge.beans.live.SelectLecturersBean;
import com.risenb.studyknowledge.beans.topic.ChargePrivilegeBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhuzh on 2017/11/14.
 *
 * 收费权限
 */
@ContentView(R.layout.charge_privilege_ui)
public class ChargePrivilegeUI extends BaseUI implements BaseQuickAdapter.OnItemClickListener {

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_select)
    private TextView tv_select;
    @ViewInject(R.id.rv_privilege_list)
    private RecyclerView rv_privilege_list;
    private boolean isSelect = false;

    private ChargePrivilegeAdapter mAdapter;
    private List<ChargePrivilegeBean> listData = new ArrayList<>();
    private List<ChargePrivilegeBean> checkedList = new ArrayList<>();
    ;

    @Override
    protected void back() {
        finish();
        overridePendingTransition(R.anim.alpha, R.anim.activity_bottom_out);

    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ChargePrivilegeUI.class);
        context.startActivity(starter);
        ((Activity)context).overridePendingTransition(R.anim.activity_bottom_in, R.anim.alpha);
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.charge_privilege));
        iv_back.setImageResource(R.mipmap.down_arrow_big_white);
        rightVisible("确定");

        for (int i = 0; i < 10; i++) {
            ChargePrivilegeBean bean = new ChargePrivilegeBean();
            bean.setName("景小甜" + i);
            listData.add(bean);
        }
        mAdapter = new ChargePrivilegeAdapter(R.layout.charge_privilege_item, listData);
        rv_privilege_list.setAdapter(mAdapter);
        rv_privilege_list.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(this);
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

    @OnClick({R.id.ll_right,R.id.tv_select,R.id.tv_confirm})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_right:
                finish();
                overridePendingTransition(R.anim.alpha, R.anim.activity_bottom_out);
                break;
            case R.id.tv_select:
                if (!isSelect){
                    tv_select.setText("全部");
                    isSelect = true;
                    for (int i = 0; i < listData.size(); i++) {
                        if(!listData.get(i).isChecked()){
                            checkedList.add(listData.get(i));
                        }
                    }
                    listData.removeAll(checkedList);
                    mAdapter.notifyDataSetChanged();
                } else {
                    tv_select.setText("已选");
                    isSelect = false;
                    listData.clear();
                    for (int i = 0; i < 10; i++) {
                        ChargePrivilegeBean bean = new ChargePrivilegeBean();
                        bean.setName("景小甜" + i);
                        listData.add(bean);
                    }
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_confirm:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        boolean isChecked = listData.get(position).isChecked();
        listData.get(position).setChecked(!isChecked);
        mAdapter.notifyDataSetChanged();
    }


}
