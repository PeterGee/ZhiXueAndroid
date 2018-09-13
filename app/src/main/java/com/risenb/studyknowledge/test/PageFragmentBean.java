package com.risenb.studyknowledge.test;

import com.lidroid.mutils.fragpage.BaseMenuBean;

/**
 * @author 作者: wangjian
 * @version 创建时间：2015年8月10日 下午3:36:16
 * @类说明 4.18. 获取分类/GetSort
 */
public class PageFragmentBean extends BaseMenuBean
{
    private String sortID;// 分类id,
    private String title;// 分类标题

    public void setSortID(String sortID)
    {
        this.sortID = sortID;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSortID()
    {
        return sortID;
    }

    public String getTitle()
    {
        return title;
    }

    @Override
    public String getBaseMenuBeanID()
    {
        return sortID;
    }

    @Override
    public String getBaseMenuBeanTitle()
    {
        return title;
    }

}
