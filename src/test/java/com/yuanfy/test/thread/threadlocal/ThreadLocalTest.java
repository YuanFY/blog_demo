package com.yuanfy.test.thread.threadlocal;

import org.junit.Test;

/**
 * @Description: ThreadLocal 测试类
 * @author yuanfy
 * @date 2018年1月26日 下午2:02:04 
 * @version 1.0
 */
public class ThreadLocalTest {

    /**
     * @Description: ThreadLocal类说明：这个类提供了线程局部变量。<br>
     * 这些变量与正常的对应变量不同，因为每个访问一个线程的线程（通过它的get或set方法）都有自己的，独立初始化的变量副本。 <br>
     * ThreadLocal实例通常是希望将状态与线程关联的类（例如，用户ID或事务ID）中的私有静态字段。<br>
     * <br>
     * 只要线程处于活动状态并且ThreadLocal实例可以访问，每个线程就拥有对其线程局部变量副本的隐式引用;<br>
     * 在一个线程消失之后，线程本地实例的所有副本都会被垃圾收集（除非存在对这些副本的其他引用）<br>
     * @author yuanfy
     * @date 2018年1月26日 下午2:08:3
     * @version 6.5
     */
    @Test
    public void test1(){
        ThreadLocalDemo test1 = new ThreadLocalDemo(10);  
        ThreadLocalDemo test2 = new ThreadLocalDemo(20);  
        test1.start();  
        test2.start();  
    }
    
    @Test
    public void test2(){
        final ThreadLocal<String> tl = new ThreadLocal<String>();
        tl.set("test2");
        Thread t = new Thread(){
            //@Override
            public void run() {
                System.out.println("threadLocal variable1 : " + tl.get());
            }
        };
        t.start();
    }
}
