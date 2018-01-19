package com.yuanfy.test.statics;

public class StaticTest4 {
    static{
        System.out.println("StaticTest4 static");
    }
    public static void main(String[] args) {
        new MyClass();
    }
}
 
class Person{
    static{
        System.out.println("person static");
    }
    public Person(String str) {
        System.out.println("person "+str);
    }
}
 
class TestClass {
    Person person = new Person("Test");
    static{
        System.out.println("test static");
    }
     
    public TestClass() {
        System.out.println("test constructor");
    }
}
class MyClass extends TestClass {
    Person person = new Person("MyClass");
    static{
        System.out.println("myclass static");
    }
     
    public MyClass() {
        System.out.println("myclass constructor");
    }
}