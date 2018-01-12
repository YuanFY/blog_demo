package com.yuanfy.test.serializable;

import java.io.Serializable;

public class Women extends Person implements Serializable{
    
    private static final long serialVersionUID = -1259423203325949704L;
    
    private String name;
    //必须提供无参构造函数
    public Women(){}
    
    public Women(String name, int age, int weight) {
        super(age, weight);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", weight=" + weight + "]";
    }
}
