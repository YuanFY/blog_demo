package com.yuanfy.test.thread.sync;


public class Thread2 implements Runnable {
    int number = 10;

    public void firstMethod() throws Exception {
        synchronized (this) {
            number += 100;
            System.out.println(number);
        }
    }

    public void secondMethod() throws Exception {
        synchronized (this) {
            /**
             * (休息2S,阻塞线程)
             * 以验证当前线程对象的机锁被占用时,
             * 是否被可以访问其他同步代码块
             */
            //Thread.sleep(5000);
            this.wait(5000);
            number *= 200;
        }
    }

    @Override
    public void run() {
        try {
            while(true)
            firstMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread2 threadTest = new Thread2();
        Thread thread = new Thread(threadTest);
        thread.start();
        threadTest.secondMethod();
    }
}
