package com.risenb.studyknowledge.beans.institution;

/**
 * Created by yy on 2017/9/26.
 * 近期收益
 */

public class RecentIncomeBean {
    private String incomeType;
    private String incomePrice;
    private int resourceIcon;
    public RecentIncomeBean() {
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(String incomePrice) {
        this.incomePrice = incomePrice;
    }
    public int getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(int resourceIcon) {
        this.resourceIcon = resourceIcon;
    }
}
