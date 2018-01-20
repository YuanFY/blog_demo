package com.yuanfy.test.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

public class ListTest {
    
    @Test
    public void testArraysAsList(){
        String[] str = new String[] { "a", "b" };
        List<String> list = Arrays.asList(str);
        //list.add("c"); 报错。
        print(list);
        
        int[] nums = {1,2,3};
        List<int[]> list2 = Arrays.asList(nums);//当做一个对象处理。
        System.out.println(list2.size());
    }
    
    @SuppressWarnings("rawtypes")
    public void print(List list){
        for (int i = 0; i < list.size(); i ++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
    
    @Test
    public void testDeleteArrayList(){
        List<String> list = new ArrayList<String>();  
        list.add("1");  
        list.add("2");  
        list.add("3");  
        list.add("4");  
        list.add("5");  
        //会默认调用迭代器Itr，遍历时也会调用其内部方法。
        for (String item : list) {  
            if (item.equals("4")) {  
                System.out.println(item);  
                list.remove(item);  
            }  
        }  
        print(list);
    }
    
    @Test
    public void testSubList(){
        List<String> list = new ArrayList<String>();  
//        List<String> list = new LinkedList<String>();  
        list.add("1");  
        list.add("2");  
        list.add("3");  
        list.add("4");  
        list.add("5");  
        
        List<String> subList = list.subList(1, 2);
        subList.add("6");//这里操作仍然会添加的父list中。
        list.add("7");
        print(subList);//
    }
    @Test
    public void testArray(){
        List<String> list = new ArrayList<String>();  
        list.add("1");  
        list.add("2");  
        list.add("3");  
        list.add("4");  
        list.add("5");  
        
        String[] str = new String[list.size()];
        str = list.toArray(str);
        System.out.println(str.length);
    }
    
    @Test
    public void testEquals(){
        List<String> arryList = new ArrayList<String>();  
        arryList.add("1");  
        arryList.add("2");  
        arryList.add("3"); 
        
        List<String> linkedList = new ArrayList<String>();  
        linkedList.add("1");  
        linkedList.add("2");  
        linkedList.add("3");
        
        List<String> vector = new Vector<String>();  
        vector.add("1");  
        vector.add("2");  
        vector.add("3"); 
        
        System.out.println(linkedList.equals(arryList));
        System.out.println(linkedList.equals(vector));
        System.out.println(arryList.equals(vector));
    }
}
