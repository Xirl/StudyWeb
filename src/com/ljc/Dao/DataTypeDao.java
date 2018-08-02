package com.ljc.Dao;

import java.util.List;
import java.util.Map;

import com.ljc.entity.DataType;

public interface DataTypeDao {
	/**
	 * 通过条件遍历
	 * @param map
	 * @return
	 */
	public List<DataType> findbyCondition(String name);
	
	public List<DataType> select(Map<String, Object> map);
}
