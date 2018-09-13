package com.risenb.studyknowledge.ui.institution;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.CustomListener;
import com.lidroid.mutils.screen.ScreenUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.views.lib.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yy on 2017/9/28.
 * 查询时间弹窗
 */

public class InquireTimeFragment extends BaseFragment {
    @ViewInject(R.id.tv_start_time)
    TextView tv_start_time;
    @ViewInject(R.id.tv_end_time)
    TextView tv_end_time;

    private TimePickerView pvCustomTime;
    public String mStartTime;
    public String mEndTime;


    /**
     * 查询后重置时间
     * @param startTime
     * @param endTime
     */
    public void initStartEndTime(String startTime, String endTime) {
        mStartTime = startTime;
        mEndTime = endTime;
    }

    public InquireTimeFragment() {
    }

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_inquire_time, container, false);
    }

    @Override
    public void setControlBasis() {
        initCustomTimePicker();

    }

    @Override
    public void prepareData() {

    }

    @OnClick({R.id.rl_inquire_bg, R.id.tv_back, R.id.tv_commit, R.id.tv_start_time, R.id.tv_end_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_inquire_bg://查询时间背景
                mOnInquireTimeListener.closeInquireTimeListener(mStartTime, mEndTime);
                break;
            case R.id.tv_commit://提交
                mOnInquireTimeListener.closeInquireTimeListener(mStartTime, mEndTime);
                break;
            case R.id.tv_back://返回
                mOnInquireTimeListener.closeInquireTimeListener(mStartTime, mEndTime);
                break;
            case R.id.tv_start_time://查询起始时间
                pvCustomTime.show(tv_start_time);
                break;
            case R.id.tv_end_time://查询结束时间
                pvCustomTime.show(tv_end_time);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化时间选择
     */
    private void initCustomTimePicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2000, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(3000, 12, 31);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (v == tv_start_time) {
                    mStartTime = getTime(date);
                    tv_start_time.setText(mStartTime);
                } else if (v == tv_end_time) {
                    mEndTime = getTime(date);
                    tv_end_time.setText(mEndTime);
                }

            }
        })
                .setDate(selectedDate)
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
                        View view_bg = v.findViewById(R.id.view_bg);
                        view_bg.setBackgroundColor(getResources().getColor(R.color.translete));
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(getResources().getColor(R.color.gray))
                .setTextColorCenter(getResources().getColor(R.color.gray_3))
                .gravity(Gravity.CENTER)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    /**
     * 关闭查询时间弹窗的接口回调
     */
    private OnInquireTimeListener mOnInquireTimeListener;

    public interface OnInquireTimeListener {
        void closeInquireTimeListener(String startTime, String endTime);
    }

    public OnInquireTimeListener getOnInquireTimeListener() {
        return mOnInquireTimeListener;
    }

    public void setOnInquireTimeListener(OnInquireTimeListener onInquireTimeListener) {
        mOnInquireTimeListener = onInquireTimeListener;
    }
}
