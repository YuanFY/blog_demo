package com.yuanfy.test.thread;

import org.junit.Test;

public class ThreadDemo {
    
    @Test
    public void testYield(){
        MyThread mt = new MyThread();
        mt.setPriority(Thread.MAX_PRIORITY);
        mt.start();
        
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        for (int i = 0; i < 5; i ++) {
            Thread.yield();
            System.out.println(Thread.currentThread().getName()  + " in control");
        }
    }
    
    @Test
    public void testJoin() throws InterruptedException{
        MyThread mt = new MyThread();
        mt.setPriority(Thread.MAX_PRIORITY);
        mt.start();
        
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        mt.join();
        for (int i = 0; i < 5; i ++) {
            System.out.println(Thread.currentThread().getName()  + " in control");
        }
    }
}
