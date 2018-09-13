package com.risenb.studyknowledge.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.CustomListener;
import com.lidroid.mutils.screen.ScreenUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.SignDataAdapter;
import com.risenb.studyknowledge.adapter.personal.SignXAdapter;
import com.risenb.studyknowledge.adapter.personal.SignYAdapter;
import com.risenb.studyknowledge.beans.personal.SignInManagerBean;
import com.risenb.studyknowledge.beans.personal.SignInterceptBean;
import com.risenb.studyknowledge.ui.BaseUI;
import com.risenb.studyknowledge.ui.info.PersonalInfoUI;
import com.risenb.studyknowledge.utils.CommonUtils;
import com.risenb.studyknowledge.utils.StatusBarUtils;
import com.risenb.studyknowledge.views.lib.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yy on 2017/10/27.
 * 签到管理
 */
@ContentView(R.layout.ui_sign_in_manage)
public class SignInManageUI extends BaseUI implements SignInManagerP.SignInManagerFace {
    @ViewInject(R.id.rv_y_intercept)
    RecyclerView rv_y_intercept;
    @ViewInject(R.id.rv_x_intercept)
    RecyclerView rv_x_intercept;
    @ViewInject(R.id.rv_data)
    RecyclerView rv_data;
    @ViewInject(R.id.tv_show_date)
    TextView tv_show_date;

    private List<SignInterceptBean> list_y=new ArrayList<>();
    private List<SignInterceptBean> list_x;
    private SignYAdapter mYAdapter;
    private SignXAdapter mXAdapter;
    private SignDataAdapter mDataAdapter;
    private TimePickerView pvCustomTime;
    private Calendar mSelectedDate;
    private SignInManagerP mSignInManagerP;
    private String C="1643";
    private String CollegeId="45";
    private String searchMonth;
    private List<SignInManagerBean.DataBean.TotalNumBean> mTotalNumList;
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
        setTitle(getResources().getString(R.string.sign_in_manager));//签到管理
        //系统当前时间
        mSelectedDate = Calendar.getInstance();
        searchMonth=CommonUtils.getTime(mSelectedDate.getTime());
        initCustomTimePicker();

        //初始化纵坐标y
        initY();
        //初始化横坐标X
        initX(CommonUtils.getDaysOfMonth(mSelectedDate.getTime()));
        //初始化柱状图数据
        initData();
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, SignInManageUI.class);
        context.startActivity(starter);
    }
    @Override
    protected void prepareData() {
        mSignInManagerP = new SignInManagerP(this, getActivity());
        mSignInManagerP.getSignList(C,CollegeId,searchMonth);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    /**
     * 初始化纵坐标
     */
    public void initY(){
        String[] y_intercept =new String[]{"500","400","300","200","100","Zero"};
        for (int i = 0; i < y_intercept.length; i++) {
            SignInterceptBean bean = new SignInterceptBean();
            bean.setIntercept(y_intercept[i]);
            list_y.add(bean);
        }
        mYAdapter = new SignYAdapter(R.layout.sign_y_item, list_y);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_y_intercept.setAdapter(mYAdapter);
        rv_y_intercept.setLayoutManager(linearLayoutManager);
    }

    /**
     * 初始化横坐标X
     * @param dayOfMonth  当前月份的总天数
     */
    public void initX(int dayOfMonth){
        list_x=new ArrayList<>();
        for (int i = 1; i < dayOfMonth+1; i++) {
            SignInterceptBean bean = new SignInterceptBean();
            if(i==1||i==5||i==15||i==25||i==dayOfMonth||i==10||i==20){
                bean.setIntercept(i+"");
            }else {
                bean.setIntercept("");
            }
            list_x.add(bean);
        }
        mXAdapter = new SignXAdapter(R.layout.sign_x_item, list_x);
        LinearLayoutManager linearLayoutManagerX = new LinearLayoutManager(this);
        linearLayoutManagerX.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_x_intercept.setAdapter(mXAdapter);
        rv_x_intercept.setLayoutManager(linearLayoutManagerX);
    }

    /**
     * 初始化柱状图数据
     */
    public void initData(){
        mDataAdapter = new SignDataAdapter(R.layout.sign_data_item);
        LinearLayoutManager linearLayoutManagerData = new LinearLayoutManager(this);
        linearLayoutManagerData.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_data.setAdapter(mDataAdapter);
        rv_data.setLayoutManager(linearLayoutManagerData);
    }

    @OnClick({R.id.tv_change_date})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_change_date://更换日期
                pvCustomTime.show(false);
                break;
            default:
                break;

        }
    }
    /**
     * 初始化时间选择
     */
    private void initCustomTimePicker() {
        Calendar now = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(now.get(Calendar.YEAR)-100,1,1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(now.get(Calendar.YEAR)+20,12,31);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                searchMonth=CommonUtils.getTime(date);
                int dayOfMonth = CommonUtils.getDaysOfMonth(date);
                initX(dayOfMonth);
                mSignInManagerP.getSignList(C,CollegeId,searchMonth);
            }
        })
                .setDate(mSelectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_sign_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                        TextView v_bottom = (TextView) v.findViewById(R.id.v_bottom);
                        if (v_bottom != null) {
                            ViewGroup.MarginLayoutParams bottomParams = (ViewGroup.MarginLayoutParams) v_bottom.getLayoutParams();
                            bottomParams.height = ScreenUtils.getScreenUtils().getBottomStatusHeight(getActivity());
                            v_bottom.setLayoutParams(bottomParams);
                        }
                    }
                })
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("","","","","","")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(getResources().getColor(R.color.gray))
                .setTextColorCenter(getResources().getColor(R.color.gray_3))
                .gravity(Gravity.CENTER)
                .build();

    }

    /**
     * 获取签到管理成功的回调
     * @param month
     * @param result
     */
    @Override
    public void getSignInSuccess(String month, List<SignInManagerBean.DataBean.TotalNumBean> result) {
        tv_show_date.setText(month);
        this.mTotalNumList=result;
        mDataAdapter.setNewData(mTotalNumList);
        mDataAdapter.notifyDataSetChanged();
    }
}
