package com.yuanfy.monitorsite.framework;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.yuanfy.monitorsite.common.Constants;
import com.yuanfy.monitorsite.system.entity.UserEntity;

/**
 * @Description: 会话管理工具类
 * @author yuanfy
 * @date 2017年7月17日 下午4:04:13 
 * @version 1.0
 */
public class SessionUtil {
    protected static final Logger logger = Logger.getLogger(SessionUtil.class);

    /**
     * @Description: 设置session的值
     * @author yuanfy
     * @date 2017年7月17日 下午4:07:32 
     * @version 1.0
     */
    public static void setAttr(HttpServletRequest request, String key, Object value) {
        request.getSession(true).setAttribute(key, value);
    }
    /**
     * @Description: 获取session的值
     * @author yuanfy
     * @date 2017年7月17日 下午4:07:32 
     * @version 1.0
     */
    public static Object getAttr(HttpServletRequest request, String key) {
        return request.getSession(true).getAttribute(key);
    }
    /**
     * @Description: 获取用户信息
     * @author yuanfy
     * @date 2017年7月17日 下午4:07:32 
     * @version 1.0
     */
    public static UserEntity getUser(HttpServletRequest request) {
        UserEntity user = (UserEntity)request.getSession(true).getAttribute(Constants.SESSION_USER_KEY);
        return user;
    }
    
    /**
     * @description 移除用户信息
     * @author YuanFY
     * @date 2017年11月12日 下午3:26:45
     * @version 1.0
     */
    public static void removeUser(HttpServletRequest request) {
        request.getSession(true).removeAttribute(Constants.SESSION_USER_KEY);
    }
}
