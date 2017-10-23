package com.bc.capital.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/17.
 */

public class DateBean implements Serializable {
    private String sessionId;
    private SessionUser session_user;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public SessionUser getSession_user() {
        return session_user;
    }

    public void setSession_user(SessionUser session_user) {
        this.session_user = session_user;
    }
}
