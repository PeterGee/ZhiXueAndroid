package com.risenb.studyknowledge.network;

import com.lidroid.mutils.network.MOKHttpUtils;
import com.lidroid.mutils.network.NetMethod;
import com.risenb.studyknowledge.beans.BaseBean;

/**
 * @author 作者: wangjian
 * @version 创建时间：2015年9月21日 上午10:23:15
 * @类说明 联网二次封装
 */
public class NetUtils extends MOKHttpUtils<BaseBean> {
    private static NetUtils netUtils;

    public static NetUtils getNetUtils() {
        if (netUtils == null) {
            netUtils = new NetUtils();
        }
        return netUtils;
    }

    public NetUtils() {
        super(BaseBean.class, NetMethod.GET, 10000, 10000, 10000);
    }
}
