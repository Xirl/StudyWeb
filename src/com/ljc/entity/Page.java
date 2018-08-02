package com.ljc.entity;

import java.util.List;

public class Page<T> {
	private int pageIndex;//页码索引
	private int pageSize=4;//页码显示的数据
	private int pageCount;//总页码
	private int dataCount;//总数据
	private List<T> list;//从数据中拿到的数据集合
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		if(pageIndex>1){
			this.pageIndex = pageIndex;
		}else{
			this.pageIndex=1;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount() {//总页数等于总数据数除每页数据数，因为int类型没有小数，
											//如果除不尽，要加1
		this.pageCount = this.dataCount%this.pageSize==0?
				this.dataCount/this.pageSize:
					this.dataCount/this.pageSize+1;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	
}
