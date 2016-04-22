package com.forjob.trigger.factory;


import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 * 解决集群Quartz时，在根据class生成对象时，把Spring容器中对应的对象中的参数注入到生成的对象中
 * @author zhanglm@joyplus.com.cn
 * @date 2016年4月18日 下午3:15:52
 * @version 1.0
 */
public class MyJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}


