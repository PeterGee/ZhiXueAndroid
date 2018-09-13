package com.risenb.studyknowledge.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.risenb.studyknowledge.enums.EnumTAB;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return EnumTAB.values().length;
    }

    @Override
    public Fragment getItem(int position) {
        return EnumTAB.values()[position].getFragment();
    }

}
