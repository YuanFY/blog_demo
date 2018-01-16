package com.yuanfy.test.sourcecode;

import org.junit.Test;

public class StringTest {

    @Test
    public void test(){
        String s2 = "Hello";
        String s1 = s2 + " world";//会创建一个StringBuilder类去实现拼接
        System.out.println(s1);
        s1.intern();
        String s3 = "hello world";
        System.out.println(s1 == s3);//false
    }
    
    @Test
    public void testNull(){
        String s2 = null;
        String s1 = new String(s2);
        System.out.println(s1);
    }
    @Test
    public void testHashCode(){
        String s2 = "12";
        String s1 = new String(s2);
        System.out.println(s1.hashCode());
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s2.hashCode());
    }
    /**
     * @Description: 基于jdk1.7测试。  jdk1.7 常量池被分配到了Java Heap，保存常量时，会创建一个引用来执行字符串
     * @author yuanfy
     * @date 2018年1月16日 上午11:27:52 
     * @version 1.0
     */
    @Test
    public void testItern(){
        String s1 = new String("Java");//此时Java会分配与常量池中
        s1.intern();//调用此方法时，发现常量池已经存在Java字符串，则直接返回。 s1还是原来new 分配的地址
        String s2 = "Java";//指向常量池的引用
        System.out.println(s1 == s2); //false
        
        //转换成stringbuilder 拼接后返回new String(value, 0, count); 此时test intern 是没有存放在常量池的。
        String s3 = new String("test") + new String(" intern");
        s2.intern();//调用此方法时，发现常量池不存则，则创建引用并返回
        String s4 = "test intern";//指向常量池的引用
        System.out.println(s3 == s4);// true
    }
    @Test
    public void stringTest(){
        String a = "a"+"b"+1;
        String b = "ab1";
        System.out.println(a == b);
    }
    
    @Test
    public void testStringBuilder(){
        StringBuilder sb = new StringBuilder();
        String s = null;
        sb.append(s);
        System.out.println(sb.toString());
        System.out.println(sb.length());
    }
}
