package com.yuanfy.monitorsite.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.yuanfy.monitorsite.common.util.FileUtils;

@Controller
public class UploadFileTestController {
    
    /**
     * @Description: 通过文件流的形式上传
     * @param file   @RequestParam("txtFile") 将name=txtFile控件得到的文件封装成CommonsMultipartFile对象,
     *              如果不这样做会报CommonsMultipartFile没有初始化的错误
     *              java.lang.NoSuchMethodException: org.springframework.web.multipart.commons.CommonsMultipartFile.<init>()
     * @return
     * @author yuanfy
     * @date 2017年9月15日 下午4:36:11 
     * @version 6.5
     */
    @RequestMapping(value="test/upload1")
    @ResponseBody
    public String testUpload1(@RequestParam("txtFile")CommonsMultipartFile file){
        Long times = System.currentTimeMillis();
        if (file == null) {
            return null;
        }
        StringBuilder fileContent = new StringBuilder();
        //1、获取文件信息
        FileUtils.getFileInfo(file, fileContent);
        
        //2、获取文件内容
        try {
            file.transferTo(new File("D:\\cpu.log"));
            fileContent.append(FileUtils.getFileContentByLine(file.getInputStream()));
        }
        catch (IOException e) {
            return "获取文件内容失败";
        }
        
        //3、返回文件信息和内容
        String content = fileContent.toString();
        content = content.replace("times", (System.currentTimeMillis()-times) + "ms");
        return content;
    }
    
    @RequestMapping(value="test/upload2")
    @ResponseBody
    public String testUpload2(HttpServletRequest request){
        Long times = System.currentTimeMillis();
        StringBuilder fileContent = new StringBuilder();
        //1.用于判断是普通表单，还是带文件上传的表单。
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (!multipartResolver.isMultipart(request)) {
            return "没有选择上传文件，请重新选择！";
        }
        //2.获取文件相关对象
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        //文件存放的map对象
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        Iterator<Entry<String, MultipartFile>> iterator = fileMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, MultipartFile> entry = iterator.next();
            MultipartFile multipartFile = entry.getValue();
            
            FileUtils.getFileInfo(multipartFile, fileContent);
            
            try {
                multipartFile.transferTo(new File("D:\\cpu.log"));
                fileContent.append(FileUtils.getFileContentByLine(multipartFile.getInputStream()));
            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        
        //3、返回文件信息和内容
        String content = fileContent.toString();
        content = content.replace("times", (System.currentTimeMillis()-times) + "ms");
        return content;
    }
}
