package com.commom.struts.filter.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * <action>��ǩ��ӳ����
 * 
 * @author Administrator
 *
 */
public class ActionMapping {
	private String actionName;// action name
	private String actionClass;// action class name�������ȡʵ����
	private String actionMethod = "execute";// action classҪִ�еķ�����

	private Map<String, ResultMapping> resultMappingMap = new HashMap<String, ResultMapping>();// �����ͼ����

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public Map<String, ResultMapping> getResultMappingMap() {
		return resultMappingMap;
	}

	public void setResultMappingMap(Map<String, ResultMapping> resultMappingMap) {
		this.resultMappingMap = resultMappingMap;
	}

}
