package com.forjob.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;

import org.apache.log4j.Logger;

/**
 * Bean 工具包 包括设
 * @author xupengspu@cnshipping.com.
 * @date 2014/11/3
 */
public class BeanUtils {
    
    static final Logger logger = Logger.getLogger(BeanUtils.class);
    
    private static final ConcurrentHashMap<String, BeanMap> BEAN_MAP = new ConcurrentHashMap<String, BeanMap>();
    
    public static final List<String> DEFAULT_FILTER_FIELDS = Arrays.asList("uuid", "createDate", "updateDate", "createBy", "updateBy", "owner", "isDelete", "officeCode");
    /**
     * 
     * Description: 
     * <br/>获取Cglib BeanMap对象 
     * <p>@author guozj Joyplus</p>  
     * @param obj
     * @return
     */
    public static BeanMap getBeanMap(Object obj){
        if( BEAN_MAP.contains(obj.getClass().getName()) ){
            BeanMap map = BEAN_MAP.get(obj.getClass().getName());
            map.setBean(obj);
            return map;
        }
        else{
            BeanMap map = BeanMap.create(obj);
            BEAN_MAP.put( obj.getClass().getName() , map);
            return map;
        }
    }
    
    /**
     * Description: 
     * <br/>处理两个对象之间的Copy,对Null进行Copy，两个对象之间不需要是同一个类的实体，
     * <br/>可以是不同类，根据类中属性进行替换，但是要求数据类型要一致
     * <p>@author guozj Joyplus</p>  
     * @param source 
     * @param target 需要被替换的对象
     */
    public static void beanCopy(Object source , Object target){ 
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false); 
        copier.copy(source, target , null); 
    }
    
    /**
     * Description: 
     * <br/>copy对象，忽略DEFAULT_FILTER_FIELDS中定义的常用字段，以及filterFields中传递的特殊字段
     * <p>@author guozj Joyplus</p>  
     * @param source
     * @param target
     * @param filterFields 需要忽略的字段
     */
    public static void beanCopy(Object source , Object target , List<String>filterFields){
        List<String> list = new ArrayList<String>();
        list.addAll(DEFAULT_FILTER_FIELDS);
        if(filterFields != null){
            list.addAll(filterFields);
        }
        beanCopy(source , target , false , list);
    }
    
    /**
     * Description: 
     * <br/>处理两个对象之间的Copy,两个对象之间不需要是同一个类的实体，
     * <p>@author guozj Joyplus</p>  
     * @param source
     * @param target
     * @param copyNull true表示copyNull ， false表示不Copy null值
     * @param filterFields 该List中的字段会被忽略掉，不进行处理
     */
    public static void beanCopy(Object source , Object target , boolean copyNull , List<String> filterFields){
        if( copyNull && (filterFields == null || filterFields.size() == 0 ) ){
            beanCopy(source , target);
        }
        else{
            BeanMap beanMap = getBeanMap(source);
            @SuppressWarnings("unchecked")
            Iterator<String> iterator = beanMap.keySet().iterator();
            HashMap<String , Object> map = new HashMap<String , Object>();
            String key = null;
            Object value = null;
            
            /**
             * 循环处理，剔除为空的数据
             */
            while(iterator.hasNext()){
                key = iterator.next();
                value = beanMap.get(key);
                putToMap(map , copyNull , key , value , filterFields);
            }
            
            BeanMap beanMapTarget = getBeanMap(target);
            beanMapTarget.putAll(map);
        }
    }
    
    
    /**
     * Description: 
     * <br/>对两个对象之间互相Copy
     * <p>@author guozj Joyplus</p>  
     * @param source 源对象
     * @param target 目标对象
     * @param copyNull 如果为true，将默认copy null值，否则null忽略
     */
    public static void beanCopy(Object source , Object target , boolean copyNull){
        beanCopy(source , target , copyNull , null);
    }
    
    private static void putToMap(HashMap<String , Object> map , boolean copyNull ,String key , Object value , List<String> filterFields){
        if(value == null){
            if(copyNull){
                if( filterFields == null || !filterFields.contains(key) ){
                    map.put(key, null);
                    return;
                }
            }
            return;
        }else{
            if(filterFields == null || !filterFields.contains(key)){
                map.put(key, value);
            }
        }
    }
}
