package com.sx.dao;

import java.util.List;
import java.util.Map;

import com.sx.bean.PageBean;



public interface GeneralDao<T> {
	public List<T> find(T entity);
	public List<T> findBy(T entity);
	public int update(T entity);
	public int save(T entity);
	public int delete(String id);
	public PageBean<T> findCondition(Map<String,Object> map,int pageSize,int pageIndex);
	public int findCount(Map<String,Object> map);
	public int deleteBy(T entity);
}
