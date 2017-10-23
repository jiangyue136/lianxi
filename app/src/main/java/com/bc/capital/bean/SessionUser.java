package com.bc.capital.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/17.
 */

public class SessionUser implements Serializable {

    private Integer userId;
    private String userName;
    private String realName;
    private String cardId;
    private String usr_cust_id;
    private Integer isbank;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUsrCustId() {
        return usr_cust_id;
    }

    public void setUsr_cust_id(String usr_cust_id) {
        this.usr_cust_id = usr_cust_id;
    }

    public Integer getIsbank() {
        return isbank;
    }

    public void setIsbank(Integer isbank) {
        this.isbank = isbank;
    }
}
