package com.yuanfy.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Aspect
public class AspectJTest {
    @Pointcut("execution(* *.test(..))")
    public void test(){
        System.out.println("AspectJTest");
    }
    @Before("test()")
    public void beforeTest(){
        System.out.println("beforeTest");
    }
    @After("test()")
    public void afterTest(){
        System.out.println("afterTest");
    }
    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.out.println("before1");
        Object o = null;
        try {
            o = p.proceed();
        }
        catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("after1");
        return o;
    }
    
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestBean t = (TestBean)ac.getBean("test");
        t.test();
    }
}
