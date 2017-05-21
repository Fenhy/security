package com.noryar.security.controller.view;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.noryar.security.constant.SecurityConstant;
import com.noryar.security.dto.UserInfoDTO;

/**
 * 功能描述：【视图】用户登入控制层.
 * @author Leon.
 *
 */
@Controller
@RequestMapping("viewc/login")
public class ViewLoginController {

	/**
	 * 功能描述：登入页.
	 * @param session SESSION.
	 * @return 登入页.
	 */
	@RequestMapping("index.do")
	public ModelAndView index(HttpSession session){
		UserInfoDTO loginUser = (UserInfoDTO) session.getAttribute(SecurityConstant.SESSION_USER_INFO);
		ModelAndView modelAndView = new ModelAndView();
		if(null != loginUser && StringUtils.isNotBlank(loginUser.getUsername())){
			modelAndView.setViewName("redirect:/viewc/index.do");
		}else{
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	/**
	 * 功能描述：注册.
	 * @return 注册页.
	 */
	@RequestMapping("register.do")
	public ModelAndView register(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	/**
	 * 功能描述：退出.
	 * @param session SESSION.
	 * @return 首页.
	 */
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		session.removeAttribute(SecurityConstant.SESSION_USER_INFO);
		modelAndView.setViewName("redirect:/viewc/index.do");
		return modelAndView;
	}
	
}
