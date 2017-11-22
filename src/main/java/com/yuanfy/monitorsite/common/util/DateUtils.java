package com.yuanfy.monitorsite.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @description 时间工具类
 * @author YuanFY 
 * @date 2017年9月24日 下午9:04:47
 * @version 1.0
 */
public class DateUtils {
    
	private static final String pattern = "yyyyMMddHHmmss";
	
	/**
	 * @description 将日期格式化成yyyyMMddHHmmss
	 * @author YuanFY
	 * @date 2017年9月24日 下午9:13:47
	 * @version 1.0
	 */
	public static String dateFormatToTimestamp(){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}
	
}
