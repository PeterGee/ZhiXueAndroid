package com.risenb.studyknowledge.test;

import com.lidroid.mutils.MUtils;

public class HandlerTest
{
    public HandlerTest()
    {
        MUtils.getMUtils().getHandler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

            }
        }, 1000);

        MUtils.getMUtils().getHandler().post(new Runnable()
        {
            @Override
            public void run()
            {

            }
        });
    }
}
