package com.zlpy.Dao.Impl;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oracle.jdbc.internal.OracleTypes;

import com.commom.BaseDao.BaseDao;
import com.zlpy.Bean.TopicBean;
import com.zlpy.Dao.TopicDao;

public class TopicDaoImpl extends BaseDao<TopicBean> implements TopicDao{

	@Override
	public List<TopicBean> findAllTopic(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		sf.append("select * from y_topic where 1=1");
		List<Object> list=new ArrayList<Object>();
		int count=this.findCount(map);
		if(count<=0){
			return null;
		}
		if(map!=null && map.size()>0){
			if(map.get("queId")!=null){
				sf.append(" and queId=?");
				list.add(map.get("queId"));
			}
			if(map.get("typeId")!=null){
				sf.append(" and typeId=?");
				list.add(map.get("typeId"));
			}
		}
		return this.excutQuery(sf.toString(), list.toArray());
	}

	@Override
	public int findCount(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("select count(*) from y_topic where 1=1");
		if(map!=null && map.size()>0){
			if(map.get("typeId")!=null){
				sf.append(" and typeId=?");
				list.add(map.get("typeId"));
			}
		}
		return this.getCount(sf.toString(), list.toArray());
	}

	@Override
	public int insertTopic(TopicBean topic) {
		conn=this.getConnection();
		CallableStatement call=null;
		if(topic==null) return 0;
		try{
			call=conn.prepareCall("{?=call fun_topic(?,?,?,?)}");
			call.registerOutParameter(1, OracleTypes.NUMBER);
			call.setObject(2, topic.getTopicName());
			call.setObject(3, topic.getScore());
			call.setInt(4, topic.getTypeId());
			call.setInt(5, topic.getQueId());
			call.execute();
			return call.getInt(1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

}
