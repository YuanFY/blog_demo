package com.yuanfy.monitorsite.common.util;

import com.yuanfy.monitorsite.rest.conn.ConnectionInfo;

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
	public static String[] toArray(String str){
		if(isEmpty(str)){
			return null;
		}
		return str.split(",");
	}
	
	/**
	 * @Description: 将sql中的名称转换成Java name,exp:user_name  ->  userName
	 * @param sqlName
	 * @return
	 * @author yuanfy
	 * @date 2017年8月11日 下午2:30:04 
	 * @version 1.0
	 */
	public static String toJavaName(String sqlName) {
	    if (isEmpty(sqlName)) {
	        return null;
	    }
	    sqlName = sqlName.toLowerCase();
	    StringBuilder javaName = new StringBuilder();
	    String[] names = sqlName.split("_");
	    boolean upper = false;
	    for (String name : names) {
	        if (!upper) {
	            javaName.append(uncapitalize(name));
	            upper = true;
	        } else {
	            javaName.append(capitalize(name));
	        }
	    }
	    return javaName.toString();
	}
	/**
	 * @Description: 根据连接信息，拼接http url
	 * @author yuanfy
	 * @date 2018年1月5日 下午6:03:56 
	 * @version 1.0
	 */
	public static String getHttpUrl(ConnectionInfo info, String urlStr){
        StringBuffer sb = new StringBuffer("");
        sb.append("http://").append(info.getHost()).append(":").append(info.getPort()).append("/").append(urlStr);
        return sb.toString();
	}
}
