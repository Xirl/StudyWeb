package com.ljc.Dao;

import java.util.List;
import java.util.Map;

import com.ljc.entity.Data;

public interface DataDao {
	/**
	 * ��������Data
	 * @param map
	 * @return
	 */
	public List<Data> findbyCondition(Map<String, Object> map);
	
}
