package com.bc.capital.bean;

import java.io.Serializable;

/**
 * 作者：LiYaHong on 2017/6/19 17:16
 * QQ：129929684
 * 邮箱：1299259684@qq.com
 */

public class MessageListBean implements Serializable {

    private int msgState;       //信息状态        0:未读  1:已读
    private boolean isShowCb;   //是否显示复选框   false:不显示  true:显示
    private boolean isSelected; //复选框是否被选中 false:不勾选  true:勾选
    private String msgDesc;     //消息内容

    public MessageListBean() {}

    public MessageListBean(int msgState, boolean isShowCb, boolean isSelected, String msgDesc) {
        this.msgState = msgState;
        this.isShowCb = isShowCb;
        this.isSelected = isSelected;
        this.msgDesc = msgDesc;

    }

    public int getMsgState() {
        return msgState;
    }

    public void setMsgState(int msgState) {
        this.msgState = msgState;
    }

    public boolean isShowCb() {
        return isShowCb;
    }

    public void setShowCb(boolean showCb) {
        isShowCb = showCb;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc;
    }

    @Override
    public String toString() {
        return "MessageListBean{" +
                "msgState=" + msgState +
                ", isShowCb=" + isShowCb +
                ", isSelected=" + isSelected +
                ", msgDesc='" + msgDesc + '\'' +
                '}';
    }
}
