package com.yuanfy.monitorsite.socket;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.yuanfy.monitorsite.common.util.SocketUtils;
import com.yuanfy.monitorsite.common.util.StringUtils;
import com.yuanfy.monitorsite.framework.SessionUtil;
import com.yuanfy.monitorsite.framework.dto.AjaxResult;
import com.yuanfy.monitorsite.socket.client.Client;
import com.yuanfy.monitorsite.socket.client.task.ReceiveMessageFromServerTask;
import com.yuanfy.monitorsite.system.entity.UserEntity;

/**
 * @Description: 公共聊天控制器类
 * @author yuanfy
 * @date 2017年7月25日 下午4:21:18 
 * @version 1.0
 */
@Controller
public class ChatController {
    
    /**
     * @Description: 测试转发
     * @param attr RedirectAttributes
     * @author yuanfy
     * @date 2017年7月26日 上午10:09:36 
     * @version 6.2
     */
    @RequestMapping(value = "chat/test")
    public String testRedirect(RedirectAttributes attr, HttpServletRequest request){
        ((FlashMap)((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes()))
                .getRequest().getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE)).put("name", "1234");
        
        RequestContextUtils.getOutputFlashMap(request).put("name1", "纳秒1");
        
        attr.addFlashAttribute("id", 1);
        
        //拼接在url后面
        attr.addAttribute("test", "test");
        
        
        return "redirect:/chat/index.html";
    }
    
    
	@RequestMapping(value = "chat/index")
	public String index(Model model, HttpServletRequest request){
	    model.addAttribute("user", SessionUtil.getUser(request));
		return "chat/index";
	}
	
	/**
	 * @Description: 发起聊天
	 * @param user 用户信息
	 * @param message 发送信息
	 * @author yuanfy
	 * @date 2017年7月25日 下午4:21:51 
	 * @version 1.0
	 */
	@RequestMapping(value="chat/startClient")
	@ResponseBody
	public AjaxResult startClient(UserEntity user, String message, HttpServletRequest request) {
	    AjaxResult result = new AjaxResult(1);
	    user = user.getSessionUser(request);
	    if (StringUtils.isEmpty(message)) {
	        Client.startClient(user);
	    } else {
	        Client.sendMessage(user, message);
	    }
	    result.setData(user);
	    return result;
	}
	
	/**
	 * @Description: 获取服务端返回的信息
	 * @author yuanfy
	 * @date 2017年7月25日 下午4:22:16 
	 * @version 1.0
	 */
	@RequestMapping(value="chat/getReceiveMessage")
    @ResponseBody
    public AjaxResult getReceiveMessageFromServer(HttpServletRequest request) {
	    AjaxResult result = new AjaxResult(1);
	    UserEntity user = SessionUtil.getUser(request);
	    ReceiveMessageFromServerTask task = SocketUtils.receiveMessageFromServerTaskMap.get(SocketUtils.clientMap.get(user));
	    if(task != null) {
	        result.setData(task.getReceiveMessage());
	    }
	    return result;
	}
}
