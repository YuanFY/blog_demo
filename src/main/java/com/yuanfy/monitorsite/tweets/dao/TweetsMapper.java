package com.yuanfy.monitorsite.tweets.dao;

import java.util.List;
import java.util.Map;

import com.yuanfy.monitorsite.base.BaseMapper;
import com.yuanfy.monitorsite.tweets.entity.TweetsEntity;

/**
 * @Description: 动弹模块接口层
 * @author yuanfy
 * @date 2017年8月3日 上午11:16:04 
 * @version 1.0
 */
public interface TweetsMapper extends BaseMapper<TweetsEntity>{
    
    /**
     * @Description: 查找所有动弹
     * @author yuanfy
     * @date 2017年8月3日 上午11:18:22 
     * @version 6.2
     */
    public List<TweetsEntity> findAll(Map<String, Object> params);
    
    /**
     * @description 获取总记录数
     * @author YuanFY
     * @date 2017年12月20日 下午9:43:47
     * @version 1.0
     */
    public Long getCount();
}