package com.yuanfy.monitorsite.mybatis.dto;

/**
 * @Description: 列名称展示
 * @author yuanfy
 * @date 2017年8月11日 上午11:11:27 
 * @version 1.0
 */
public class ColumnDTO {
    private String name;
    
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
