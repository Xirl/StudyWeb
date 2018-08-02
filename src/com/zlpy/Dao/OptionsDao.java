package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.OptionsBean;


public interface OptionsDao  {
	/**
	 * ��������ѡ��
	 * @return
	 */
	public List<OptionsBean> findAllOptions(Map<String, Object> map);
	/**
	 * ���ҵ���ѡ��
	 * @param topicId
	 * @return
	 */
	public OptionsBean findOption(int topicId);
	/**
	 * ���ѡ��
	 * @param option
	 * @return
	 */
	public int inserOption(OptionsBean option);
	/**
	 * ������������ѡ�����
	 * @param map
	 * @return
	 */
	public int findOpCount(Map<String, Object> map);
}
