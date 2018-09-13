package com.risenb.studyknowledge.beans;

/**统一bean的头
 * Created by yonghao zeng on 2017/5/11.
 */

public  class NetBaseBean<T> {

    public NetBaseBean() {
    }


    public  T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String errorCode;
    public String errorMsg;
    public boolean status;



    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
