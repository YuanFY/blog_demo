package com.yuanfy.monitorsite.tweets.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuanfy.monitorsite.tweets.service.TweetsService;

/**
 * @description TODO
 * @author YuanFY 
 * @date 2017年2月11日 下午9:50:47
 * @version 1.0
 */
@Controller
public class TweetsController{
	final static Logger log = Logger.getLogger(TweetsController.class);
	
	@Autowired
	private TweetsService tweetsService;
	
	@RequestMapping(value = "/tweets/index")
	public String index(HttpServletRequest request,Model model) {
		return "/tweets/index";
	}
	
}
