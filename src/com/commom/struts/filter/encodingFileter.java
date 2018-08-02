package com.commom.struts.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljc.entity.User;

public class encodingFileter implements Filter {
	private String encoding;
	private String[] strUrl={"personalinfo","updateperson","topicType","downvedio","uploadhead"};
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		String uri=request.getRequestURI();
		String webName=request.getContextPath();
		String url=uri.substring(webName.length()+1);
		String newUrl=url.substring(url.lastIndexOf("/")+1);
		boolean isFlag=true;
		if(strUrl!=null && strUrl.length>0){
			for(int i=0;i<strUrl.length;i++){
				if(newUrl.startsWith(strUrl[i])){
					isFlag=false;
					break;
				}
			}
			if(isFlag){
				chain.doFilter(req, resp);
				return;
			}
		}
		User user=null;
		if(request.getSession().getAttribute("currUser")!=null){
			user=(User) request.getSession().getAttribute("currUser");
			if(user!=null){
				int id=user.getUserId();
				if(id>0){
					chain.doFilter(req, resp);
					return;
				}
			}
		}
		request.getRequestDispatcher("../ljc/login.jsp").forward(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		encoding=config.getInitParameter("encoding");
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
