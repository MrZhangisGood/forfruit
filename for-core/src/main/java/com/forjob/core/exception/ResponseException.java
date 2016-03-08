/**   
* Copyright 中海信息  Co.Ltd
* @Title: BaseException.java 
* @Package com.forjob.core.exception 
* @author zhanglm@joyplus.com.cn  
* @date 2015年3月12日 下午9:51:38 
* @version V1.0   
*/
package com.forjob.core.exception;

import com.forjob.core.enums.EErrorEnum;

/** 
 * @Description: 错误类
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 下午9:51:38   
 * @version 1.0
 */
public class ResponseException extends RuntimeException {

    private static final long serialVersionUID = 4297479212026237120L;
    
    private EErrorEnum errorEnum;
    
    public ResponseException(EErrorEnum errorEnum){
        this.errorEnum = errorEnum;
    }

    public EErrorEnum getErrorEnum() {
        return errorEnum;
    }
    public void setErrorEnum(EErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

}
