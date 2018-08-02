package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.PageBean;
import com.zlpy.Bean.QuestionPaperBean;
import com.zlpy.Bean.TopicShow;

public interface QuestionPaperDao {
	/**
	 * 根据分页进行查询数据
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageBean<QuestionPaperBean> fingBypage(int pageIndex,int pageSize,Map<String, Object> map);
	/**
	 * 查找数据总数
	 * @return
	 */
	public int findCount(Map<String, Object> map);
	/**
	 * 查找所有的题目
	 * @return
	 */
	public List<TopicShow> fingAllTopic(Map<String, Object> map);
	/**
	 * 添加题卷并返回Id
	 * @param que
	 * @return
	 */
	public int inserPaper(QuestionPaperBean que);
	
	/**
	 * 查找所有题卷
	 */
	public List<QuestionPaperBean> findAllQue(Map<String, Object> map);
}
