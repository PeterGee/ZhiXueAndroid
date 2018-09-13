package com.risenb.studyknowledge.adapter.topic;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.topic.ActionManageBean;
import com.risenb.studyknowledge.beans.topic.VoteListBean;
import com.risenb.studyknowledge.beans.topic.VoteManageBean;
import com.risenb.studyknowledge.ui.topic.ActionNeophyteUI;
import com.risenb.studyknowledge.ui.topic.VoteNeophyteUI;

import java.util.List;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/9/21.
 */
public class VoteManageAdapter extends BaseItemDraggableAdapter<VoteListBean,BaseViewHolder> {

    public VoteManageAdapter(int layoutResId, List<VoteListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final VoteListBean item) {

        helper.setText(R.id.tv_vote_name, item.getVoteName());
        helper.setText(R.id.tv_topic_name,"话题："+item.getTopicName());
        int voteIsTop = item.getVoteIsTop();
        if(voteIsTop==0){
            helper.setText(R.id.tv_is_top,"是否置顶：不");
        }else if(voteIsTop==1) {
            helper.setText(R.id.tv_is_top, "是否置顶：是");
        }
        helper.setText(R.id.tv_put_name,"发起人："+ item.getUserName());
        helper.setText(R.id.tv_start_time, item.getStartTime());
        helper.setText(R.id.tv_end_time, item.getEndTime());
        helper.setText(R.id.tv_vote_num, item.getVoteJoinNum()+"");
        ImageView iv_vote_img  = helper.getView(R.id.iv_vote_img);
        GlideImageLoader.create(iv_vote_img).loadImage(item.getVoteImg(),R.mipmap.unify_image_ing);



        helper.addOnClickListener(R.id.tv_menu_one).addOnClickListener(R.id.tv_menu_two);

        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onItemListener.itemListener(view, helper.getLayoutPosition());
                VoteNeophyteUI.start(mContext,item.getVoteId()+"");


            }
        });
    }

}
