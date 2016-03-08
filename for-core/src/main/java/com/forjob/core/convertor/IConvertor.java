package com.forjob.core.convertor;

/**
 * 
 * <p>@Title: IConvertor.java</p> 
 * <br/>Description: 
 * <br/>自定义的convertor接口
 * <br/>Copyright 中海信息  Co.Ltd
 * <br/> date 2014年11月18日 下午12:52:52
 * @author guozj Joyplus     
 * @version 1.0
 */
public interface IConvertor {
    public Object convert(Object src, Class<?> destClass);
    
}
