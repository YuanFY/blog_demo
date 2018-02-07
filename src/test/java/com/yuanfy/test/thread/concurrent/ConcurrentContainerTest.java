package com.yuanfy.test.thread.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import org.junit.Test;

/**
 * @Description: 并发容器测试
 * @author yuanfy
 * @date 2018年1月31日 下午2:31:51 
 * @version 1.0
 */
public class ConcurrentContainerTest {
    
    @Test
    public void testBarrier(){
        final CyclicBarrier cb = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("饭局正式开始。。。。");
            }
        });
        for (int i = 0; i < 5; i ++) {
            new Thread(i+""){
                public void run(){
                    try{
                        System.out.println("同学"+ Thread.currentThread().getName() + "到达饭店, 等待....");
                        cb.await();
                        System.out.println("同学"+ Thread.currentThread().getName() + " 开始吃饭，聊天....");
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    
    @Test
    public void testSemaphore() throws InterruptedException{
        final Semaphore semaphore = new Semaphore(5);
        
        for (int i = 0; i < 10; i ++) {
            new Thread(i+""){
                public void run(){
                    try{
                        semaphore.acquire();
                        System.out.println("子线程：" + Thread.currentThread().getName() + " 获取许可，进行自己的操作...");
                        Thread.sleep(3000);
                        semaphore.release();
                        System.out.println("-------子线程：" + Thread.currentThread().getName() + "释放许可后，还有" + semaphore.availablePermits() + "个许可");
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        Thread.sleep(20000);
        
        System.out.println("1234");
    }
    
    @Test
    public void testFutureTask() throws InterruptedException, ExecutionException{
        //方法一
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new Task());
        new Thread(futureTask1).start();
        System.out.println(futureTask1.get());
        
        System.out.println("----------------------------------");
        
        //方法二
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new Task());
        executor.submit(futureTask2);
        executor.shutdown();
        System.out.println(futureTask2.get());
    }
    
    @Test
    public void testCountDownLatch() throws InterruptedException{
        final CountDownLatch countThread = new CountDownLatch(5);
        for (int i = 0; i < 5; i ++) {
            new Thread(){
                public void run(){
                    System.out.println("sub Thread running");
                    countThread.countDown();
                }
            }.start();
        }
        System.out.println("等待子线程执行...");
        countThread.await();
        System.out.println("main Thread running");
    }
    
    @Test
    public void testBlockingQueue(){
        BlockingQueue<Integer> queue1 = new LinkedBlockingQueue<Integer>();
        queue1.add(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
        queue2.add(2);
    }
    
    @Test
    public void testConcurrentHashMap(){
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
        map.put("Aa", "1234");
        map.put("BB", "test");
        System.out.println(map.size());
    }
    
    @Test
    public void testCopyOnWrite(){
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<String>();
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<String>();
        cowList.add("1");
        set.add("2");
    }
    class Task implements Callable<Integer>{
        int sum = 0;
        @Override
        public Integer call() throws Exception {
            System.out.println("任务正在进行...");
            Thread.sleep(5000);
            for (int i = 0; i < 10; i ++) {
                sum += i;
            }
            return sum;
        }
        
    }
}
