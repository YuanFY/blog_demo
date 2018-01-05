package com.yuanfy.monitorsite.rest.entity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

/**
 * @Description: rest请求实体类
 * @author yuanfy
 * @date 2018年1月5日 下午3:04:24 
 * @version 1.0
 */
public class RestRequestEntity extends StringEntity{

    /**
     * @param body 设置请求body 以及设置默认的contentType和contentEncoding
     * @throws UnsupportedEncodingException
     */
    public RestRequestEntity(String body) throws UnsupportedEncodingException {
        super(body, ContentType.APPLICATION_JSON);
        this.setContentEncoding(ContentType.APPLICATION_JSON.getCharset().toString());
    }
    
    public RestRequestEntity(String body, ContentType contentType) throws UnsupportedCharsetException {
        super(body, contentType);
        this.setContentEncoding(contentType.getCharset().toString());
    }
    
}
