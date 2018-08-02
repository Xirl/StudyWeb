package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.TopicTypeBean;

public interface TopicTypeDao {
	/**
	 * 根据条件查找所有的题目类型
	 * @param map
	 * @return
	 */
public List<TopicTypeBean> findAllType(Map<String, Object> map);

/**
 * 根据条件查询具体表格的类型
 * @param tableName
 * @return
 */
public List<TopicTypeBean> findQueType(String tableName,Map<String, Object> map);

}
