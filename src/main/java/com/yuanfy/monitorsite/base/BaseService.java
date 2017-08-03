package com.yuanfy.monitorsite.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 基础服务层
 * @author yuanfy
 * @date 2017年8月3日 上午10:59:09 
 * @version 1.0
 */
@Service
public class BaseService<T> {
    @Autowired
    private BaseMapper<T> baseMapper;
    
    public void insert(T obj){
        baseMapper.insert(obj);
    }
    
    public void deleteById(Object id){
        baseMapper.deleteById(id);
    }
    
    public void update(T obj){
        baseMapper.update(obj);
    }
    
    public void getById(Object id){
        baseMapper.getById(id);
    }
}
