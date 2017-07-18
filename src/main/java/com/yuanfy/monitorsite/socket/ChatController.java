package com.yuanfy.monitorsite.socket;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanfy.monitorsite.framework.SessionUtil;
import com.yuanfy.monitorsite.framework.dto.AjaxResult;
import com.yuanfy.monitorsite.system.entity.UserEntity;

@Controller
public class ChatController {
	
    
	@RequestMapping(value = "chat/index")
	public String index(Model model, HttpServletRequest request){
	    model.addAttribute("user", SessionUtil.getUser(request));
		return "chat/index";
	}
	
	@ResponseBody
	@RequestMapping(value="chat/createClient")
	public AjaxResult createClient(String userName) {
	    AjaxResult result = new AjaxResult(1);
	    UserEntity user = new UserEntity(userName);
	    
	    return result;
	}
}
