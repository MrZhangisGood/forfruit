package com.forjob.core.util;


/**
 * @Description: 简单的数据转换
 * @author zhanglm@joyplus.com.cn
 * @date 2013-8-19 下午1:22:52
 * @version 1.0
 */
public class TypeConvert {

	/**
	 * @Description:转化成金额形式
     * @return
	 */
	public static String toDecimal(Object obj) {
		return new java.text.DecimalFormat("#.0000").format(obj);
	}

    /**
     * @Description:将Object数据转成Double
     * @return
     */
	public static Double toDou(Object obj) {
		if(obj == null){
			return 0.0;
		}else{
			return VerifyTools.isNumber(obj.toString()) ? Double.parseDouble(obj.toString()) : 0.0;
		}
	}

    /**
     * @Description:将Object数据转成String
     * @return
     */
    public static String toStr(Object obj) {
        if(obj == null || "".equals(String.valueOf(obj))){
            return null;
        }
        return String.valueOf(obj);
    }
    
	/**
     * @Description:将Object数据转成Integer
     * @return
     */
    public static Integer toInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        String str = (String) obj;
        return VerifyTools.isInteger(str) ? Integer.valueOf(str) : null;
    }
	
}
