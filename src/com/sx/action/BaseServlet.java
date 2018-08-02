package com.sx.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseServlet extends  HttpServlet {
	protected void toJson(Object obj,HttpServletResponse resp){
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonStr=mapper.writeValueAsString(obj);
			PrintWriter out=resp.getWriter();
			System.out.println(jsonStr);
			out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
