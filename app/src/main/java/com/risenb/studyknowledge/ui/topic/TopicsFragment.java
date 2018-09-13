package com.risenb.studyknowledge.ui.topic;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.LazyLoadFragment;


/**
 * Created by zhuzh on 2017/9/16.
 *
 * 话题管理
 */

public class TopicsFragment extends LazyLoadFragment {

    @ViewInject(R.id.tv_topic)
    private TextView tv_topic;
    @ViewInject(R.id.tv_action)
    private TextView tv_action;
    @ViewInject(R.id.tv_vote)
    private TextView tv_vote;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.ui_topic, container, false);

    }

    @Override
    public void setControlBasis() {
    }

    @Override
    public void prepareData() {

    }

    @OnClick({R.id.tv_topic, R.id.tv_action, R.id.tv_vote})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_topic:
                TopicListManageUI.start(getActivity());
                break;
            case R.id.tv_action:
                ActionManageUI.start(getActivity());
                break;
            case R.id.tv_vote:
                VoteManageUI.start(getActivity());
                break;
            default:
                break;
        }
    }

}
