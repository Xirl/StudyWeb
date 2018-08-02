package com.commom.struts.filter.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * <package>��ǩ��ӳ����
 * 
 * @author Administrator
 *
 */
public class PackageMapping {
	private String packageName;// package name
	private String packageNamespace = "/";// �����ռ�
	private String packageExtends;// �̳еĸ�����

	private Map<String, ActionMapping> actionMappingMap = new HashMap<String, ActionMapping>();// action����

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageNamespace() {
		return packageNamespace;
	}

	public void setPackageNamespace(String packageNamespace) {
		this.packageNamespace = packageNamespace;
	}

	public String getPackageExtends() {
		return packageExtends;
	}

	public void setPackageExtends(String packageExtends) {
		this.packageExtends = packageExtends;
	}

	public Map<String, ActionMapping> getActionMappingMap() {
		return actionMappingMap;
	}

	public void setActionMappingMap(Map<String, ActionMapping> actionMappingMap) {
		this.actionMappingMap = actionMappingMap;
	}

}
