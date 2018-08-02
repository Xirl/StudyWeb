package com.sx.bean;

import java.sql.Clob;
import java.sql.Date;

public class Theme {
	private Integer id; 
	private String tcontext; 
	private Date tdate; 
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
