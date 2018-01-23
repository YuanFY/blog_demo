package com.yuanfy.test.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 盘子工厂
 * @author yuanfy
 * @date 2018年1月23日 下午5:07:09 
 * @version 1.0
 */
public class PlateFactorys {
    private final static int MAX_SIZE = 10;
    private Queue<String> queue = new LinkedList<String>();
    
    public void put(){
        synchronized (queue) {
            if (queue.size() == MAX_SIZE) {
                try {
                    wait();
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            while (queue.size() < MAX_SIZE) {
                String name = "盘" + (queue.size() + 1);
                queue.add(name);
                System.out.println("product plate: " + name);
                notify();
            }
            
        }
    }
    
    public void get(){
        synchronized (queue) {
            if (queue.size() == 0) {
                try {
                    wait();
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            while (queue.size() > 0) {
                String name = queue.poll();
                System.out.println("consume plate: " + name);
                notify();
            }
        }
    }
}
