package com.sx.bean;

public class Img {
	private Integer id;
	private String imgPath;
	private String imgName;
	private Integer imgCommid;
	
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public Integer getImgCommid() {
		return imgCommid;
	}
	public void setImgCommid(Integer imgCommid) {
		this.imgCommid = imgCommid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
