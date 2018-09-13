package com.risenb.studyknowledge.test;

import com.lidroid.mutils.xlist.XListView;
import com.lidroid.mutils.xlist.XListView.IXListViewListener;
import com.lidroid.xutils.view.annotation.ContentView;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;

@ContentView(R.layout.keyboard)
public class XListTest extends BaseUI implements IXListViewListener {

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("");
    }

    @Override
    protected void prepareData() {
        final XListView mlv = new XListView(getActivity());
        mlv.setXListViewListener(this);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @Override
    public void onLoad(int page) {

    }

}
