package com.yuanfy.test.proxy.jdk;

public class Demo implements DemoFactory{

    @Override
    public void demo() {
        System.out.println("产生一个demo");
    }

}
