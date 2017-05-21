package com.noryar.security.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能描述：【视图】安全系统控制层.
 * @author Leon.
 *
 */
@Controller
@RequestMapping("viewc/license")
public class ViewSecurityController {

	/**
	 * 功能描述：证书首页.
	 * @return 首页信息.
	 */
	@RequestMapping("index.do")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("license");
		return modelAndView;
	}
}
