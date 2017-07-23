package com.yuanfy.study;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;

import com.yuanfy.monitorsite.system.entity.UserEntity;

public class BeanWrapperTest {
    
    public static void main(String[] args) {
        UserEntity user = new UserEntity();
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(user);
        bw.setPropertyValue("userName", "zs");
        System.out.println(user.getUserName());
        
        PropertyValue value = new PropertyValue("userName", "ls");
        bw.setPropertyValue(value);
        System.out.println(user.getUserName());
        
    }
}
