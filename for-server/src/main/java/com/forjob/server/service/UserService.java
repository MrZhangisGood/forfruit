package com.forjob.server.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.forjob.server.BaseService;
import com.forjob.core.response.ResponseMsg;
import com.forjob.server.dao.UserDao;
import com.forjob.server.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forjob.core.interceptor.LoginInterceptor;
import com.forjob.core.util.MD5Helper;
import com.forjob.core.util.VerifyTools;

/**
 * @Description: 用户Service层
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 上午10:18:36   
 * @version 1.0
 */
@Service
public class UserService extends BaseService<UserEntity> {

    @Autowired
    protected UserDao userDao;

    /** 保存用户 - 重载
     * @author zhanglm@joyplus.com.cn
     * @see com.forjob.core.base.impl.BaseService#save(java.util.Map)
     */
    @Override
    public ResponseMsg save(Map<String, Object> map) throws Exception {
        if(VerifyTools.isEmpty(map.get("loginName"))){

        }
        if(VerifyTools.isEmpty(map.get("password"))){

        }
        String password = MD5Helper.MD5Encode(map.get("password").toString());
        map.put("password", password);
        return super.save(map);
    }

    /**
     * @Description: 根据账号密码进行登陆获取登录信息
     * @author zhanglm@joyplus.com.cn
     * @param loginName
     * @param password
     * @return 
     */
    public UserEntity getLoginUser(HttpSession session, String loginName, String password) {
        if(VerifyTools.isEmpty(loginName) && VerifyTools.isEmpty(password)){
            return null;
        }
        List<UserEntity> users = userDao.findByProperty(new String[]{"loginName", "password"}, new Object[]{loginName, MD5Helper.MD5Encode(password)});
        if(users != null && users.size() > 0){
            session.setAttribute(LoginInterceptor.SESSIN_USER_KEY, users.get(0));
            return users.get(0);
        }
        return null;
    }

    /**
     * @Description: 登陆
     * @author zhanglm@joyplus.com.cn
     * @param phone
     * @return 
     */
    public UserEntity getLoginByPhone(HttpSession session, String phone) {
        //登陆
        List<UserEntity> users = userDao.findByProperty(new String[]{"phone"}, new Object[]{phone});
        if(users != null && users.size() > 0){
            session.setAttribute(LoginInterceptor.SESSIN_USER_KEY, users.get(0));
            return users.get(0);
        }else{
            UserEntity user = new UserEntity();
            user.setPhone(phone);
            userDao.saveOrUpdate(user);
            session.setAttribute(LoginInterceptor.SESSIN_USER_KEY, user);
            return user;
        }
    }

}
