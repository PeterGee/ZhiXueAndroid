package com.risenb.studyknowledge.beans;

import com.lidroid.mutils.viewdata.OnImageData;
import com.lidroid.mutils.viewdata.OnTextData;
import com.risenb.studyknowledge.R;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ViewDataBean {
    @OnTextData(R.id.title)
    private String plantTitle;//名称,
    @OnImageData(R.id.back)
    private String imageBig;//图片URL,

    public String getPlantTitle() {
        return plantTitle;
    }

    public void setPlantTitle(String plantTitle) {
        this.plantTitle = plantTitle;
    }

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }
}
