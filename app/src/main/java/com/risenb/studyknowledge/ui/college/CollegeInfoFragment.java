package com.risenb.studyknowledge.ui.college;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.college.CollegeBean;
import com.risenb.studyknowledge.beans.college.CollegeInfoBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.institution.AddCollegeUI;
import com.risenb.studyknowledge.utils.DateUtils;

import glideimageview.GlideImageLoader;

/**
 * Created by zhuzh on 2017/10/19.
 */

public class CollegeInfoFragment extends BaseFragment implements CollegeInfoP.CollegeInfoFace {
    @ViewInject(R.id.iv_college)
    private ImageView iv_college;
    @ViewInject(R.id.iv_edit)
    private ImageView iv_edit;
    @ViewInject(R.id.tv_college_name)
    private TextView tv_college_name;
    @ViewInject(R.id.tv_expire_time)
    private TextView tv_expire_time;
    @ViewInject(R.id.tv_content)
    private TextView tv_content;

    private CollegeInfoP collegeInfoP;
    private CollegeBean collegeBean;


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.college_info_fm, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        Utils.getUtils().showProgressDialog(getActivity(), null);
        collegeInfoP = new CollegeInfoP(this, getActivity());
        collegeInfoP.getCollegeInfo(application.getC());
    }

    @Override
    public void setControlBasis() {

        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CollegeEditFragment newFragment = CollegeEditFragment.newInstance(collegeBean);
//
//                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                transaction.replace(R.id.rl_college_info, newFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
                AddCollegeUI.start(view.getContext());
            }
        });
    }

    @Override
    public void prepareData() {

    }

    @Override
    public void collegeInfoSuccess(CollegeInfoBean.DataBean result) {
        Utils.getUtils().dismissDialog();
        collegeBean = result.getCollege();
        GlideImageLoader.create(iv_college).loadImage(collegeBean.getCollegeBackimg(), R.mipmap.unify_image_ing);
        tv_college_name.setText(collegeBean.getCollegeName());
        tv_expire_time.setText(DateUtils.stampToDate(collegeBean.getCollegeGradetime()) + "到期");
        tv_content.setText(Html.fromHtml(collegeBean.getCollegeInfo()));
    }

    @Override
    public void editCollegeInfoSuccess() {

    }
}
