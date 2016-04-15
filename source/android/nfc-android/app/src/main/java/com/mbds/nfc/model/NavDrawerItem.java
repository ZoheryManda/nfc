package com.mbds.nfc.model;

public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private String subtitle;
    private int mIcon;

    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public NavDrawerItem(boolean showNotify, String title, String subtitle, int mIcon) {
        this.showNotify = showNotify;
        this.title = title;
        this.subtitle = subtitle;
        this.mIcon = mIcon;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}