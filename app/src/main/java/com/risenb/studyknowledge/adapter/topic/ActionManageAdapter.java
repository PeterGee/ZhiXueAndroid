package com.risenb.studyknowledge.adapter.topic;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.MemberApplyAdapter;
import com.risenb.studyknowledge.beans.personal.MemberApplyBean;
import com.risenb.studyknowledge.beans.topic.ActionManageBean;
import com.risenb.studyknowledge.beans.topic.ActivityListBean;
import com.risenb.studyknowledge.ui.topic.ActionNeophyteUI;

import java.util.List;

import glideimageview.GlideImageLoader;

import static com.risenb.studyknowledge.R.id.tab_college;
import static com.risenb.studyknowledge.R.id.view;

/**
 * Created by zhuzh on 2017/9/20.
 */

public class ActionManageAdapter extends BaseItemDraggableAdapter<ActivityListBean,BaseViewHolder> {

    public ActionManageAdapter(int layoutResId, List<ActivityListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ActivityListBean item) {
        helper.setText(R.id.tv_action_name,item.getActivityName());
        helper.setText(R.id.tv_topic_name,"话题："+item.getTopicName());
        helper.setText(R.id.tv_start_time, item.getStartTime());
        helper.setText(R.id.tv_end_time, item.getEndTime());
        helper.setText(R.id.tv_action_num, item.getPostJoinNum()+"");
        ImageView iv_action_img = helper.getView(R.id.iv_action_img);
        GlideImageLoader.create(iv_action_img).loadImage(item.getPostPicture(),R.mipmap.unify_image_ing);


        helper.addOnClickListener(R.id.tv_menu_one).addOnClickListener(R.id.tv_menu_two);

        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onItemListener.itemListener(view, helper.getLayoutPosition());
//                Toast.makeText(mContext, item.getActionName(), Toast.LENGTH_SHORT).show();
                ActionNeophyteUI.start(mContext, item.getActivityId()+"");


            }
        });

        //关闭复用
//        helper.setIsRecyclable(false);
    }

    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public interface OnItemListener{
        void itemListener(View view, int position);
    }

}
