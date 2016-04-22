package com.forjob.server;

import com.forjob.core.entity.BaseEntity;
import com.forjob.core.exception.ResponseException;
import com.forjob.core.response.ResponseMsg;
import com.forjob.core.util.JsonUtil;
import com.forjob.core.util.SpringHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.*;


public abstract class BaseController<T extends BaseEntity> {

    protected static final Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    protected SpringHelper springHelper;

    /**
     * @Description: 在调用方法前 进行初始化数据
     * @author zhanglm@joyplus.com.cn
     * @return
     */
    //@ModelAttribute
    //public void before(){
    //}

    private BaseService<T> baseService;

    public BaseService<T> getBaseService(){
        if(baseService == null){
            Class<T> clazz = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            String beanName = clazz.getName().replace("Entity", "Service");
            int index = beanName.lastIndexOf(".")+1;
            beanName = beanName.substring(index, index+1).toLowerCase()+beanName.substring(index+1, beanName.length());
            baseService = (BaseService)springHelper.getBean(beanName);
        }
        return baseService;
    }

    /**
     * @Description: 用于处理异常的
     * @author zhanglm@joyplus.com.cn
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public String exception(Exception e) {
        ResponseMsg response = null;
        if(e instanceof ResponseException){
            ResponseException res = (ResponseException) e;
            response = new ResponseMsg(false, res.getErrorEnum().getCode(), res.getErrorEnum().getMsg(), null);
            logger.info(res.getErrorEnum().getMsg());
        }else{
            response = new ResponseMsg(false, null, e.getMessage(), null);
            logger.error(e);
        }
        return JsonUtil.object2Json(response);
    }

    /**
     * ************* 保存操作 ***
     * @author zhanglm@joyplus.com.cn
     *
     *
     *
     */
    @RequestMapping("save")
    @ResponseBody
    public String save(String objJson) throws Exception{
        Object result = null;
        Map<String, Object> map = (HashMap<String, Object>) JsonUtil.json2Object(objJson, HashMap.class);
        if(map == null){
            //TODO 验证
        }else{
            result = this.getBaseService().save(map);
        }
        return JsonUtil.object2Json(result);
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
    @RequestMapping("deleteById")
    @ResponseBody
    public String deleteById(String id) {
        Object result = this.getBaseService().deleteById(id);
        return JsonUtil.object2Json(result);
    }

    @RequestMapping("deleteAll")
    @ResponseBody
    public String deleteByIds(String uuidsJson) {
        List<String> uuids = (ArrayList<String>)JsonUtil.json2Object(uuidsJson, ArrayList.class);
        Object result = this.getBaseService().deleteAll(uuids);
        return JsonUtil.object2Json(result);
    }

    /**
     * ************* 查询操作 **
     * @author zhanglm@joyplus.com.cn
     *
     *
     *
     *
     *
     */
    @RequestMapping("findAll")
    @ResponseBody
    public String findAll() {
        List<T> list = this.getBaseService().findAll();
        return JsonUtil.object2Json(list);
    }
    @RequestMapping("findById")
    @ResponseBody
    public String findById(String id) {
        T obj = this.getBaseService().findById(id);
        return JsonUtil.object2Json(obj);
    }
    @RequestMapping("findByField")
    @ResponseBody
    public String findByField(HttpServletRequest request) {
        Enumeration<String> paraNames=request.getParameterNames();

        List<Object> fieldNames = new ArrayList<Object>();
        List<Object> values = new ArrayList<Object>();
        for(Enumeration e = paraNames; e.hasMoreElements();){
            String thisName = e.nextElement().toString();
            String thisValue = request.getParameter(thisName);
            fieldNames.add(thisName);
            values.add(thisValue);
        }
        //TODO TEST
        List<T> list = null;
        //List<T> list = this.getBaseService().findByField(fieldNames.toArray(), values.toArray());
        return JsonUtil.object2Json(list);
    }

}
