package com.yuanfy.monitorsite.system.dao;

import org.apache.ibatis.annotations.Param;

import com.yuanfy.monitorsite.base.BaseMapper;
import com.yuanfy.monitorsite.system.entity.UserEntity;

/**
 * @description 用户接口层
 * @author YuanFY 
 * @date 2017年11月5日 下午9:36:42
 * @version 1.0
 */
public interface UserMapper extends BaseMapper<UserEntity>{
	/**
	 * @description 验证用户名是否唯一
	 * @param name
	 * @return 1 表示昵称存在重复，2 表示邮箱存在重复，3 表示手机号存在
	 * @author YuanFY
	 * @date 2017年11月11日 下午9:41:52
	 * @version 1.0
	 */
	public int isValidateUserUnique(UserEntity entity);
	
	/**
	 * @description 根据账号和密码获取用户
	 * @param name 指邮箱或手机号
	 * @return UserEntity
	 * @author YuanFY
	 * @date 2017年11月11日 下午9:41:52
	 * @version 1.0
	 */
	public UserEntity getLoginUser(@Param(value="name")String name, @Param(value="password")String password);
}
