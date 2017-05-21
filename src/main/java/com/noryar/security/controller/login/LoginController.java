package com.noryar.security.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noryar.security.constant.SecurityConstant;
import com.noryar.security.dto.UserInfoDTO;
import com.noryar.security.service.login.LoginService;

/**
 * 功能描述：【用户登入】控制层.
 * @author Leon.
 *
 */
@RestController
@RequestMapping("login")
public class LoginController {
	
	/**
	 * loginService.
	 */
	@Autowired
	private LoginService loginService;
	
	/**
	 * 功能描述：登入.
	 * @param request REQUEST.
	 * @param username 用户名.
	 * @param password 密码.
	 * @return Map.
	 * @throws Exception e.
	 */
	@RequestMapping("login.do")
	public Map<String, Object> login(
			HttpServletRequest request,
			UserInfoDTO searchModel) throws Exception{
		Map<String, Object> result = new HashMap<>();
		// 判断是否需要登入
		HttpSession session = request.getSession();
		if (null != session.getAttribute(SecurityConstant.SESSION_USER_INFO)) {
			result.put("msg", "您已经登入");
		} else {
			UserInfoDTO userInfo = loginService.getUserByUnameAndPwd(searchModel);
			if (userInfo != null && StringUtils.isNotBlank(userInfo.getUsername())) {
				result.put("msg", "登入成功");
				session.setAttribute(SecurityConstant.SESSION_USER_INFO, userInfo);
			} else {
				result.put("msg", "用户名或密码错误");
			}
		}
		return result;
	}
	
	/**
	 * 功能描述：注册.
	 * @param userInfo 用户信息.
	 * @return map.
	 */
	@RequestMapping("register.do")
	public Map<String, Object> register(UserInfoDTO userInfo){
		Map<String,Object> result = new HashMap<>();
		loginService.saveUser(userInfo);
		result.put("msg", "注册成功");
		return result;
	}
	
}
