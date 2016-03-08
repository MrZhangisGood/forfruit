package com.forjob.core.convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <p>@Title: String2DateImpl.java</p> 
 * <br/>Description: 
 * <br/>字符串转换为日期类型
 * <br/>Copyright 中海信息  Co.Ltd
 * <br/> date 2014年11月18日 下午1:02:16
 * @author guozj Joyplus     
 * @version 1.0
 */
public class String2DateImpl implements IConvertor{
    
    private static String2DateImpl instance;
    private static Lock lock = new ReentrantLock();// 锁对象
    
    public static final String DAY_FORMAT_CN  = "yyyy-MM-dd";
    public static final String DAY_FORMAT_EN  = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
     
    
    private String2DateImpl(){
        
    }
    
    /**
     * 
     * Description: 
     * <br/>获取String2Date对象实例
     * <p>@author guozj Joyplus</p>  
     * @return
     */
    public static String2DateImpl getInstance(){            
        if( instance == null){
            lock.lock();
            if( instance == null){
                instance = new String2DateImpl();
            }
            lock.unlock();
        }
        return instance;
    }

    /**
     * (non-Javadoc)
     * <p>@author guozj Joyplus</p>  
     * @see com.eship.core.convertor.IConvertor#convert(java.lang.Object, java.lang.Class)
     */
    @Override
    public Object convert(Object src, Class<?> destClass) {
        if( src == null || src.toString().trim().equals("")){
            return null;
        }
        
        if (String.class.isInstance(src)) { // 必须是字符串
            
            try {
                return new SimpleDateFormat(DAY_FORMAT_CN).parse((String) src);
            } catch (ParseException e) {
                throw new IllegalArgumentException("convert failed: [" + src + "," + destClass.getName() + "]", e);
            }
        }

        throw new IllegalArgumentException("Unsupported convert: [" + src + "," + destClass.getName() + "]");
    }

}
