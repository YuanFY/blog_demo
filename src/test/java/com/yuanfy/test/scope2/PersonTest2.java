package com.yuanfy.test.scope2;

import com.yuanfy.test.scope1.Person;

/**
 * @Description: 不同包下面测试 四种作用域
 * @author yuanfy
 * @date 2017年12月8日 下午5:11:12 
 * @version 6.5
 */
public class PersonTest2 {
    public static void main(String[] args) {
        Person person = new Person("James", 32, "red");
        
        //System.out.println("name:" + person.name);//private 编译报错，所以private修饰的 是不能在其他包下的任何类访问（除了自身）
        //System.out.println("age:" + person.age);// default 编译报错，所以default修饰的  是不能在其他包下访问的。
        //System.out.println("color:" + person.color);//protected 编译报错，所以default修饰的  是不能在其他包下访问的。
        person.say();//public
    }
}
