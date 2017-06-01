package com.yuanfy.monitorsite.socket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
	
	@RequestMapping(value = "chat/index")
	public String index(){
		return "chat/index";
	}
}
