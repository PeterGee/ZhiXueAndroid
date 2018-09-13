package com.risenb.studyknowledge.ui.college;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    private String[] titles = null;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> pages, String[] titles) {

        super(fm);

        this.list = pages;
        this.titles = titles;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }


    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


}
