package com.ljc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commom.BaseAction.BaseAction;
import com.commom.Utils.StringUtils;
import com.commom.struts.action.Action;
import com.ljc.Dao.DataDao;
import com.ljc.Dao.DataDetialDao;
import com.ljc.Dao.DataTypeDao;
import com.ljc.Dao.UserDao;
import com.ljc.Dao.Impl.DataDaoImpl;
import com.ljc.Dao.Impl.DataDetialDaoImpl;
import com.ljc.Dao.Impl.DataTypeDaoImpl;
import com.ljc.Dao.Impl.UserDaoImpl;
import com.ljc.entity.Data;
import com.ljc.entity.DataDetial;
import com.ljc.entity.DataType;
import com.ljc.entity.User;
import com.zlpy.Bean.ItemPoolDetailsBean;
import com.zlpy.Bean.PageBean;
import com.zlpy.Dao.ItemPoolDetailsDao;
import com.zlpy.Dao.Impl.ItemPoolDetailsDaoImpl;

public class DataAction extends BaseAction implements Action {
	public String select(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {    
			DataDao datadao=new DataDaoImpl();
			List<Data> list=new ArrayList<Data>();
			String dataName=req.getParameter("dataName");
			System.out.println("dataName:"+dataName);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("dataName", dataName);
			map.put("dataType", dataName);
			map.put("typeId", dataName);
			list=datadao.findbyCondition(map);
			if(list!=null && list.size()>0){
				this.getJson(req, resp, list);
				return null;   
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			System.out.println("msg:"+msg);
			this.getJson(req, resp, msg);
			return null;
		}
		this.getJson(req, resp, "没有数据");
		return null;
	}
	
	public String findName(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			System.out.println("参数："+req.getParameter("dataName"));
			DataTypeDao typedao=new DataTypeDaoImpl();
			List<DataType> list=new ArrayList<DataType>();
			String dataName=req.getParameter("dataName");
			list=typedao.findbyCondition(dataName);
			for(DataType d:list){
				System.out.println(d.getName());
			}
			if(list!=null && list.size()>0){
				this.getJson(req, resp, list);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			System.out.println("msg:"+msg);
			this.getJson(req, resp, msg);
			return null;
		}
		this.getJson(req, resp, "没有数据");
		return null;
	}
	public String findData(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			System.out.println("I'm come in?");
			Integer dataid=StringUtils.str2Integer(req.getParameter("id"), null);
			String detialname=req.getParameter("name");
			System.out.println("dataid:"+dataid);
			DataDetialDao detial=new DataDetialDaoImpl();
			List<DataDetial>list=new ArrayList<DataDetial>();
			list=detial.findbyCondition(dataid);
			this.getJson(req, resp, list);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			this.getJson(req, resp, msg);
			return null;
		}
	}
	public String getDataTypeName(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			DataTypeDao typedao=new DataTypeDaoImpl();
			List<DataType> list=new ArrayList<DataType>();
			list=typedao.select(null);
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getName());
			}
			//插入代码
			ItemPoolDetailsDao it = new ItemPoolDetailsDaoImpl();
			PageBean<ItemPoolDetailsBean> page = new PageBean<ItemPoolDetailsBean>();
			Integer pageIndex = StringUtils.str2Integer(
					(String) req.getAttribute("pageIndex"), 1);
			page = it.fingBypage(pageIndex,
					(Integer) req.getAttribute("pageSize"), null);
			//System.out.println("getCountSize:"+page.getCountSize());
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("datea", list);
			map.put("page", page);
			this.getJson(req, resp, map);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			this.getJson(req, resp, msg);
			return null;
		}
	}public String selectDataDetial(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			String dataname=req.getParameter("detialname");
			System.out.println("dataname:"+dataname);
			DataDetialDao detialdao=new DataDetialDaoImpl();
			List<DataDetial> list=new ArrayList<DataDetial>();
			list=detialdao.select(dataname);
			this.getJson(req, resp, list);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			this.getJson(req, resp, msg);
			return null;
		}
	}
	public String findvedio(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			Integer detialid=StringUtils.str2Integer(req.getParameter("id"), null);
			System.out.println("dataid:"+detialid);
			DataDetialDao detial=new DataDetialDaoImpl();
			List<DataDetial>list=new ArrayList<DataDetial>();
			list=detial.findbyid(detialid);
			this.getJson(req, resp, list);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			this.getJson(req, resp, msg);
			return null;
		}
	}
	public String homepage(HttpServletRequest req,HttpServletResponse resp){
		return this.SUCCESS;
	}
	
	public String findDataName(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			String typeName=req.getParameter("typeName");
			System.out.println("typeName:"+typeName);
			DataDao datadao=new DataDaoImpl();
			List<Data> list=new ArrayList<Data>();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("typeName", typeName);
			list=datadao.findbyCondition(map);
			System.out.println(list.get(0).getDataname());
			for(Data d:list){
				System.out.println(d.getDataname());
			}
			this.getJson(req, resp, list);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			this.getJson(req, resp, msg);
			return null;
		}
	}
	public String findTitle(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			Integer dataid=StringUtils.str2Integer(req.getParameter("dataid"), null);
			System.out.println("dataid:"+dataid);
			DataDetialDao detialdao=new DataDetialDaoImpl();
			List<DataDetial> list=new ArrayList<DataDetial>();
			list=detialdao.findTitle(dataid);
			for(DataDetial d:list){
				System.out.println(d.getTitle());
			}
			this.getJson(req, resp, list);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			this.getJson(req, resp, msg);
			return null;
		}
	}
}	
