package com.risenb.studyknowledge.ui.institution;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.beans.institution.MedalBean;
import com.risenb.studyknowledge.beans.institution.MedalInfoBean;
import com.risenb.studyknowledge.beans.institution.MedalModifyBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.PresenterBase;
import com.risenb.studyknowledge.utils.NetworkUtils;

import okhttp3.Call;

/**
 * Created by yy on 2017/11/15.
 * 编辑（添加或修改）勋章
 */

public class AddMedalP extends PresenterBase {
    private AddModifyMedalFace addModifyMedalFace;
    public AddMedalP(AddModifyMedalFace face, FragmentActivity activity) {
        this.addModifyMedalFace = face;
        setActivity(activity);
    }

    /**
     * 添加或修改勋章
     * @param c                  登录标识
     * @param collegeId         学院ID
     * @param medalTypeId       勋章类型ID
     * medalTypeName     勋章类型标题
     *medalTypeInfo     勋章类型描述
     * medalTypeMig      勋章类型图片
     */
    public void addModifyMedal(String c, String collegeId,String medalTypeId) {
        if(TextUtils.isEmpty(addModifyMedalFace.getMedalTitle())){
            makeText(getActivity().getResources().getString(R.string.medal_title_null));
            return;
        }
        if(TextUtils.isEmpty(addModifyMedalFace.getMedalImg())){
            makeText(getActivity().getResources().getString(R.string.medal_img_null));
            return;
        }
        if(TextUtils.isEmpty(addModifyMedalFace.getMedalInfo())){
            makeText(getActivity().getResources().getString(R.string.medal_content_null));
            return;
        }
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().addModifyMedal(c,collegeId,medalTypeId,addModifyMedalFace.getMedalTitle(),
                addModifyMedalFace.getMedalInfo(),addModifyMedalFace.getMedalImg(), new
                        DataCallback<MedalModifyBean.DataBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(MedalModifyBean.DataBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        addModifyMedalFace.addModifyMedalSuccess(result.getMedalType());
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }

    public interface AddModifyMedalFace {
        //新增或修改勋章成功的回调
        void addModifyMedalSuccess(MedalInfoBean result);
        //获取勋章标题
        String getMedalTitle();
        //获取勋章内容
        String getMedalInfo();
        //获取勋章图片
        String getMedalImg();
    }
}
