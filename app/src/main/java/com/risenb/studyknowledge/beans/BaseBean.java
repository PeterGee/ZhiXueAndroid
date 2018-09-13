package com.risenb.studyknowledge.beans;

import java.io.Serializable;

import com.lidroid.mutils.network.MutilsBaseBean;

/**
 * 描述：公用
 *
 * @author wangjian
 */
public class BaseBean extends MutilsBaseBean implements Serializable {
    private static final long serialVersionUID = 1876345352L;

    private String sussces;
    private String errorCode;
    private String errorMsg;
    private String data;
    private String count;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setSussces(String sussces) {
        this.sussces = sussces;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public boolean isStatus() {
        return "true".equals(sussces);
    }

    @Override
    public String getCode() {
        return errorCode;
    }

    @Override
    public String getMsg() {
        return errorMsg;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String getCount() {
        return count;
    }

}
