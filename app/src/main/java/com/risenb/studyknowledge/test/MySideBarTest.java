package com.risenb.studyknowledge.test;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;

import com.lidroid.mutils.sort.MySideBar;
import com.lidroid.mutils.sort.MySideBar.OnTouchingLetterChangedListener;
import com.lidroid.mutils.sort.PopSideBar;
import com.lidroid.mutils.sort.SortUtils;
import com.risenb.studyknowledge.ui.BaseUI;

/**
 * @author 作者: wangjian
 * @version 创建时间：2015年7月14日 下午6:54:45
 * @类说明
 */
public class MySideBarTest extends BaseUI {
    private MySideBar msb_ss;
    private ListView lv;

    private PopSideBar popSideBar;
    private int[] msb;

    @Override
    protected void back() {

    }

    @Override
    protected void setControlBasis() {
    }

    @Override
    protected void prepareData() {
        List<CLbean> list = new ArrayList<CLbean>();
        msb = SortUtils.getSrtUtils().sort(list);

        msb_ss.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterUP() {
                popSideBar.dismiss();
            }

            @Override
            public void onTouchingLetterChanged(int idx) {
                try {
                    lv.setSelection(msb[idx]);
                } catch (Exception e) {
                }
            }

            @Override
            public void onTouchingLetterChanged(String s) {
                if (popSideBar == null) {
                    popSideBar = new PopSideBar(getActivity());
                }
                popSideBar.showAsDropDown();
                popSideBar.setText(s);
            }
        });
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    class CLbean extends com.lidroid.mutils.sort.BaseSortBean {
        @Override
        public String getBaseSortBeanTag() {
            return null;
        }

        // if (position == 0 || getItem(position - 1).getBaseSortBeanID() != bean.getBaseSortBeanID())
        // {
        // tv_city_item.setVisibility(View.VISIBLE);
        // }
        // else
        // {
        // tv_city_item.setVisibility(View.GONE);
        // }
    }

}
