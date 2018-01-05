package com.yuanfy.monitorsite.rest.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuanfy.monitorsite.exception.AppException;
import com.yuanfy.monitorsite.rest.RestClient;
import com.yuanfy.monitorsite.rest.conn.ConnectionInfo;
import com.yuanfy.monitorsite.rest.entity.RestRequestEntity;
import com.yuanfy.monitorsite.rest.entity.RestResponseEntity;

/**
 * @Description: rest默认实现类
 * @author yuanfy
 * @date 2018年1月5日 下午3:52:21 
 * @version 1.0
 */
public class DefaultRestClient implements RestClient {

    private Logger log = LoggerFactory.getLogger(DefaultRestClient.class);
    
    private ConnectionInfo connectionInfo;
    
    private HttpClient httpClient;
    
    private static final Map<String, HttpClient> CONNECTIONS = new ConcurrentHashMap<String, HttpClient>();
    
    private void init(){
        this.httpClient = CONNECTIONS.get(this.connectionInfo.getHost());
        synchronized (CONNECTIONS) {
            if (httpClient == null) {
                this.httpClient = HttpClients.createDefault();
                CONNECTIONS.put(this.connectionInfo.getHost(), this.httpClient);
            }
        }
    }
    
    @Override
    public RestResponseEntity get(String uri) throws Exception {
        HttpGet method = new HttpGet(uri);
        return executeRequest(method);
    }

    @Override
    public RestResponseEntity put(String uri, RestRequestEntity entity) throws Exception {
        HttpPut method = new HttpPut(uri);
        method.setEntity(entity);
        return executeRequest(method);
    }

    @Override
    public RestResponseEntity post(String uri, RestRequestEntity entity) throws Exception {
        HttpPost method = new HttpPost(uri);
        method.setEntity(entity);
        return executeRequest(method);
    }

    @Override
    public RestResponseEntity delete(String uri, RestRequestEntity entity) throws Exception {
        HttpDelete method = new HttpDelete(uri);
        return executeRequest(method);
    }
    
    protected RestResponseEntity executeRequest(HttpRequestBase request) throws Exception{
        try {
            init();
            HttpResponse httpResponse = this.httpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();
            RestResponseEntity responseEntity = new RestResponseEntity(httpResponse.getStatusLine().getStatusCode(), 
                entity.getContentType().getValue(), EntityUtils.toString(entity));
            responseEntity.setHeaders(httpResponse.getAllHeaders());
            EntityUtils.consume(entity);
            log.info("REST返回状态码: " + responseEntity.getStatusCode());
            log.info("REST返回Body: " + responseEntity.getEntity());
        }
        catch (ClientProtocolException e) {
            log.error("HttpClient执行请求时，客户端协议报错：", e);
            throw new AppException("HttpClient执行请求时，客户端协议报错", e);
        }
        catch (Exception e) {
            throw new AppException("HttpClient执行请求报错", e);
        }
        return null;
    }

    public ConnectionInfo getConnectionInfo() {
        return connectionInfo;
    }

    public void setConnectionInfo(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

}
