package ${basepackage}.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eship.core.beans.PageParam;

import ${basepackage}.dao.${className}DAO;

/**
 * <br/> 
 * <br/> date ${createDate}
 * @author zhanglm@joyplus.com.cn Joyplus     
 * @version 1.0
 */
@Service
public class ${className}Service {

    static final Logger logger = Logger.getLogger(${className}Service.class);

    @Resource
    ${className}DAO ${varName}DAO;

    /**
     * Description: 搜索
     * <p>@author zhanglm@joyplus.com.cn Joyplus</p>  
     * @param model
     * @return
     */
    public Map<String, Object> search(PageParam param) {
        return ${varName}DAO.search(param);
    }
    
}

