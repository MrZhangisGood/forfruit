package com.forjob.core.aop;

import com.forjob.core.util.JsonUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/** 
 * @Description: 日志记录的AOP
 * @author zhanglm@joyplus.com.cn 
 * @date 2016年4月20日 下午6:31:08
 * @version 1.0
 */
@Component
@Aspect
public class LoggerAop {

    protected static final Logger logger = Logger.getLogger(LoggerAop.class);

    //@Pointcut("execution(public * com.forjob.server.*Controller.*(..))")
    @Pointcut("execution( * com..*Controller.*(..))")
    public void insert() {
    }


    /**
     * 注意：
     * 1.User开头的类都不进行拦截
     * @author zhanglm@joyplus.com.cn
     */
    @Around("insert()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //TODO 通过RabbitMQ 进行记录日志
        logger.info(pjp.toString());
        Object[] args = pjp.getArgs();
        for (Object arg : args){
            if(arg instanceof HttpServletRequest){
                logger.info("arg : HttpServletRequest");
            }else{
                logger.info("arg : " + JsonUtil.object2Json(arg));
            }
        }
        Object object = null;
        try{
            object = pjp.proceed();
        }catch (Throwable e){
            //处理e
            throw e;
        }
        return object;
    }

    /*
    @Before("insert()")
    public void doBefore(JoinPoint jp) {
        System.out.println("Before");
    }

    @After("insert()")
    public void doAfter(JoinPoint jp) {
        System.out.println("After");
    }

    @AfterReturning("insert()")
    public void doAfterReturn(JoinPoint jp) {
        System.out.println("AfterReturn");
    }

    @AfterThrowing("insert()")
    public void doAfterThrowing() {
        System.out.println("AfterThrowing");
    }
    */

}
