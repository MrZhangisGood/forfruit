/**   
* Copyright 中海信息  Co.Ltd
* @Title: FillMaterialTrigger.java 
* @Package com.eship.wms.trigger 
* @author zhanglm@joyplus.com.cn  
* @date 2015年2月3日 下午3:15:52 
* @version V1.0   
*/
package com.forjob.trigger.job;

import org.apache.log4j.Logger;

/**
 * @Description: 定时器
 * @author zhanglm@joyplus.com.cn 
 * @date 2016年4月15日 下午3:15:52
 * @version 1.0
 */
public class TestTrigger {

    protected static final Logger logger = Logger.getLogger(TestTrigger.class);

    /**
     * @Description: 开始方法
     * @author zhanglm@joyplus.com.cn
     */
    public void start(){
        try{
            logger.debug("[---]TestTrigger start");
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
        }
    }
}
