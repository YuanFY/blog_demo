package com.yuanfy.test.scope1;

/**
 * @Description: 父类
 * @author yuanfy
 * @date 2017年12月8日 下午5:04:35 
 * @version 6.5
 */
public class Person {
    private String name;
    int age;
    protected String color;
    
    public Person(){}
    
    public Person(String name, int age, String color) {
        super();
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public void say(){
        System.out.println(this.name + " say: I am " + this.color + " Person");
    }
}
