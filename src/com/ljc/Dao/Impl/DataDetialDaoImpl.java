package com.ljc.Dao.Impl;

import java.util.List;
import java.util.Map;

import com.commom.BaseDao.BaseDao;
import com.ljc.Dao.DataDetialDao;
import com.ljc.entity.DataDetial;

public class DataDetialDaoImpl extends BaseDao<DataDetial> implements DataDetialDao {
  
	@Override
	public List<DataDetial> findbyCondition(Integer dataid) {
		StringBuffer sf=new StringBuffer();
		sf.append("select * from c_datadetial where dataid=?");
		Object[] obj={dataid};
		return this.excutQuery(sf.toString(), obj);
	}

	@Override
	public List<DataDetial> select(String dataname) {
		String sql="select * from c_datadetial where dataid=(select dataid from c_data where dataname=?)";
		Object[] obj={dataname};
		return this.excutQuery(sql, obj);
	}

	@Override
	public List<DataDetial> findbyid(Integer detialid) {
		StringBuffer sf=new StringBuffer();
		sf.append("select * from c_datadetial where detialid=?");
		Object[] obj={detialid};
		return this.excutQuery(sf.toString(), obj);
	}

	@Override
	public List<DataDetial> finddataName(String	typeName) {
		String sql="select * from c_datadetial d where ( d.typeid=(select typeid from c_datatype where name=?))";
		Object[] obj={typeName};
		return this.excutQuery(sql, obj);
	}

	@Override
	public List<DataDetial> findTitle(Integer dataid) {
		String sql="select * from c_datadetial d where dataid= ?";
		Object[] obj={dataid};
		return this.excutQuery(sql, obj);
	}
}
