package com.yuanfy.monitorsite.rest;

import com.yuanfy.monitorsite.rest.entity.RestRequestEntity;
import com.yuanfy.monitorsite.rest.entity.RestResponseEntity;

/**
 * @Description: rest客户操作类
 * @author yuanfy
 * @date 2018年1月5日 上午11:12:54 
 * @version 1.0
 */
public interface RestClient {
    
    public RestResponseEntity get(String uri) throws Exception;
    
    public RestResponseEntity put(String uri, RestRequestEntity entity) throws Exception;
    
    public RestResponseEntity post(String uri, RestRequestEntity entity) throws Exception;
    
    public RestResponseEntity delete(String uri, RestRequestEntity entity) throws Exception;
}
