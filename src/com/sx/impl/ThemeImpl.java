package com.sx.impl;

import java.util.List;
import java.util.Map;

import com.sx.bean.PageBean;
import com.sx.bean.Theme;
import com.sx.dao.GeneralDao;

public class ThemeImpl extends GeneralImpl<Theme> implements GeneralDao<Theme> {

	@Override
	public List<Theme> find(Theme entity) {
		// TODO Auto-generated method stub
		return super.find(entity);
	}

	@Override
	public int save(Theme entity) {
		// TODO Auto-generated method stub
	  return super.save(entity);
	}
	
	@Override
	public PageBean<Theme> findCondition(Map<String, Object> map, int pageSize,
			int pageIndex) {
		// TODO Auto-generated method stub
		return super.findCondition(map, pageSize, pageIndex);
	}

}
