package com.risenb.studyknowledge.adapter.personal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.risenb.studyknowledge.enums.EnumTAB;
import com.risenb.studyknowledge.ui.personal.PaidQuestionFragment;
import com.risenb.studyknowledge.ui.personal.TalkAboutFragment;

import java.util.List;

public class TopicPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fm;
    private List<String> list_Title;
    public TopicPageAdapter(FragmentManager fm,List<Fragment> list_fragment,List<String> list_Title) {
        super(fm);
        this.list_fm=list_fragment;
        this.list_Title=list_Title;
    }

    @Override
    public int getCount() {
        return list_fm.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list_fm.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position % list_Title.size());
    }
}
