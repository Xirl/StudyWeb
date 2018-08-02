package com.commom.struts.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commom.Utils.StringUtils;
import com.commom.struts.filter.entity.ActionMapping;
import com.commom.struts.filter.entity.PackageMapping;
import com.commom.struts.filter.entity.ResultMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlpy.Bean.TopicBean;
import com.zlpy.Bean.TopicTypeBean;
import com.zlpy.Dao.TopicDao;
import com.zlpy.Dao.TopicTypeDao;
import com.zlpy.Dao.Impl.TopicDaoImpl;
import com.zlpy.Dao.Impl.TopicTypeDaoImpl;




/**
 * 1��дһ��Action��
 *    ���巽��������Ϊ�������ĸ�ʽ�������������޸�
 *      public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
 *      
 *      }
 * 2����mystruts.xml�����ö�Ӧ��<action>��ǩ��<result>��ǩ
 * 
 * @author Administrator
 *
 */
public class StrutsPrepareAndExecuteFilter implements Filter {
	private String[] configFiles;
	private ActionMappingManager actionManager;
	private String encoding;
	private Integer pageSize;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=UTF-8");
		try {
			req.setAttribute("pageSize", pageSize);
			String actionName=this.getActionName(request);
			String packageName=this.getPackageName(request);
			ActionMapping actionMapping=actionManager.getActionMappingByActionName(actionName);
			PackageMapping packageMapping=actionManager.getPackageMapping(packageName);
			ResultMapping resultMapping=actionManager.executeMethod(actionMapping, request, response);
			if(resultMapping!=null){
				if("dispatch".equals(resultMapping.getResultType())){
					request.getRequestDispatcher(packageMapping.getPackageNamespace()+resultMapping.getResultUrl()).forward(request, response);
				}else if("redirect".equals(resultMapping.getResultType())){
					response.sendRedirect(packageMapping.getPackageNamespace()+resultMapping.getResultUrl());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private String getActionName(HttpServletRequest request){
		//1����������uri
		// /projectName/namespace/ActionName.action
		String uri=request.getRequestURI();
		//2����ù�����
		String cotextPath=request.getContextPath();
		//3�����actionName
		String url=uri.substring(cotextPath.length());
		String actionName=url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
		return actionName;
	}
	public String getPackageName(HttpServletRequest request){
		//��ȡ��ǰ��URI
				String uri=request.getRequestURI();
				//��ȡ������
				String path=request.getContextPath();
				//��ȡURL
				String url=uri.substring(path.length());
				String packageName=url.substring(1,url.lastIndexOf("/"));
				return packageName;
	}
	
	public void init(FilterConfig config) throws ServletException {
		String configFile=config.getInitParameter("configFile");
		encoding=config.getInitParameter("encoding");
		pageSize=StringUtils.str2Integer(config.getInitParameter("pageSize"), 0);
		if(configFile!=null && !"".equals(configFile)){
			configFiles=configFile.split(",");
		}
		actionManager=new ActionMappingManager(configFiles);
//		actionManager.print();
	}

}
