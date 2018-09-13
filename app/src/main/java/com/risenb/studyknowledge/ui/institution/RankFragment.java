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
 * Created by yy on 2017/9/21.
 * 举报管理排序弹窗
 */

public class RankFragment extends BaseFragment implements  BaseQuickAdapter.OnItemChildClickListener {
    @ViewInject(R.id.rv_rank_list)
    RecyclerView rv_rank_list;//排序列表
    private List<RankBean> list=new ArrayList<>();
    private RankAdapter mRankAdapter;


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_report_rank, container, false);
        //获得点击事件
        view.setClickable(true);

    }

    @Override
    public void setControlBasis() {

        String[] ranks = getResources().getStringArray(R.array.rank);
        for (int i = 0; i < ranks.length; i++) {
            RankBean bean = new RankBean();
            bean.setRank(ranks[i]);
            list.add(bean);
        }
        list.get(0).setChecked(true);


        //首选项中保存的选中rank
        String rank = SPUtils.getString(getContext(), ReportManageUI.RANK);
        if(!TextUtils.isEmpty(rank)){
            for (int i = 0; i < list.size(); i++) {
                if(TextUtils.equals(list.get(i).getRank(),rank)){
                    list.get(i).setChecked(true);
                }else {
                    list.get(i).setChecked(false);
                }
            }
        }

        mRankAdapter = new RankAdapter(R.layout.rank_item, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_rank_list.setAdapter(mRankAdapter);
        rv_rank_list.setLayoutManager(linearLayoutManager);
        mRankAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void prepareData() {

    }
    @OnClick({R.id.rl_rank_bg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_rank_bg://等级列表下半部分白色透明背景
                //关闭当前的排序列表
                mOnRankListener.closeRankListener(view,getCheckedLevel());
                break;
            default:
                break;
        }
    }
    /**
     * 条目点击事件
     * @param adapter
     * @param view
     * @param position
     */
//    @Override
//    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        boolean checked = list.get(position).isChecked();
//        checked=!checked;
//        //如果点击事件是选中等级，将所有等级设为未选中
//        if(checked){
//            for (int i = 0; i < list.size(); i++) {
//                list.get(i).setChecked(!checked);
//            }
//            list.get(position).setChecked(checked);
//        }
//        mRankAdapter.notifyDataSetChanged();
//        mOnRankListener.closeRankListener(view,list.get(position).getRank());
//    }

    /**
     * 获取选择的排序类型
     * @return
     */
    public int getCheckedLevel(){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).isChecked()){
                return i;
            }
        }
        return 0;
    }


    /**
     * 关闭会员等级列表弹窗的接口回调
     */
    private OnRankListener mOnRankListener;

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
        mOnRankListener.closeRankListener(view,position);
    }

    public interface OnRankListener{
        void closeRankListener(View view, int rankPosition);
    }
    public OnRankListener getOnRankListener() {
        return mOnRankListener;
    }

    public void setOnRankListener(OnRankListener onRankListener) {
        mOnRankListener = onRankListener;
    }

}
