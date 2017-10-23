package com.bc.capital.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/4.
 */

public  class InvestBean implements Serializable {
    /**
     * number : 12595.0
     * amount : 8.9166375E7
     */

    private String  number;
    private String amount;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}