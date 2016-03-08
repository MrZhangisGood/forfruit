package com.forjob.server;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import com.forjob.core.enums.EBaseEntity;
import com.forjob.core.util.TypeConvert;
import com.forjob.core.util.VerifyTools;
import com.forjob.server.dao.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.forjob.core.entity.BaseEntity;

/**
 * @Description: 基础DAO
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月10日 下午9:23:18  
 * @version 1.0
 */
public class BaseDao<T extends BaseEntity>{

    protected static final Logger logger = Logger.getLogger(BaseDao.class);
    
    protected Class<T> objectClass = null;
    
    @Autowired
    public HibernateTemplate hibernateTemplate;

    /**
     * @Description: 获取泛型的字节码
     * @author zhanglm@joyplus.com.cn
     * @return
     */
    public Class<T> getObjectClass(){
        if(this.objectClass == null){
            this.objectClass =(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return this.objectClass;
    }

    /*************** 增加，修改操作 ***/
    public T saveOrUpdate(final T object) {
        this.hibernateTemplate.saveOrUpdate(object);
        return object;
    }
    public void saveOrUpdateAll(List<T> list){
        this.hibernateTemplate.saveOrUpdateAll(list);
    }

    /*************** 删除操作 ***/
    public boolean deleteById(String id) {
        try {
            T object = this.hibernateTemplate.get(this.getObjectClass(), id);
            object.setIsDelete(EBaseEntity.DELETE.ordinal());
            this.hibernateTemplate.saveOrUpdate(object);
            return true;
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }
    public boolean deleteAll(final List<String> uuids) {
        if (uuids == null || uuids.size() == 0) {
            return false;
        }
        final StringBuffer hql = new StringBuffer();
        hql.append(" update ");
        hql.append(this.getObjectClass().getName());
        hql.append(" set isDelete = ? ");
        try {
            hql.append(" where ").append(((BaseEntity) getObjectClass().newInstance()).loadPkName()).append(" in (:uuids)");
        } catch (InstantiationException e) {
            logger.error(e);
        } catch (IllegalAccessException e) {
            logger.error(e);
        }
        Object updateRow = this.hibernateTemplate.execute(new HibernateCallback<Object>() {
            public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql.toString());
                query.setParameter(0, EBaseEntity.DELETE.ordinal());
                query.setParameterList("uuids", uuids);
                return query.executeUpdate();
            }
        });
        return TypeConvert.toInt(updateRow) > 0;
    }

    /*************** 查询操作 ***/
    public T findById(String id) {
        T object = this.hibernateTemplate.get(this.getObjectClass(), id);
        return object;
    }
    public List<T> findByProperty(String[] propertyName , Object[] value){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.getObjectClass());
        for (int i = 0; i < value.length; i++) {
            if(!VerifyTools.isEmpty(value[i])){
                detachedCriteria.add(Restrictions.eq(propertyName[i], value[i]));
            }
        }
        detachedCriteria.add(Restrictions.eq("isDelete", EBaseEntity.USE.ordinal()));
        return this.hibernateTemplate.findByCriteria(detachedCriteria);
    }
    public List<T> findAll() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.getObjectClass());
        detachedCriteria.add(Restrictions.eq("isDelete", EBaseEntity.USE.ordinal()));
        return this.hibernateTemplate.findByCriteria(detachedCriteria);
    }

    /**
     * Main 测试获取class
     * @param args
     */
    public static void main(String[] args) {
        UserDao gt0 = new UserDao();
        System.out.println("entityClass:" + gt0.getObjectClass());
    }
}
