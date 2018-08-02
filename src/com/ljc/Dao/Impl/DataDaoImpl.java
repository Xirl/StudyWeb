package com.ljc.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.commom.BaseDao.BaseDao;
import com.ljc.Dao.DataDao;
import com.ljc.entity.Data;

public class DataDaoImpl extends BaseDao<Data> implements DataDao {
  
	@Override
	public List<Data> findbyCondition(Map<String, Object> map) {
		StringBuffer sf =new StringBuffer();
		sf.append("select * from c_data d where ( d.typeid=(select typeid from c_datatype where name=?))");
		List<Object> list=new ArrayList<Object>();
		list.add(map.get("dataName"));
		return this.excutQuery(sf.toString(), list.toArray());
	}
}
