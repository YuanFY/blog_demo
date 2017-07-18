package com.yuanfy.monitorsite.socket.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.yuanfy.monitorsite.common.util.SocketUtils;
import com.yuanfy.monitorsite.common.util.StreamUtils;
import com.yuanfy.monitorsite.socket.client.task.ReceiveMessageFromServerTask;
import com.yuanfy.monitorsite.system.entity.UserEntity;

/**
 * @Description: 客户端
 * @author yuanfy
 * @date 2017年5月25日 下午3:03:47 
 * @version 1.0
 */
public class Client extends Socket{

    private static Logger log = Logger.getLogger(Client.class);
    
    public Client(String host, int port) throws IOException {
        super(host, port);
    }
    
    public static boolean startClient(UserEntity user) {
        boolean result =  true;
        Client client = SocketUtils.clientMap.get(user);
        PrintWriter printWriter = null;
        BufferedReader consoleReader = null;
        try {
            if ( client == null ){
                //1、创建socket
                client = new Client(SocketUtils.getHost(), SocketUtils.getPort());
            }
            //2、根据socket获取输出流
            printWriter = new PrintWriter(client.getOutputStream(), true);
            
            //3、启动接收服务端信息的线程
            new Thread(new ReceiveMessageFromServerTask(client)).start();
            
            //4、主线程控制输入消息
            while (true) {
                consoleReader = new BufferedReader(new InputStreamReader(System.in));
                String inputContent = consoleReader.readLine();
                printWriter.println(inputContent);
            }
        }
        catch (IOException e) {
            log.error("创建客户端socket出错：" + e);
        }
        finally {
            StreamUtils.close(client);
            StreamUtils.close(consoleReader);
            StreamUtils.close(printWriter);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Client client = null;
        PrintWriter printWriter = null;
        BufferedReader consoleReader = null;
        try {
            //1、创建socket
            client = new Client(SocketUtils.getHost(), SocketUtils.getPort());
            //2、根据socket获取输出流
            printWriter = new PrintWriter(client.getOutputStream(), true);
            
            //3、启动接收服务端信息的线程
            new Thread(new ReceiveMessageFromServerTask(client)).start();
            
            //4、主线程控制输入消息
            while (true) {
                consoleReader = new BufferedReader(new InputStreamReader(System.in));
                String inputContent = consoleReader.readLine();
                printWriter.println(inputContent);
            }
        }
        catch (IOException e) {
            log.error("创建客户端socket出错：" + e);
        }
        finally {
            StreamUtils.close(client);
            StreamUtils.close(consoleReader);
            StreamUtils.close(printWriter);
        }
    }
}
