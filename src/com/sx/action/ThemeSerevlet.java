package com.sx.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sx.bean.Comment;
import com.sx.bean.PageBean;
import com.sx.bean.Reply;
import com.sx.bean.Theme;
import com.sx.utils.Factory;

public class ThemeSerevlet extends BaseServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String opr=req.getParameter("opr");
		if("1".equals(opr)){
			initData(req,resp);
		}else{
			initReply(req,resp);
		}
		
	}
	
	
	
	
	//获取初始的数据（主题和评论）
	public void initData(HttpServletRequest req, HttpServletResponse resp){
		System.out.println(resp.getStatus() + "状态码值");
		String pageIndex = req.getParameter("pageIndex");
		//获取不同的PageBean
		PageBean<Theme> themepage = Factory.getThemeImpl().findCondition(null, 3,
				Integer.parseInt(pageIndex));
		PageBean<Comment> Commpage = Factory.getCommentImpl().findCondition(
				null, 3, Integer.parseInt(pageIndex));
		Map<String, Object> map = new HashMap<String, Object>();
		if (themepage.getList() != null && themepage.getList().size() > 0) {
			map.put("theme", themepage);
		}
		System.out.println(themepage.toString()+"主题");
		if (Commpage.getList() != null && Commpage.getList().size() > 0) {
			map.put("comment", Commpage);
		}
		map.put("status", resp.getStatus());
		System.out.println("list是：" + Commpage.getList().get(0));
		toJson(map, resp);
	}

	public void initReply(HttpServletRequest req, HttpServletResponse resp) {
		List<Reply> RepList = Factory.getThemeImpl().find(null);
		Map<String, Object> map = new HashMap<String, Object>();
		if (RepList != null && RepList.size() > 0) {
			map.put("reply", RepList);
		}
		map.put("status", resp.getStatus());
		toJson(map, resp);
	}
}

    

