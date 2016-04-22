package com.forjob.core.interceptor;

import com.forjob.core.enums.EErrorEnum;
import com.forjob.core.response.ResponseMsg;
import com.forjob.core.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @Description: 登录的拦截器
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月14日 下午6:31:08   
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    protected static final Logger logger = Logger.getLogger(LoginInterceptor.class);
    
    public static final String SESSIN_USER_KEY = "SESSIN_USER_KEY";
    
    private Set<String> uncheckUrls;
    
    private static List<Pattern> patterns = new ArrayList<Pattern>();
    
    public void init(){
        if(uncheckUrls != null){
            for (String uncheckUrl : uncheckUrls) {
                patterns.add(Pattern.compile(uncheckUrl));
            }
        }
    }
    
    public void setUncheckUrls(Set<String> uncheckUrls) {
        this.uncheckUrls = uncheckUrls;
    }

    /**
     * Controller前
     * @author zhanglm@joyplus.com.cn
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  Object arg) throws Exception {
        //TODO TEST
        if (1==1){
            return true;
        }

        // 无需拦截 配置 的URI
        for (int i = 0; i < patterns.size(); i++) {
            Matcher matcher = patterns.get(i).matcher(request.getRequestURI());
            if (matcher.find()) {
                return true;
            }
        }
        // 是否登陆拦截
        Object user = request.getSession(true).getAttribute(SESSIN_USER_KEY);
        if(user == null){
            ResponseMsg responseMsg = new ResponseMsg(false, EErrorEnum.COMMON_UNLOGIN.getCode(), EErrorEnum.COMMON_UNLOGIN.getMsg(), null);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JsonUtil.object2Json(responseMsg));
            return false;
        }
        return true ;
    }

    /**
     * Controller后
     * @author zhanglm@joyplus.com.cn
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg, ModelAndView view) throws Exception {
    }
    
    /**
     * 生成View后
     * @author zhanglm@joyplus.com.cn
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg, Exception e) throws Exception {
    }

}
