package com.risenb.studyknowledge.adapter.post;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.posts.AddVoteBean;

import java.util.List;

/**
 * Created by zhuzh on 2017/9/30.
 */

public class AddVoteAdapter extends BaseQuickAdapter<AddVoteBean, BaseViewHolder> {

    public AddVoteAdapter(@LayoutRes int layoutResId, @Nullable List<AddVoteBean> data) {
        super(layoutResId, data);
    }

    public void setList(List<AddVoteBean> list) {
        this.list = list;
    }

    private List<AddVoteBean> list;



    @Override
    protected void convert(final BaseViewHolder helper, final AddVoteBean item) {

        helper.setText(R.id.tv_vote_content, item.getContent());

        helper.getView(R.id.tv_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
                list.remove(helper.getLayoutPosition());
                notifyDataSetChanged();
            }
        });

    }
}
