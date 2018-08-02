package com.ljc.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.enterprise.inject.New;

public class User {
	private Integer userId;
	private String loginName;
	private String password;
	private String name;
	private String sex;  
	private Timestamp regtime=new Timestamp(new Date().getTime());
	private String head="moren.jpg";
	private String signature;
	private Timestamp birthday;
	private String usertype="member";
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Timestamp getRegtime() {
		return regtime;
	}
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
}
