package com.yuanfy.test.thread.threadlocal;

public class ThreadDemo implements Runnable{
    
    private static int num = 0;

    static ThreadLocal<Integer> t = new ThreadLocal<Integer>();
    static {
        t.set(num++);
    }
    
    @Override
    public void run(){
        t.set(num++);
        System.out.println(Thread.currentThread().getName() + " num:" + t.get());
    }
    
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo, "线程A").start();
        new Thread(threadDemo, "线程B").start();
        new Thread(threadDemo, "线程C").start();
    }
}
