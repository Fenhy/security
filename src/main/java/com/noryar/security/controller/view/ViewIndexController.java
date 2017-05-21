package com.noryar.security.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能描述：【视图】首页控制层.
 * @author Leon.
 *
 */
@Controller
@RequestMapping("viewc")
public class ViewIndexController {
	
	/**
	 * 功能描述：首页.
	 * @return 首页信息.
	 */
	@RequestMapping("index.do")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
}
