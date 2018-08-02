package test;

import java.sql.Timestamp;

public class TestBean {
private Integer id;
private String userName;
private Integer psw;
private Timestamp day;

public Timestamp getDay() {
	return day;
}
public void setDay(Timestamp day) {
	this.day = day;
}

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getPsw() {
	return psw;
}
public void setPsw(Integer psw) {
	this.psw = psw;
}

}
