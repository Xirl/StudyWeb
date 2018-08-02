package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.ItemPoolDetailsBean;
import com.zlpy.Bean.PageBean;


public interface ItemPoolDetailsDao {

	/**
	 * 根据页数与页码进行分页查询
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageBean<ItemPoolDetailsBean> fingBypage(int pageIndex,int pageSize,Map<String, Object> map);
	/**
	 * 查找所有题库
	 * @param map
	 * @return
	 */
	public List<ItemPoolDetailsBean> findAllPool(Map<String, Object> map);
	/**
	 * 查询数据的总个数
	 * @return
	 */
	public int findCount(Map<String, Object> map);
}
