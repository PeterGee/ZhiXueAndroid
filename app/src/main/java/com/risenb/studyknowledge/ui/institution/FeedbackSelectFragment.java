package com.risenb.studyknowledge.ui.institution;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.RankAdapter;
import com.risenb.studyknowledge.beans.institution.RankBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/28.
 * 意见反馈选择弹窗
 */

public class FeedbackSelectFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener {
    @ViewInject(R.id.rv_select)
    RecyclerView rv_select;//筛选列表
    private List<RankBean> list=new ArrayList<>();
    private RankAdapter mRankAdapter;
    public static final String READ_TRUE="1";//已读
    public static final String READ_FALSE="0";//未读
    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_feedback_select, container, false);
    }

    @Override
    public void setControlBasis() {
        String[] ranks = getResources().getStringArray(R.array.select);
        for (int i = 0; i < ranks.length; i++) {
            RankBean bean = new RankBean();
            bean.setRank(ranks[i]);
            list.add(bean);
        }
        list.get(0).setChecked(true);

        //首选项中保存的选中rank
        String select = SPUtils.getString(getContext(), FeedbackListUI.SELECT);
        if(!TextUtils.isEmpty(select)){
            for (int i = 0; i < list.size(); i++) {
                if(TextUtils.equals(list.get(i).getRank(),select)){
                    list.get(i).setChecked(true);
                }else {
                    list.get(i).setChecked(false);
                }
            }
        }

        mRankAdapter = new RankAdapter(R.layout.rank_item, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_select.setAdapter(mRankAdapter);
        rv_select.setLayoutManager(linearLayoutManager);
        mRankAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void prepareData() {

    }
    @OnClick({R.id.rl_rank_bg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_rank_bg://背景
                mOnFeedbackSelectListener.closeSelectListener(view,getChecked(),getKey());
                break;
            default:
                break;
        }
    }

    /**
     * 获取选择的类型
     * @return
     */
    public String getChecked(){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).isChecked()){
                return list.get(i).getRank();
            }
        }
        return list.get(0).getRank();
    }

    /**
     * 获取选择类型的查询key值
     * * @param key           key=0 未读  key=1已读(为空时则展示全部列表)
     * @return
     */
    public String getKey(){
        if(list.get(1).isChecked()){//已读
            return READ_TRUE;
        }else if(list.get(2).isChecked()){//未读
            return READ_FALSE;
        }else {
            return "";
        }
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        boolean checked = list.get(position).isChecked();
        checked=!checked;
        //如果点击事件是选中等级，将所有等级设为未选中
        if(checked){
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(!checked);
            }
            list.get(position).setChecked(checked);
        }
        mRankAdapter.notifyDataSetChanged();
        mOnFeedbackSelectListener.closeSelectListener(view,list.get(position).getRank(),getKey());
    }
    /**
     * 关闭查询时间弹窗的接口回调
     */
    private OnFeedbackSelectListener mOnFeedbackSelectListener;



    public interface OnFeedbackSelectListener{
        void closeSelectListener(View view, String selet,String key);
    }
    public OnFeedbackSelectListener getOnFeedbackSelectListener() {
        return mOnFeedbackSelectListener;
    }

    public void setOnFeedbackSelectListener(OnFeedbackSelectListener onFeedbackSelectListener) {
        mOnFeedbackSelectListener = onFeedbackSelectListener;
    }
}
