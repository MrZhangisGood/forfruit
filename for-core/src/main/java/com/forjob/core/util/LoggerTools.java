package com.forjob.core.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 * @Description: 打印错误
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 上午11:16:52   
 * @version 1.0
 */
public class LoggerTools {

    private static final Logger logger = Logger.getLogger(LoggerTools.class);
    
    /**
     * @Description: 打印错误日志 
     * @author zhanglm@joyplus.com.cn
     * @param logger
     * @param e
     */
    public static void print(Logger logger, Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        logger.error(sw);
        try {
            pw.close();
            sw.close();
        } catch (IOException ex) {
            LoggerTools.logger.error(ex);
        }
    }
    
}
