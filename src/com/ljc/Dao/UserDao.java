package com.ljc.Dao;

import java.util.Map;

import com.ljc.entity.User;


public interface UserDao {
	/**
	 * ͨ��ID��ѯ�û�
	 * @param UserId
	 * @return
	 */
	public User findById(Integer UserId);
	/**
	 * ��ѯ�û�
	 * @param map
	 * @return
	 */
	public User findByCondition(Map<String, Object> map);
	/**
	 * ��ѯ��û���û�����ͬ��
	 * @param map
	 * @return
	 */
	public int selectLoginName(String LoginName);
	
	/**
	 * ��¼�û�
	 * @param loginName
	 * @param password
	 * @return
	 */
	public User UserLogin(String loginName,String password);
	/**
	 * ɾ������
	 * @param UserId
	 * @return
	 */
	public int delete(Integer UserId);
	/**
	 * �޸�
	 * @param user
	 * @return
	 */
	public int update(User user);
	/**
	 * ����
	 * @param user
	 * @return
	 */
	public int insert(User user);
}
