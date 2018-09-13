package com.risenb.studyknowledge.test;

import android.inputmethodservice.KeyboardView;
import android.widget.EditText;

import com.lidroid.mutils.idcard.KeyboardUtil;
import com.lidroid.xutils.view.annotation.ContentView;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;

/**
 * 输入身份证号
 *
 * @author Administrator
 */
@ContentView(R.layout.keyboard)
public class KeyboardTest extends BaseUI {
    private EditText etIdCard;
    private KeyboardView keyboardView;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("");
    }

    @Override
    protected void prepareData() {
        // <RelativeLayout
        // android:layout_width="match_parent"
        // android:layout_height="match_parent" >
        //
        // <include layout="@layout/keyboard"/>
        // </RelativeLayout>

        KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), etIdCard, keyboardView);
        if (!keyboardUtil.checkNum(etIdCard.getText().toString())) {
            makeText("请输入正确的身份证号");
            return;
        }

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

}
