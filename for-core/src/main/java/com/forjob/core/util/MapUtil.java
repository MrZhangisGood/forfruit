package com.forjob.core.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

import org.apache.log4j.Logger;

import com.forjob.core.convertor.ConvertorHelper;

/**
 * @Description: map 复制到对象中
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 下午6:19:17   
 * @version 1.0
 */
public class MapUtil {
    
    static final Logger logger = Logger.getLogger(MapUtil.class);


    /**
     * Description: 
     * 将Map中的值转换为Bean对象(通过map中的key对应bean对象中的属性) 
     * @param map 键值对，会根据键值到obj中匹配
     * @param obj 需要转换的对象
     * @return
     */
    public static Object map2Bean(Map<?, ?> map, Object obj) {
        if(map == null){
            return null;
        }
        
        BeanMap beanMap = BeanUtils.getBeanMap(obj);
        
        Iterator<?> iter = map.keySet().iterator();
        Map<String , Object> valueMap = new HashMap<String , Object>();
        String key = null;
        Class<?> cla;
        while(iter.hasNext()){
            key = iter.next().toString();
            if(beanMap.containsKey(key)){
               cla = beanMap.getPropertyType(key);
               if(map.get(key) != null){
                   valueMap.put(key, ConvertorHelper.convert(map.get(key) , cla));
               }
            }
        }
        beanMap.putAll(valueMap);
        return obj;
    }

}  

