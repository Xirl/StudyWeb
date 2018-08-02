package com.sx.bean;

import java.sql.Clob;
import java.sql.Date;

public class LoadComment {
	private Integer id;
	private String tcontext;
	private Date cdate; 
	private String loginName;
	private String ccontent;
	private Integer zcount;
	private Integer rcount;
	private String type;
	private Integer userid; 
	//private Integer ruserid;
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
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public Integer getZcount() {
		return zcount;
	}
	public void setZcount(Integer zcount) {
		this.zcount = zcount;
	}
	public Integer getRcount() {
		return rcount;
	}
	public void setRcount(Integer rcount) {
		this.rcount = rcount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
//	public Integer getRuserid() {
//		return ruserid;
//	}
//	public void setRuserid(Integer ruserid) {
//		this.ruserid = ruserid;
//	}
	
}
