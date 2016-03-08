package com.forjob.view.controller;

import java.text.DecimalFormat;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forjob.core.response.ResponseMsg;
import com.forjob.core.sms.SendSMSTool;
import com.forjob.core.util.JsonUtil;
import com.forjob.core.util.TypeConvert;
import com.forjob.core.util.VerifyTools;

@Controller
@RequestMapping("sms")
public class SmsController {

    protected static final Logger logger = Logger.getLogger(SmsController.class);
    
    protected static final DecimalFormat dFormat = new DecimalFormat("000000");
    //超时时间
    protected static final int invalidMin = 3;
    
    @RequestMapping("send.pass")
    @ResponseBody
    public String send(HttpSession session, String phone){
        ResponseMsg msg = new ResponseMsg();
        
        //验证参数
        if(VerifyTools.isEmpty(phone)){
            msg.fail("请填写手机号", null);
        }else{
            //生成随机数
            String verityCode = dFormat.format((int) (Math.random()*100000));
            session.setMaxInactiveInterval(invalidMin * 60);
            session.setAttribute(SendSMSTool.SESSION_VERIFYCODE_KEY+phone, verityCode);
            //发送短信
            if(!SendSMSTool.sendSMS(phone, verityCode, TypeConvert.toStr(invalidMin))){
                logger.info(phone + "发送失败" + " 验证码: "+verityCode);
                msg.fail("发送失败", null);
            }
            logger.info(phone + "发送成功" + " 验证码: "+verityCode);
        }
        
        return JsonUtil.object2Json(msg);
    }
	
}
