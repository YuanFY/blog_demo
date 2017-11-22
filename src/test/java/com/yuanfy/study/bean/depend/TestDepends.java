package com.yuanfy.study.bean.depend;

import org.junit.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description 测试循环依赖
 * @author YuanFY 
 * @date 2017年11月21日 下午9:34:12
 * @version 1.0
 */
public class TestDepends {
	/**
	 * <bean id="testA" class="com.yuanfy.study.bean.depend.TestA">
    	<constructor-arg index="0" ref="testB"/>
    </bean>
    <bean id="testB" class="com.yuanfy.study.bean.depend.TestB">
    	<constructor-arg index="0" ref="testC"/>
    </bean>
    <bean id="testC" class="com.yuanfy.study.bean.depend.TestC">
    	<constructor-arg index="0" ref="testA"/>
    </bean>
	 */
	@Test(expected = BeanCurrentlyInCreationException.class)
	public void testConstructor(){
		new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	/**
	<bean id="testA" class="com.yuanfy.study.bean.depend.TestA" scope="prototype">
    	<property name="testB" ref="testB"/>
    </bean>
    <bean id="testB" class="com.yuanfy.study.bean.depend.TestB" scope="prototype">
    	<property name="testC" ref="testC"/>
    </bean>
    <bean id="testC" class="com.yuanfy.study.bean.depend.TestC" scope="prototype">
    	<property name="testA" ref="testA"/>
    </bean>
	 */
	@Test
	public void testSetter(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestA a = context.getBean(TestA.class);
		System.out.println(a);
		//a.a();
	}
}
