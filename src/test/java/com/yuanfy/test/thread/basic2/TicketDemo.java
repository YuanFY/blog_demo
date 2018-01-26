package com.yuanfy.test.thread.basic2;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketDemo implements Runnable{
	//原子保证不会出现-1的情况
	private AtomicInteger ticket = new AtomicInteger(100);
	
	@Override
	public void run() {
		while(ticket.intValue() > 0){
			
			System.out.println(Thread.currentThread().getName() + " 卖票一张，剩余:" + (ticket.decrementAndGet()));
		}
	}

}
