package com.yuanfy.test.thread.sync;

public class SyncDemo {
	public synchronized void test1() {  
        System.out.println("test1开始..");  
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println("test1结束..");  
    }
	
	public void test2() {  
        System.out.println("test2开始..");  
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println("test2结束..");  
    }  
	
	public synchronized void test3() {  
        System.out.println("test3开始..");  
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println("test3结束..");  
    }
}
