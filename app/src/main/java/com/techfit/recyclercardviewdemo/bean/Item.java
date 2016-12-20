package com.techfit.recyclercardviewdemo.bean;

/**
 * Created by wangzhengyang on 2016/12/13.
 */

public class Item {
    private String mainTitle;
    private String subTitle;
    private boolean active;
    public int dimen;

    public Item(String mainTitle, String subTitle) {
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
    }

    public Item(boolean active, String mainTitle, String subTitle) {
        this.active = active;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Item{" +
                "mainTitle='" + mainTitle + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", active=" + active +
                '}';
    }
}
