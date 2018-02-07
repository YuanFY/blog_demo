package com.yuanfy.test.thread.producerAndConsumerModel2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 生产者与消费者
 * @author yuanfy
 * @date 2018年1月31日 下午7:35:46 
 * @version 1.0
 */
class Producer implements Runnable{
    private final BlockingQueue<String> queue;
    
    private AtomicInteger count = new AtomicInteger(0);
    
    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                queue.put("盘子");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "生产的第" +count.incrementAndGet()+ "个盘子，共" + queue.size() + "盘子");
        }
    }
}

class Consumer implements Runnable{
    private final BlockingQueue<String> queue;
    
    private AtomicInteger count = new AtomicInteger(0);
    
    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        while (true) {
            
            try {
                Thread.sleep(3000);
                queue.take();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "用的第" +count.incrementAndGet()+ "个盘子，还剩" + queue.size() + "盘子");
        }
    }
}
public class ProducerAndConsumerModelTest {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
        new Thread(new Producer(queue), "生产者A").start();
        new Thread(new Producer(queue), "生产者B").start();
        new Thread(new Consumer(queue), "-------消费者A").start();
        new Thread(new Consumer(queue), "-------消费者B").start();
    }
}
