package com.yuanfy.test.thread.sync;

import org.junit.Test;


class MyThread extends Thread { 
	//每次都创建了不同的对象，所以synchronized绑定的就不是同一个对象了。 
    static SyncDemo sync = new SyncDemo();  
    public void run() {  
        sync.test1();  
    }  
}

public class SynchronizedTest {
	/**
	 * @description 因为使用单元测试线程 会有影响所以使用main方法来执行。
	 * @param args
	 * @author YuanFY
	 * @date 2018年1月27日 下午9:01:09
	 * @version 1.0
	 */
    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
    }
    /**
     * @description 因为在JUnit测试框架中，主线程退出，子线程也会退出
     * @throws InterruptedException
     * @author YuanFY
     * @date 2018年1月27日 下午10:11:12
     * @version 1.0
     */
    @Test
    public void test() throws InterruptedException{
    	Thread t1 = new MyThread();
    	Thread t2 = new MyThread();
    	t1.start();
    	t2.start();
    	// 支持线程的单元测试。可以执行
    	//Thread.currentThread().join();
    	//支持线程的单元测试。
    	Thread.sleep(5000);
    }
}
