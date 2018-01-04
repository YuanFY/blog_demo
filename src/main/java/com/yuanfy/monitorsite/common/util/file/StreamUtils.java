package com.yuanfy.monitorsite.common.util.file;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.log4j.Logger;

/**
 * @Description: 流相关工具类
 * @author yuanfy
 * @date 2017年5月25日 下午2:17:38 
 * @version 6.2
 */
public class StreamUtils {

    
    private static Logger log = Logger.getLogger(StreamUtils.class);
    
    public static void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            }
            catch (IOException e) {
                log.error("关闭socket失败：" + e);
            }
        }
    }
    
    public static void close(InputStream in){
        if (in != null) {
            try {
                in.close();
            }
            catch (IOException e) {
                log.error("关闭输入流失败：", e);
            }
        }
    }
    
    public static void close(OutputStream out){
        if (out != null) {
            try {
                out.close();
            }
            catch (IOException e) {
                log.error("关闭输出流失败：", e);
            }
        }
    }
    
    public static void close(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            }
            catch (IOException e) {
                log.error("关闭reader失败：" + e);
            }
        }
    }
    
    public static void close(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            }
            catch (IOException e) {
                log.error("关闭writer失败：" + e);
            }
        }
    }
    
    public static void close(BeanWriter beanWriter) {
        if (beanWriter != null) {
            try {
                beanWriter.close();
            }
            catch (IOException e) {
                log.error("关闭beanWriter失败：" + e);
            }
        }
    }
}
