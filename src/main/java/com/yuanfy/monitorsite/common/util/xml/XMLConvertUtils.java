package com.yuanfy.monitorsite.common.util.xml;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.yuanfy.monitorsite.test.entity.LicenseEntity;

/**
 * @Description: Xml文件与javaBean对象互转工具类 
 * @author yuanfy
 * @date 2017年12月28日 下午1:51:49 
 * @version 1.0
 */
public class XMLConvertUtils {
    private static Logger log = LoggerFactory.getLogger(XMLConvertUtils.class);
    
    private static final String XML_HEAD = "<?xml version='1.0' ?>\n";
    
    /**
     * @Description: 将对象转换为xml字符串
     * @param object
     * @return String
     * @author yuanfy
     * @date 2017年12月28日 下午2:27:49 
     * @version 1.0
     */
    public static String objectConvertXMl(Object object) {
        String xmlString = null;
        //1、创建beanWriter(因为是要转换成string所以使用StringWriter)
        StringWriter stringWriter = new StringWriter();
        stringWriter.write(XML_HEAD);
        BeanWriter beanWriter = new BeanWriter(stringWriter);
        //2、设置相关属性
        //不要显示map对象id
        beanWriter.getBindingConfiguration().setMapIDs(false);
        //不要将为空的元素添加进去 
        beanWriter.setWriteEmptyElements(false);
        try {
            beanWriter.write(object);
            xmlString = stringWriter.toString();
        }
        catch (Exception e) {
            log.error("beanWriter write：", e);
            return null;
        }finally {
            try{
                beanWriter.close();
                stringWriter.close();
            }catch (IOException e){
                log.error("close IOException:", e);
            }
        }
        return xmlString;
    }
    /**
     * @Description: 将xml内容转换成Ben对象
     * @param xmlString xml内容
     * @param className 要转换bean的className
     * @return
     * @author yuanfy
     * @date 2017年12月28日 下午3:04:11 
     * @version 1.0
     */
    public static Object xmlConvertObject(String xmlString, String className) {
        Object obj = null;
        //跟生成xml一样，BeanWriter对应BeanReader，StringWriter->StringReader
        StringReader stringReader = new StringReader(xmlString);
        BeanReader beanReader = new BeanReader();
        try {
            //2、加载class
            Class<?> clazz = Class.forName(className);
            //3、注册bean
            beanReader.registerBeanClass(clazz);
            //4、解析
            obj = beanReader.parse(stringReader);
        }
        catch (ClassNotFoundException e) {
            log.error("找不到对应的类" + className, e);
        }
        catch (IntrospectionException e) {
            log.error("beanReader.registerBeanClass error:", e);
        }
        catch (IOException e) {
            log.error("beanReader.parse 引起的io异常：", e);
        }
        catch (SAXException e) {
            log.error("beanReader.parse 解析报错：", e);
        }
        finally {
            stringReader.close();
        }
        return obj;
    }
    
    public static void main(String[] args) {
        LicenseEntity entity = new LicenseEntity();
        entity.setNumber(1234);
        entity.setOrgName("阿斯蒂芬");
        entity.setOrgPhone("1234");
        //entity.setVersion("1234");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tqewr", 1234);
        map.put("哈哈", "善心");
        entity.setParams(map);
        String xmlStr = objectConvertXMl(entity);
        System.out.println(xmlStr);
        
        LicenseEntity obj = (LicenseEntity)xmlConvertObject(xmlStr, LicenseEntity.class.getCanonicalName());
        System.out.println(obj.getOrgName());
        System.out.println(obj.getOrgPhone());
    }
}
