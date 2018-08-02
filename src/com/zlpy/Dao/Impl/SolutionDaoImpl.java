package com.zlpy.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.commom.BaseDao.BaseDao;
import com.zlpy.Bean.SolutionBean;
import com.zlpy.Dao.SolutionDao;

public class SolutionDaoImpl extends BaseDao<SolutionBean> implements SolutionDao {

	@Override
	public SolutionBean findSolution(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		sf.append("select * from y_solution where 1=1");
		List<Object> list=new ArrayList<Object>();
		if(map!=null && map.size()>0){
			if(map.get("topicId")!=null){
				sf.append(" and topicId=?");
				list.add(map.get("topicId"));
			}
			
		}
		List<SolutionBean> soList=new ArrayList<SolutionBean>();
		soList=this.excutQuery(sf.toString(), list.toArray());
		if(soList!=null && soList.size()>0){
			return soList.get(0);
		}
		return null;
	}

	@Override
	public List<SolutionBean> findAllSolu(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		sf.append("select * from y_solution where 1=1");
		List<Object> list=new ArrayList<Object>();
		if(map!=null && map.size()>0){
			if(map.get("topicId")!=null){
				sf.append(" and topicId=?");
				list.add(map.get("topicId"));
			}
			if(map.get("queId")!=null){
				sf.append(" and queId=?");
				list.add(map.get("queId"));
			}
		}
		return this.excutQuery(sf.toString(), list.toArray());
	}

	@Override
	public List<SolutionBean> findSoluByTable(String tableName,
			Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("select distinct s.* from y_solution s,"+tableName+" t where 1=1");
		if(map!=null && map.size()>0){
			if(map.get("queId")!=null){
				sf.append(" and t.queId=?");
				list.add(map.get("queId"));
			}
			if(map.get("typeId")!=null){
				sf.append(" and t.typeId=?");
				list.add(map.get("typeId"));
			}
		}
		return this.excutQuery(sf.toString(), list.toArray());
	}

	@Override
	public int inserSolu(SolutionBean solu) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("insert into y_solution values(seq.nextval,?,?,?)");
		list.add(solu.getSolution());
		list.add(solu.getTopicId());
		list.add(solu.getQueId());
		return this.excutUpdate(sf.toString(), list.toArray());
	}

	@Override
	public int delectSolu(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		if(map!=null && map.size()>0){
			if(map.get("topicId")!=null){
				sf.append("delete from y_solution where topicId=?");
				list.add(map.get("topicId"));
			}
		}
		return this.excutUpdate(sf.toString(), list.toArray());
	}

}
