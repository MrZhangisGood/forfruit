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
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Description: 定时器
 * @author zhanglm@joyplus.com.cn 
 * @date 2016年4月15日 下午3:15:52
 * @version 1.0
 */
public class TestJob extends QuartzJobBean {

    protected static final Logger logger = Logger.getLogger(TestJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            logger.debug("[---]TestJob start");
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
        }
    }
}
