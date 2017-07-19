package com.yuanfy.monitorsite.socket;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanfy.monitorsite.framework.SessionUtil;
import com.yuanfy.monitorsite.framework.dto.AjaxResult;
import com.yuanfy.monitorsite.socket.client.Client;
import com.yuanfy.monitorsite.system.entity.UserEntity;

@Controller
public class ChatController {
	
    
	@RequestMapping(value = "chat/index")
	public String index(Model model, HttpServletRequest request){
	    model.addAttribute("user", SessionUtil.getUser(request));
		return "chat/index";
	}
	
	@RequestMapping(value="chat/startClient")
	@ResponseBody
	public AjaxResult startClient(UserEntity user,HttpServletRequest request) {
	    AjaxResult result = new AjaxResult(1);
	    if (user.getUserId() == null){
            user.setUserId(System.currentTimeMillis());
            SessionUtil.setAttr(request, SessionUtil.SESSION_USER, user);
        } else {
            UserEntity sessionUser = SessionUtil.getUser(request);
            if (sessionUser.getUserId().equals(user.getUserId())) {
               user = sessionUser; 
            }
        }
	    if (Client.startClient(user)) {
	        result.setData(user);
	    }
	    return result;
	}
}
