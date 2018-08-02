package com.sx.bean;

import java.sql.Date;

public class LoadTheme {
	private Integer id;
	private String tcontext;
	private Date tdate; 
	private String loginName;
	private String type;
	private Integer count;
	private Integer userid; 
	private Integer themeType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTcontext() {
		return tcontext;
	}
	public void setTcontext(String tcontext) {
		this.tcontext = tcontext;
	}
	public Date getTdate() {
		return tdate;
	}
	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getThemeType() {
		return themeType;
	}
	public void setThemeType(Integer themeType) {
		this.themeType = themeType;
	}
	
}
