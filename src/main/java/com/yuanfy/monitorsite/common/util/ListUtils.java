package com.yuanfy.monitorsite.common.util;

import java.util.List;

/**
 * @description List集合工具类
 * 		list特点：存放有序、允许元素为空
 * 		list常用子类：ArrayList、LinkedList 和Vection，其中分别区别为：
 * 			1、ArrayList： 随机访问速度快，插入(需要扩容1.5倍)删除消耗大、线程不安全；（数据结构：数组）
 * 			2、LinkedList: 双向链表，插入删除快，随机访问较慢;(数据结构：链表)
 * 			3、Vector与ArrayList相似，但Vector是线程安全的，扩容是原来的两倍;(数据结构：数组)
 * @author YuanFY 
 * @time 下午8:39:42
 * @version 1.0
 */
public class ListUtils{
	
	/**
	 * @description 判断list集合是否为空
	 * @author YuanFY 
	 * @time 下午8:42:05
	 * @version 1.0
	 */
	public static boolean isEmpty(@SuppressWarnings("rawtypes") List list){
		if(list == null || list.size() == 0){
			return true;
		}
		return false;
	}
	
}
