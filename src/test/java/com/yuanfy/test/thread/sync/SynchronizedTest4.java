package com.yuanfy.test.thread.sync;

class Widget{
	public  void doSomething(){
		System.out.println("Widget doSomething...");
	}
}
class LoggingWidget extends Widget{
	
	public  void doSomething(){
		System.out.println("LoggingWidget doSomething...");
		super.doSomething();
	}
}
public class SynchronizedTest4 {
    public static void main(String[] args) {  
    	Widget lw = new LoggingWidget();
    	lw.doSomething();
    } 
}
