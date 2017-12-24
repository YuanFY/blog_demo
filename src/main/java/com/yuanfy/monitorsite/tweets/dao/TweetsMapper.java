package com.yuanfy.monitorsite.tweets.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuanfy.monitorsite.base.BaseMapper;
import com.yuanfy.monitorsite.tweets.entity.TweetsEntity;

/**
 * @Description: 动弹模块接口层
 * @author yuanfy
 * @date 2017年8月3日 上午11:16:04 
 * @version 1.0
 */
public interface TweetsMapper extends BaseMapper<TweetsEntity>{
    
    public List<TweetsEntity> findAll(Map<String, Object> params);
    
    public Long getCount();
    
    /**
     * @description 获取热门动弹列表
     */
    public List<TweetsEntity> findHotList(@Param(value="limit") Integer limit);
}