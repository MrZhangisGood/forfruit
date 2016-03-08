/**   
* Copyright 中海信息  Co.Ltd
* @Title: IEnum.java 
* @Package com.eship.basedata.common 
* @author guozj@joyplus.com.cn  
* @date 2013年8月22日 上午9:00:43 
* @version V1.0   
*/
package com.forjob.core.enums;

/** 
 * @Description: 错误枚举信息
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 下午9:58:43   
 * @version 1.0
 */
public enum EErrorEnum{

    /** 公用 - 保存失败 */
    COMMON_SAVE_FAIL(1001, "保存失败"),
    /** 公用 - 删除失败 */
    COMMON_DELETE_FAIL(1002, "删除失败"),
    /** 公用 - 请登陆完继续操作 */
    COMMON_UNLOGIN(1003, "请求数据失败, 请进行登陆!");
    
    /** 消息码 */
    private Integer code;
    
    /** 消息内容 */
    private Object msg;

    private EErrorEnum(Integer code, Object msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Object getMsg() {
        return msg;
    }
    public void setMsg(Object msg) {
        this.msg = msg;
    }
    
}
