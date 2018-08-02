package com.zlpy.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.commom.BaseDao.BaseDao;
import com.zlpy.Bean.TopicTypeBean;
import com.zlpy.Dao.TopicTypeDao;

public class TopicTypeDaoImpl extends BaseDao<TopicTypeBean> implements TopicTypeDao {

	@Override
	public List<TopicTypeBean> findAllType(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		sf.append("select * from y_topic_type where 1=1");
		List<Object> list=new ArrayList<Object>();
		if(map!=null && map.size()>0){
			if(map.get("typeName")!=null){
				sf.append(" and typeName=?");
				list.add(map.get("typeName"));
			}
			if(map!=null && map.size()>0){
				if(map.get("queId")!=null){
					sf.append(" and queId=?");
					list.add(map.get("queId"));
				}
			}
		}
		return this.excutQuery(sf.toString(), list.toArray());
	}

	@Override
	public List<TopicTypeBean> findQueType(String tableName,
			Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("select distinct p.typeName,p.typeId from y_topic_type p,"+tableName+" y where p.typeid=y.typeid ");
		if(map!=null && map.size()>0){
			if(map.get("queId")!=null){
				sf.append(" and queId=?");
				list.add(map.get("queId"));
			}
		}
		return this.excutQuery(sf.toString(), list.toArray());
	}

}
