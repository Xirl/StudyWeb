package com.sx.bean;

import java.util.List;

public class PageBean<T> {
private int pageIndex;
private int pageSize=5;
private int pageCount;
private int pageTotal;
private List<T> list;
public int getPageIndex() {
	return pageIndex;
}
public void setPageIndex(int pageIndex) {
	if(pageIndex<1){
		this.pageIndex=1;
	}else{
		this.pageIndex=pageIndex;
	}
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
	setPageCount();
}
public int getPageCount() {
	return pageCount;
}
public void setPageCount() {
    this.pageCount=this.pageTotal%this.pageSize==0?this.pageTotal/this.pageSize
    		:this.pageTotal/this.pageSize+1;
}
public int getPageTotal() {
	return pageTotal;
}
public void setPageTotal(int pageTotal) {
	this.pageTotal = pageTotal;
	setPageCount();
}
public List<T> getList() {
	return list;
}
public void setList(List<T> list) {
	this.list = list;
}

@Override
	public String toString() {
		return "pageSize"+pageSize+" "+"pageCount"+pageCount+""+"pageTotal"
		+pageTotal+" "+"pageIndex"+pageIndex;
	}
}
