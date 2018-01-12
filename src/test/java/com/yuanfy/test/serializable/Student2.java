package com.yuanfy.test.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @Description: 学生类 已实现序列化接口
 * @author yuanfy
 * @date 2018年1月11日 上午11:36:37 
 * @version 1.0
 */
public class Student2 implements Serializable{

    private static final long serialVersionUID = 61234L;

    private String name;
    
    private transient int age;
    
    private String sex;
    
    //如果覆盖了无参构造函数就一定要显示声明无参构造函数，跟父类序列化的案例一样。
    public Student2(){}
    
    public Student2(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    
    public  String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        name = "010101";
        out.writeUTF(name);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        name = in.readUTF();
    }
}
