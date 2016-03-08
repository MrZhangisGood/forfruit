package ${basepackage}.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.csis.core.DataModel;
import com.csis.core.annotation.DataAction;
import com.eship.core.beans.PageParam;
import com.eship.core.utils.JsonUtils;
import com.eship.core.utils.MapUtil;

import ${basepackage}.service.${className}Service;

/**
 * <br/> 
 * <br/>Copyright 中海信息  Co.Ltd
 * <br/> date ${createDate}
 * @author zhanglm@joyplus.com.cn Joyplus     
 * @version 1.0
 */
@Controller
public class ${className}Controller {
    
    @Autowired
    ${className}Service ${varName}Service;
    
    /**
     * Description: 搜索
     * <p>@author zhanglm@joyplus.com.cn Joyplus</p>  
     * @param model
     * @return
     */
    @DataAction("search")
    public String search(DataModel model){
        PageParam param = new PageParam();
        MapUtil.fullBean(param, model);
        Map<String,Object> resultMap = ${varName}Service.search(param);
        return JsonUtils.object2Json(resultMap);
    }
}
