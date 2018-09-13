package com.risenb.studyknowledge.network;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mzhy.http.okhttp.callback.Callback;
import com.risenb.studyknowledge.beans.NetBaseBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 通用网络访问callback 去除base头 返回data信息 如果base中含有error则直接回调为onError
 * Created by yonghao zeng on 2017/5/11.
 */

public abstract class DataCallback<T> extends Callback<NetBaseBean<T>> {
    private Class<T> entityClass;


    @Override
    public NetBaseBean parseNetworkResponse(Response response, int i) throws Exception {
        //反射获取泛型类名
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        this.entityClass = (Class) params[0];
        String re = response.body().string();


        Log.e(response.request().url() + params.toString(), re);
        NetBaseBean<T> bean = JSON.parseObject(re, new
                TypeReference<NetBaseBean<T>>(entityClass) {
                });

//        T data = bean.getData();
        return bean;

    }


    @Override
    public void onResponse(NetBaseBean<T> t, int i) {
        if (t == null) {
            onJsonParesError();
        }

        T data = t.getData();
        //状态错误
        if (!t.isStatus()) {
            onStatusError(t.getErrorCode(), t.getErrorMsg());
            return;
        }
        //获得data数据
        onSuccess(data, i);

    }


    //data是单个对象回调
    public abstract void onSuccess(T result, int i);

    public void onJsonParesError() {

    }


    public abstract void onStatusError(String errorCode, String errorMsg);

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        HttpUrl url = request.url();


        Log.d("DataCallback", url + request.body().toString());
    }


//    public OnTokenOutListener mOnTokenOutListener;
//    public interface OnTokenOutListener{
//        void onTokenListener();
//    }
//    public OnTokenOutListener getOnTokenOutListener() {
//        return mOnTokenOutListener;
//    }
//
//    public void setOnTokenOutListener(OnTokenOutListener onTokenOutListener) {
//        mOnTokenOutListener = onTokenOutListener;
//    }
}
