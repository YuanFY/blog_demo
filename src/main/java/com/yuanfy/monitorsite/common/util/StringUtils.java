package com.yuanfy.monitorsite.common.util;

/**
 * @description 字符串处理工具类
 * @author YuanFY 
 * @time 下午9:19:14
 * @version 1.0
 */
public class StringUtils extends org.springframework.util.StringUtils{
	
	/**
	 * @description 将携有逗号,的字符串转换为数组
	 * @author YuanFY 
	 * @time 下午9:20:56
	 * @version 1.0
	 */
	public String[] toArray(String str){
		if(isEmpty(str)){
			return null;
		}
		return str.split(",");
	}
}
