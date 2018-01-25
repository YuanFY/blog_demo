package com.yuanfy.test.thread.basic2;

import org.junit.Test;

/**
 * @description runnable实现 火车票 卖票窗口
 * @author YuanFY 
 * @date 2018年1月25日 下午10:33:13
 * @version 1.0
 */
public class ThreadTest {

	@Test
	public void test(){
		TicketDemo d1 = new TicketDemo();
		new Thread(d1, "窗口A").start();
		new Thread(d1, "窗口B").start();
		new Thread(d1, "窗口C").start();
	}
}
