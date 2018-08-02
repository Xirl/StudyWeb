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
	 * ���������ļ������ݴ��뵽packageMappingMap������
	 * @param configFile
	 */
	public void init(String configFile){
		try {
			//1���ж��ļ��Ƿ���� 
			if(configFile==null || "".equals(configFile)){
				throw new RuntimeException(configFile+"�ļ�������");
			}
			
			//2�����Document����
			InputStream is=this.getClass().getResourceAsStream("/"+configFile);
			System.out.println("is:"+is);
			SAXReader sr=new SAXReader();
			Document doc=sr.read(is);
			System.out.println("doc:"+doc); 
			
			//3����ø�Ԫ��
			Element rootEle=doc.getRootElement();
			System.out.println("rootEleName:"+rootEle.getName());
			//4�����packageԪ���б�����
			List<Element> packageEleList=rootEle.elements("package");
			if(packageEleList!=null && packageEleList.size()>0){
				for(Element packageEle:packageEleList){
					PackageMapping packageMapping=new PackageMapping();
					packageMapping.setPackageName(getAttributeValueByName(packageEle, "name", null));
					packageMapping.setPackageNamespace(getAttributeValueByName(packageEle, "namespace", packageMapping.getPackageNamespace()));
					packageMapping.setPackageExtends(getAttributeValueByName(packageEle, "extends", null));
					
					//�ж����ݵĺϷ���
					if(!StringUtils.strIsNotNull(packageMapping.getPackageName())){
						throw new RuntimeException("package name����Ϊ��");
					}
					if(packageMappingMap.containsKey(packageMapping.getPackageName())){
						throw new RuntimeException("package name����Ψһ");
					}
					//4�����actionԪ���б�����
					List<Element> actionEleList=packageEle.elements("action");
					if(actionEleList!=null && actionEleList.size()>0){
						for(Element actionEle:actionEleList){
							ActionMapping actionMapping=new ActionMapping();
							actionMapping.setActionName(getAttributeValueByName(actionEle, "name", null));
							actionMapping.setActionClass(getAttributeValueByName(actionEle, "class", null));
							actionMapping.setActionMethod(getAttributeValueByName(actionEle, "method", actionMapping.getActionMethod()));
							//�ж����ݵĺϷ���
							if(!StringUtils.strIsNotNull(actionMapping.getActionName())){
								throw new RuntimeException("action name����Ϊ��");
							}
							if(!StringUtils.strIsNotNull(actionMapping.getActionClass())){
								throw new RuntimeException("action class����Ϊ��");
							}
							if(packageMapping.getActionMappingMap().containsKey(actionMapping.getActionName())){
								throw new RuntimeException("action name����Ψһ");
							}
							
							//���resultԪ���б�
							List<Element> resultEleList=actionEle.elements("result");
							if(resultEleList!=null && resultEleList.size()>0){
								for(Element resultEle:resultEleList){
									ResultMapping resultMapping=new ResultMapping();
									resultMapping.setResultName(getAttributeValueByName(resultEle, "name", null));
									resultMapping.setResultType(getAttributeValueByName(resultEle, "type", resultMapping.getResultType()));
									resultMapping.setResultUrl(resultEle.getText());
									//�ж����ݵĺϷ���
									if(!StringUtils.strIsNotNull(resultMapping.getResultName())){
										throw new RuntimeException("result name����Ϊ��");
									}
									if(actionMapping.getResultMappingMap().containsKey(resultMapping.getResultName())){
										throw new RuntimeException("result name����Ψһ");
									}
									//������ݺϷ���resultԪ�ؼ��뵽action������
									actionMapping.getResultMappingMap().put(resultMapping.getResultName(), resultMapping);
								}
							}
							//������ݺϷ���actionԪ�ؼ��뵽package������
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
	 * ����Ԫ�ض����ö�Ӧ������������ֵ
	 * @param tagEle		��ǩԪ��
	 * @param propertyName	������
	 * @param defValue		Ĭ�ϵĵ�����ֵ
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
	 * ���ݸ�����actionName�ҵ���Ӧ��<action>��ǩ��ӳ����
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
	 * ִ��ActionMapping���õ����󷽷����resultMappingʵ��
	 * @param actionMapping	��õ�<action>Ԫ�ص�ӳ����
	 * @param request	
	 * @param response
	 * @return
	 */
	public ResultMapping executeMethod(ActionMapping actionMapping
			,HttpServletRequest request,HttpServletResponse response){
		try {
			//1�����Ҫִ�е�Action��Class
			Class clazz=Class.forName(actionMapping.getActionClass());
			Object obj=clazz.newInstance();
			//ִ��Action��Ӧ�����󷽷�
			String methodName=actionMapping.getActionMethod();
			Class[] parameterTypes={HttpServletRequest.class,HttpServletResponse.class};
			Method method=clazz.getDeclaredMethod(methodName, parameterTypes);
			Object[] args={request,response};
			//ִ�з������resultName
			String resultName=(String) method.invoke(obj, args);
			//����resultName��ö�Ӧ��ResultMappingʵ��
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
	 * ����packageName��ö�Ӧ��packageMapping
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
