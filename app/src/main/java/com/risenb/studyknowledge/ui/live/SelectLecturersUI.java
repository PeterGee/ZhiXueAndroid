package com.risenb.studyknowledge.ui.live;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.live.SelectLecturersAdapter;
import com.risenb.studyknowledge.beans.live.TeacherListBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.utils.InputMethodUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayout;
import com.risenb.studyknowledge.views.refreshlayout.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzh on 2017/9/28.
 *
 * 选择讲师
 */
@ContentView(R.layout.select_lecturer_ui)
public class SelectLecturersUI extends BaseUI implements MyRefreshLayoutListener, BaseQuickAdapter.OnItemClickListener, SelectLecturersP.SelectLecturersFace {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.mrl_lecturer_list)
    private MyRefreshLayout mrl_lecturer_list;
    @ViewInject(R.id.rv_lecturer_list )
    private RecyclerView rv_lecturer_list;
    @ViewInject(R.id.et_search)
    private EditText et_search;
    public static final int REQUEST_CODE = 91;
    public static final String TEACHER_INFO="teacher_info";
    private SelectLecturersAdapter mAdapter;
    private List<TeacherListBean> listData = new ArrayList<>();
    private int PAGE=1;
    private String LIMIT = "999";
    private String TIMESTAMP="";
    private String C="702";
    private String CollegeId="58";
    private SelectLecturersP selectLecturersP;
    private String key ="";


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
        Intent starter = new Intent(context, SelectLecturersUI.class);
        ((Activity)context).startActivityForResult(starter, REQUEST_CODE);
        ((Activity)context).overridePendingTransition(R.anim.activity_bottom_in, R.anim.alpha);
    }

    @Override
    protected void setControlBasis() {
        StatusBarUtils.transparencyBar(this);
        setTitle(getResources().getString(R.string.select_lecturer));
        iv_back.setImageResource(R.mipmap.down_arrow_big_white);

        mAdapter = new SelectLecturersAdapter(R.layout.select_lecturer_item, listData);
        rv_lecturer_list.setAdapter(mAdapter);
        rv_lecturer_list.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(this);
//        //添加分隔线
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, R.drawable.divider_activity_line, LinearLayoutManager.VERTICAL);
//        rv_lecturer_list.addItemDecoration(itemDecoration);
        mrl_lecturer_list.setMyRefreshLayoutListener(this);//刷新加载

        //搜索
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    InputMethodUtils.hideInputMethod(v);
                    key = et_search.getText().toString().trim();
                    PAGE = 1;
                    selectLecturersP.getTeacherList(selectLecturersP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT,key);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        selectLecturersP = new SelectLecturersP(this, getActivity());
        selectLecturersP.getTeacherList(selectLecturersP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT,"");
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @Override
    public void onRefresh(View view) {
        PAGE = 1;
        selectLecturersP.getTeacherList(selectLecturersP.REFRESH,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT,"");

    }

    @Override
    public void onLoadMore(View view) {
        PAGE++;
        selectLecturersP.getTeacherList(selectLecturersP.LOAD,C,CollegeId,TIMESTAMP,PAGE+"",LIMIT,"");

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra(TEACHER_INFO, listData.get(position));
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(R.anim.alpha, R.anim.activity_bottom_out);
    }

    @Override
    public void teacherListSuccess(String tag, String time, List<TeacherListBean> list) {
        Utils.getUtils().dismissDialog();
        if(TextUtils.equals(tag,selectLecturersP.REFRESH)){
            mrl_lecturer_list.refreshComplete();
        }else if(TextUtils.equals(tag,selectLecturersP.LOAD)){
            mrl_lecturer_list.loadMoreComplete();
        }
        TIMESTAMP=time;
        listData=list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void teacherListFail() {
        Utils.getUtils().dismissDialog();
        mrl_lecturer_list.refreshComplete();
        mrl_lecturer_list.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }
}
