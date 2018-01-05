package com.yuanfy.monitorsite.rest.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;


/**
 * @Description: rest响应实体类
 * @author yuanfy
 * @date 2018年1月5日 下午3:04:24 
 * @version 1.0
 */
public class RestResponseEntity{

    /**
     * http返回状态码
     */
    private int statusCode;
    /**
     * MIME类型
     */
    private String type;
    /**
    * 响应实体字符串
    */
    private String entity;
   
    /**
    * 文件头参数
    */
    private Map<String, String> headers;

    public RestResponseEntity(){}
    
    public RestResponseEntity(int statusCode, String type, String entity) {
        this.statusCode = statusCode;
        this.type = type;
        this.entity = entity;
        this.headers = new HashMap<String, String>();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    
    public void setHeaders(Header[] headers){
        if (headers == null) {
            return ;
        }
        for (Header header : headers) {
            this.headers.put(header.getName(), header.getValue());
        }
    }
}
