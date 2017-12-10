package com.yuanfy.test.statics;
/**
 * @description static修饰符加载的先后顺序
 * @author YuanFY 
 * @date 2017年12月10日 上午11:48:00
 * @version 1.0
 */
class Test{
	public Test(){
		System.out.println("test()");
	}
}
public class StaticTest3 {
	static {
		System.out.println("代码块1");
	}
	private static Test test = new Test();
	static {
		System.out.println("代码块2");
	}
	
	public static void test(){
		System.out.println("StaticTest3 test()");
	}
	
	static class Test2{
		static Test test2 = new Test();
		static {
			System.out.println("12");
		}
	}
	public static void main(String[] args) {
		StaticTest3.test();
		StaticTest3.Test2.test2.toString();
	}
}
