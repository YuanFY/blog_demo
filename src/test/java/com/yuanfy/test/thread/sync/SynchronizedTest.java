package com.yuanfy.test.thread.sync;


class Sync {  
    
    public synchronized void test() {  
        System.out.println("test开始..");  
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println("test结束..");  
    }  
}  
  
class MyThread extends Thread {  
    static Sync sync = new Sync();  
    public void run() {  
        sync.test();  
    }  
}

public class SynchronizedTest {
    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
    }
    
}
