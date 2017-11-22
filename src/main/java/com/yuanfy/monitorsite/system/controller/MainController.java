package com.yuanfy.monitorsite.system.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanfy.monitorsite.framework.SessionUtil;

/**
 * @description 主控制器类
 * @author YuanFY 
 * @date 2017年2月11日 下午9:50:47
 * @version 1.0
 */
@Controller
public class MainController{
	final static Logger log = Logger.getLogger(MainController.class);
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,Model model){
		model.addAttribute("user", SessionUtil.getUser(request));
		return "/index";
	}
	
	@RequestMapping(value = "/test/index")
    public String testIndex(HttpServletRequest request,Model model){
        return "/test/index";
    }
	
	@RequestMapping(value = "login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value = "register")
	public String register(){
		return "register";
	}
	
	@RequestMapping(value = "/getNameById", method=RequestMethod.POST)
	@ResponseBody
	public String getNameById(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException{
		ServletInputStream inputStream = request.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(inputStream);
		System.out.println(ois.readInt());
		return "/index";
	}
	
	public static void main(String[] args) {
	    Map<String, String> map = new ConcurrentHashMap<String, String>();
	
	    map.put("e", "2");
	    map.put("01", "1");
	    map.put("9", "9");
	    map.put("asdf", "10");
	    
	    map.entrySet();
	    //System.out.println(map.toString());
	    
	   List<String> list = new LinkedList<String>();
	   //list.add(null);
	   list.add("test");
	   list.add("test1");
	   list.add("test2");
	   list.add("test");
	   //list.add(null);
	   list.add("yuanfy");
	   list.add("java");
	   
	   //有序
	   Set<String> set = new LinkedHashSet<String>();
	   set.addAll(list);
	   for(String key : set){
	       System.out.print(key + " ");
	   }
	   System.out.println();
	   
	   //无序
	   set = new HashSet<String>();
	   set.addAll(list);
	   for(String key : set){
           System.out.print(key + " ");
       }
	   System.out.println();
	   
	   set = new TreeSet<String>();
	   set.addAll(list);
	   for(String key : set){
           System.out.print(key + " ");
       }
	  
	   System.out.println("\n-------map-----");
	   map = new HashMap<String, String>();
	   map.put("1", "1234");
	   map.put("2", "2345");
	   map.put("1", "1234");
	   map.put(null, "1234");
	   map.put("3", null);
	   map.put(null, null);
	   
	   for(Map.Entry<String, String> entry : map.entrySet()){
	       System.out.println(entry.getKey() + " : " + entry.getValue());
	   }
	   System.out.println("---");
	   map = new LinkedHashMap<String, String>();
       map.put("1", "1234");
       map.put("2", "2345");
       map.put("1", "1234");
       map.put(null, "1234");
       map.put("3", null);
       map.put(null, null);
       
       for(Map.Entry<String, String> entry : map.entrySet()){
           System.out.println(entry.getKey() + " : " + entry.getValue());
       }
       
       System.out.println("---");
       map = new TreeMap<String, String>();
       map.put("2", "2345");
       map.put("1", "1234");
       //map.put(null, "1234");
       map.put("1", "1234");
       map.put("3", null);
       //map.put(null, null);
       
       for(Map.Entry<String, String> entry : map.entrySet()){
           System.out.println(entry.getKey() + " : " + entry.getValue());
       }
	}
	
}
