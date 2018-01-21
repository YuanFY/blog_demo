package com.yuanfy.test.container;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;


class Person {
	String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return 1;
	}
	
}

public class MapTest {
	@Test
	public void testHashMap(){
		Map<String, Object> map = new HashMap<String, Object>(12);
		map.put("1234", 1234);
		map.put("1334", 1234);
		map.put("1134", 1234);
		print(map);
	}
	
	@Test
	public void testLinkedHashMap(){
		Map<String, Object> map = new LinkedHashMap<String, Object>(12);
		map.put("1234", 1234);
		map.put("1334", 1234);
		map.put("1134", 1234);
		print(map);
	}
	
	@Test
	public void testTreeMap(){
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("1", null);
		System.out.println(map.size());
	}
	
	@Test
	public void testHashTable(){
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("1", "1");
		System.out.println(map.size());
		
		System.out.println(Integer.getInteger("0x7FFFFFFF", 10));
	}
	
	@Test
	public void testConcurrentHashMap(){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put("1", "1");
		System.out.println(map.size());
		
		System.out.println(Integer.getInteger("0x7FFFFFFF", 10));
	}
	
	public void print(Map<String, Object> map){
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}
