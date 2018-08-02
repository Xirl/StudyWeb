package com.ljc.Dao;

import java.util.Map;

import com.ljc.entity.User;


public interface UserDao {
	/**
	 * 通过ID查询用户
	 * @param UserId
	 * @return
	 */
	public User findById(Integer UserId);
	/**
	 * 查询用户
	 * @param map
	 * @return
	 */
	public User findByCondition(Map<String, Object> map);
	/**
	 * 查询有没有用户名相同的
	 * @param map
	 * @return
	 */
	public int selectLoginName(String LoginName);
	
	/**
	 * 登录用户
	 * @param loginName
	 * @param password
	 * @return
	 */
	public User UserLogin(String loginName,String password);
	/**
	 * 删除功能
	 * @param UserId
	 * @return
	 */
	public int delete(Integer UserId);
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	public int update(User user);
	/**
	 * 增加
	 * @param user
	 * @return
	 */
	public int insert(User user);
}
