package com.zlpy.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.commom.BaseDao.BaseDao;
import com.zlpy.Bean.OptionsBean;
import com.zlpy.Dao.OptionsDao;

public class OptionsDaoImpl extends BaseDao<OptionsBean> implements OptionsDao {

	@Override
	public List<OptionsBean> findAllOptions(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("select * from y_options where 1=1");
		if(map!=null && map.size()>0){
			if(map.get("topicId")!=null){
				sf.append(" and topicId=?");
				list.add(map.get("topicId"));
			}
		}
		return this.excutQuery(sf.toString(), list.toArray());
	}

	@Override
	public OptionsBean findOption(int topicId) {
		StringBuffer sf=new StringBuffer();
		sf.append("select * from y_options where topicId=?");
		Object obj[]={topicId};
		List<OptionsBean> list=this.excutQuery(sf.toString(), obj);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int inserOption(OptionsBean option) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("insert into y_options values(seq.nextval,?,?)");
		list.add(option.getOptions());
		list.add(option.getTopicId());
		return this.excutUpdate(sf.toString(), list.toArray());
	}

	@Override
	public int findOpCount(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("select * from y_options  where 1=1");
		if(map!=null && map.size()>0){
			if(map.get("topicId")!=null){
				sf.append(" and topicId=?");
				list.add(map.get("topicId"));
			}
		}
		return this.getCount(sf.toString(), list.toArray());
	}

}
