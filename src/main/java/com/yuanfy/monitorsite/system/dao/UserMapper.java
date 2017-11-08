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
	
	public boolean isValidateNameUnique(@Param(value="name")String name);
}
