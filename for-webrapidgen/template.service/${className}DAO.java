package ${basepackage}.dao;

import org.springframework.stereotype.Repository;

import com.eship.core.dao.HibernateBaseDAO;
import ${basepackage}.entity.${className}Entity;

/**
 * <br/> 
 * <br/> date ${createDate}
 * @author zhanglm@joyplus.com.cn Joyplus     
 * @version 1.0
 */
@Repository
public class ${className}DAO extends HibernateBaseDAO<${className}Entity, String> {

    public ${className}DAO() {
        super(${className}Entity.class);
    }

}
