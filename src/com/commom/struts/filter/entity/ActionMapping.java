package com.commom.struts.filter.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * <action>标签的映射类
 * 
 * @author Administrator
 *
 */
public class ActionMapping {
	private String actionName;// action name
	private String actionClass;// action class name（反射获取实例）
	private String actionMethod = "execute";// action class要执行的方法名

	private Map<String, ResultMapping> resultMappingMap = new HashMap<String, ResultMapping>();// 结果视图集合

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
