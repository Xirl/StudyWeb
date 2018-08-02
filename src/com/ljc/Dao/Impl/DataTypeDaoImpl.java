package com.ljc.Dao.Impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import com.commom.BaseDao.BaseDao;
import com.ljc.Dao.DataTypeDao;
import com.ljc.entity.DataType;

import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.dcn.DatabaseChangeRegistration;

public class DataTypeDaoImpl extends BaseDao<DataType> implements DataTypeDao{
  
	@Override
	public List<DataType> findbyCondition(String name) {
		String sql="select * from c_datatype where name=?";
		Object [] obj={name};
		return this.excutQuery(sql, obj);
	}

	@Override
	public List<DataType> select(Map<String, Object> map) {
		String sql="select * from c_datatype ";
		return this.excutQuery(sql, null);
	}
}
