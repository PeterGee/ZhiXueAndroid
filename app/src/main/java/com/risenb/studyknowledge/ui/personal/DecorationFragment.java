package com.risenb.studyknowledge.ui.personal;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.DecorationAdapter;
import com.risenb.studyknowledge.beans.institution.MedalInfoBean;
import com.risenb.studyknowledge.ui.BaseFragment;
import com.risenb.studyknowledge.ui.institution.MedalManagerP;
import com.risenb.studyknowledge.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/21.
 * 勋章弹窗
 */

public class DecorationFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, MedalManagerP.MedalListFace {
    @ViewInject(R.id.rv_decoration_list)
    RecyclerView rv_decoration_list;//勋章列表
    private DecorationAdapter mDecorationAdapter;
    private int PAGE = 1;
    private String LIMIT;
    private String C = "1643";
    private String collegeId = "45";
    private MedalManagerP mMedalManagerP;
    private List<MedalInfoBean> medalList;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_decoration, container, false);
        //获得点击事件
        view.setClickable(true);
    }

    @Override
    public void setControlBasis() {
        LIMIT = getResources().getString(R.string.limit_10);


        mDecorationAdapter = new DecorationAdapter(R.layout.decoration_list_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_decoration_list.setAdapter(mDecorationAdapter);
        rv_decoration_list.setLayoutManager(linearLayoutManager);
        mDecorationAdapter.setOnItemClickListener(this);
        mDecorationAdapter.setEmptyView(R.layout.empty_member_detail_view, (ViewGroup) rv_decoration_list
                .getParent());
    }

    @Override
    public void prepareData() {
        mMedalManagerP = new MedalManagerP(this, getActivity());
        mMedalManagerP.getMedalList(C, collegeId, PAGE + "", LIMIT);
    }

    @OnClick({R.id.bg_decoration, R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bg_decoration://勋章列表左半部分白色透明背景
                mDecorationListener.closeDecorationListener(view);//关闭勋章弹窗
                break;
            case R.id.tv_confirm://确认选中的勋章
                if (!TextUtils.isEmpty(getCheckedMedalID())) {
                    mDecorationListener.medalIconListener(view, getCheckedMedalID(), getCheckedMedalIMG());
                } else {
                    ToastUtils.showToast("请选择勋章");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取勋章列表成功的回调
     *
     * @param medalTypeList
     */
    @Override
    public void medalListSuccess(List<MedalInfoBean> medalTypeList) {
        this.medalList = medalTypeList;
        mDecorationAdapter.setNewData(medalList);
        mDecorationAdapter.notifyDataSetChanged();
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
        boolean checked = medalList.get(position).isChecked();
        medalList.get(position).setChecked(!checked);
        mDecorationAdapter.notifyDataSetChanged();
    }

    /**
     * 获取选中勋章id组拼
     *
     * @return
     */
    public String getCheckedMedalID() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < medalList.size(); i++) {
            if (medalList.get(i).isChecked()) {
                stringBuilder.append(medalList.get(i).getMedalTypeId());
                stringBuilder.append(",");
            }
        }
        String medalIds = "";
        if (!TextUtils.isEmpty(stringBuilder.toString())) {
            medalIds = stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        return medalIds;
    }

    public List<String> getCheckedMedalIMG() {
        List<String> medalImgList = new ArrayList<>();
        for (int i = 0; i < medalList.size(); i++) {
            if (medalList.get(i).isChecked()) {
                medalImgList.add(medalList.get(i).getMedalTypeMig());
            }
        }
        return medalImgList;
    }

    /**
     * 关闭勋章列表弹窗的接口回调
     */
    private OnDecorationListener mDecorationListener;

    public interface OnDecorationListener {
        void closeDecorationListener(View view);

        void medalIconListener(View view, String ids, List<String> medalImg);
    }

    public OnDecorationListener getDecorationListener() {
        return mDecorationListener;
    }

    public void setDecorationListener(OnDecorationListener decorationListener) {
        mDecorationListener = decorationListener;
    }

}
