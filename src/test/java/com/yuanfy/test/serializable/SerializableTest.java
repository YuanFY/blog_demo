package com.yuanfy.test.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * @Description: 序列化测试类
 * @author yuanfy
 * @date 2018年1月11日 上午11:39:13 
 * @version 1.0
 */
public class SerializableTest {

    /**
     * @Description: 测试序列化
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @author yuanfy
     * @date 2018年1月11日 下午5:17:49 
     * @version 1.0
     */
    @Test
    public void testSerializable() throws FileNotFoundException, IOException{
        Student s = new Student("james", 31, "man");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("E:\\Student.txt")));
        oos.writeObject(s);
        oos.close();
    }
    
    /**
     * @Description: 测试反序列化
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @author yuanfy
     * @date 2018年1月11日 下午5:17:59 
     * @version 1.0
     */
    @Test
    public void testDeserialize() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:\\Student.txt")));
        Student s = (Student)ois.readObject();
        System.out.println(s);
        ois.close();
    }
    
    @Test
    public void test2() throws FileNotFoundException, IOException, ClassNotFoundException{
        Women w = new Women("Scarlett Johansson", 34, 50);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("E:\\Women.txt")));
        oos.writeObject(w);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:\\Women.txt")));
        Women s = (Women)ois.readObject();
        System.out.println(s);
        ois.close();
    }
    
    @Test
    public void test3() throws FileNotFoundException, IOException, ClassNotFoundException{
        Student s1 = new Student("james", 31, "man");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("E:\\Student.txt")));
        oos.writeObject(s1);
        oos.flush();
        s1.setAge(1);
        oos.writeObject(s1);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:\\Student.txt")));
        Student s2 = (Student)ois.readObject();
        System.out.println(s2);
        Student s3 = (Student)ois.readObject();
        System.out.println(s3);
        ois.close();
    }
    
    @Test
    public void test4() throws FileNotFoundException, IOException, ClassNotFoundException{
        Student2 s1 = new Student2("james", 31, "man");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("E:\\Student.txt")));
        oos.writeObject(s1);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:\\Student.txt")));
        Student2 s2 = (Student2)ois.readObject();
        System.out.println(s2);
        ois.close();
    }
}
