package com.risenb.studyknowledge.beans;

import java.util.List;

public class CommsBean {
    private String id;
    private List<ItemBean> item;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

}
