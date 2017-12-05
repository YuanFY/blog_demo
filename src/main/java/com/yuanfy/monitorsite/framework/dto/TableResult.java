package com.yuanfy.monitorsite.framework.dto;

import java.util.List;

/**
 * @description 列表结果返回集合
 * @author YuanFY 
 * @date 2017年12月5日 下午9:18:29
 * @version 1.0
 * @param <T>
 */
public class TableResult<T> {

	private List<T> data;
	
	private int total;
	
	private int pageNo;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
