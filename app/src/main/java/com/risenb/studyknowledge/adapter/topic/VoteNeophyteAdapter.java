package com.risenb.studyknowledge.adapter.topic;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.VoteDetailListBean;
import com.risenb.studyknowledge.beans.topic.VoteNeophyteBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzh on 2017/9/30.
 */

public class VoteNeophyteAdapter extends BaseQuickAdapter<VoteDetailListBean, BaseViewHolder> {

//    private List<VoteDetailListBean.VoteOptionBean> list = new ArrayList<>();

    public VoteNeophyteAdapter(@LayoutRes int layoutResId, @Nullable List<VoteDetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VoteDetailListBean item) {

        helper.setText(R.id.tv_vote_name, item.getVoteName());
        helper.setText(R.id.tv_voter_name, item.getUserName());
        helper.setText(R.id.tv_phone_num,"联系方式："+item.getUserPhone());
        helper.setText(R.id.tv_email, "邮箱："+ item.getUserEmail());
        helper.setText(R.id.tv_join_time, item.getVoteCreattime());


//        for (int i = 0; i < 3; i++) {
//            VoteNeophyteBean.VoteOptionBean beanTwo = new VoteNeophyteBean.VoteOptionBean();
//            beanTwo.setContent("项目管理投票选项"+i);
//            list.add(beanTwo);
//        }

//        RecyclerView rv_vote_option = helper.getView(R.id.rv_vote_option);
//        VoteNeophyteOptionAdapter mAdapter = new VoteNeophyteOptionAdapter(R.layout.vote_neophyte_option_item, list);
////        mAdapter.setData(item.getVoteOptionBean());
//        rv_vote_option.setAdapter(mAdapter);
//        rv_vote_option.setLayoutManager(new LinearLayoutManager(mContext));
//        rv_vote_option.setNestedScrollingEnabled(false);

    }
}
