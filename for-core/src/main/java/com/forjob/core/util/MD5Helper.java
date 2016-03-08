package com.forjob.core.util;

import java.security.MessageDigest;

/**
 * @Description: MD5加密
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 下午6:19:17   
 * @version 1.0
 */
public final class MD5Helper {
    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return new StringBuilder().append(hexDigits[d1]).append(hexDigits[d2])
                .toString();
    }

    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString
                    .getBytes()));
        } catch (Exception ignored) {
        }
        return resultString;
    }
}

