package com.yuanfy.monitorsite.socket.init;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.yuanfy.monitorsite.socket.server.Server;

/**
 * @Description: 初始化公共聊天客户端
 * @author yuanfy
 * @date 2017年7月18日 下午2:18:48 
 * @version 1.0
 */
public class InitChatServer implements InitializingBean{

    private static Logger log = Logger.getLogger(InitChatServer.class);    

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("---------------开始监控公共聊天服务器端：------------------");
        Server.start();
    }

}
