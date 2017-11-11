package com.yuanfy.monitorsite.system.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanfy.monitorsite.common.Constants;
import com.yuanfy.monitorsite.framework.SessionUtil;
import com.yuanfy.monitorsite.framework.dto.AjaxResult;
import com.yuanfy.monitorsite.system.dao.UserMapper;
import com.yuanfy.monitorsite.system.entity.UserEntity;

/**
 * @description 用户相关逻辑层类
 * @author YuanFY 
 * @date 2017年11月5日 下午9:04:12
 * @version 1.0
 */
@Service
public class UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserMapper userMapper;
	
	public AjaxResult save(UserEntity entity, HttpServletRequest request){
		AjaxResult result = new AjaxResult(1);
		if (entity == null) {
			result.setError(0);
			log.error("用户数据为空，请重新检查!");
			return result;
		}
		if (entity.getId() != null) {
			userMapper.update(entity);
			return result;
		} 
		if (userMapper.isValidateNameUnique(entity.getName())) {
			result.setError(0);
			result.setMsg("该昵称已被注册，请换一个！");
		}else {
			userMapper.insert(entity);
			SessionUtil.setAttr(request, Constants.SESSION_USER_KEY, entity);
		}
		return result;
	}
	
	public AjaxResult loginJudge(String name, String password, HttpServletRequest request){
		AjaxResult result = new AjaxResult(1);
		UserEntity user = userMapper.getLoginUser(name, password);
		if (user == null) {
			result.setError(0);
			result.setMsg("账号或密码错误，请重试！");
		}else {
			SessionUtil.setAttr(request, Constants.SESSION_USER_KEY, user);
		}
		return result;
	}
}
