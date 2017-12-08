package com.yuanfy.test.scope1;

/**
 * @Description: 同包下面测试 四种作用域
 * @author yuanfy
 * @date 2017年12月8日 下午5:11:12 
 * @version 6.5
 */
public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person("James", 32, "red");
        
        //System.out.println("name:" + person.name);//编译报错，所以private修饰的 是不能在同包下的其他类访问
        System.out.println("age:" + person.age);// default
        System.out.println("color:" + person.color);//protected
        person.say();//public
    }
}
