package com.yuanfy.test.container;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;


public class SetTest {
    @Test
    public void testHashSet(){
        Set<Integer> set = new HashSet<Integer>();
        set.add(null);
        System.out.println("size: " + set.size());//size: 1
    }
    
    @Test
    public void testPrintHashSet(){
        Set<Integer> set = new HashSet<Integer>();
        set.add(null);
        set.add(1);
        set.add(15);
        set.add(11);
        set.add(11);
        printSet(set);
    }
    @SuppressWarnings("rawtypes")
    public void printSet(Set set){
        Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
    @Test
    public void testLinkedHashSet(){
        Set<Integer> set = new LinkedHashSet<Integer>();
        set.add(null);
        set.add(1);
        set.add(15);
        set.add(11);
        set.add(13);
        printSet(set);
    }
    @Test
    public void testTreeSet(){
        Set<Integer> set = new TreeSet<Integer>();
        set.add(null);
        set.add(15);
        set.add(12);
        set.add(14);
        printSet(set);
    }
}
