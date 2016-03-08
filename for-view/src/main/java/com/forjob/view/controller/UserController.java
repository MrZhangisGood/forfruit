package com.forjob.view.controller;

import com.forjob.core.response.ResponseMsg;
import com.forjob.core.sms.SendSMSTool;
import com.forjob.core.util.JsonUtil;
import com.forjob.core.util.TypeConvert;
import com.forjob.server.BaseController;
import com.forjob.server.entity.UserEntity;
import com.forjob.server.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController extends BaseController<UserEntity> {

	protected static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * @Description: 获取BaseService
	 * @author zhanglm@joyplus.com.cn
	 * @return
	 */
	/*@Override
	public BaseService<UserEntity> getBaseService() {
		return this.userService;
	}*/

	@RequestMapping("test")
	@ResponseBody
	public String test(Model model){
		logger.error("test");
		super.getBaseService();
		return "Test Method.";
	}

	@RequestMapping("login.pass")
	@ResponseBody
	public String login(HttpSession session, String loginName, String password){
		ResponseMsg msg = new ResponseMsg();
		UserEntity user = userService.getLoginUser(session, loginName, password);
		if(user != null){
			msg.setData(user);
		}else{
			msg.fail("账号或者密码错误", null);
		}
		return JsonUtil.object2Json(msg);
	}

	@RequestMapping("phoneLogin.pass")
	@ResponseBody
	public String phoneLogin(HttpSession session, String phone, String verifyCode){
		ResponseMsg msg = new ResponseMsg();

		//获取验证码
		String sesVerifyCode = TypeConvert.toStr(session.getAttribute(SendSMSTool.SESSION_VERIFYCODE_KEY + phone));
		if(sesVerifyCode == null || !sesVerifyCode.equals(verifyCode)){
			msg.fail("验证码错误", null);
			return JsonUtil.object2Json(msg);
		}

		//登陆
		UserEntity user = userService.getLoginByPhone(session, phone);
		if(user != null){
			msg.setData(user);
		}else{
			msg.fail("登陆失败", null);
		}
		return JsonUtil.object2Json(msg);
	}

}