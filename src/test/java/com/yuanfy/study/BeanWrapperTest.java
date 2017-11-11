package com.yuanfy.study;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;

import com.yuanfy.monitorsite.system.entity.UserEntity;

public class BeanWrapperTest {
    
    public static void main(String[] args) {
        UserEntity user = new UserEntity();
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(user);
        bw.setPropertyValue("name", "zs");
        System.out.println(user.getName());
        
        PropertyValue value = new PropertyValue("name", "ls");
        bw.setPropertyValue(value);
        System.out.println(user.getName());
        
    }
}
