package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.TopicBean;

public interface TopicDao {
	/**
	 * ����������ѯ������Ŀ
	 * @param map
	 * @return
	 */
	public List<TopicBean> findAllTopic(Map<String, Object> map);
	/**
	 * ��������������Ŀ����
	 * @param map
	 * @return
	 */
	public int findCount(Map<String, Object> map);
	/**
	 * ������ݲ�����Id
	 * @param topic
	 * @return
	 */
	public int insertTopic(TopicBean topic);
}
