package com.yuanfy.test.thread.sync;
/**
 * @description 
 * 1、当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
 * 2、当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
 * @author YuanFY 
 * @date 2018年1月27日 下午9:04:53
 * @version 1.0
 */
public class SynchronizedTest3 {
    public static void main(String[] args) {  
        final SyncDemo demo = new SyncDemo();
        Thread t1 = new Thread(){
        	public void run(){
        		demo.test1();
        		demo.test3();
        	}
        };
        Thread t2 = new Thread(){
        	public void run(){
        		//1、当一个线程访问object的一个synchronized(this)同步代码块时，
        		//另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
        		demo.test2();
        	}
        };
        Thread t3 = new Thread(){
        	public void run(){
        		//1、当一个线程访问object的一个synchronized(this)同步代码块时，
        		//其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
        		demo.test3();
        	}
        };
        t1.start();
        t2.start();
        t3.start();
    } 
}
