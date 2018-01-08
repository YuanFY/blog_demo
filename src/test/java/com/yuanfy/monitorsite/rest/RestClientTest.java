package com.yuanfy.monitorsite.rest;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.yuanfy.monitorsite.common.PropertiesUtils;
import com.yuanfy.monitorsite.common.util.StringUtils;
import com.yuanfy.monitorsite.rest.conn.ConnectionInfo;
import com.yuanfy.monitorsite.rest.entity.RestRequestEntity;
import com.yuanfy.monitorsite.rest.entity.RestResponseEntity;
/**
 * @Description: rest测试类
 * @author yuanfy
 * @date 2018年1月5日 下午4:46:48 
 * @version 1.0
 */
public class RestClientTest {
    
    @Test
    public void testPost() {
        ConnectionInfo connectionInfo = PropertiesUtils.getZabbixInfo();
        String url = StringUtils.getHttpUrl(connectionInfo, "zabbix/api_jsonrpc.php");
        String json = getJson();
        try {
            RestRequestEntity entity = new RestRequestEntity(json);
            RestClient client = RestClientFactory.getClient(connectionInfo);
            RestResponseEntity responseEntity = client.post(url, entity);
            System.out.println(responseEntity.getEntity());
        }
        catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static String getJson(){
        JsonObject json = new JsonObject();
        json.addProperty("jsonrpc", "2.0");
        json.addProperty("method", "user.login");
        JsonObject paramsJson = new JsonObject();
        paramsJson.addProperty("user", "admin");
        paramsJson.addProperty("password", "zabbix");
        json.add("params", paramsJson);
        json.addProperty("id", 1);
        return json.toString();
    }
    
    
    @Test
    public void testDelete() {
        ConnectionInfo connectionInfo = PropertiesUtils.getZabbixInfo();
        String url = StringUtils.getHttpUrl(connectionInfo, "zabbix/api_jsonrpc.php");
        String json = getJsonObj();
        try {
            RestRequestEntity entity = new RestRequestEntity(json);
            RestClient client = RestClientFactory.getClient(connectionInfo);
            client.post(url, entity);
        }
        catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static String getJsonObj() {
      
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", "host.delete");
        JSONObject params = new JSONObject();
        params.put("hostid", "2001328");
        json.put("params", params);
        json.put("id", 1);
        json.put("auth", "d0205bce5b33e85644da31bbb99d0b16");//来源于test1中的result 
        String data = json.toString();
        return data;
    }
}
