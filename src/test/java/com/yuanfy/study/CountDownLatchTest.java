package com.yuanfy.study;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * @description CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完成后在执行。
 * CountDownLatch 是通过一个计数器来实现的， 计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。
 * 当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁等待的线程就可以恢复执行任务。
 * <br/>
 * 使用场景：等待线程执行完再执行主线程
 * @author YuanFY 
 * @date 2017年4月23日 上午10:23:19
 * @version 1.0
 */
public class CountDownLatchTest {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @description 只有等待所有工人都完成工作，主线程才开始工作。
	 * @throws InterruptedException
	 * @author YuanFY
	 * @date 2017年4月23日 下午3:57:55
	 * @version 1.0
	 */
	@Test
	public void test() throws InterruptedException{
		CountDownLatch latch = new CountDownLatch(2);
		Worker zs = new Worker("张三", 5000, latch);
		Worker ls = new Worker("李四", 6000, latch);
		zs.start();
		ls.start();
		latch.await();
		System.out.println("all work dona at " + sdf.format(new Date()));
	}
}

class Worker extends Thread {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String workerName;
	private int workTime;
	private CountDownLatch latch;
	public Worker(String workerName, int workTime, CountDownLatch latch) {
		this.workerName = workerName;
		this.workTime = workTime;
		this.latch = latch;
	}
	
	public void run() {
		System.out.println("Worker " + workerName + " do work begin at " + sdf.format(new Date()));
		try {
			doWork();
			System.out.println("Worker " + workerName + " do work complete at " + sdf.format(new Date()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			latch.countDown();
		}
	}
	
	public void doWork() throws InterruptedException{
		Thread.sleep(workTime);
	}
}