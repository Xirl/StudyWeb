package com.sx.dao;

import java.util.List;
import java.util.Map;

import com.sx.bean.Util;

public interface TagsDao<T> extends GeneralDao<T> {
public List<Util> findId(Map<String,Object> map);
public int updateBy(T entity);
}
