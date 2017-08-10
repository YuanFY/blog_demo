package com.yuanfy.monitorsite.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: 上下文工具类：加载spring配置信息，并获取spring加载的bean
 * @author yuanfy
 * @date 2017年8月10日 下午6:52:51 
 * @version 1.0
 */
public class ApplicationContextUtil {
    
    private static String classpath = "applicationContext.xml";
    
    private static ApplicationContext context = new ClassPathXmlApplicationContext(classpath);
    
    public static <T>T getBeanByClass(Class<T> c) {
        return context.getBean(c);
    }
}
