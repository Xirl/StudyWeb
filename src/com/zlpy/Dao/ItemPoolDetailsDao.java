package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.ItemPoolDetailsBean;
import com.zlpy.Bean.PageBean;


public interface ItemPoolDetailsDao {

	/**
	 * ����ҳ����ҳ����з�ҳ��ѯ
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageBean<ItemPoolDetailsBean> fingBypage(int pageIndex,int pageSize,Map<String, Object> map);
	/**
	 * �����������
	 * @param map
	 * @return
	 */
	public List<ItemPoolDetailsBean> findAllPool(Map<String, Object> map);
	/**
	 * ��ѯ���ݵ��ܸ���
	 * @return
	 */
	public int findCount(Map<String, Object> map);
}
