package com.bc.capital.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/17.
 */

public class LoginBean implements Serializable {
    /**
     * code : 0
     * message : 登录请求成功
     * data : {" sessionId":"DC8EA3FA5717CB4D593B070949580A38 ","session_user":{"userId":1,"userName":"18610040912","realName":"刘家晨"," cardId ":"330726196507040016"," usr_cust_id ":"6000060075557091"," isbank ":1}}
     */

    private Integer code;
    private String message;
    private DateBean data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateBean getData() {
        return data;
    }

    public void setData(DateBean data) {
        this.data = data;
    }
}
