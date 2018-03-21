package com.yuanfy.test.proxy.cglib;



public class CglibProxyTest {

    public static void main(String[] args) {
        
        CglibProxyInterceptor interceptor = new CglibProxyInterceptor();
        Demo proxy = (Demo)interceptor.getProxy(new Demo());
        proxy.demo();
        
    }
}
