package com.bc.capital.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/4.
 */

public class HomeBean implements Serializable {

    /**
     * code : 0
     * data : {"notices":[{"investType":"去投资","investCode":"001","linkUrl":"https://www.huienziben.com/mobileLoan/loan?id=1950","title":"001  房贷  去投资","type":"1","investTitle":"房贷"},{"investType":"去投资","investCode":"333","linkUrl":"https://www.huienziben.com/mobileLoan/loan?id=1948","title":"333  11111111111111\u2026\u2026  去投资","type":"1","investTitle":"11111111111111\u2026\u2026"}],"invest":{"number":12595,"amount":8.9166375E7},"moudle":[{"icon":"https://www.huienziben.com/resource/mobileMember/myInvest.icon","action":"/mobileMember/myInvest","title":"我要投资"},{"icon":"https://www.huienziben.com/resource/mobileMember/myloan.icon","action":"/mobileMember/moneyManagement/borrowing","title":"我要借款"},{"icon":"https://www.huienziben.com/resource/mobileMember/guide.icon","action":"/single/queryAllDepuy1","title":"新手指引"},{"icon":"https://www.huienziben.com/resource/mobileMember/tuiguang.icon","action":"/mobileMoreIntroduc/generalize","title":"邀请好友"}],"banners":[{"icon":"https://www.xinrong.com/register/images/fast_n/10.jpg","action":"/loaninfo/openLoan.htm","title":"Test1"},{"icon":"https://www.xinrong.com/register/images/fast_n/11.jpg","action":"/visitor/to-regist","title":"Test2"},{"icon":"https://www.xinrong.com/register/images/fast_n/12.jpg","action":"/visitor/to-regist","title":"Test3"},{"icon":"https://www.xinrong.com/register/images/fast_n/13.jpg","action":"/visitor/to-regist","title":"Test4"}]}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }






}
