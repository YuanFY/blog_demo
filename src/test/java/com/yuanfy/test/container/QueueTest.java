package com.yuanfy.test.container;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import org.junit.Test;



public class QueueTest {
    @Test
    public void testRemove(){
        Queue<String> queue = new ArrayDeque<String>();
        queue.add("1");
        queue.offer("2");
        //remove 没有元素时会报错：java.util.NoSuchElementException
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
    
    @Test
    public void testPoll(){
        Queue<String> queue = new ArrayDeque<String>();
        queue.add("1");
        queue.offer("2");
        //没有元素时会返回null
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
    
    @Test
    public void testElement(){
        Queue<String> queue = new ArrayDeque<String>();
        //queue.add("1");
        //queue.offer("2");
        //没有元素时会报错：java.util.NoSuchElementException
        System.out.println(queue.element());//返回第一个元素，但不会移除
        System.out.println(queue.element());
        System.out.println(queue.element());
    }
    
    @Test
    public void testPeek(){
        Queue<String> queue = new ArrayDeque<String>();
        //queue.add("1");
        //queue.offer("2");
        //没有元素时会返回null
        System.out.println(queue.peek());//返回第一个元素，但不会移除
        System.out.println(queue.peek());
        System.out.println(queue.peek());
    }
    
    @SuppressWarnings("rawtypes")
    public void print(Queue queue){
        Iterator it = queue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
    
    public void test(){
        
    }
}
