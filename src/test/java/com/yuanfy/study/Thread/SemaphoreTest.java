package com.yuanfy.study.Thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.junit.Test;

public class SemaphoreTest {

	@Test
	public void test(){
		ExecutorService service  = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(3);
		System.out.println(semaphore.availablePermits());
		for (int i = 0; i < 10; i ++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有"
							+ (3 - semaphore.availablePermits()) + "个并发");
					
					try {
						long time = (long)(Math.random()) * 10000;
						System.out.println(time);
						Thread.sleep(time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
					
					semaphore.release();
					
//					System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有"
//							+ (3 - semaphore.availablePermits()) + "个线程");
				}
			};
			service.execute(runnable);           
		}
		
	}
	
	@Test
	public void test2(){
		ExecutorService service = Executors.newCachedThreadPool(); 
        final  CyclicBarrier cb = new CyclicBarrier(3); 
        for(int i=0;i<3;i++){ 
            Runnable runnable = new Runnable(){ 
            	@Override
            	public void run(){ 
                    try { 
                       
                        System.out.println("线程" + Thread.currentThread().getName() +  
                                "即将到达集合地点1，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，" + (cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));                        
                        cb.await(); 
                           
                        //Thread.sleep((long)(Math.random()*10000));   
                        System.out.println("线程" + Thread.currentThread().getName() +  
                                "即将到达集合地点2，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，" + (cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候")); 
                        cb.await();  
                        //Thread.sleep((long)(Math.random()*10000));   
                        System.out.println("线程" + Thread.currentThread().getName() +  
                                "即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));                      
                        cb.await();                      
                    } catch (Exception e) { 
                        e.printStackTrace(); 
                    }                
                } 
            }; 
            service.execute(runnable); 
        } 
        //service.shutdown();
	}
}
