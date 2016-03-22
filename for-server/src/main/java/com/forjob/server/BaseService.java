package com.forjob.server;

import com.forjob.core.entity.BaseEntity;
import com.forjob.core.enums.EErrorEnum;
import com.forjob.core.exception.ResponseException;
import com.forjob.core.response.ResponseMsg;
import com.forjob.core.util.MapUtil;
import com.forjob.core.util.SpringHelper;
import com.forjob.core.util.TypeConvert;
import com.forjob.core.util.VerifyTools;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @Description: 基础的Service层
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 下午2:07:02  
 * @param <T> 
 * @version 1.0
 */
public class BaseService<T extends BaseEntity> {

    @Autowired
    protected SpringHelper springHelper;
    
    private BaseDao<T> baseDao;

    public BaseDao<T> getBaseDao() {
        if(baseDao == null){
            Class<T> clazz = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            String beanName = clazz.getName().replace("Entity", "Dao");
            int index = beanName.lastIndexOf(".")+1;
            beanName = beanName.substring(index, index+1).toLowerCase()+beanName.substring(index+1, beanName.length());
            baseDao = (BaseDao)springHelper.getBean(beanName);
        }
        return baseDao;
    }

    /**
     * ************* 增加，修改操作  **
     * @author zhanglm@joyplus.com.cn
     *
     *
     *
     *
     *
     *
     */
    public ResponseMsg save(Map<String, Object> map) throws Exception {
        String uuid = TypeConvert.toStr(map.get("uuid"));
        T obj = null;
        if(!VerifyTools.isEmpty(uuid)){//修改
            obj = this.getBaseDao().findById(uuid);
            MapUtil.map2Bean(map, obj);
            if(this.getBaseDao().saveOrUpdate(obj) == null){
                throw new ResponseException(EErrorEnum.COMMON_SAVE_FAIL);
            }
        }else{//新增
            obj = this.getBaseDao().getObjectClass().newInstance();
            MapUtil.map2Bean(map, obj);
            if(this.getBaseDao().saveOrUpdate(obj) == null){
                throw new ResponseException(EErrorEnum.COMMON_SAVE_FAIL);
            }
        }
        //TODO 测试事务
//        Integer i = 1;
//        if(i == 1){
//            throw new ResponseException(EErrorEnum.COMMON_SAVE_FAIL);
//        }
        return new ResponseMsg().success("保存成功", obj);
    }

    /**
     * ************* 删除操作 **
     * @author zhanglm@joyplus.com.cn
     *
     *
     *
     *
     *
     */
    public ResponseMsg deleteById(String id) {
        if(this.getBaseDao().deleteById(id)) {
            return new ResponseMsg().success("删除成功", null);
        }
        throw new ResponseException(EErrorEnum.COMMON_DELETE_FAIL);
    }
    public ResponseMsg deleteAll(List<String> uuids) {
        if(this.getBaseDao().deleteAll(uuids)) {
            return new ResponseMsg().success("删除成功", null);
        }
        throw new ResponseException(EErrorEnum.COMMON_DELETE_FAIL);
    }

    /**
     * ************* 查询操作 **
     * @author zhanglm@joyplus.com.cn
     *
     *
     *
     *
     */
    public List<T> findAll() {
        return this.getBaseDao().findAll();
    }
    public T findById(String id) {
        return this.getBaseDao().findById(id);
    }
    public List<T> findByField(Object[] fieldNames, Object[] values) {
        return this.getBaseDao().findByField(fieldNames, values);
    }
}
