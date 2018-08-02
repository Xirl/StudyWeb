package com.sx.impl;

import java.util.List;
import java.util.Map;

import com.sx.bean.Comment;
import com.sx.bean.PageBean;
import com.sx.dao.GeneralDao;

public class CommentImpl extends GeneralImpl<Comment> implements GeneralDao<Comment> {

	@Override
	public List<Comment> find(Comment entity) {
	
		return super.find(entity);
	}
	
	@Override
	public PageBean<Comment> findCondition(Map<String, Object> map, int pageSize,
			int pageIndex) {
		// TODO Auto-generated method stub
		return super.findCondition(map, pageSize, pageIndex);
	}
	
	
}
