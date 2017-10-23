package com.bc.capital.common;

/**
 * Created by Administrator on 2017/5/15.
 */

public class AppNetConfig {

    public static final String BASE_URL="http://test.huienziben.com";//测试路径

    public static final String GETVERIFICATIONCODE=BASE_URL+"/mobileRegistration/sendSmsPhone?phone=";//获取短信验证码

    public static final String USERREGISTER=BASE_URL+"/mobileRegistration/registration.htm";//用户注册

    public static final String USERLOGIN=BASE_URL+"/mobileVisitor/login.htm";//用户登录

    public static final String INDEX=BASE_URL+"/index";//访问homefragment

    public static final String FEEDBACK=BASE_URL+"/FeedBack";//用户反馈

    public static final String UPDATE=BASE_URL+"/update.json";//更新应用

    public static final String HOMEURL = BASE_URL+"/mobileVisitor/";//首页接口

    public static final String INVESTMENTLIST = BASE_URL+"/mobileLoan/loans";//投资列表

    public static final String NOVICEGUIDE = BASE_URL+"/mobileVisitor/getPageHtm?name=新手指引";//新手指引

    public static final String LISTOFCLAIMS = BASE_URL+"/mobileLoan/debts";//债权列表


}
