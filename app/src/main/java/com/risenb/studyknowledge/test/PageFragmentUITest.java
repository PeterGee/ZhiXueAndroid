package com.risenb.studyknowledge.test;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.lidroid.mutils.fragpage.BasePageFragment;
import com.lidroid.mutils.fragpage.BasePageUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;

@ContentView(R.layout.page_fragment)
public class PageFragmentUITest extends BaseUI
{
    /** hsv */
    @ViewInject(R.id.hsv_page_fragment)
    private HorizontalScrollView hsv_page_fragment;
    /** tab */
    @ViewInject(R.id.rg_page_fragment)
    private RadioGroup rg_page_fragment;
    /** 游标 */
    @ViewInject(R.id.v_page_fragment)
    private View v_page_fragment;
    /** 显示的内容 */
    @ViewInject(R.id.vp_page_fragment)
    private ViewPager vp_page_fragment;

    @Override
    protected void back()
    {
        finish();
    }

    @Override
    protected void setControlBasis()
    {
        setTitle("");
    }

    @Override
    protected void prepareData()
    {
        List<PageFragmentBean> list = new ArrayList<PageFragmentBean>();

        for (int i = 0; i < list.size(); i++)
        {
            list.get(i).setFragment(new PageFragment(list.get(i), i));
        }

        BasePageUtils<PageFragmentBean> basePageUtils = new BasePageUtils<PageFragmentBean>();
        basePageUtils.setActivity(getActivity());
        basePageUtils.setNumColumns(5);
        basePageUtils.setMargin(0);
        basePageUtils.setHorizontalScrollView(hsv_page_fragment);
        basePageUtils.setRadioGroup(rg_page_fragment);
        basePageUtils.setCursor(v_page_fragment);
        basePageUtils.setViewPager(vp_page_fragment);
        basePageUtils.setList(list);
        basePageUtils.setRadioButton00(R.id.radio_button00);
        basePageUtils.setRadioLayoutID(R.layout.rb_page_fragment);
        basePageUtils.info();
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    @SuppressLint("ValidFragment")
    public class PageFragment extends BasePageFragment<PageFragmentBean>
    {
        @ViewInject(R.id.back)
        private ImageView back;

        public PageFragment(PageFragmentBean baseMenuBean, int position)
        {
            super(baseMenuBean, position);
        }

        @Override
        protected void loadViewLayout(LayoutInflater inflater, ViewGroup container)
        {
            view = inflater.inflate(R.layout.keyboard, null);
        }

        @Override
        protected void setControlBasis()
        {

        }

        @Override
        protected void prepareData()
        {

        }

    }
}
