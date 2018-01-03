package com.yuanfy.monitorsite.common.util;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 下载工具类
 * @author yuanfy
 * @date 2018年1月3日 下午2:17:13 
 * @version 6.5
 */
public class DownloadUtils{
	
	//private Logger log = LoggerFactory.getLogger(DownloadUtils.class);
	
    /**
     * @Description: 设置响应头
     * @author yuanfy
     * @date 2018年1月3日 下午2:35:25 
     * @version 6.5
     */
	public static void setFileDownloadHeader( HttpServletResponse response, 
	    String fileName, String fileType) {
	    response.setCharacterEncoding("UTF-8");
	    if ("MSEXCEL".equals(fileType)) {
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	    } 
	    else if ("MSWORD".equals(fileType)) {
	        response.setContentType("application/msword;charset=utf-8");
	    }
	    else if ("TXT".equals(fileType)) {
	        response.setContentType("text/plain;charset=utf-8");
	    }else {
	        response.setContentType("application/octet-stream");
	    }
	    response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
	}
}
