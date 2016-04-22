package com.forjob.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <br/>日志注解
 * <br/> date 2016/04/20
 * @author zhanglm@joyplus.com.cn
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logger {
    public abstract boolean isOffice() default true;
}
