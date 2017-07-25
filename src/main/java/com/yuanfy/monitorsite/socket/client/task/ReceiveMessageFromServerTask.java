package com.yuanfy.monitorsite.socket.client.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.log4j.Logger;

import com.yuanfy.monitorsite.common.util.SocketUtils;
import com.yuanfy.monitorsite.common.util.StreamUtils;


/**
 * 
 * @Description: 接收服务端信息线程类
 * @author yuanfy
 * @date 2017年5月25日 上午10:13:09 
 * @version 6.2
 */
public class ReceiveMessageFromServerTask implements Runnable{

    private static Logger log = Logger.getLogger(ReceiveMessageFromServerTask.class);
    
    private Socket socket;
    
    private BufferedReader serverReader;
    
    private BlockingQueue<String> messageQueue ;
    
    /**
     * 通过构造方法获取socket输入流
     * @param socket
     */
    public ReceiveMessageFromServerTask(Socket socket) {
        this.socket = socket;
        try {
            serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            messageQueue = new LinkedBlockingDeque<String>();
            SocketUtils.receiveMessageFromServerTaskMap.put(socket, this);
        }
        catch (IOException e) {
            log.error("获取socket输入流出错：" + e);
        }
    }

    @Override
    public void run() {
        try {
            String receiveMessage = "";
            while (!"byeClient".equals(receiveMessage)) {
                receiveMessage = serverReader.readLine();
                messageQueue.add(receiveMessage);
            }
        }
        catch (IOException e) {
            if (e.getMessage().contains("Connection reset")) {
                log.info("服务器已经关闭，自动关闭客户端！");
            }
        }
        finally {
            StreamUtils.close(socket);
            StreamUtils.close(serverReader);
        }
    }
    
    public List<String> getReceiveMessage(){
        List<String> messageList = new ArrayList<String>();
        String message = null;
        while ((message = messageQueue.poll()) != null) {
            messageList.add(message);
        }
        return messageList;
    }
}
