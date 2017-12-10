package com.yuanfy.test.statics;

import static org.springframework.util.StringUtils.*;
import static com.yuanfy.monitorsite.common.util.StringUtils.*;
class Student {
	//非静态变量
	private String name;
	int age;
	//静态变量、全局变量
	public static String schoolName = "清华大学";

	public Student(){}
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public static void say(){
		//2、由于静态方法是通过类直接调用的，所以静态方法中没有this对象。
		//System.out.println("调用者:" + this);//编译失败
		//3、静态方法可以调用静态变量或方法，但是不能调用非静态变量或方法
		//Cannot make a static reference to the non-static 
		//System.out.println("调用非静态变量name：" + name);//编译失败
		//但是可以调用静态方法
		System.out.println("调用静态变量schoolName:" + schoolName);
	}
	public void setName(){
		//非静态方法可以使用this，知道是哪个实例对象在调用。
		System.out.println("调用者：" + this);
	}
}
class StudentDTO extends Student{

	//编译报错； 4、静态方法不能被覆盖覆盖。因为静态方法是编译时静态绑定的，而覆盖是基于运行时动态绑定的。
	//@Override
//	public static void say(){
//		
//	}
	
}
public class StaticTest {
	private static int startPrice = 10;//起步价
	
	private static int kmPrice = 2;//每一公里2块钱
	
	private int kmCount;//里程计时器
	
	static class Car1{
		//编译成功， 2、非静态内部类无法声明静态方法和静态变量，只有静态内部类才可以。
		public static void start(){
			//编译失败，3、非静态内部类可以随意访问外部类的成员或方法，而静态内部类只能访问静态变量或方法。
//			kmCount = 0;
			System.out.println("car1汽车启动，起步价是：" + startPrice);
		}
	}
	class Car2{
		//编译报错。 2、非静态内部类无法声明静态方法和静态变量，只有静态内部类才可以。
//		public static void start(){
//			System.out.println("开始启动");
//		}
		
		public void start2(){
			//编译成功，3、非静态内部类可以随意访问外部类的成员或方法，而静态内部类只能访问静态变量或方法。
			kmCount = 0;
			System.out.println("car2汽车启动，起步价是：" + startPrice);
		}
	}
	public static void main(String[] args) {
		StaticTest.Car1.start();
		//编译报错 4、调用静态内部类不需要持有外部内的引用可以直接调用，而非静态内部类就必须持有外部内的引用才能调用
//		StaticTest.Car2.start();
		
		//编译成功
		StaticTest.Car2 car2 = new StaticTest().new Car2();
		car2.start2();
		
	}
}
