package com.yuanfy.test.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyHandler implements InvocationHandler{

    private Object target;
    
    public JDKProxyHandler(Object obj) {
        this.target = obj;
    }
    
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
            target.getClass().getInterfaces(), this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----before-----");
        Object result = method.invoke(target, args);
        System.out.println("-----after-----");
        return result;
    }

}
