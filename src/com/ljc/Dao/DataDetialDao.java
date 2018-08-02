package com.ljc.Dao;

import java.util.List;
import java.util.Map;

import com.ljc.entity.DataDetial;

public interface DataDetialDao {
	/**
	 * 按条件查询
	 * @param map
	 * @return
	 */
	public List<DataDetial> findbyCondition(Integer dataid);
	
	public List<DataDetial> select(String dataname);
	
	public List<DataDetial> findbyid(Integer detialid);
	
	public List<DataDetial> finddataName(String typeName);
	
	public List<DataDetial> findTitle(Integer dataid);
}
