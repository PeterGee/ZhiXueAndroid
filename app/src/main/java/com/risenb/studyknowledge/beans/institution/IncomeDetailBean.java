package com.risenb.studyknowledge.beans.institution;

/**
 * Created by yy on 2017/9/26.
 * 入群收益明细
 */

public class IncomeDetailBean {
    private String name;
    private String time;
    private String price;
    private String priceName;
    private String content;

    public IncomeDetailBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
