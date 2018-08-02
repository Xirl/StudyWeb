package com.commom.struts.filter;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.commom.Utils.StringUtils;
import com.commom.struts.filter.entity.ActionMapping;
import com.commom.struts.filter.entity.PackageMapping;
import com.commom.struts.filter.entity.ResultMapping;




public class ActionMappingManager {
	private Map<String, PackageMapping> packageMappingMap=new HashMap<String, PackageMapping>();
	
	public ActionMappingManager(String[] confileFiles){
		if(confileFiles!=null && confileFiles.length>0){
			for(String configFile:confileFiles){
				init(configFile);
			}
		}
	}
	
	/**
	 * 解析配置文件将数据存入到packageMappingMap集合中
	 * @param configFile
	 */
	public void init(String configFile){
		try {
			//1、判断文件是否存在 
			if(configFile==null || "".equals(configFile)){
				throw new RuntimeException(configFile+"文件不存在");
			}
			
			//2、获得Document对象
			InputStream is=this.getClass().getResourceAsStream("/"+configFile);
			System.out.println("is:"+is);
			SAXReader sr=new SAXReader();
			Document doc=sr.read(is);
			System.out.println("doc:"+doc); 
			
			//3、获得根元素
			Element rootEle=doc.getRootElement();
			System.out.println("rootEleName:"+rootEle.getName());
			//4、获得package元素列表并遍历
			List<Element> packageEleList=rootEle.elements("package");
			if(packageEleList!=null && packageEleList.size()>0){
				for(Element packageEle:packageEleList){
					PackageMapping packageMapping=new PackageMapping();
					packageMapping.setPackageName(getAttributeValueByName(packageEle, "name", null));
					packageMapping.setPackageNamespace(getAttributeValueByName(packageEle, "namespace", packageMapping.getPackageNamespace()));
					packageMapping.setPackageExtends(getAttributeValueByName(packageEle, "extends", null));
					
					//判断数据的合法性
					if(!StringUtils.strIsNotNull(packageMapping.getPackageName())){
						throw new RuntimeException("package name不能为空");
					}
					if(packageMappingMap.containsKey(packageMapping.getPackageName())){
						throw new RuntimeException("package name必须唯一");
					}
					//4、获得action元素列表并遍历
					List<Element> actionEleList=packageEle.elements("action");
					if(actionEleList!=null && actionEleList.size()>0){
						for(Element actionEle:actionEleList){
							ActionMapping actionMapping=new ActionMapping();
							actionMapping.setActionName(getAttributeValueByName(actionEle, "name", null));
							actionMapping.setActionClass(getAttributeValueByName(actionEle, "class", null));
							actionMapping.setActionMethod(getAttributeValueByName(actionEle, "method", actionMapping.getActionMethod()));
							//判断数据的合法性
							if(!StringUtils.strIsNotNull(actionMapping.getActionName())){
								throw new RuntimeException("action name不能为空");
							}
							if(!StringUtils.strIsNotNull(actionMapping.getActionClass())){
								throw new RuntimeException("action class不能为空");
							}
							if(packageMapping.getActionMappingMap().containsKey(actionMapping.getActionName())){
								throw new RuntimeException("action name必须唯一");
							}
							
							//获得result元素列表
							List<Element> resultEleList=actionEle.elements("result");
							if(resultEleList!=null && resultEleList.size()>0){
								for(Element resultEle:resultEleList){
									ResultMapping resultMapping=new ResultMapping();
									resultMapping.setResultName(getAttributeValueByName(resultEle, "name", null));
									resultMapping.setResultType(getAttributeValueByName(resultEle, "type", resultMapping.getResultType()));
									resultMapping.setResultUrl(resultEle.getText());
									//判断数据的合法性
									if(!StringUtils.strIsNotNull(resultMapping.getResultName())){
										throw new RuntimeException("result name不能为空");
									}
									if(actionMapping.getResultMappingMap().containsKey(resultMapping.getResultName())){
										throw new RuntimeException("result name必须唯一");
									}
									//如果数据合法将result元素加入到action集合中
									actionMapping.getResultMappingMap().put(resultMapping.getResultName(), resultMapping);
								}
							}
							//如果数据合法将action元素加入到package集合中
							packageMapping.getActionMappingMap().put(actionMapping.getActionName(), actionMapping);
						}
					}
					packageMappingMap.put(packageMapping.getPackageName(), packageMapping);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据元素对象获得对应属性名的属性值
	 * @param tagEle		标签元素
	 * @param propertyName	属性名
	 * @param defValue		默认的的属性值
	 * @return
	 */
	private String getAttributeValueByName(
			Element tagEle,String propertyName,String defValue){
		if(tagEle!=null){
			Attribute attr=tagEle.attribute(propertyName);
			if(attr!=null){
				return attr.getText();
			}
		}
		return defValue;
	}
	
//	public void print(){
//		for (Map.Entry<String, PackageMapping> entry:packageMappingMap.entrySet()) {
//			PackageMapping packageMapping=entry.getValue();
//			System.out.println("package name:"+packageMapping.getPackageName());
//			System.out.println("package namespace:"+packageMapping.getPackageNamespace());
//			System.out.println("package extends:"+packageMapping.getPackageExtends());
//			for (Map.Entry<String, ActionMapping> entry1:packageMapping.getActionMappingMap().entrySet()) {
//				ActionMapping actionMapping=entry1.getValue();
//				System.out.println("---action name:"+actionMapping.getActionName());
//				System.out.println("---action class:"+actionMapping.getActionClass());
//				System.out.println("---action method:"+actionMapping.getActionMethod());
//				for (Map.Entry<String, ResultMapping> entry2:actionMapping.getResultMappingMap().entrySet()) {
//					ResultMapping resultMapping=entry2.getValue();
//					System.out.println("-----result name:"+resultMapping.getResultName());
//					System.out.println("-----result type:"+resultMapping.getResultType());
//					System.out.println("-----result url:"+resultMapping.getResultUrl());
//				}
//			}
//		}
//	}
	
	/**
	 * 根据给定的actionName找到对应的<action>标签的映射类
	 * @param actionName
	 * @return
	 */
	public ActionMapping getActionMappingByActionName(String actionName){
		for (Map.Entry<String, PackageMapping> entry:packageMappingMap.entrySet()) {
			PackageMapping packageMapping=entry.getValue();
			if(packageMapping.getActionMappingMap().containsKey(actionName)){
				return packageMapping.getActionMappingMap().get(actionName);
			}
		}
		return null;
	}
	
	/**
	 * 执行ActionMapping配置的请求方法获得resultMapping实例
	 * @param actionMapping	获得的<action>元素的映射类
	 * @param request	
	 * @param response
	 * @return
	 */
	public ResultMapping executeMethod(ActionMapping actionMapping
			,HttpServletRequest request,HttpServletResponse response){
		try {
			//1、获得要执行的Action的Class
			Class clazz=Class.forName(actionMapping.getActionClass());
			Object obj=clazz.newInstance();
			//执行Action对应的请求方法
			String methodName=actionMapping.getActionMethod();
			Class[] parameterTypes={HttpServletRequest.class,HttpServletResponse.class};
			Method method=clazz.getDeclaredMethod(methodName, parameterTypes);
			Object[] args={request,response};
			//执行方法获得resultName
			String resultName=(String) method.invoke(obj, args);
			//根据resultName获得对应的ResultMapping实例
			if(actionMapping!=null 
					&& actionMapping.getResultMappingMap()!=null 
					&& actionMapping.getResultMappingMap().size()>0){
				return actionMapping.getResultMappingMap().get(resultName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据packageName获得对应的packageMapping
	 * @param packageName
	 * @return
	 */
	public PackageMapping getPackageMapping(String packageName){
		if(packageName!=null){
			for(Map.Entry<String,PackageMapping> map:packageMappingMap.entrySet()){
				String name=map.getKey();
				if(name.equals(packageName)){
					return map.getValue();
				}
			}
		}
		return null;
	}
}
