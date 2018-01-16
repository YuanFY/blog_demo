package com.yuanfy.test.sourcecode;

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
}
