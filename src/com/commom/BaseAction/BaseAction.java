
	package com.commom.BaseAction;

	import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

	import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	import com.commom.Utils.StringUtils;
import com.commom.struts.action.Action;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljc.entity.User;

	public class BaseAction implements Action {
		public User getCurrLoginUser(HttpSession session){
//			return new User(1,"admin","123");
			return (User) session.getAttribute("user");
		}
		
		
		/**
		 * @param request
		 * @param clazz
		 * @return
		 * @throws Exception 
		 */
		public Object getEntity(HttpServletRequest request,Class clazz) throws Exception{
			request.setCharacterEncoding("utf-8");
			Object result=null;
			String value=null;
//			System.out.println("loginName:"+request.getParameter("loginName"));
			try {
				result=clazz.newInstance();
				Field[] propertyNameFileds=clazz.getDeclaredFields();
				if(propertyNameFileds!=null && propertyNameFileds.length>0){
					for(Field f:propertyNameFileds){
						String fname=f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
		           		String methodName="set"+fname;
		           		Method m1=clazz.getDeclaredMethod(methodName,f.getType());
//		           		System.out.println(f.getName());
		           		System.out.println(f.getName());
		           		value=request.getParameter(f.getName());
						if(value==null)
						{
							continue;
						}
						if(f.getType().toString().indexOf("Double")>0)
	        			{
	        				m1.invoke(result,StringUtils.str2Double(value, null));
	        			}
	           			else if (f.getType().toString().indexOf("Integer")>0) {
	           				m1.invoke(result, StringUtils.str2Integer(value, null));
						}
	           			else if (f.getType().toString().indexOf("Date")>0) {
	           				SimpleDateFormat sdf=new SimpleDateFormat();
	           				java.util.Date date1=sdf.parse(value);
	           				java.sql.Date date=new java.sql.Date(date1.getTime());
	            			m1.invoke(result,date);
						}
	           			else if (f.getType().toString().indexOf("Timestamp")>0) {
	           				Timestamp time=StringUtils.str2Timestamp(value, null);
	           				m1.invoke(result,time);
						}
	           			else {
	           				m1.invoke(result,(Object) value);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		/**
		 * 将返回值写入流中
		 * @param request
		 * @param response
		 * @param obj
		 */
		public void getJson(HttpServletRequest request,
				HttpServletResponse response,Object obj){
			ObjectMapper mapper=new ObjectMapper();
			String json;
			try {
				json = mapper.writeValueAsString(obj);
				PrintWriter out=response.getWriter();
				out.println(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


