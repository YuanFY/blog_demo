package com.yuanfy.test.thread.threadlocal;


public class ThreadLocalDemo extends Thread{

    public static ThreadLocal<Integer> intThreadLocal = new ThreadLocal<Integer>();  
    public static ThreadLocal<String> strThreadLocal = new ThreadLocal<String>();  
      
    public Integer num;  

    public ThreadLocalDemo(Integer num) {  
        super("线程" + num);  
        this.num = num;  
    }  
  
    @SuppressWarnings("static-access")
    @Override  
    public void run() {  
        Integer n = intThreadLocal.get();  
        if(num != 20) {  
            String s = strThreadLocal.get(); 
            System.out.println(this.currentThread().getName() + " str=" + s);
        }  
              
        if(n == null) {  
            intThreadLocal.set(num);  
        }  
        System.out.println(this.currentThread().getName() + " int=" + intThreadLocal.get());  
    }  
    
}
