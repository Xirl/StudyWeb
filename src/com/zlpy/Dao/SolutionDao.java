package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.SolutionBean;

public interface SolutionDao {
	/**
	 * 根据条件查询答案
	 * @param map
	 * @return
	 */
	public SolutionBean findSolution(Map<String, Object> map);
	/**
	 * 根据条件查找答案
	 * @param map
	 * @return
	 */
	public List<SolutionBean> findAllSolu(Map<String, Object> map);
	/**
	 * 双表查询答案
	 * @param tableName
	 * @param map
	 * @return
	 */
	public List<SolutionBean> findSoluByTable(String tableName,Map<String, Object> map);
	/**
	 * 添加答案
	 * @param solu
	 * @return
	 */
	public int inserSolu(SolutionBean solu);
	/**
	 * 删除答案
	 * @param topicId
	 * @return
	 */
	public int delectSolu(Map<String, Object> map);
}
