package com.ljc.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.commom.BaseAction.BaseAction;
import com.commom.struts.action.Action;
import com.ljc.Dao.UserDao;
import com.ljc.Dao.Impl.UserDaoImpl;
import com.ljc.entity.User;

public class UserAction extends BaseAction implements Action {
	public String login(HttpServletRequest req,HttpServletResponse resp){
		String msg;
		try {  
			String loginName=req.getParameter("loginName");
			String password=req.getParameter("pwd");
			System.out.println("loginNameAction:"+loginName);
			System.out.println("passwordAction:"+password);
			UserDao userdao=new UserDaoImpl();
			if(loginName==null) throw new RuntimeException("请输入用户名");
			if(password==null) throw new RuntimeException("请输入密码");
			if(loginName!=null && password!=null){
				User user=userdao.UserLogin(loginName, password);
				System.out.println(user.getLoginName());
				System.out.println(user.getName());
				if(user.getName()!=null){
					req.getSession().setAttribute("currUser", user);
					this.getJson(req, resp, user);
					return null;
				}
			}
		} catch (Exception e) {
			msg=e.getMessage();
			this.getJson(req, resp, msg);
			return null;
		}
		msg="用户名或密码错误";
		this.getJson(req, resp, msg);
		return null;
	}
public String selectloginName(HttpServletRequest req,HttpServletResponse resp){
		try {
			String loginName=req.getParameter("loginName");
//			System.out.println("loginName:"+loginName);
			UserDao userdao=new UserDaoImpl();
			if(loginName!=null && !"".equals(loginName)){
				int row=userdao.selectLoginName(loginName);
//				System.out.println(row);
				req.setAttribute("row", row);
				this.getJson(req, resp, row);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.INPUT;
		}
		return this.SUCCESS;
	}
	public String userInsert(HttpServletRequest req,HttpServletResponse resp){
		try {
			System.out.println("asd");
			User user=(User) this.getEntity(req, User.class);
			if(user.getLoginName()!=null){
				UserDao userdao=new UserDaoImpl();
				int row=userdao.insert(user);
				this.getJson(req, resp, row);
				return null;
			}
		} catch (Exception e) {
			String msg=e.getMessage();
			req.setAttribute("msg", msg);
			return this.INPUT;
		}
		return this.SUCCESS;
	}
	public String setdata(HttpServletRequest req,HttpServletResponse resp){
		try {
			User user=new User();
			user=this.getCurrLoginUser(req.getSession());
			req.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			return this.INPUT;
		}
		return this.SUCCESS;
	}
	public String update(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			User currUser=(User) req.getSession().getAttribute("currUser");
			User user=new User();
			UserDao userdao=new UserDaoImpl();
			user=(User) this.getEntity(req, User.class);
			System.out.println("头像:"+user.getHead());
			System.out.println("当前用户头像："+currUser.getHead());
			if(currUser!=null){
				currUser.setBirthday(user.getBirthday());
				if(user.getHead()!=null && !"".equals(user.getHead())){
					currUser.setHead(user.getHead());
				}
				if(user.getName()!=null && !"".equals(user.getName())){
					currUser.setName(user.getName());
				}
				currUser.setSex(user.getSex());
				currUser.setSignature(user.getSignature());
			}
			
			int row=userdao.update(currUser);
			if(row>0){
				this.getJson(req, resp, row);
				return null;
			}else{
				this.getJson(req, resp, "修改失败");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			this.getJson(req, resp, msg);
			return null;
		}
	}
	public String updatepassword(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			User currUser=(User) req.getSession().getAttribute("currUser");
			User user=new User();
			UserDao userdao=new UserDaoImpl();
			user=(User) this.getEntity(req, User.class);
			if(currUser!=null){
				if(user.getPassword()!=null && !"".equals(user.getPassword())){
					currUser.setPassword(user.getPassword());
				}
			}
			int row=userdao.update(currUser);
			if(row>0){
				this.getJson(req, resp, row);
				return null;
			}else{
				this.getJson(req, resp, "修改失败");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			System.out.println("msg:"+msg);
			this.getJson(req, resp, msg);
			return null;
		}
	}
	public String clear(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		try {
			System.out.println("清除session");
			req.getSession().setAttribute("currUser", null);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			System.out.println("msg:"+msg);
			this.getJson(req, resp, msg);
			return null;
		}
	}
}
