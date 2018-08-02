package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.OptionsBean;


public interface OptionsDao  {
	/**
	 * 查找所有选项
	 * @return
	 */
	public List<OptionsBean> findAllOptions(Map<String, Object> map);
	/**
	 * 查找单个选项
	 * @param topicId
	 * @return
	 */
	public OptionsBean findOption(int topicId);
	/**
	 * 添加选项
	 * @param option
	 * @return
	 */
	public int inserOption(OptionsBean option);
	/**
	 * 根据条件查找选项个数
	 * @param map
	 * @return
	 */
	public int findOpCount(Map<String, Object> map);
}
