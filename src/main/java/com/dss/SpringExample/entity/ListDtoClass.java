package com.dss.SpringExample.entity;

import java.util.Map;

public class ListDtoClass {
	
	private Map<String, Object> filter;
	private Map<String, Object> sort;
    private int rows;
	private int pages;
	
	public ListDtoClass() {
		super();
	}
	public ListDtoClass(Map<String, Object> filter, Map<String, Object> sort, int rows, int pages) {
		super();
		this.filter = filter;
		this.sort = sort;
		this.rows = rows;
		this.pages = pages;
	}
	public Map<String, Object> getFilter() {
		return filter;
	}
	public void setFilter(Map<String, Object> filter) {
		this.filter = filter;
	}
	public Map<String, Object> getSort() {
		return sort;
	}
	public void setSort(Map<String, Object> sort) {
		this.sort = sort;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	

}
