package com.yuanfy.monitorsite.mybatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yuanfy.monitorsite.common.util.ListUtils;
import com.yuanfy.monitorsite.common.util.StringUtils;
import com.yuanfy.monitorsite.framework.ApplicationContextUtil;
import com.yuanfy.monitorsite.mybatis.dto.ColumnDTO;


/**
 * @Description: 代码生成器：针对表生成具体的实体类、mapper接口、mapper文件、服务类、控制层类
 * @author yuanfy
 * @date 2017年8月11日 上午10:18:28 
 * @version 6.2
 */
public class CodeGenerator {
    private static Logger log = Logger.getLogger(CodeGenerator.class);
    
    //表名（可以以,隔开进行多个表设置）
    private String tableNames = "tb_tweets";
    //实体名（可以以,隔开进行多个表设置）
    private String entityNames = "Tweets";
    //子包名（可以以,隔开进行多个表设置）
    private String subPkgs = "tweets";
    //根包名
    private static final String BASE_PKG = "com.yuanfy.monitorsite";
    //模板代码配置文件路径
    private static final String TEMPLATE_FILE = "codeTemplate.xml";
    //存放模板文件内容
    private Map<String, String> templates = new HashMap<String, String>();
    
    private String saveClassPath = "E:\\work\\blog_demo\\src\\main\\java\\com\\yuanfy\\monitorsite";
    private String saveXMLPath = "E:\\work\\blog_demo\\src\\main\\resources\\mapper";
    
    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator();
        generator.generate();
    }
    
    public void generate() {
        if (StringUtils.isEmpty(tableNames)) {
            log.error("必须设置表名");
            return;
        }
        if (StringUtils.isEmpty(entityNames)) {
            log.error("必须设置实体名");
            return;
        }
        if (StringUtils.isEmpty(subPkgs)) {
            log.error("必须设置子包名");
            return;
        }
        String[] tableNameArray = tableNames.split(",");
        String[] entityNameArray = entityNames.split(",");
        String[] subPkgArray = subPkgs.split(",");
        
        //1、初始化模板，获取模板文件内容
        initTemplate();
        if (templates == null) {
            log.error("模板内容不存在");
            return ;
        }
        
        for (int i = 0; i < tableNameArray.length; i ++) {
            //2、根据表获取表中的列名
            List<ColumnDTO> cols = getTableColumns(tableNameArray[i]);
            if (ListUtils.isEmpty(cols)) {
                log.warn("没有找到相关的列定义，请检查表名" + tableNameArray[i]);
                return;
            }
            //3、生成文件
            String entityClass = createEntityClass(cols, subPkgArray[i], entityNameArray[i]);
            String mapperClass = createMapperInterface(subPkgArray[i], entityNameArray[i]);
            String mapperXml = createMapperXML(cols, subPkgArray[i], entityNameArray[i], tableNameArray[i]);
            String serviceClass = createServiceClass(subPkgArray[i], entityNameArray[i]);
            String controllerClass = createControllerClass(subPkgArray[i], entityNameArray[i]);
            
            //输出控制台
            System.out.println("---------- 实体类 " + entityNameArray[i] + " ------------\n");
            System.out.println(entityClass + "\n");
            System.out.println("---------- DAO接口 " + entityNameArray[i] + "Dao ------------\n");
            System.out.println(mapperClass + "\n");
            System.out.println("---------- DAO映射文件 " + entityNameArray[i] + "Dao.xml ------------\n");
            System.out.println(mapperXml + "\n");
            System.out.println("---------- Service类 " + entityNameArray[i] + "Service ------------\n");
            System.out.println(serviceClass + "\n");
            System.out.println("---------- Controller类 " + entityNameArray[i] + "Controller ------------\n");
            System.out.println(controllerClass + "\n");
            
            //4 生成文件
           
            try {
                FileUtils.write(new File(saveClassPath + "\\" + subPkgArray[i] + "\\entity\\" + entityNameArray[i] + "Entity.java"), entityClass, "utf-8");
                FileUtils.write(new File(saveClassPath + "\\" + subPkgArray[i] + "\\dao\\" + entityNameArray[i] + "Mapper.java"), mapperClass, "utf-8");
                //FileUtils.write(new File(saveXMLPath + subPkgArray[i] + entityNameArray[i] + "Mapper.xml"), mapperXml, "utf-8");
                FileUtils.write(new File(saveClassPath + "\\" + subPkgArray[i] + "\\service\\" + entityNameArray[i] + "Service.java"), serviceClass, "utf-8");
                //FileUtils.write(new File(saveClassPath + BASE_PKG + subPkgArray[i] + "controller" + entityNameArray[i] + "Controller.java"), controllerClass, "utf-8");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            
            System.out.println("--------------------------------------");
            System.out.println("同时，相应的文件已经输出到：" + this.saveClassPath);
        }
    }
    
    /**
     * @Description: 获取模板文件中的id和id下的内容并封装至map
     * @author yuanfy
     * @date 2017年8月11日 下午12:43:06 
     * @version 6.2
     */
    private void initTemplate() {
        try {
            File file = new File(getClass().getResource("/").getPath() + TEMPLATE_FILE);
            InputStream inputStream = new FileInputStream(file);
            Document document = new SAXReader().read(inputStream);
            for (Object obj : document.getRootElement().elements()) {
                Element e = (Element) obj;
                templates.put(e.attributeValue("id"), e.getTextTrim());
            }
            inputStream.close();
        }
        catch (Exception e) {
            log.error("解析模板文件错误：" + e.getMessage());
        }
    }
    /**
     * @Description: 根据节点id获取模板中的内容
     * @author yuanfy
     * @date 2017年8月11日 下午2:38:56 
     * @version 6.2
     */
    public String getTemplate(String id, String subPkg, String entityName) {
        String template = templates.get(id);
        if (template == null) {
            log.error("找不到id为" + id + "的模板");
            return "";
        }
        return template.replace("${basePkg}", BASE_PKG)
                .replace("${subPkg}", subPkg)
                .replace("${entityName}", entityName)
                .replace("${entityVar}", StringUtils.uncapitalize(entityName));
    }
    
    /**
     * @Description: 获取表中的列名和对应的属性名
     * @param tableName 表名
     * @author yuanfy
     * @date 2017年8月11日 上午10:52:25 
     * @version 6.2
     */
    private List<ColumnDTO> getTableColumns(String tableName) {
        try {
            List<ColumnDTO> list = new ArrayList<ColumnDTO>();
            //1 获取数据源，根据数据源获取数据库连接
            DataSource dataSource = ApplicationContextUtil.getBeanByClass(DataSource.class);
            Connection connection = dataSource.getConnection();
            //2、获取元数据
            DatabaseMetaData metaData = connection.getMetaData();
            //3、根据元数据对象获取数据库表中的信息并返回结果集
            ResultSet set = metaData.getColumns(null, null, tableName, "%");
            //4、遍历结果集返回列名和类型
            while (set.next()) {
                ColumnDTO col = new ColumnDTO();
                col.setName(set.getString("COLUMN_NAME"));
                col.setType(set.getString("TYPE_NAME"));
                list.add(col);
            }
            return list;
        }
        catch (SQLException e) {
            log.error("获取表中名称字段失败：" + e.getMessage());
            return null;
        }
    }
    
    /**
     * @Description: 根据表中字段生成实体类
     * @param cols 字段列表
     * @author yuanfy
     * @date 2017年8月11日 下午2:07:48 
     * @version 1.0
     */
    public String createEntityClass(List<ColumnDTO> cols, String subPkg, String entityName) {
        StringBuilder attributes = new StringBuilder();
        for (ColumnDTO col : cols) {
            attributes.append("\tprivate ").append(ColTypeEnum.getJavaType(col.getType()))
                .append(" ").append(StringUtils.toJavaName(col.getName()))
                .append(";\n");
        }
        return getTemplate("entity", subPkg, entityName).replace("${attributes}", attributes.toString());
    }
    
    public String createMapperInterface(String subPkg, String entityName) {
        return getTemplate("mapperInterface", subPkg, entityName);
    }
    
    public String createServiceClass(String subPkg, String entityName) {
        return getTemplate("service", subPkg, entityName);
    }
    
    public String createControllerClass(String subPkg, String entityName) {
        return getTemplate("controller", subPkg, entityName);
    }
    
    public String createMapperXML(List<ColumnDTO> cols, String subPkg, String entityName, String tableName) {
        StringBuilder mapProperties = new StringBuilder();
        StringBuilder insertFields = new StringBuilder();
        StringBuilder insertValues = new StringBuilder();
        StringBuilder updateSets = new StringBuilder();
        
        int i = 0, len = cols.size();
        for (ColumnDTO col : cols) {
            i ++;
            String javaName = StringUtils.toJavaName(col.getName());
            mapProperties.append("\t\t<result property=\"").append(javaName)
                .append("\" column=\"").append(col.getName()).append("\"/>");
            
            insertFields.append(col.getName());
            insertValues.append("#{").append(javaName).append("}");
            
            updateSets.append("\t\t\t<if test=\"").append(javaName).append(" != null\">\n")
                .append("\t\t\t\t").append(col.getName()).append(" = ").append("#{").append(javaName).append("}\n");
           
            if (i < len) {
                mapProperties.append("\n");
                insertFields.append(", ");
                insertValues.append(", ");
                updateSets.append(",");
            }
            updateSets.append("\t\t\t</if>\n");
        }
        return getTemplate("mapper", subPkg, entityName)
                .replace("${tableName}", tableName)
                .replace("${mapProperties}", mapProperties)
                .replace("${insertFields}", insertFields)
                .replace("${insertValues}", insertValues)
                .replace("${updateSets}", updateSets);
    }
}
