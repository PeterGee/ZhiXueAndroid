package com.risenb.studyknowledge.ui;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.lidroid.mutils.utils.Utils;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.AddFileBean;
import com.risenb.studyknowledge.beans.NetBaseBean;
import com.risenb.studyknowledge.network.DataCallback;
import com.risenb.studyknowledge.ui.login.GetCodeP;
import com.risenb.studyknowledge.utils.NetworkUtils;
import com.risenb.studyknowledge.utils.PhoneUtils;

import java.io.File;

import okhttp3.Call;

/**
 * Created by yy on 2017/11/27.
 * 文件上传（支持多个文件上传）
 */

public class FileAddP extends PresenterBase {
    private FileAddFace fileAddFace;
    public FileAddP(FileAddFace face, FragmentActivity activity) {
        this.fileAddFace = face;
        setActivity(activity);
    }
    public void fileAdd(File file) {
        Utils.getUtils().showProgressDialog(getActivity(), null);
        NetworkUtils.getNetworkUtils().addFile(file, new
                DataCallback<AddFileBean.DataBean>() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Utils.getUtils().dismissDialog();
                        makeText(activity.getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onSuccess(AddFileBean.DataBean result, int i) {
                        Utils.getUtils().dismissDialog();
                        fileAddFace.addFileSuccess(result.getUrl());
                    }

                    @Override
                    public void onStatusError(String errorCode, String errorMsg) {
                        Utils.getUtils().dismissDialog();
                        makeText(errorMsg);
                    }
                });

    }
    public interface FileAddFace {
        void addFileSuccess(String url);
    }
}
