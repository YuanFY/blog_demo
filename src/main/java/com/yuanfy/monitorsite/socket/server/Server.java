package com.yuanfy.monitorsite.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.yuanfy.monitorsite.common.util.SocketUtils;
import com.yuanfy.monitorsite.socket.server.task.PrintOutClientTask;
import com.yuanfy.monitorsite.socket.server.task.SendMessageToClientTask;

/**
 * @Description: socket服务端
 * @author yuanfy
 * @date 2017年5月25日 上午8:32:03 
 * @version 1.0
 */
public class Server extends ServerSocket implements Runnable{

    private static Logger log = Logger.getLogger(Server.class);    
    
    private static Executor threadPool = Executors.newCachedThreadPool();//定义线程池
    
    private Server(int port) throws IOException {
        super(port);
    }
    
    /**
     * @Description: 服务端启动接收和发送
     * @author yuanfy
     * @date 2017年5月25日 上午8:46:11 
     * @version 1.0
     */
    public void run() {
        try {
            //1、创建向客户端打印信息线程
            new Thread(new PrintOutClientTask()).start();
            //2、主线程 监听客户端请求，并启动线程处理请求
            while (true) {
                Socket socket = this.accept();
                System.out.println(socket);
                threadPool.execute(new SendMessageToClientTask(socket));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            log.error("接收客户端请求出错：", e);
        }
        finally {
            try {
                close();
            }
            catch (IOException e) {
                log.error("关闭ServerSocket出错：", e);
            }
        }
    }
    
    public static void start() {
        try {
            Server server = new Server(SocketUtils.getPort());//创建ServerSocket
            new Thread(server).start();
            log.info("---------------成功启动聊天服务器------------------");
        }
        catch (IOException e) {
            if (e.getMessage().contains("Address already in use")) {
                log.error("服务端已经启动，若需要再次启动请修改端口！");
            }
        }
    }
}
