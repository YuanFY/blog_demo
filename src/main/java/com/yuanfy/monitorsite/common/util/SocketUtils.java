package com.yuanfy.monitorsite.common.util;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.log4j.Logger;

import com.yuanfy.monitorsite.socket.client.Client;
import com.yuanfy.monitorsite.socket.client.task.ReceiveMessageFromServerTask;
import com.yuanfy.monitorsite.socket.server.task.SendMessageToClientTask;
import com.yuanfy.monitorsite.system.entity.UserEntity;



/**
 * @Description: 公共工具类
 * @author yuanfy
 * @date 2017年5月25日 上午8:26:29 
 * @version 1.0
 */
public class SocketUtils {
    
    private static Logger log = Logger.getLogger(CommonUtils.class);  
    
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("common");
    
    //存放用户集合    Restructure
    private static List<String> userList = new ArrayList<String>();
    
    //存放消息队列
    public static BlockingQueue<String> queue = new LinkedBlockingDeque<String>();
    
    public static Map<UserEntity, Client> clientMap = new HashMap<UserEntity, Client>();
    
    public static Map<Socket, SendMessageToClientTask> sendMessageToClientTaskMap = new HashMap<Socket, SendMessageToClientTask>();
    
    public static Map<Socket, ReceiveMessageFromServerTask> receiveMessageFromServerTaskMap= new HashMap<Socket, ReceiveMessageFromServerTask>();
    
    public static volatile boolean isPrint = false; //volatile 具有可见性
    /**
     * @Description: 根据key获取配置件中的内容
     * @param key
     * @author yuanfy
     * @date 2017年5月25日 上午9:15:01 
     * @version 1.0
     */
    public static String getKey(String key) {
        return resourceBundle.getString(key);
    }
    
    /**
     * @Description: 添加用户名至集合中
     * @author yuanfy
     * @date 2017年5月25日 上午9:14:38 
     * @version 1.0
     */
    public static void addUser(String user) {
        userList.add(user);
    }
    /**
     * @Description: 返回用户列表
     * @author yuanfy
     * @date 2017年5月25日 上午9:16:05 
     * @version 1.0
     */
    public static List<String> getUserList() {
        return userList;
    }
    
    /**
     * @Description: 移除用户
     * @author yuanfy
     * @date 2017年5月25日 上午9:16:05 
     * @version 1.0
     */
    public static void removeUser(String name) {
        userList.remove(name);
    }
    
    /**
     * @Description: 返回用户列表信息
     * @author yuanfy
     * @date 2017年5月25日 上午9:16:05 
     * @version 1.0
     */
    public static String getUserListInfo() {
        String userInfo = "----------在线用户列表----------\n";
        for (String name : userList) {
            userInfo += "[" + name + "]\n";
        }
        userInfo += "----------------------------\n";
        
        return userInfo;
    }
    
    /**
     * @Description: 存放消息至队列中
     * @param message
     * @author yuanfy
     * @date 2017年5月25日 上午9:24:06 
     * @version 1.0
     */
    public static void putMessage(String message){
        try {
            queue.put(message);
            isPrint = true;
        }
        catch (InterruptedException e) {
            log.error("存放消息至队列的过程中出错：" + e);
        }
    }
    /**
     * @Description: 获取并移除此队列的头部信息
     * @return
     * @author yuanfy
     * @date 2017年5月25日 上午9:25:43 
     * @version 1.0
     */
    public static String removeMessage() {
        return queue.poll();
    }
    
    /**
     * @Description: 获取common配置文件中的host_ip
     * @return
     * @author yuanfy
     * @date 2017年5月25日 上午10:04:29 
     * @version 1.0
     */
    public static String getHost() {
        return resourceBundle.getString("host_ip");
    }
    
    /**
     * @Description: 获取common配置文件中的host_port
     * @return
     * @author yuanfy
     * @date 2017年5月25日 上午10:04:29 
     * @version 1.0
     */
    public static int getPort() {
        return Integer.parseInt(resourceBundle.getString("host_port"));
    }
    
}
