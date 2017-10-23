package com.bc.capital.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/4.
 */


public  class BannersBean implements Serializable {
    /**
     * icon : https://www.xinrong.com/register/images/fast_n/10.jpg
     * action : /loaninfo/openLoan.htm
     * title : Test1
     */

    private String icon;
    private String action;
    private String title;
    private String imageUrl;
    private String  linkUrl;

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}