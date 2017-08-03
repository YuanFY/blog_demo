package com.yuanfy.monitorsite.base;

/**
 * @Description: 基础Mapper接口层
 * @author yuanfy
 * @date 2017年8月3日 上午10:49:29 
 * @version 1.0
 */
public interface BaseMapper<T> {
    
    
    public void insert(T obj); 
    
    public void deleteById(Object id);
    
    public void update(T obj);
    
    public void getById(Object id);
    
}
