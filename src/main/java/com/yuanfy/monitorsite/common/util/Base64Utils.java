package com.yuanfy.monitorsite.common.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @Description: base64编/解码工具类
 * @author yuanfy
 * @date 2018年1月2日 下午4:42:10 
 * @version 1.0
 */
public class Base64Utils {
    private static Logger log = LoggerFactory.getLogger(Base64Utils.class);
    
    /**
     * @Description: 使用base64编码得到字符串
     * @author yuanfy
     * @date 2018年1月2日 下午4:49:06 
     * @version 1.0
     */
    public static String encode(byte[] data) {
        return filter((new BASE64Encoder()).encodeBuffer(data));
    }
    /**
     * @Description: 使用base64解码得到字节数组
     * @author yuanfy
     * @date 2018年1月2日 下午4:49:06 
     * @version 1.0
     */
    public static byte[] decode(String data) {
        try {
            return (new BASE64Decoder()).decodeBuffer(data);
        }
        catch (IOException e) {
            log.error("base64解码失败：", e);
            return null;
        }
    }
    
    /**
     * @Description: 过滤换行键和回车键
     * @author yuanfy
     * @date 2018年1月2日 下午4:47:07 
     * @version 1.0
     */
    public static String filter(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if (asc != 10 && asc != 13)
                sb.append(str.substring(i, i + 1));
        }
        return sb.toString();
    }
}
