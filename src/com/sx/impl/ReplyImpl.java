package com.sx.impl;

import java.util.List;

import com.sx.bean.Reply;
import com.sx.dao.ReplyDao;

public class ReplyImpl extends GeneralImpl<Reply> implements ReplyDao<Reply> {

	@Override
	public List<Reply> find(Reply entity) {
		return super.find(entity);
	}
	@Override
	public int save(Reply entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}
}
