package com.noryar.security.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.noryar.security.constant.SecurityConstant;
import com.noryar.security.dto.UserInfoDTO;

/**
 * 功能描述：通用拦截器.
 * @author leon.
 *
 */
public class CommonInterceptor implements HandlerInterceptor{

	/**
	 * 日志.
	 */
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		// 是否需要跳转
		String lastUri = (String) session.getAttribute(SecurityConstant.LAST_REQUEST_URI);
		UserInfoDTO user = (UserInfoDTO) session.getAttribute(SecurityConstant.SESSION_USER_INFO);
		if (StringUtils.isNotBlank(lastUri) && null != user) {
			log.info(String.format("用户【id=%s;nickName=%s】跳转至路径【%s】", user.getUserid(), user.getNickname(), lastUri));
			response.sendRedirect(lastUri);
			session.removeAttribute(SecurityConstant.LAST_REQUEST_URI);
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
