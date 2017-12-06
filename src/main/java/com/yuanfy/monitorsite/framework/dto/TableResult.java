package com.yuanfy.monitorsite.framework.dto;

import java.util.List;
import java.util.Map;

/**
 * @description 表格数据结果
 * @author YuanFY 
 * @date 2017年12月5日 下午9:18:29
 * @version 1.0
 * @param <T>
 */
public class TableResult<T> {

	private List<T> data;
	
	/**
	 * 数据总条数
	 */
	private Long dataTotal;
	
	/**
	 * 总页数
	 */
	private Integer pageTotal; 
	/**
	 * 当前页条数
	 */
	private Integer currentPageSize;
	
	/**
	 * 当前页
	 */
	private Integer currentPage;

	/**
	 * 辅助性参数
	 */
	private Map<String, Object> arguments;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getDataTotal() {
        return dataTotal;
    }

    public void setDataTotal(Long dataTotal) {
        this.dataTotal = dataTotal;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getCurrentPageSize() {
        return currentPageSize;
    }

    public void setCurrentPageSize(Integer currentPageSize) {
        this.currentPageSize = currentPageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }
}
