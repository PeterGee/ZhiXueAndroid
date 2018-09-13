package com.risenb.studyknowledge.ui.institution;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.institution.AddTopicAdapter;
import com.risenb.studyknowledge.beans.topic.TopicListBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.live.TopicDecorationP;

import java.util.List;

/**
 * Created by yy on 2017/9/21.
 * 添加所属话题弹窗
 */

public class AddTopicFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, TopicDecorationP.TopicDecorationFace {
    @ViewInject(R.id.rv_decoration_list)
    RecyclerView rv_decoration_list;//话题列表
    private List<TopicListBean> list;
    private AddTopicAdapter mAddTopicAdapter;
    public TopicDecorationP mTopicDecorationP;

    private int page = 1;
    private int limit = 100;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_decoration, container, false);
        //获得点击事件
        view.setClickable(true);
    }

    @Override
    public void setControlBasis() {

        mTopicDecorationP = new TopicDecorationP(this, getActivity());
        mTopicDecorationP.getTopicList(application.getC(), "45", "", String.valueOf(page), String.valueOf(limit));
        //创建假数据

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_decoration_list.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void prepareData() {

    }

    @OnClick({R.id.bg_decoration})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bg_decoration://话题列表左半部分白色透明背景
                mOnTopicListener.closeTopicListener(view);
                break;
            default:
                break;
        }
    }

    /**
     * 勋章条目点击事件
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mOnTopicListener.topicListener(view, String.valueOf(list.get(position).getTopicId()),list.get(position).getTopicName());
    }


    /**
     * 关闭话题列表弹窗的接口回调
     */
    private OnTopicListener mOnTopicListener;

    @Override
    public void topicListSuccess(List<TopicListBean> list) {
        this.list = list;
        mAddTopicAdapter = new AddTopicAdapter(R.layout.add_topic_item, list);
        rv_decoration_list.setAdapter(mAddTopicAdapter);
        mAddTopicAdapter.setOnItemClickListener(this);
    }

    @Override
    public void topicListFail() {

    }

    public interface OnTopicListener {
        void closeTopicListener(View view);

        void topicListener(View view, String topic,String topicName);
    }

    public OnTopicListener getOnTopicListener() {
        return mOnTopicListener;
    }

    public void setOnTopicListener(OnTopicListener onTopicListener) {
        mOnTopicListener = onTopicListener;
    }
}
