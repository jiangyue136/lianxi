package com.bc.capital.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/4.
 */

public  class NoticesBean implements Serializable {
    /**
     * investType : 去投资
     * investCode : 001
     * linkUrl : https://www.huienziben.com/mobileLoan/loan?id=1950
     * title : 001  房贷  去投资
     * type : 1
     * investTitle : 房贷
     */

    private String investType;
    private String investCode;
    private String linkUrl;
    private String title;
    private String type;
    private String investTitle;

    public String getInvestType() {
        return investType;
    }

    public void setInvestType(String investType) {
        this.investType = investType;
    }

    public String getInvestCode() {
        return investCode;
    }

    public void setInvestCode(String investCode) {
        this.investCode = investCode;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInvestTitle() {
        return investTitle;
    }

    public void setInvestTitle(String investTitle) {
        this.investTitle = investTitle;
    }
}
