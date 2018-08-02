package com.sx.action;

import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ljc.entity.User;
import com.sx.utils.Factory;

public class VerifyAction extends BaseServlet {
	public String verifyUser(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		User user1=Factory.getUserImpl().findById(1);
		HttpSession session=req.getSession();
		session.setAttribute("currUser", user1);
		Object user=session.getAttribute("currUser");
		Map<String,Object> map=Factory.getMap();
		String msg="0";
		if(user!=null){
			map.put("user",user);
			msg="1";
		}
		map.put("msg", msg);
		Factory.getJson().toJson(map, resp);
		return null;	
	}

}
