package com.risenb.studyknowledge.utils;

/**
 * @author 作者: wangjian
 * @version 创建时间：2015年9月8日 下午1:46:07
 * @类说明
 */
public class ErrorUtils
{
    public static void info()
    {
        com.lidroid.mutils.network.ErrorUtils.getMap().put("000", "接口调用失败");
    }
}
