package com.forjob.trigger.quartz;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * @author zhanglm@joyplus.com.cn
 * @date 2016年4月15日 下午3:15:52
 * @version 1.0
 */
public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        // Remove the JobDetail element
        //getJobDataMap().remove(JobDetailAwareTrigger.JOB_DETAIL_KEY);
    }
}
