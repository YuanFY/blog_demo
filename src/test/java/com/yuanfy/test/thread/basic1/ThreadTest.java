package com.yuanfy.test.thread.basic1;

import org.junit.Test;

/**
 * @description 单个模式的消费者与生产者
 * @author YuanFY 
 * @date 2018年1月25日 下午9:52:16
 * @version 1.0
 */
public class ThreadTest {
	
	@Test
	public void test(){
		PlateFactory pf = new PlateFactory();
		new Thread(new Producter(pf), "生产者").start();
		new Thread(new Consumer(pf), "消费者A").start();
		//new Thread(new Consumer(pf), "消费者B").start(); //多个消费者就会有问题
	}
	
	@Test
	public void test1() throws InterruptedException{
		Object obj = new Object();
		//Object lock = new Object();
		synchronized (obj) {
			obj.wait();
	        obj.notifyAll();
		} 
	}
	@Test
	public void test2(){
		Object obj = new Object();
		obj.notify();
	}
}
