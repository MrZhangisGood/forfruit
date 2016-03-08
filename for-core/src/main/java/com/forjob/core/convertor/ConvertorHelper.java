package com.forjob.core.convertor;

import java.util.Date;

public class ConvertorHelper {
    
    
    public static Object convert(Object src, Class<?> destClass){
        //如果是字符串类型，不需要转换
        if( destClass == String.class ){
            if( src.getClass() == String.class){
                return src;
            }
            return src.toString();
        }
        
        IConvertor convertor = getConvertor(destClass);
        return convertor.convert(src, destClass);
    }
    
    /**
     * 
     * Description: 
     * <br/>获取转换对象
     * <p>@author guozj Joyplus</p>  
     * @param destClass
     * @return
     */
    private static IConvertor getConvertor(Class<?> destClass){
        if( destClass == Date.class ){
            return String2DateImpl.getInstance();
        }
        else if(destClass == Boolean.class || destClass == boolean.class){
            return Common2Boolean.getInstance();
        }
        else{
            return String2CommonImpl.getInstance();
        }
    }
}
