package com.yuanfy.monitorsite.tweets.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanfy.monitorsite.base.BaseService;
import com.yuanfy.monitorsite.tweets.dao.TweetsMapper;
import com.yuanfy.monitorsite.tweets.entity.TweetsEntity;

/**
 * @Description: 
 * @author yuanfy
 * @date 2017年8月3日 上午11:19:29 
 * @version 1.0
 */
@Service
public class TweetsService extends BaseService<TweetsEntity>{

    
    @Autowired
    public TweetsMapper tweetsMapper;
    
    public List<TweetsEntity> findAll(Map<String, Object> params){
        return tweetsMapper.findAll(params);
    }
    
    public Long getCount(){
        return tweetsMapper.getCount();
    }
    /**
	 * @description 获取热门动弹（热门动弹：评论或点赞数超过15，且根据点赞数和评论数降序排序）
	 * @param limit 限制条数
	 * @author YuanFY
	 * @date 2017年12月24日 上午10:25:37
	 * @version 1.0
	 */
    public List<TweetsEntity> findHotList(Integer limit) {
    	return tweetsMapper.findHotList(limit);
    }
}