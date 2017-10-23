package com.bc.capital.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public  class DataBean implements Serializable {
    /**
     * notices : [{"investType":"去投资","investCode":"001","linkUrl":"https://www.huienziben.com/mobileLoan/loan?id=1950","title":"001  房贷  去投资","type":"1","investTitle":"房贷"},{"investType":"去投资","investCode":"333","linkUrl":"https://www.huienziben.com/mobileLoan/loan?id=1948","title":"333  11111111111111\u2026\u2026  去投资","type":"1","investTitle":"11111111111111\u2026\u2026"}]
     * invest : {"number":12595,"amount":8.9166375E7}
     * moudle : [{"icon":"https://www.huienziben.com/resource/mobileMember/myInvest.icon","action":"/mobileMember/myInvest","title":"我要投资"},{"icon":"https://www.huienziben.com/resource/mobileMember/myloan.icon","action":"/mobileMember/moneyManagement/borrowing","title":"我要借款"},{"icon":"https://www.huienziben.com/resource/mobileMember/guide.icon","action":"/single/queryAllDepuy1","title":"新手指引"},{"icon":"https://www.huienziben.com/resource/mobileMember/tuiguang.icon","action":"/mobileMoreIntroduc/generalize","title":"邀请好友"}]
     * banners : [{"icon":"https://www.xinrong.com/register/images/fast_n/10.jpg","action":"/loaninfo/openLoan.htm","title":"Test1"},{"icon":"https://www.xinrong.com/register/images/fast_n/11.jpg","action":"/visitor/to-regist","title":"Test2"},{"icon":"https://www.xinrong.com/register/images/fast_n/12.jpg","action":"/visitor/to-regist","title":"Test3"},{"icon":"https://www.xinrong.com/register/images/fast_n/13.jpg","action":"/visitor/to-regist","title":"Test4"}]
     */

    private InvestBean invest;
    private List<NoticesBean> notices;
    private List<MoudleBean> moudle;
    private List<BannersBean> banners;

    public InvestBean getInvest() {
        return invest;
    }

    public void setInvest(InvestBean invest) {
        this.invest = invest;
    }

    public List<NoticesBean> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticesBean> notices) {
        this.notices = notices;
    }

    public List<MoudleBean> getMoudle() {
        return moudle;
    }

    public void setMoudle(List<MoudleBean> moudle) {
        this.moudle = moudle;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }
}