package com.zlpy.Bean;

import java.util.List;

public class PageBean<T> {
private List<T> list;
private Integer pageIndex;//当前页码
private int pageSize;//每页数量
private int countIndex;//总页码
private int countSize;//总数量

public Integer getPageIndex() {
	return pageIndex;
}

public void setPageIndex(Integer pageIndex) {
	if(pageIndex!=null && pageIndex>0){
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

public int getCountIndex() {
	return countIndex;
}

public void setCountIndex(int countIndex) {
	this.countIndex = countIndex;
}

public int getCountSize() {
	return countSize;
}

public void setCountSize(int countSize) {
	this.countSize = countSize;
}

public List<T> getList() {
	return list;
}

public void setList(List<T> list) {
	this.list = list;
}

}
