package com.yuanfy.monitorsite.rest.conn;

/**
 * @Description: 连接信息类
 * @author yuanfy
 * @date 2018年1月5日 下午5:25:03 
 * @version 1.0
 */
public class ConnectionInfo {
    //主机地址，一般是ip
    private String host;
    //端口号
    private Integer port;
    //用户名
    private String userName;
    //密码
    private String password;
    //请求超时,单位是秒
    private Integer timeOut = 120;
    
    public ConnectionInfo() {
    }
    
    public ConnectionInfo(String host, Integer port, String userName, String password) {
        super();
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}
