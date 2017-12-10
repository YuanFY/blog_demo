package com.yuanfy.test.scope2;

import com.yuanfy.test.scope1.Person;
/**
 * @Description: 在子孙类下测试 四种作用域
 * @author yuanfy
 * @date 2017年12月8日 下午5:24:30 
 * @version 6.5
 */
public class BlackPerson extends Person{

    public BlackPerson(String name, int age) {
        super(name, age, "black");
    }
    public static void main(String[] args) {
        BlackPerson person = new BlackPerson("James", 32);
        
        //System.out.println("name:" + person.name);//private 编译报错，所以private修饰的 是不能在不同包下的子孙类访问
        //System.out.println("age:" + person.age);// default  编译报错，所以default修饰的时不能在不同包下的子孙类访问
        System.out.println("color:" + person.color);//protected 
        person.say();//public
    }
}
