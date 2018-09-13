package com.risenb.studyknowledge.ui.personal;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.adapter.personal.MemberIdentityAdapter;
import com.risenb.studyknowledge.beans.personal.MemberIdentityBean;
import com.risenb.studyknowledge.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/9/21.
 * 会员身份弹窗
 */

public class IdentityFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @ViewInject(R.id.rv_identity_list)
    RecyclerView rv_identity_list;
    private List<MemberIdentityBean> list=new ArrayList<>();
    private MemberIdentityAdapter mMemberIdentityAdapter;
    private String[] mIdentity;
    private int mIdentity_type;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fm_identity, container, false);
        //获得点击事件
        view.setClickable(true);
    }

    @Override
    public void setControlBasis() {
        //人员类型(0：学生、1：管理员、2老师
        mIdentity_type = getIdentity_type();

        mIdentity = getResources().getStringArray(R.array.member_identity);
        for (int i = 0; i < mIdentity.length; i++) {
            MemberIdentityBean bean = new MemberIdentityBean();
            bean.setIdentity(mIdentity[i]);
            bean.setChecked(false);
            list.add(bean);
        }
        if(mIdentity_type ==0){
            list.get(1).setChecked(true);//默认学生选中
        }else if(mIdentity_type ==1){
            list.get(2).setChecked(true);//默认管理员选中
        }else if(mIdentity_type ==2){
            list.get(0).setChecked(true);//默认老师选中
        }

        mMemberIdentityAdapter = new MemberIdentityAdapter(R.layout.member_identity_item, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_identity_list.setAdapter(mMemberIdentityAdapter);
        rv_identity_list.setLayoutManager(linearLayoutManager);
        mMemberIdentityAdapter.setOnItemClickListener(this);
    }

    @Override
    public void prepareData() {

    }
    @OnClick({R.id.bg_identity,R.id.tv_cancel,R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bg_identity://会员身份上半部分白色透明背景
                mIdentityListener.closeIdentityListener(view);//关闭勋章弹窗
                break;
            case R.id.tv_cancel://取消
                mIdentityListener.closeIdentityListener(view);//关闭勋章弹窗
                break;
            case R.id.tv_confirm://确定
                int type = identityType();
                mIdentityListener.checkedIdentityListener(mIdentity[type],identityNet());
                break;
            default:
                break;
        }
    }
    /**
     * 勋章条目点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChecked(false);
        }
        list.get(position).setChecked(true);
        mMemberIdentityAdapter.notifyDataSetChanged();
    }
    //app定义的身份顺序
    public int identityType(){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).isChecked()){
                return i;
            }
        }
        return 1;//默认学生
    }
    //上传后台请求的身份顺序
    public int identityNet(){
        //人员类型(0：学生、1：管理员、2老师
        if(identityType()==0){//老师
            return 2;
        }else if(identityType()==1){//学生
            return 0;
        }else if(identityType()==2){//管理员
            return 1;
        }
        return 0;
    }
    /**
     * 关闭勋章列表弹窗的接口回调
     */
    private OnIdentityListener mIdentityListener;
    public interface OnIdentityListener{
        void closeIdentityListener(View view);
        void checkedIdentityListener(String identity,int type);
    }
    public OnIdentityListener getIdentityListener() {
        return mIdentityListener;
    }

    public void setIdentityListener(OnIdentityListener identityListener) {
        mIdentityListener = identityListener;
    }
    public int getIdentity_type() {
        return mIdentity_type;
    }

    public void setIdentity_type(int identity_type) {
        mIdentity_type = identity_type;
    }
}
