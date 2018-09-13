//package com.risenb.studyknowledge.adapter.topic;
//
//import android.support.annotation.LayoutRes;
//import android.support.annotation.Nullable;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.risenb.studyknowledge.R;
//import com.risenb.studyknowledge.beans.topic.VoteNeophyteBean;
//import com.risenb.studyknowledge.beans.topic.VoteNeophyteOptionBean;
//
//import java.util.List;
//
//import static android.R.id.list;
//
///**
// * Created by zhuzh on 2017/9/30.
// */
//
//public class VoteNeophyteOptionAdapter extends BaseQuickAdapter<VoteNeophyteBean.VoteOptionBean, BaseViewHolder> {
//
//
//    public VoteNeophyteOptionAdapter(@LayoutRes int layoutResId, @Nullable List<VoteNeophyteBean.VoteOptionBean> data) {
//        super(layoutResId, data);
//    }
//
//    public void setData(List<VoteNeophyteBean.VoteOptionBean> data) {
//        this.data = data;
//    }
//
//    private List<VoteNeophyteBean.VoteOptionBean> data;
//
//
//    @Override
//    protected void convert(BaseViewHolder helper, VoteNeophyteBean.VoteOptionBean item) {
//
//        helper.setText(R.id.tv_content, item.getContent());
//    }
//}
