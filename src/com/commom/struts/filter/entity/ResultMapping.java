package com.commom.struts.filter.entity;

/**
 * <result>��ǩ��ӳ����
 * 
 * @author Administrator
 *
 */
public class ResultMapping {
	private String resultName;// result name
	private String resultType = "dispatch";// ��ת��ʽ��Ĭ��ֵ��dispath(ת������redirect���ض���
	private String resultUrl;// ��ת��Ŀ��url

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
