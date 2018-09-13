package com.risenb.studyknowledge.adapter.institution;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.institution.NoticeListBean;

import java.util.List;

/**
 * Created by yy on 2017/9/20.
 * 公告列表
 */

public class AnnounceAdapter extends BaseQuickAdapter<NoticeListBean,BaseViewHolder>{
    public AnnounceAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public AnnounceAdapter(@LayoutRes int layoutResId, @Nullable List<NoticeListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeListBean item) {
        helper.setText(R.id.tv_announce_title,item.getNoticeTitle());//公告标题
        helper.setText(R.id.tv_announce_time,item.getNoticeCreationtime());//公告创建时间
        helper.addOnClickListener(R.id.tv_delete);//删除
        helper.addOnClickListener(R.id.content);
    }
}
