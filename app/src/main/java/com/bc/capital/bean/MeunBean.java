package com.bc.capital.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/15.
 */

public class MeunBean implements Serializable {
    private int id;
    private String name;
    private int resIcon;

    public MeunBean(int id, String name, int resIcon) {
        this.id = id;
        this.name = name;
        this.resIcon = resIcon;
    }

    public MeunBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }
}
