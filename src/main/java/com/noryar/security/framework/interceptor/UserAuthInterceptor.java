package com.noryar.security.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.noryar.security.constant.SecurityConstant;
import com.noryar.security.dto.UserInfoDTO;

/**
 * 功能描述：用户操作权限认证拦截器.
 * @author leon.
 *
 */
public class UserAuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserInfoDTO loginUser = (UserInfoDTO) session.getAttribute(SecurityConstant.SESSION_USER_INFO);
		String uri = request.getRequestURI();
		// 登入验证失败
		if (null == loginUser) {
			// 记录最后访问位置，并跳转到登入页
			session.setAttribute(SecurityConstant.LAST_REQUEST_URI, uri);
			request.getRequestDispatcher("/viewc/login/index.do").forward(request, response);
			return false;
		} 
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
