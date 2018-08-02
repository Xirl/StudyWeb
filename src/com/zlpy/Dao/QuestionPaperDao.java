package com.zlpy.Dao;

import java.util.List;
import java.util.Map;

import com.zlpy.Bean.PageBean;
import com.zlpy.Bean.QuestionPaperBean;
import com.zlpy.Bean.TopicShow;

public interface QuestionPaperDao {
	/**
	 * ���ݷ�ҳ���в�ѯ����
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageBean<QuestionPaperBean> fingBypage(int pageIndex,int pageSize,Map<String, Object> map);
	/**
	 * ������������
	 * @return
	 */
	public int findCount(Map<String, Object> map);
	/**
	 * �������е���Ŀ
	 * @return
	 */
	public List<TopicShow> fingAllTopic(Map<String, Object> map);
	/**
	 * ����������Id
	 * @param que
	 * @return
	 */
	public int inserPaper(QuestionPaperBean que);
	
	/**
	 * �����������
	 */
	public List<QuestionPaperBean> findAllQue(Map<String, Object> map);
}
