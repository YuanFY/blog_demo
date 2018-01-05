package com.yuanfy.monitorsite.common;

import java.util.ResourceBundle;

import com.yuanfy.monitorsite.rest.conn.ConnectionInfo;

/**
 * @Description: 属性文件工具类
 * @author yuanfy
 * @date 2018年1月5日 下午5:26:44 
 * @version 1.0
 */
public class PropertiesUtils {
    
    public static ResourceBundle common = ResourceBundle.getBundle("common");
    
    /**
     * @Description: 获取zabbix连接信息
     * @author yuanfy
     * @date 1.0
     */
    public static ConnectionInfo getZabbixInfo(){
        ConnectionInfo connectInfo = new ConnectionInfo();
        connectInfo.setHost(common.getString("zabbix.ip"));
        connectInfo.setPort(Integer.parseInt(common.getString("zabbix.port")));
        connectInfo.setUserName(common.getString("zabbix.username"));
        connectInfo.setPassword(common.getString("zabbix.password"));
        return connectInfo;
    }
}
