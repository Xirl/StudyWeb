package com.sx.bean;

import java.sql.Clob;
import java.sql.Date;

import com.sx.utils.Utils;

public class Comment {
	private Integer id; 
	private Clob ccontent; 
	private Date cdate;
	private String tag;
	private Integer themeId; 
	private Integer toComment;
	
	public Integer getId() {  
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCcontent() {	
		return Utils.convertClob(this.ccontent, "ªÒ»° ß∞‹");
	}
	public void setCcontent(Clob ccontent) {
		this.ccontent = ccontent;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getThemeId() {
		return themeId;
	}
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}
	public Integer getToComment() {
		return toComment;
	}
	public void setToComment(Integer toComment) {
		this.toComment = toComment;
	}
}
