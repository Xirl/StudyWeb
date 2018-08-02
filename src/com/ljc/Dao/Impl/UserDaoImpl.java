package com.ljc.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.commom.BaseDao.BaseDao;
import com.ljc.Dao.UserDao;
import com.ljc.entity.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    
	@Override
	public User findById(Integer UserId) {
		String sql="select * from c_user where userid=?";
		Object[] obj={UserId};
		User user=new User();
		List<User> list=new ArrayList<User>();
		list=this.excutQuery(sql, obj);
		for(User u:list){
			user.setUserId(u.getUserId());
			user.setLoginName(u.getLoginName());
			user.setPassword(u.getPassword());
			user.setName(u.getName());
			user.setUsertype(u.getUsertype());
		}
		return user;
	}

	@Override
	public User findByCondition(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		sf.append("select * from c_user where 1=1");
		List<Object> list=new ArrayList<Object>();
		if(map!=null && map.size()>0){
			if(map.get("loginName")!=null){
				sf.append("and loginName=?");
				list.add(map.get("loginName"));
			}
		}
		List<User> UserList=new ArrayList<User>();
		UserList=this.excutQuery(sf.toString(),list.toArray());
		User user=null;
		if(UserList!=null && UserList.size()>0){
			for(User u:UserList){
				user=new User();
				user.setUserId(u.getUserId());
				user.setLoginName(u.getLoginName());
				user.setPassword(u.getPassword());
				user.setName(u.getName());
				user.setUsertype(u.getUsertype());
			}
		}
		return user;
	}

	@Override
	public User UserLogin(String loginName, String password) {
		String sql="select * from c_user where loginName=? and password=?";
		Object[] obj={loginName,password};
		User user=new User();
		List<User> list=new ArrayList<User>();
		list=this.excutQuery(sql, obj);
		for(User u:list){
			user.setUserId(u.getUserId());
			user.setLoginName(u.getLoginName());
			user.setPassword(u.getPassword());
			user.setName(u.getName());
			user.setSex(u.getSex());
			user.setRegtime(u.getRegtime());
			user.setHead(u.getHead());
			user.setSignature(u.getSignature());
			user.setBirthday(u.getBirthday());
			user.setUsertype(u.getUsertype());
		}
		return user;
	}

	@Override
	public int delete(Integer UserId) {
		String sql="delete c_user where userid=?";
		Object[] obj={UserId};
		return this.excutUpdate(sql, obj);
	}

	@Override
	public int update(User user) {
		String sql="update c_user set loginName=?,password=?,name=?,sex=?,head=?,signature=?,birthday=? where userid=?";
		Object[] obj={
				user.getLoginName(),
				user.getPassword(),
				user.getName(),
				user.getSex(),
				user.getHead(),
				user.getSignature(),
				user.getBirthday(),
				user.getUserId()
		};
		return this.excutUpdate(sql, obj);
	}

	@Override
	public int insert(User user) {
		String sql="insert into c_user values(seq_userid.nextval,?,?,?,?,?,?,?,?,?)";
		Object[] obj={
				user.getLoginName(),
				user.getPassword(),
				user.getName(),
				user.getSex(),
				user.getRegtime(),
				user.getHead(),
				user.getSignature(),
				user.getBirthday(),
				user.getUsertype()
		};
		return this.excutUpdate(sql, obj);
	}

	@Override
	public int selectLoginName(String LoginName) {
		String sql="select * from c_user where loginName=?";
		Object[] obj={LoginName};
		int row=this.getCount(sql, obj);
		return row;
	}

}
