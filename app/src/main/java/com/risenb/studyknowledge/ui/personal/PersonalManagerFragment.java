package com.risenb.studyknowledge.ui.personal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.LazyLoadFragment;
import com.risenb.studyknowledge.ui.TabUI;
import com.risenb.studyknowledge.ui.login.ForgetPasswordUI;
import com.risenb.studyknowledge.ui.login.RegisterUI;

/**
 * 人员管理
 */
public class PersonalManagerFragment extends LazyLoadFragment {

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_personal_manager, container, false);
    }

    @Override
    public void setControlBasis() {

    }

    @Override
    public void prepareData() {

    }
    @OnClick({R.id.rl_member_manager,R.id.rl_member_application,R.id.rl_kick_out_member,R.id
            .rl_black_list,R.id.rl_sign_in_manager})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_member_manager://C端会员管理
                MemberManagerUI.start(getContext());
                break;
            case R.id.rl_member_application://会员申请
                MemberApplyUI.start(getContext());
                break;
            case R.id.rl_kick_out_member://踢出的会员
                KickOutMemberUI.start(getContext());
                break;
            case R.id.rl_black_list://黑名单
                BlacklistUI.start(getContext());
                break;
            case R.id.rl_sign_in_manager://签到管理
                SignInManageUI.start(getContext());
                break;
            default:
                break;
        }
    }
}
