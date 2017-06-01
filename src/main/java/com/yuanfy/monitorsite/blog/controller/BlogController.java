package com.yuanfy.monitorsite.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description TODO
 * @author YuanFY 
 * @date 2017年2月11日 下午9:50:47
 * @version 1.0
 */
@Controller
public class BlogController{
	final static Logger log = Logger.getLogger(BlogController.class);
	
	@RequestMapping(value = "/blog/index")
	public String index(HttpServletRequest request,Model model) {
		return "/blog/index";
	}
	
	
}
