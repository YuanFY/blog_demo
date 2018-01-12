package com.yuanfy.test.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @Description: 学生类 已实现序列化接口
 * @author yuanfy
 * @date 2018年1月11日 上午11:36:37 
 * @version 1.0
 */
public class Student implements Externalizable{

    private static final long serialVersionUID = 61234L;

    private String name;
    
    private transient int age;
    
    private static String sex;
    
    public Student(String name, int age, String sex) {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // TODO Auto-generated method stub
        out.writeUTF(name);
        out.writeInt(age);
        out.writeObject(sex);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.age = in.readInt();
        this.sex = (String)in.readObject();
    }
}
