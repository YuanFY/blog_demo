package com.yuanfy.test.thread.basic1;

public class PlateFactory {
	private boolean isEmpty = true;
	public synchronized void put(){
		if (!isEmpty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " add plate");
		isEmpty = false;
		notify();
	}
	
	public synchronized void get(){
		if (isEmpty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " remove plate");
		isEmpty = true;
		notify();
	}
}
