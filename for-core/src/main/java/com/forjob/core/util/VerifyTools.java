package com.forjob.core.util;

import java.util.regex.Pattern;


/**、
 * @Description: 简单数据的验证
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月10日 下午10:34:03   
 * @version 1.0
 */
public class VerifyTools {

    /**
     * @Description:检查是否为数字
     * @param src 符串
     * @return true：是 false：不是
     */
    public static boolean isNumber(String src) {
        if (src == null || src.equals("")) {
            return false;
        }
        return Pattern.matches("^[-]?[0]{1}[.]{1}[0-9]+|[-]?[1-9]{1}[0-9]*[.]{1}[0-9]+|[-]?[1-9]{1}[0-9]*|[0]{1}$", src);
    }

    /**
     * @Description:检查是否为整数
     * @param src 字符串
     * @return true： 是 false：不是
     */
    public static boolean isInteger(String src) {
        if (src == null || src.equals("")) {
            return false;
        }
        return Pattern.matches("^[-]?[0-2]?[0-9]{1,9}$", src) && Long.parseLong(src) >= -(2 << 30) && Long.parseLong(src) <= (2 << 30) - 1;
    }

    /**
     * @Description:检查字符串是否为空或者null
     * @param src字符串
     * @return 检查结果
     */
    public static boolean isEmpty(Object src) {
        if (src == null || src.equals(""))
            return true;
        String temp = src.toString().trim();
        return temp.equals("") || temp.equals("null");
    }
}
