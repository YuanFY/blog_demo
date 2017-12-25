package com.yuanfy.test.exception;

import org.junit.Test;

public class ExceptionTest {
    
    @Test
    public void test1() {
        System.out.println("基本类型mod1()=" + mol4());
        System.out.println("字符串类型mod2()=" + mol5());
    }
    
    public int mol4() {
        int count = 0;
        int[] arr = {1,3,5,0};
        try{
            for (int i = 0; i < arr.length; i ++) {
                arr[i] = 10/arr[i];
            }
        }catch (ArithmeticException e){
            return count;
        }finally{
            count = -1;
            return count;//直接返回值，不会在进入catch块中执行
        }
    }
    
    public String mol5() {
        String msg = "success";
        int[] arr = {1,3,5,0};
        try{
            for (int i = 0; i < arr.length; i ++) {
                arr[i] = 10/arr[i];
            }
        }catch (ArithmeticException e){
            throw new ArithmeticException("分母不能为0");
        }finally{
            //字符串修改无效，其实这相当于一个局部变量
            msg = "fail, finally";
            return msg;//直接返回值，不会在进入catch块抛出异常
        }
        //return msg;
    }
    
    public int mol1() {
        int count = 0;
        int[] arr = {1,3,5,0};
        try{
            for (int i = 0; i < arr.length; i ++) {
                arr[i] = 10/arr[i];
            }
        }catch (ArithmeticException e){
            return count;
        }finally{
            //基本类型修改无效，其实这相当于一个局部变量
            count = -1;
        }
        return count;
    }
    
    public String mol2() {
        String msg = "success";
        int[] arr = {1,3,5,0};
        try{
            for (int i = 0; i < arr.length; i ++) {
                arr[i] = 10/arr[i];
            }
        }catch (ArithmeticException e){
            msg = "fail";
            return msg;
        }finally{
            //字符串修改无效，其实这相当于一个局部变量
            msg = "fail, finally";
        }
        return msg;
    }
    public StringBuffer mol3() {
        StringBuffer msg = new StringBuffer();
        int[] arr = {1,3,5,0};
        try{
            for (int i = 0; i < arr.length; i ++) {
                arr[i] = 10/arr[i];
            }
        }catch (ArithmeticException e){
            msg.append("fail");
            return msg;
        }finally{
            //对象类型，其成员变量char[] value;的值是可以被改变的
            msg.append(", finally");
        }
        return msg;
    }
    
    @Test
    public void test2() {
        int[] arr = new int[9000000];
        long startTime = System.currentTimeMillis();
        for (int i = 0 ; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }
    @Test
    public void test3() {
        int[] arr = new int[9000000];
        long startTime = System.currentTimeMillis();
        try{
            int i = 0;
            while(true) {
                arr[i] = i++; 
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("error");
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }
}

