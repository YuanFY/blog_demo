package com.yuanfy.test.thread.sync;

public class SynchronizedTest2 implements Runnable {  
    public void run() {  
         synchronized(this) {  
              for (int i = 0; i < 5; i++) {  
                   System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);  
              }  
         }  
    }  
    public static void main(String[] args) {  
         SynchronizedTest2 t1 = new SynchronizedTest2();  
         //作用域都是t1,所以谁拥有t1对象谁就进行，其他等待。
         Thread ta = new Thread(t1, "A");  
         Thread tb = new Thread(t1, "B");  
         ta.start();  
         tb.start();  
    } 
}
