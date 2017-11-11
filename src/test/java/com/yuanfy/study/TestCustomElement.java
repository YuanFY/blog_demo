package com.yuanfy.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yuanfy.monitorsite.system.entity.UserEntity;

public class TestCustomElement {
	public static void main(String[] args) {
		ApplicationContext bf = new ClassPathXmlApplicationContext("com/yuanfy/study/test.xml");
		UserEntity e = (UserEntity)bf.getBean("testBane");
		System.out.println(e.getId() + " " + e.getName());
	}
}
