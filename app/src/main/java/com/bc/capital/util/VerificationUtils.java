package com.bc.capital.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/6.
 */

public class VerificationUtils {
    /**
     * 验证手机号码
     * */
    public static boolean isMobileNO(String mobiles) {
        String regExp = "^[1]([3][0-9]{1}|[4][0-9]{1}|[5][0-9]{1}|[7][0-9]{1}|[8][0-9]{1})[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


}
