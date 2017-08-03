package com.yuanfy.monitorsite.tweets.service;

import java.util.List;

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
    
    public List<TweetsEntity> findAll(){
        return tweetsMapper.findAll();
    }
}
