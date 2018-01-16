package com.yuanfy.test.sourcecode;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.yuanfy.test.enums.Week;
import com.yuanfy.test.enums.Week1;
import com.yuanfy.test.enums.Week2;

public class EnumTest {
    
    @Test
    public void testTraverse(){
        Week[] values = Week.values();
        for (Week w : values) {
            System.out.println(w.name()+ " : " + w.ordinal());
        }
    }
    
    @Test
    public void testSwitch(){
        Week2 w = Week2.Monday;
        switch (w) {
        case Monday:
            System.out.println(w.name);
            break;
        case Firday:
            System.out.println("星期二");
            break;
        default:
            break;
        }
    }
    
    @Test
    public void testMethod(){
        Week1 w = Week1.Firday;
        System.out.println(w.toString());
    }
    
    @Test
    public void testEnumSet(){
        EnumSet<Week1> set = EnumSet.allOf(Week1.class);
        for (Week1 w : set) {
            System.out.println(w.name()+ " : " + w.ordinal());
        }
    }
    
    @Test
    public void testEnumMap(){
        EnumMap<Week1, String> enumMap = new EnumMap<Week1, String>(Week1.class);
        enumMap.put(Week1.Monday, "星期一");
        enumMap.put(Week1.Firday, "星期二");
        enumMap.put(Week1.Wednesday, "星期三");
        enumMap.put(Week1.Thursday, "星期四");
        
        for (Map.Entry<Week1, String> entry : enumMap.entrySet()){
        	Week1 w = entry.getKey();
        	System.out.println(w.name()+ " : " + w.ordinal() + ", value=" + entry.getValue());
        }
    }
}
