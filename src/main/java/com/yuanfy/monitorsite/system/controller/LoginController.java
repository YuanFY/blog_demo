package com.yuanfy.monitorsite.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanfy.monitorsite.framework.dto.AjaxResult;
import com.yuanfy.monitorsite.system.service.UserService;

/**
 * @description 登录控制器类
 * @author YuanFY 
 * @date 2017年11月11日 下午9:05:40
 * @version 1.0
 */
@Controller
public class LoginController{
	final static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/system/login/index")
	public String index(HttpServletRequest request,Model model){
		return "/system/login/index";
	}
	
	@RequestMapping(value = "/system/login/login")
	@ResponseBody
	public AjaxResult login(String name, String password, HttpServletRequest request){
		AjaxResult result = new AjaxResult(1);
		try{
			result = userService.loginJudge(name, password, request);
		} catch (Exception e) {
			result.setError(0);
			log.error("用户登录失败:", e);
		}
		return result;
	}
	
}
