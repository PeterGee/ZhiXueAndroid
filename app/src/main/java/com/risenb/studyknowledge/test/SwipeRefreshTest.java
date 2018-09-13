package com.risenb.studyknowledge.test;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.lidroid.mutils.MUtils;
import com.lidroid.mutils.utils.Log;
import com.lidroid.mutils.utils.UIManager;
import com.lidroid.mutils.xlist.ListViewInit;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;

@ContentView(R.layout.swipe_refresh)
public class SwipeRefreshTest extends BaseUI {
    @ViewInject(R.id.srl_swipe_refresh)
    private SwipeRefreshLayout srl_swipe_refresh;

    @ViewInject(R.id.lv_swipe_refresh)
    private ListView lv_swipe_refresh;

    @Override
    protected void back() {
        UIManager.getInstance().popActivity(getClass());
    }

    @Override
    protected void setControlBasis() {
        setTitle("");
    }

    @Override
    protected void prepareData() {
        // Intent intent = new Intent(getActivity(), ZUI.class);
        // startActivity(intent);

        final ListViewInit listViewInit = new ListViewInit();
        listViewInit.init(srl_swipe_refresh, lv_swipe_refresh);
        listViewInit.setOnLoadListener(new ListViewInit.OnLoadListener() {
            @Override
            public void onLoad(int page) {
                Log.e("page = " + page);
                MUtils.getMUtils().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listViewInit.setRefreshing();
                    }
                }, 3000);
            }
        });

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    /**
     *
     */
    @OnClick(R.id.back)
    private void test(View v) {

    }
}
