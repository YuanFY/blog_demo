package com.yuanfy.monitorsite.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanfy.monitorsite.framework.dto.AjaxResult;
import com.yuanfy.monitorsite.system.entity.UserEntity;
import com.yuanfy.monitorsite.system.service.UserService;

/**
 * @description 注册控制器类
 * @author YuanFY 
 * @date 2017年11月5日 下午8:54:50
 * @version 1.0
 */
@Controller
public class RegisterController{
	final static Logger log = Logger.getLogger(RegisterController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/system/register/index")
	public String index(HttpServletRequest request,Model model){
		return "/system/register/index";
	}
	
	@RequestMapping(value = "/system/register/save")
	@ResponseBody
	public AjaxResult save(UserEntity entity){
		AjaxResult result = new AjaxResult(1);
		try{
			result = userService.save(entity);
		} catch (Exception e) {
			result.setError(0);
			log.error("注册用户失败:", e);
		}
		return result;
	}
	
}
