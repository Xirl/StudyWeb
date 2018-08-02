package com.sx.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commom.BaseAction.BaseAction;
import com.sx.bean.Type;
import com.sx.utils.Factory;

public class TypeAction extends BaseAction {
	
	public String getType(HttpServletRequest req,HttpServletResponse resp){
	System.out.println("进来没有");
		Map<String,Object> map=Factory.getMap();
		BaseServlet base=Factory.getJson();
		List<Type> list=Factory.getTypeImpl().find(Factory.getType());
		
		if(list!=null && list.size()>0){
			map.put("list", list);
		}
		base.toJson(map, resp);
		return null;	
	}

}
