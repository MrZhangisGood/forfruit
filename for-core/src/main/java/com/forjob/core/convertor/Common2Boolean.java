package com.forjob.core.convertor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <p>@Title: Common2Boolean.java</p> 
 * <br/>Description: 
 * <br/>常用类型转换为boolean类型
 * <br/>Copyright 中海信息  Co.Ltd
 * <br/> date 2014年12月5日 上午10:24:55
 * @author guozj Joyplus     
 * @version 1.0
 */
public class Common2Boolean implements IConvertor{
    
    private Common2Boolean(){
        
    }
    
    private static Common2Boolean instance;
    private static Lock lock = new ReentrantLock();// 锁对象  
    
    /**
     * 
     * Description: 
     * <br/>获取该类实例
     * <p>@author guozj Joyplus</p>  
     * @return
     */
    public static Common2Boolean getInstance(){            
        if( instance == null){
            lock.lock();
            if( instance == null){
                instance = new Common2Boolean();
            }
            lock.unlock();
        }
        return instance;
    }

    /**
     * <= 0 返回false , > 0 返回true
     * 空字符串或空返回false
     * <p>@author guozj Joyplus</p>  
     * @see com.forjob.core.convertor.eship.core.convertor.IConvertor#convert(java.lang.Object, java.lang.Class)
     */
    @Override
    public Object convert(Object src, Class<?> destClass) {
        if( src == null ){
            return false;//如果src == null ，返回false;
        }
        
        //如果
        if( Integer.class == src.getClass() || int.class == src.getClass() ){
            Integer i = (Integer) src ;
            if( i <= 0 ){
                return false;
            }
            else{
                return true;
            }
        }
        else if(  String.class.isInstance(src) ){ 
            String srcStr = src.toString().toLowerCase();
            if( srcStr.equals("true") || srcStr.equals("t") || srcStr.equals("1") ){
                return true;
            }
            
            if( srcStr.equals("false") || srcStr.equals("f") || srcStr.equals("0") || srcStr.equals("") ){
                return false;
            }
            
            try{
                Integer i = Integer.parseInt(src.toString());
                if( i <= 0 ){
                    return false;
                }
                else{
                    return true;
                }
            }   
            catch(java.lang.NumberFormatException ex){
                return false;//如果出现异常返回false;
            }
        }
        return false;
    }

}
