package com.yuanfy.test.serializable;

public class Person {
    public int age;
    
    public int weight;
    
    //必须提供无参构造函数
    public Person(){}
    
    public Person(int age, int weight) {
        this.age = age;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
}
