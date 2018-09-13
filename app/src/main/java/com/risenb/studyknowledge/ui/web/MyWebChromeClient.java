package com.risenb.studyknowledge.ui.web;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;

/**
 * 浏览本地sd卡
 *
 * @author Administrator
 *
 */
public class MyWebChromeClient extends WebChromeClient
{
    private FragmentActivity activity;
    public ValueCallback<Uri> mUploadMessage;

    public MyWebChromeClient(FragmentActivity activity, ValueCallback<Uri> mUploadMessage)
    {
        this.activity = activity;
        this.mUploadMessage = mUploadMessage;
    }

    public FragmentActivity getActivity()
    {
        return activity;
    }

    // For Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType)
    {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        getActivity().startActivityForResult(Intent.createChooser(i, "File Chooser"), WebP.FILECHOOSER_RESULTCODE);
    }

    // For Android < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg)
    {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        getActivity().startActivityForResult(Intent.createChooser(i, "File Chooser"), WebP.FILECHOOSER_RESULTCODE);
    }

    // For Android > 4.1.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
    {
        openFileChooser(uploadMsg, acceptType);
    }
}
