package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.TopicBean;

public interface TopicDao {
	/**
	 * 根据条件查询所有题目
	 * @param map
	 * @return
	 */
	public List<TopicBean> findAllTopic(Map<String, Object> map);
	/**
	 * 根据条件查找题目数量
	 * @param map
	 * @return
	 */
	public int findCount(Map<String, Object> map);
	/**
	 * 添加数据并返回Id
	 * @param topic
	 * @return
	 */
	public int insertTopic(TopicBean topic);
}
