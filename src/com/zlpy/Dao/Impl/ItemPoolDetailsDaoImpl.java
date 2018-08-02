package com.zlpy.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.commom.BaseDao.BaseDao;
import com.zlpy.Bean.ItemPoolDetailsBean;
import com.zlpy.Bean.PageBean;
import com.zlpy.Dao.ItemPoolDetailsDao;

public class ItemPoolDetailsDaoImpl extends BaseDao<ItemPoolDetailsBean> implements ItemPoolDetailsDao {

	@Override
	public PageBean<ItemPoolDetailsBean> fingBypage(int pageIndex, int pageSize,Map<String, Object> map) {
		int count=this.findCount(map);
		if(count>0){
			PageBean<ItemPoolDetailsBean> page=new PageBean<ItemPoolDetailsBean>();
			List<Object> list=new ArrayList<Object>();
			page.setPageIndex(pageIndex);
			page.setPageSize(pageSize);
			page.setCountSize(count);
			page.setCountIndex(count%pageSize==0?count/pageSize:count/pageSize+1);
			StringBuffer sf=new StringBuffer();
			int start=(page.getPageIndex()-1)*page.getPageSize()+1;
			int end=page.getPageIndex()*page.getPageSize();
			sf.append("select * from (");
			sf.append("select g.*,rownum r from y_item_pool_details g where 1=1");
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
		sf.append("select * from y_item_pool_details where 1=1 ");
		List<Object> list=new ArrayList<Object>();
		return this.getCount(sf.toString(), list.toArray());
	}

	@Override
	public List<ItemPoolDetailsBean> findAllPool(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("select * from y_item_pool_details where 1=1");
		return this.excutQuery(sf.toString(),list.toArray());
	}

	
}
