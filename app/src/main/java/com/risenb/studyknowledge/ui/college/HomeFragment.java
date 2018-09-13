package com.risenb.studyknowledge.ui.college;

import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.enums.EnumTAB;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.TabUI;
import com.risenb.studyknowledge.ui.institution.InstitutionManageUI;
import com.risenb.studyknowledge.utils.StatusBarUtils;

import java.util.ArrayList;

import static com.risenb.studyknowledge.R.id.drawer_layout;

/**
 * 首页
 *
 * @author Administrator
 */
public class HomeFragment extends BaseFragment {

    @ViewInject(R.id.tab_college)
    private SlidingTabLayout tab_college;
    @ViewInject(R.id.vp_content)
    private ViewPager vp_content;
    @ViewInject(R.id.iv_head)
    private ImageView iv_head;
    @ViewInject(R.id.iv_college)
    private ImageView iv_college;
    ArrayList<Fragment> fragmentList = new ArrayList<>();
    private MyFragmentPagerAdapter viewPagerAdapter;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.ui_home, container, false);
    }

    @Override
    protected boolean isStatusBar() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void setControlBasis() {
        StatusBarUtils.transparencyBar(getActivity());
        iv_head.setImageResource(R.mipmap.head_icon);

        String[] strings = {"基本信息", "学院VIP购买"};
        fragmentList.clear();
        CollegeInfoFragment fragment = new CollegeInfoFragment();
        fragmentList.add(fragment);
        CollegeVipFragment collegeVipFragment = new CollegeVipFragment();
        fragmentList.add(collegeVipFragment);
//        tab_college.setViewPager(vp_content, strings, getActivity(), fragmentList);
        viewPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentList, strings);
        vp_content.setAdapter(viewPagerAdapter);
        tab_college.setViewPager(vp_content);

    }

    @Override
    public void prepareData() {

    }

    @OnClick({R.id.iv_head,R.id.iv_college})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_head://个人中心
                TabUI.openLeftLayout();
                break;
            case R.id.iv_college://学院管理
                InstitutionManageUI.start(getActivity());
                break;
            default:
                break;
        }
    }

}
