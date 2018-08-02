package com.zlpy.Dao.Impl;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import com.commom.BaseDao.BaseDao;
import com.zlpy.Bean.PageBean;
import com.zlpy.Bean.QuestionPaperBean;
import com.zlpy.Bean.TopicShow;
import com.zlpy.Dao.QuestionPaperDao;

public class QuestionPaperDaoImpl extends BaseDao<QuestionPaperBean> implements QuestionPaperDao{

	@Override
	public PageBean<QuestionPaperBean> fingBypage(int pageIndex, int pageSize,
			Map<String, Object> map) {
		int count=this.findCount(map);
		if(count>0){
			PageBean<QuestionPaperBean> page=new PageBean<QuestionPaperBean>();
			List<Object> list=new ArrayList<Object>();
			page.setPageIndex(pageIndex);
			page.setPageSize(pageSize);
			page.setCountSize(count);
			page.setCountIndex(count%pageSize==0?count/pageSize:count/pageSize+1);
			StringBuffer sf=new StringBuffer();
			int start=(page.getPageIndex()-1)*page.getPageSize()+1;
			int end=page.getPageIndex()*page.getPageSize();
			sf.append("select * from (");
			sf.append("select g.*,rownum r from y_question_paper g where 1=1");
			if(map!=null && map.size()>0){
				if(map.get("poolId")!=null){
					sf.append(" and detailsId=?");
					list.add(map.get("poolId"));
				}
				if(map.get("userId")!=null){
					sf.append(" and userId=?");
					list.add(map.get("userId"));
				}
				if(map.get("questionPaperName")!=null && !"%%".equals(map.get("questionPaperName"))){
					sf.append(" and questionPaperName like ?");
					list.add(map.get("questionPaperName"));
				}
			}
			sf.append(" ) where r between ? and ?");
			list.add(start);
			list.add(end);
			page.setList(this.excutQuery(sf.toString(), list.toArray()));
			return page;
		}
		return null;
	}

	@Override
	public int findCount(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		sf.append("select * from y_question_paper where 1=1 ");
		List<Object> list=new ArrayList<Object>();
		if(map!=null && map.size()>0){
			if(map.get("poolId")!=null){
				sf.append(" and detailsId=?");
				list.add(map.get("poolId"));
			}
			if(map.get("questionPaperName")!=null && !"%%".equals(map.get("questionPaperName"))){
				sf.append(" and questionPaperName like ?");
				list.add(map.get("questionPaperName"));
			}
		}
		return this.getCount(sf.toString(), list.toArray());
	}

	@Override
	public List<TopicShow> fingAllTopic(Map<String, Object> map) {
		
		
		return null;
	}

	@Override
	public int inserPaper(QuestionPaperBean que) {
		conn=this.getConnection();
		CallableStatement call=null;
		if(que==null) return 0;
		try{
			call=conn.prepareCall("{?=call fun_paper(?,?,?)}");
			call.registerOutParameter(1, OracleTypes.NUMBER);
			call.setObject(2, que.getQuestionPaperName());
			call.setDouble(3,que.getDetailsId());
			call.setInt(4,que.getUserId());
			call.execute();
			return call.getInt(1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<QuestionPaperBean> findAllQue(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("select * from y_question_paper where 1=1");
		if(map!=null && map.size()>0){
			if(map.get("questionPaperName")!=null){
				sf.append(" and questionPaperName like ?");
				list.add(map.get("questionPaperName"));
			}
			
		}
		return this.excutQuery(sf.toString(), list.toArray());
	}

}
