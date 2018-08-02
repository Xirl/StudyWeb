package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.SolutionBean;

public interface SolutionDao {
	/**
	 * ����������ѯ��
	 * @param map
	 * @return
	 */
	public SolutionBean findSolution(Map<String, Object> map);
	/**
	 * �����������Ҵ�
	 * @param map
	 * @return
	 */
	public List<SolutionBean> findAllSolu(Map<String, Object> map);
	/**
	 * ˫���ѯ��
	 * @param tableName
	 * @param map
	 * @return
	 */
	public List<SolutionBean> findSoluByTable(String tableName,Map<String, Object> map);
	/**
	 * ��Ӵ�
	 * @param solu
	 * @return
	 */
	public int inserSolu(SolutionBean solu);
	/**
	 * ɾ����
	 * @param topicId
	 * @return
	 */
	public int delectSolu(Map<String, Object> map);
}
