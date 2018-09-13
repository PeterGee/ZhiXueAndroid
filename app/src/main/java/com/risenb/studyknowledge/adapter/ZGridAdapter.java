package com.risenb.studyknowledge.adapter;

import android.content.Context;

import com.lidroid.mutils.adapter.BaseGridAdapter;
import com.lidroid.mutils.adapter.BaseViewHolder;
import com.risenb.studyknowledge.R;

public class ZGridAdapter<T extends Object> extends BaseGridAdapter<T> {
    public ZGridAdapter(int numColumns) {
        super(numColumns);
    }

    @Override
    public int getViewCount() {
        return 12;
    }

    @Override
    protected BaseViewHolder<T> loadView(Context context) {
        return new ViewHolder(context, R.layout.keyboard);
    }

    private class ViewHolder extends BaseViewHolder<T> {
        // @ViewInject(R.id.title)
        // private TextView title;
        //
        // @ViewInject(R.id.back)
        // private ImageView back;

        public ViewHolder(Context context, int layoutID) {
            super(context, layoutID);
        }

        @Override
        protected void prepareData() {
            // ViewDataUtils.inject(bean, convertView, bitmapUtils);
            // title.setText("");
            // bitmapUtils.display(back, "");
        }
    }
}
