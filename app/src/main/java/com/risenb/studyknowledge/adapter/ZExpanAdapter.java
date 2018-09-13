package com.risenb.studyknowledge.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.lidroid.mutils.adapter.BaseViewHolder;
import com.risenb.studyknowledge.R;

public class ZExpanAdapter<T extends Object, E extends Object> extends BaseExpandableListAdapter {
    private List<T> list;

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return 5;
        // return list != null ? list.size() : 0;
    }

    @Override
    public T getGroup(int groupPosition) {
        return list != null ? list.get(groupPosition) : null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 5;
        // return list.get(groupPosition) != null ? list.get(groupPosition).getContentsBean().size() : 0;
    }

    @Override
    public E getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GViewHolder vh;
        if (convertView == null) {
            vh = new GViewHolder(parent.getContext(), R.layout.include_title);
        } else {
            vh = (GViewHolder) convertView.getTag();
        }

        vh.prepareData(getGroup(groupPosition), groupPosition);

        return vh.getView();
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CViewHolder vh;
        if (convertView == null) {
            vh = new CViewHolder(parent.getContext(), R.layout.include_title);
        } else {
            vh = (CViewHolder) convertView.getTag();
        }

        vh.prepareData(getChild(groupPosition, childPosition), groupPosition, childPosition);

        return vh.getView();
    }

    private class GViewHolder extends BaseViewHolder<T> {
        public GViewHolder(Context context, int layoutID) {
            super(context, layoutID);
        }

        @Override
        protected void prepareData() {

        }
    }

    private class CViewHolder extends BaseViewHolder<E> {
        // @ViewInject(R.id.title)
        // private TextView title;
        //
        // @ViewInject(R.id.back)
        // private ImageView back;

        public CViewHolder(Context context, int layoutID) {
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
