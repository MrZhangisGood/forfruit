/**   
* Copyright 中海信息  Co.Ltd
* @Title: FillMaterialTrigger.java 
* @Package com.eship.wms.trigger 
* @author zhanglm@joyplus.com.cn  
* @date 2015年2月3日 下午3:15:52 
* @version V1.0   
*/
package com.forjob.trigger.quartz;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * @Description: Because of Spring class CronTriggerFactoryBean that stores a reference to the JobDetail in the JobDataMap, which cannot be represented as a set of properties.
 * @author zhanglm@joyplus.com.cn
 * @version 1.0
 */
public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();

        //Remove the JobDetail element
        //getJobDataMap().remove(JobDetailAwareTrigger.JOB_DETAIL_KEY);
    }
}
