package com.sx.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.commom.BaseDao.BaseDao;
import com.sx.bean.Tags;
import com.sx.bean.Util;
import com.sx.dao.TagsDao;

public class TagsImpl extends GeneralImpl<Tags> implements TagsDao<Tags> {
BaseDao base=new BaseDao();
	@Override
	public List<Util> findId(Map<String,Object> map) {
		List<Util> list=new ArrayList<Util>();
		String sql="select count(id) c,typeid from x_tags where status="+map.get("status")+
				" and totheme="+map.get("totheme")+" group by typeid order by c desc";
		System.out.println(sql+"取赞的sql");
		conn=base.getConnection1();
		try {
			pra=conn.prepareStatement(sql);
			rs=pra.executeQuery();
			while(rs.next()){
				Util util=new Util();
				util.setCount(rs.getInt(1));
				util.setCommentid(rs.getInt(2));
				list.add(util);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			base.closeAll(conn, pra, rs);
		}
		return list;
	}
	
	public int updateBy(Tags entity){
		conn=base.getConnection();
		String sql="update x_tags set status="+entity.getStatus()+" where typeId="+entity.getTypeId();
		try {
			pra=conn.prepareStatement(sql);
			System.out.println(sql+"更新点赞的");
			return pra.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 0;
		
	}

}
