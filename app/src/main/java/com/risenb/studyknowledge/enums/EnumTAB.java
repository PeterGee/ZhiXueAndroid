package com.risenb.studyknowledge.enums;

import android.support.v4.app.Fragment;
import android.widget.RadioButton;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.college.HomeFragment;
import com.risenb.studyknowledge.ui.live.LiveFragment;
import com.risenb.studyknowledge.ui.post.PostsFragment;
import com.risenb.studyknowledge.ui.topic.TopicsFragment;
import com.risenb.studyknowledge.ui.personal.PersonalManagerFragment;

public enum EnumTAB {
    TAB1(R.id.rb_tab_1, R.drawable.tab_1, "学院", new HomeFragment()), //
    TAB2(R.id.rb_tab_2, R.drawable.tab_2, "帖子", new PostsFragment()), //
    TAB3(R.id.rb_tab_3, R.drawable.tab_3, "直播预告", new LiveFragment()), //
    TAB4(R.id.rb_tab_4, R.drawable.tab_4, "话题管理", new TopicsFragment()), //
    TAB5(R.id.rb_tab_5, R.drawable.tab_5, "人员管理", new PersonalManagerFragment()); //

    private int id;
    private int drawable;
    private String title;
    private BaseFragment fragment;
    private RadioButton radioButton;

    EnumTAB(int id, int drawable, String title, BaseFragment fragment) {
        this.id = id;
        this.drawable = drawable;
        this.title = title;
        this.fragment = fragment;
    }

    public int getId() {
        return id;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getTitle() {
        return title;
    }

    public BaseFragment getFragment() {
        return fragment;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

}
