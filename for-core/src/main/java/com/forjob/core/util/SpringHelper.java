package com.forjob.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringHelper implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public Object getBean(Class clazz) {
        return applicationContext.getBean(clazz);
    }

    public <T> T getBean(String beanName, Class<T> clazs) {
        return clazs.cast(getBean(beanName));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}