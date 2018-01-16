package com.yuanfy.test.memory;

public class MemoryAllotTest {
    
    public static void main(String[] args) {
        Memory m = new Memory("test", 234);
        change(m);
        System.out.println(m);
        change2(m);
        System.out.println(m);
        objPoolTest();
    }
    public static void change(Memory m) {
        m.setName("11111");
    }
    public static void change2(Memory m) {
        m = new Memory("1234", 12);
    }
    
    public static void objPoolTest() {  
        int i = 40;  
        int i0 = 40; 
        Short s1 = 134;
        Short s2 = 134;
        Integer i1 = 133;  
        Integer i2 = 133;  
        Integer i3 = 0;  
        Integer i4 = new Integer(40);  
        Integer i5 = new Integer(40);  
        Integer i6 = new Integer(0);  
        Long l1 = 123L;
        Long l2 = 123L;
        Long l3 = 132L;
        Long l4 = 132L;
        
        Double d1=1.0;  
        Double d2=1.0;  
        Float f1 = 1f;
        Float f2 = 1f;
        
        
          
        System.out.println("i=i0\t" + (i == i0));  
        System.out.println("i1=i2\t" + (i1 == i2));  
        System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));  
        System.out.println("i4=i5\t" + (i4 == i5));  
        System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));      
        System.out.println("d1=d2\t" + (d1==d2));   
          
        System.out.println("f1=f2\t" + (f1==f2));           
        System.out.println("s1=s2\t" + (s1==s2));  
        System.out.println("l1=l2\t" + (l1==l2));       
        System.out.println("l3=l4\t" + (l3==l4));       
    }  
}

class Memory{
    private String name;
    private int size;
    
    public Memory(String name, int size) {
        super();
        this.name = name;
        this.size = size;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    @Override
    public String toString() {
        return "Memory [name="+name+", size="+size+"]";
    }
    
}