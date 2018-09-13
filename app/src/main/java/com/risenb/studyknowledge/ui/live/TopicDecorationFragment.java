package com.risenb.studyknowledge.ui.live;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.topic.TopicDecorationAdapter;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.topic.TopicDecorationBean;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.topic.TopicListManageP;

import java.util.ArrayList;
import java.util.List;

import static com.risenb.studyknowledge.R.id.mrl_topic_list;

/**
 * Created by zhuzh on 2017/9/25.
 */

public class TopicDecorationFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, TopicDecorationP.TopicDecorationFace {

    @ViewInject(R.id.rv_topic_decoration_list)
    private RecyclerView rv_topic_decoration_list;
    private List<TopicListBean> listData = new ArrayList<>();
    private TopicDecorationAdapter mAdapter;
    private TopicDecorationP topicDecorationP;
    private int PAGE=1;
    private String LIMIT = "9999";
    private String TIMESTAMP="";
    private String C="1643";
    private String CollegeId="45";
    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.topic_decoration_fm, container, false);

    }

    @Override
    public void setControlBasis() {

        mAdapter = new TopicDecorationAdapter(R.layout.topic_decoration_list_item, listData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_topic_decoration_list.setAdapter(mAdapter);
        rv_topic_decoration_list.setLayoutManager(linearLayoutManager);
        mAdapter.setOnItemClickListener(this);

    }

    @Override
    public void prepareData() {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        topicDecorationP = new TopicDecorationP(this, getActivity());
        topicDecorationP.getTopicList(C,CollegeId,TIMESTAMP,PAGE+"",LIMIT);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mDecorationListener.closeDecorationListener(view, listData.get(position).getTopicName(), listData.get(position).getTopicId());

    }

    @OnClick({R.id.white_transparent})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.white_transparent:
                mDecorationListener.closeDecorationListener(view, null, 0);//关闭弹窗
                break;
            default:
                break;
        }
    }

    private OnDecorationListener mDecorationListener;

    @Override
    public void topicListSuccess(List<TopicListBean> list) {
        Utils.getUtils().dismissDialog();

        listData=list;
        mAdapter.setNewData(listData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void topicListFail() {

    }


    public interface OnDecorationListener{
        void closeDecorationListener(View view, String topic, int topicId);
    }
    public OnDecorationListener getDecorationListener() {
        return mDecorationListener;
    }

    public void setDecorationListener(OnDecorationListener decorationListener) {
        mDecorationListener = decorationListener;
    }
}
