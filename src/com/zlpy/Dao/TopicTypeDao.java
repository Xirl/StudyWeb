package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.TopicTypeBean;

public interface TopicTypeDao {
	/**
	 * ���������������е���Ŀ����
	 * @param map
	 * @return
	 */
public List<TopicTypeBean> findAllType(Map<String, Object> map);

/**
 * ����������ѯ�����������
 * @param tableName
 * @return
 */
public List<TopicTypeBean> findQueType(String tableName,Map<String, Object> map);

}
