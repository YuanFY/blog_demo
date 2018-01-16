package com.yuanfy.test.sourcecode;

import org.junit.Test;

/**
 * @Description: 装箱与拆箱类测试
 * @author yuanfy
 * @date 2018年1月15日 下午1:47:41 
 * @version 1.0
 */
public class EightBasicTypeTest implements Cloneable{
    @Test
    public void testByte(){
        //byte的范围是-128~127，针对byte如果赋值不在范围eclipse会要求强制转型成byte.
        //封装类型Byte  new Byte("128")当使用字符串创建实例时，其中值超过范围会报错NumberFormatException。
        byte b1 = 12;
        Byte b2 = 12;//(byte)129;//超过范围要求强转
        Byte b3 = 12;//(byte)129;
        //Byte b4 = new Byte("128");//抛出异常
        Byte b4 = new Byte("12");//抛出异常
        System.out.println(b1 == b2);//true
        System.out.println(b2 == b3);//true
        System.out.println(b2 == b4);//false
    }
    
    @Test
    public void testShort(){
        //1、short范围：-32768 ~ 32767
        Short s = new Short("32767");//超过范围会报错 NumberFormatException
        s = new Short((short)327671);//超过这个范围自动转换
        
        //2、装箱与拆箱 自动转型
        short s1 = 12;
        Short s2 = new Short(s1);//手动装箱
        System.out.println("s1 == s2:" + (s1 == s2));//自动拆箱  true
        
        //3、valueOf方法缓存了-128~127的范围，超过这个范围就要另外创建这个实例。
        Short s3 = 12;
        Short s4 = 12;
        Short s5 = 128;
        Short s6 = 128;
        System.out.println("s3 == s4:" + (s3 == s4)); //true
        System.out.println("s5 == s6:" + (s5 == s6)); //false
        
        //4、由于上面这个特性，所以这种包装类型不能在循环遍历中赋值。不然其值超过这个范围的话，就会创建新的对象，如果很多的话，就会创建很多对象。浪费空间。
    }
    
    @Test
    public void testInteger(){
        //1、Integer范围：-2147483648  ~ 2147483647 
        //后面与Short一样
        //2、装箱与拆箱 自动转型
        int s1 = 12;
        Integer s2 = new Integer(s1);//手动装箱
        System.out.println("s1 == s2:" + (s1 == s2));//自动拆箱 true
        
        //3、valueOf方法缓存了-128~127的范围，超过这个范围就要另外创建这个实例。
        Integer s3 = 12;
        Integer s4 = 12;
        Integer s5 = 128;
        Integer s6 = 128;
        System.out.println("s3 == s4:" + (s3 == s4));//true
        System.out.println("s5 == s6:" + (s5 == s6));//false
        //4、由于上面这个特性，所以这种包装类型不能在循环遍历中赋值。不然其值超过这个范围的话，就会创建新的对象，如果很多的话，就会创建很多对象。浪费空间。
    }
    
    @Test
    public void testLong(){
        //范围就不考虑了。
        //同样、valueOf方法缓存了-128~127的范围，超过这个范围就要另外创建这个实例。
        Long s3 = 12L;
        Long s4 = 12L;
        Long s5 = 128L;
        Long s6 = 128L;
        System.out.println("s3 == s4:" + (s3 == s4));//true
        System.out.println("s5 == s6:" + (s5 == s6));//false
        //由于上面这个特性，所以这种包装类型不能在循环遍历中赋值。不然其值超过这个范围的话，就会创建新的对象，如果很多的话，就会创建很多对象。浪费空间。
    }
    
    @Test
    public void testFloat(){
        //没有特殊要注意的，其他跟上面一样
        int f = 1;
        Float f1 = 1F;
        Float f2 = new Float(f);
        System.out.println(f == f1);//true
        System.out.println(f1 == f2);//false
        //注意不要用这类型做加减运算，精度问题会影响。
        System.out.println(f1 - 0.1f*9);//0.099999964
    }
    
    @Test
    public void testDouble(){
        //注意不要用这类型做加减运算，精度问题会影响。
        System.out. println(1.0 - 0.1*9);//0.09999999999999998
        //valueof 
        Double i1 = 100.0;
        Double i2 = 100.0;
        Double i3 = 200.0;
        Double i4 = 200.0;
        System.out.println(i1==i2);//false
        System.out.println(i3==i4);//false
    }
    @Test
    public void testCharacter(){
        //注意valueof的缓存范围： ASCII码 小于等于127  
        Character c1 = 'a';
        Character c2 = 'a';
        System.out.println(c1 == c2);
        Character c3 = '含';
        Character c4 = '含';
        System.out.println(c3 == c4);
    }
    @Test
    public void testBoolean(){
        Boolean s1 = true;
        Boolean s2 = true;
        System.out.println(s1 == s2);//true
        Boolean s3 = new Boolean("true");
        System.out.println(s1 == s3);//false
    }
    
    @Test
    public void testEquals(){
        Short num1 = 1;
        Integer num2 = 1;
        Long num3 = 1L;
        System.out.println(num1.equals(num2));//false
        System.out.println(num2.equals(num3));//false
    }
    
    @Test
    public void testBasic(){
        byte b1 = 1;
        //b1 = b1 + 1;
        b1 += 2;
        b1 ++;
        
        short s1 = 1;
        //s1 = s1 + 1;
        s1 += 2;
        s1 ++;
        
        char c1 = 'v';
        //c1 = c1 + 1;
        c1 += '2';
        c1 ++;
    }
    
    @Test
    public void test1(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;
         
        System.out.println(c==d);//true  有缓存
        System.out.println(e==f);//false 没缓存创建新实例
        System.out.println(c==(a+b));//true a+b会转成int类型  所以只要比较值
        System.out.println(c.equals(a+b));//true 先拆箱再装箱。同一类型，值相同
        System.out.println(g==(a+b));//true a+b会转成int类型，所以左边也会等于long类型 所以只要比较值
        System.out.println(g.equals(a+b));//false 先拆箱再装箱a+b还是Integer，这里不会自动转型。equals判断不是同一类型直接返回false
        System.out.println(g.equals(a+h));//true, a+h 会转成Long类型。
    }
}
