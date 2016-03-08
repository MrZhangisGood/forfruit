package com.forjob.core.convertor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.forjob.core.util.VerifyTools;

/**
 * 
 * <p>@Title: String2CommonImpl.java</p> 
 * <br/>Description: 
 * <br/>将在符串转换为其他类型的方法
 * <br/>Copyright 中海信息  Co.Ltd
 * <br/> date 2014年12月5日 下午1:46:27
 * @author guozj Joyplus     
 * @version 1.0
 */
public class String2CommonImpl implements IConvertor{
 
        
        private String2CommonImpl(){
            
        }
        
        private static String2CommonImpl instance;
        private static Lock lock = new ReentrantLock();// 锁对象  
        
        public static String2CommonImpl getInstance(){            
            if( instance == null){
                lock.lock();
                if( instance == null){
                    instance = new String2CommonImpl();
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
            //if ( String.class.isInstance(src) ) { // src必须是String
                //修复Integer转String问题 add by linyj on 2014/11/21
                String str = String.valueOf(src);
                if (destClass == Character.class || destClass == char.class) {
                    return Character.valueOf(str.charAt(0)); // 只取第一个字符
                }
                //若为空串则用"0"来进行各数字类型转换
                if(VerifyTools.isEmpty(str)){
                    str = "0";
                }
                if (destClass == Double.class || destClass == double.class) {
                    //对象类型若为空字符串则转换为null
                    return VerifyTools.isEmpty(str)?null:Double.valueOf(str);
                }

                if (destClass == Float.class || destClass == float.class) {
                    return Float.valueOf(str);
                }

                if (destClass == Boolean.class || destClass == boolean.class) {
                    return Boolean.valueOf(str);
                }

                if (destClass == Integer.class || destClass == int.class) {
                    //对象类型若为空字符串则转换为null
                    return VerifyTools.isEmpty(str)?null:Integer.valueOf(str);
                }

                if (destClass == Short.class || destClass == short.class) {
                    return Short.valueOf(str);
                }

                if (destClass == Long.class || destClass == long.class) {
                    return Long.valueOf(str);
                }

                if (destClass == Byte.class || destClass == byte.class) {
                    return Byte.valueOf(str);
                }

                if (destClass == BigDecimal.class) {
                    return new BigDecimal(str);
                }

                if (destClass == BigInteger.class) {
                    return new BigInteger(str);
                }
            //}

            throw new IllegalArgumentException("Unsupported convert: [" + src + "," + destClass.getName() + "]");
        }
   
}
