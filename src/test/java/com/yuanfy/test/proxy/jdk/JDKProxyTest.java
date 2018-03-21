package com.yuanfy.test.proxy.jdk;


public class JDKProxyTest {

    public static void main(String[] args) {
        
        DemoFactory target = new Demo();
        
        JDKProxyHandler handler = new JDKProxyHandler(target);
        
        DemoFactory proxy = (DemoFactory)handler.getProxy();
        
        proxy.demo();
        
    }
}
