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

import com.yuanfy.monitorsite.common.util.file.FileUtils;

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
        
        //2、上传文件并获取文件内容
        try {
            file.transferTo(new File("F:\\text.log"));
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
        //1.根据servletContext获取多文件上传解析组件
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (!multipartResolver.isMultipart(request)) {
            return "不是上传文件表单，请检查表单属性";
        }
        //2.将请求对象转换为多文件请求对象。
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        //3、根据多文件请求对象获取文件存放Map
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        Iterator<Entry<String, MultipartFile>> iterator = fileMap.entrySet().iterator();
        //4、迭代文件Map,获取具体的MultipartFile
        while (iterator.hasNext()) {
            Entry<String, MultipartFile> entry = iterator.next();
            MultipartFile multipartFile = entry.getValue();
            //获取文件头信息
            FileUtils.getFileInfo(multipartFile, fileContent);
            try {
            	//上传文件
                multipartFile.transferTo(new File("F:\\text.log"));
                //获取文件内容
                fileContent.append(FileUtils.getFileContentByLine(multipartFile.getInputStream()));
            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //5、返回文件信息和内容
        String content = fileContent.toString();
        content = content.replace("times", (System.currentTimeMillis()-times) + "ms");
        return content;
    }
}
