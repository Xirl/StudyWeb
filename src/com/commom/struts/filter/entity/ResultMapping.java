package com.commom.struts.filter.entity;

/**
 * <result>标签的映射类
 * 
 * @author Administrator
 *
 */
public class ResultMapping {
	private String resultName;// result name
	private String resultType = "dispatch";// 跳转方式：默认值是dispath(转发），redirect（重定向）
	private String resultUrl;// 跳转的目标url

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getResultUrl() {
		return resultUrl;
	}

	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}

}
