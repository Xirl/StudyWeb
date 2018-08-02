package com.sx.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Reply {
	private Integer id; 
	private String replyContent; 
	private String replyType; 
	private Timestamp replyDate; 
	private Integer commentId; 
	private Integer replyId; 
	private Integer replyUser; 
	private Integer toUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyType() {
		return replyType;
	}
	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}
	public String getReplyDate() {
		SimpleDateFormat time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return time.format(replyDate);
	}
	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public Integer getReplyUser() {
		return replyUser;
	}
	public void setReplyUser(Integer replyUser) {
		this.replyUser = replyUser;
	}
	public Integer getToUser() {
		return toUser;
	}
	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}
}
