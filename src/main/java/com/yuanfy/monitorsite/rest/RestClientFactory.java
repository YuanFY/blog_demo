package com.yuanfy.monitorsite.rest;

import com.yuanfy.monitorsite.rest.conn.ConnectionInfo;
import com.yuanfy.monitorsite.rest.impl.DefaultRestClient;


/**
 * @Description: rest工厂类
 * @author yuanfy
 * @date 2018年1月5日 下午5:40:16 
 * @version 6.5
 */
public class RestClientFactory {
    
    public static DefaultRestClient getClient(ConnectionInfo info) {
        DefaultRestClient client = new DefaultRestClient();
        client.setConnectionInfo(info);
        return client;
    }
}
