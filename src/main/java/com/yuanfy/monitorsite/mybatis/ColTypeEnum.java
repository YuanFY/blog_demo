package com.yuanfy.monitorsite.mybatis;

/**
 * @Description: 字段类型枚举类
 * @author yuanfy
 * @date 2017年8月11日 下午2:24:25 
 * @version 6.2
 */
public enum ColTypeEnum {
    INT("int", "Long"),
    SMALLINT("smallint", "Integer"),
    DOUBLE("double", "Double"),
    FLOAT("float", "Float"),
    VARCHAR("varchar", "String"),
    TEXT("text", "String"),
    LONGTEXT("longtext", "String"),
    BOOLEAN("bit", "Boolean"),
    DATETIME("datetime", "Date"),
    TIMESTAMP("timestamp", "Date"),
    DATE("date", "Date");
    
    private String sqlType;
    private String javaType;
    private ColTypeEnum(String sqlType, String javaType) {
        this.sqlType = sqlType;
        this.javaType = javaType;
    }
    
    /**
     * @Description: 根据sqlType获取Java中的类型
     * @param sqlType
     * @author yuanfy
     * @date 2017年8月11日 下午2:24:39 
     * @version 6.2
     */
    public static String getJavaType(String sqlType) {
        for (ColTypeEnum types : ColTypeEnum.values()){
            if (sqlType.equalsIgnoreCase(types.sqlType)) {
                return types.javaType;
            }
        }
        return "undefined";
    }
}
