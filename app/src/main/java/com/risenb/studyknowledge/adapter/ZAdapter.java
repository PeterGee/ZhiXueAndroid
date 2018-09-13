package com.risenb.studyknowledge.adapter;

import android.content.Context;

import com.lidroid.mutils.adapter.BaseListAdapter;
import com.lidroid.mutils.adapter.BaseViewHolder;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.ViewDataBean;

public class ZAdapter<T extends ViewDataBean> extends BaseListAdapter<T> {
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    protected BaseViewHolder<T> loadView(Context context, T bean, int position) {
        return new ViewHolder(context, getViewId(bean, position));
    }

    @Override
    protected int getViewId(T bean, int position) {
        return R.layout.include_title;
    }

    private class ViewHolder extends BaseViewHolder<T> {

//        @ViewInject(R.id.title)
//        private TextView title;
//
//        @ViewInject(R.id.back)
//        private ImageView back;

        public ViewHolder(Context context, int layoutID) {
            super(context, layoutID);
        }

        @Override
        protected void prepareData() {
//            ViewDataUtils.inject(bean, convertView);
//            title.setText("");
//            Glide.with(convertView.getContext()).load("").into(back);
//            Glide.with(convertView.getContext()).load("")
//                    .placeholder(R.drawable.task_expert_ioc)//加载中
//                    .error(R.drawable.task_expert_ioc)//加载失败
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .thumbnail(0.1f)//加载缩略图
//                    .into(iv_task_expert_item);

        }
    }

}
