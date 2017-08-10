package com.yuanfy.monitorsite.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 分页工具类
 * @author yuanfy
 * @date 2017年8月10日 下午7:19:59 
 * @version 1.0
 */
public class Page<T> {
    //分页参数
    private int currentPage;//当前页
    private int pageLimit;//每页长度
    
    //排序参数
    private String orderCol;//排序列
    private String orderDir;//排序顺序：desc 、 asc
    
    //查询参数map
    @SuppressWarnings("unused")
    private Map<String, Object> params = new HashMap<String, Object>();
    
    //返回结果
    private List<T> result;
    
    //返回大小
    private Long resultTotal;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String getOrderCol() {
        return orderCol;
    }

    public void setOrderCol(String orderCol) {
        this.orderCol = orderCol;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getResultTotal() {
        return resultTotal;
    }

    public void setResultTotal(Long resultTotal) {
        this.resultTotal = resultTotal;
    }
}
