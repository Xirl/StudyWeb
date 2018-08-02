package com.zlpy.Bean;

import java.sql.Timestamp;

public class QuestionPaperBean {
private int queId;
private String questionPaperName;
private Integer detailsId;
private Timestamp uploadTime;

private int userId;
public int getQueId() {
	return queId;
}
public void setQueId(int queId) {
	this.queId = queId;
}

public Integer getDetailsId() {
	return detailsId;
}
public void setDetailsId(Integer detailsId) {
	this.detailsId = detailsId;
}
public String getQuestionPaperName() {
	return questionPaperName;
}

public void setQuestionPaperName(String questionPaperName) {
	this.questionPaperName = questionPaperName;
}
public void userId(String questionPaperName) {
	this.questionPaperName = questionPaperName;
}
public Timestamp getUploadTime() {
	return uploadTime;
}
public void setUploadTime(Timestamp uploadTime) {
	this.uploadTime = uploadTime;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}

}
