package com.yuanfy.test.enums;

public enum Week2{
    Monday("星期一"), Tuesday, Wednesday, Thursday, Firday("星期五");
    
    public String name;
    private Week2(String name){
        this.name = name;
    }
    Week2(){}
    
    public void test(){
        System.out.println("1234");
    }
}
