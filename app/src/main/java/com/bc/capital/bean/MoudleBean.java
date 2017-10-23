package com.bc.capital.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/4.
 */


public  class MoudleBean implements Serializable{
    /**
     * icon : https://www.huienziben.com/resource/mobileMember/myInvest.icon
     * action : /mobileMember/myInvest
     * title : 我要投资
     */
    private Integer id;
    private String icon;
    private String action;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
