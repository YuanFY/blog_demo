package com.yuanfy.study.bean.resolve;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class FactoryBeanTest {
	public static void main(String[] args) {
		ApplicationContext bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		Car car1 = (Car)bf.getBean("car");
		System.out.println(car1.getBrand());
		Car car2 = (Car)bf.getBean("&car");
		System.out.println(car2.getBrand());
	}
}
