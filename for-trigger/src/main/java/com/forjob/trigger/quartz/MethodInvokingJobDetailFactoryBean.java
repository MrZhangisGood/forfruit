package com.forjob.trigger.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.*;
import org.springframework.beans.support.ArgumentConvertingMethodInvoker;
import org.springframework.scheduling.quartz.JobMethodInvocationFailedException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.MethodInvoker;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvokingJobDetailFactoryBean extends ArgumentConvertingMethodInvoker implements FactoryBean<JobDetail>, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, InitializingBean,Serializable {
    private static Class<?> jobDetailImplClass;
    private static Method setResultMethod;
    private String name;
    private String group = "DEFAULT";
    private boolean concurrent = true;
    private String targetBeanName;
    private String[] jobListenerNames;
    private String beanName;
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
    private BeanFactory beanFactory;
    private JobDetail jobDetail;

    public MethodInvokingJobDetailFactoryBean() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setConcurrent(boolean concurrent) {
        this.concurrent = concurrent;
    }

    public void setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

    public void setJobListenerNames(String... names) {
        this.jobListenerNames = names;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected Class<?> resolveClassName(String className) throws ClassNotFoundException {
        return ClassUtils.forName(className, this.beanClassLoader);
    }

    public void afterPropertiesSet() throws ClassNotFoundException, NoSuchMethodException {
        this.prepare();
        String name = this.name != null?this.name:this.beanName;
        Class jobClass = this.concurrent?MethodInvokingJobDetailFactoryBean.MethodInvokingJob.class:MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob.class;
        if(jobDetailImplClass != null) {
            this.jobDetail = (JobDetail) BeanUtils.instantiate(jobDetailImplClass);
            BeanWrapper arr$ = PropertyAccessorFactory.forBeanPropertyAccess(this.jobDetail);
            arr$.setPropertyValue("name", name);
            arr$.setPropertyValue("group", this.group);
            arr$.setPropertyValue("jobClass", jobClass);
            arr$.setPropertyValue("durability", Boolean.valueOf(true));
            ((JobDataMap)arr$.getPropertyValue("jobDataMap")).put("methodInvoker", this);
        } else {
            this.jobDetail = new JobDetail(name, this.group, jobClass);
            this.jobDetail.setVolatility(true);
            this.jobDetail.setDurability(true);
            this.jobDetail.getJobDataMap().put("methodInvoker", this);
        }

        if(this.jobListenerNames != null) {
            String[] var7 = this.jobListenerNames;
            int len$ = var7.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String jobListenerName = var7[i$];
                if(jobDetailImplClass != null) {
                    throw new IllegalStateException("Non-global JobListeners not supported on Quartz 2 - manually register a Matcher against the Quartz ListenerManager instead");
                }

                this.jobDetail.addJobListener(jobListenerName);
            }
        }

        this.postProcessJobDetail(this.jobDetail);
    }

    protected void postProcessJobDetail(JobDetail jobDetail) {
    }

    public Class<?> getTargetClass() {
        Class targetClass = super.getTargetClass();
        if(targetClass == null && this.targetBeanName != null) {
            Assert.state(this.beanFactory != null, "BeanFactory must be set when using \'targetBeanName\'");
            targetClass = this.beanFactory.getType(this.targetBeanName);
        }

        return targetClass;
    }

    public Object getTargetObject() {
        Object targetObject = super.getTargetObject();
        if(targetObject == null && this.targetBeanName != null) {
            Assert.state(this.beanFactory != null, "BeanFactory must be set when using \'targetBeanName\'");
            targetObject = this.beanFactory.getBean(this.targetBeanName);
        }

        return targetObject;
    }

    public JobDetail getObject() {
        return this.jobDetail;
    }

    public Class<? extends JobDetail> getObjectType() {
        return this.jobDetail != null?this.jobDetail.getClass():JobDetail.class;
    }

    public boolean isSingleton() {
        return true;
    }

    static {
        try {
            jobDetailImplClass = Class.forName("org.quartz.impl.JobDetailImpl");
        } catch (ClassNotFoundException var2) {
            jobDetailImplClass = null;
        }

        try {
            Class ex = QuartzJobBean.class.getClassLoader().loadClass("org.quartz.JobExecutionContext");
            setResultMethod = ex.getMethod("setResult", new Class[]{Object.class});
        } catch (Exception var1) {
            throw new IllegalStateException("Incompatible Quartz API: " + var1);
        }
    }

    public static class StatefulMethodInvokingJob extends MethodInvokingJobDetailFactoryBean.MethodInvokingJob implements StatefulJob {
        public StatefulMethodInvokingJob() {
        }
    }

    public static class MethodInvokingJob extends QuartzJobBean {
        protected static final Log logger = LogFactory.getLog(MethodInvokingJobDetailFactoryBean.MethodInvokingJob.class);
        private MethodInvoker methodInvoker;

        public MethodInvokingJob() {
        }

        public void setMethodInvoker(MethodInvoker methodInvoker) {
            this.methodInvoker = methodInvoker;
        }

        protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
            try {
                ReflectionUtils.invokeMethod(MethodInvokingJobDetailFactoryBean.setResultMethod, context, new Object[]{this.methodInvoker.invoke()});
            } catch (InvocationTargetException var3) {
                if(var3.getTargetException() instanceof JobExecutionException) {
                    throw (JobExecutionException)var3.getTargetException();
                } else {
                    throw new JobMethodInvocationFailedException(this.methodInvoker, var3.getTargetException());
                }
            } catch (Exception var4) {
                throw new JobMethodInvocationFailedException(this.methodInvoker, var4);
            }
        }
    }
}
