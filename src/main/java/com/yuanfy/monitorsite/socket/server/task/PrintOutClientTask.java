package com.yuanfy.monitorsite.socket.server.task;

import java.net.Socket;
import java.util.Map.Entry;

import com.yuanfy.monitorsite.common.util.SocketUtils;

/**
 * @Description: 向客户端打印公共信息
 * @author yuanfy
 * @date 2017年5月25日 上午10:57:00 
 * @version 6.2
 */
public class PrintOutClientTask implements Runnable{

    @Override
    public void run() {
        while (true) {
            if (SocketUtils.isPrint) {
                String message = SocketUtils.removeMessage();
                if (SocketUtils.queue.size() == 0) {
                    SocketUtils.isPrint = false;
                }
                for (Entry<Socket, SendMessageToClientTask> task : SocketUtils.sendMessageToClientTaskMap.entrySet()) {
                   task.getValue().sendMessage(message);
                }
            }
        }
    }
}
