package com.risenb.studyknowledge.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.studyknowledge.R;

public class ZFragment extends BaseFragment
{

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container)
    {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.keyboard, container, false);
    }

    @Override
    public void setControlBasis()
    {

    }

    @Override
    public void prepareData()
    {

    }

}
