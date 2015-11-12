package com.chinasoft.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.model.manager.SysManager;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.user.UserService;

@Controller
public class LoginController extends BaseJsonController {
	private UserService userService;
	//登录
	@RequestMapping("/loginController/login.do")
	public String login(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		String validateCode = req.getString("validateCode");
		String loginName = req.getString("loginName");
		String password = req.getString("password");
		HttpSession session = request.getSession();
		String vcode = session.getAttribute("validatecode").toString();
		SysUser user;
		SysManager manager;
		try {
			manager=userService.findManagerByLoginName(loginName);
			user = userService.findByLognName(loginName);			
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		JSONObject jo = new JSONObject();
		if (!vcode.equalsIgnoreCase(validateCode)) {
			jo.put("state", "2");
			sendForStr(response, jo);
			return null;
		}
		if(manager==null||manager.getStatus().equals("1")){
			if (user == null||user.getFreezeStatus().equals("0")) {
			jo.put("state", "0");
			} else if (!password.equals(user.getPassword())) {
			jo.put("state", "1");
			} else {
			request.getSession().setAttribute("user", user);
			jo.put("state", "3");
			jo.put("value", JSONObject.fromObject(user,getDefaultJsonConfig()));
		}
		}else if (!password.equals(manager.getPassword())) {
			jo.put("state", "1");
		}
		else{
			jo.put("state", "4");
		}
		sendForStr(response, jo);		
		return null;
	}
	
	
	@RequestMapping("/loginController/exit.do")
	public String exit(HttpServletRequest request,
			HttpServletResponse response) {	
		request.getSession().removeAttribute("user");
		return null;
		
	}
	/**
	 * 
	 * @param request 
	 * {“email”:””,
		“password”:””,
		validateCode:””//验证码
		}
	 * @param response
	 * @return
	 */
	//注册
	@RequestMapping("/loginController/register.do")
	public String register(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		String validateCode = req.getString("validateCode");
		String loginName = req.getString("email");
		String password = req.getString("password");
		HttpSession session = request.getSession();
		SysUser u = new SysUser();
		u.setEmail(loginName);
		u.setPassword(password);
		String vcode = session.getAttribute("validatecode").toString();
		JSONObject ret = new JSONObject();
		if (!vcode.equalsIgnoreCase(validateCode)) {
			ret.put("state", "1");
			sendForStr(response, ret);
			return null;
		}
		SysUser user;
		try {
			user = userService.findByLognName(loginName);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		if (user == null) {
			try {
				this.userService.insertUser(u);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
			
			ret.put("state", "2");
			try {
				user = userService.findByLognName(loginName);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
			request.getSession().setAttribute("user", user);
			ret.put("value", JSONObject.fromObject(user,getDefaultJsonConfig()));
		} else {
			ret.put("state", "0");
		}
		sendForStr(response, ret);
		return null;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
