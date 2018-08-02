package com.ljc.entity;

import java.util.List;

public class Page<T> {
	private int pageIndex;//ҳ������
	private int pageSize=4;//ҳ����ʾ������
	private int pageCount;//��ҳ��
	private int dataCount;//������
	private List<T> list;//���������õ������ݼ���
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
	public void setPageCount() {//��ҳ����������������ÿҳ����������Ϊint����û��С����
											//�����������Ҫ��1
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
