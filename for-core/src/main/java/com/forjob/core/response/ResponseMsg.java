package com.forjob.core.response;

/**
 * @Description: 返回数据的消息类
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 下午10:09:05   
 * @version 1.0
 */
public class ResponseMsg {

    /** 结果 true成功 false失败 */
    private boolean result;
    
    /** 消息码 */
    private Integer code;
    
    /** 消息内容 */
    private Object msg;
    
    /** 数据内容 */
    private Object data;

    public ResponseMsg() {
        this.result = true;
        this.code = 0;
        this.msg = "";
        this.data = "";
    }
    
    public ResponseMsg(boolean result, Integer code, Object msg, Object data) {
        this.result = result;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
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
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @Description:返回失败
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    public ResponseMsg fail(String msg, Object data) {
        this.result = false;
        this.msg = msg;
        return this;
    }
    
    /**
     * @Description:返回成功
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    public ResponseMsg success(String msg, Object data) {
        this.result = true;
        this.data = data;
        return this;
    }
    
}
