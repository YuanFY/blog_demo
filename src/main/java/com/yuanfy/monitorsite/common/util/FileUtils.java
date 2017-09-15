package com.yuanfy.monitorsite.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 文件工具类方法
 * @author yuanfy
 * @date 2017年9月15日 下午2:45:40 
 * @version 1.0
 */
public class FileUtils {
    /**
     * @Description: 获取文件信息
     * @param file CommonsMultipartFile类型的文件
     * @param fileContent  StringBuilder，封装文件信息
     * @author yuanfy
     * @date 2017年9月15日 下午2:51:34 
     * @version 1.0
     */
    public static void getFileInfo(MultipartFile file, StringBuilder fileContent) {
        fileContent.append("文件名称：\t\t").append(file.getName()).append("\n")
            .append("文件原始名称：\t").append(file.getOriginalFilename()).append("\n")
            .append("文件大小：\t\t").append(file.getSize()).append("\n")
            .append("文件类型：\t\t").append(file.getContentType()).append("\n")
            .append("读取文件时长：\t times").append("\n");
    }
    
    /**
     * @Description: TODO
     * @param file
     * @author yuanfy
     * @date 2017年9月15日 下午5:01:57 
     * @version 1.0
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public static String getFileContentByLine(File file) throws FileNotFoundException, IOException {
        return getFileContentByLine(new FileInputStream(file));
    }
    /**
     * @Description: 根据文件输入流对象获取文件内容
     * @param in 文件输入流对象
     * @author yuanfy
     * @date 2017年9月15日 下午5:01:57 
     * @version 1.0
     * @throws IOException 
     */
    public static String getFileContentByLine(InputStream in) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            String content = new String(bytes, 0, len, "UTF-8");
            fileContent.append(content);
        }
        StreamUtils.close(in);
        return fileContent.toString();
    }
}
