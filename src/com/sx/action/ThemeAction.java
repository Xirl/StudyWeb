package com.sx.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commom.BaseAction.BaseAction;
import com.commom.Utils.StringUtils;
import com.ljc.Dao.Impl.UserDaoImpl;
import com.ljc.entity.User;
import com.sx.bean.Comment;
import com.sx.bean.LoadTheme;
import com.sx.bean.PageBean;
import com.sx.bean.Reply;
import com.sx.bean.Theme;
import com.sx.bean.Type;
import com.sx.bean.Util;
import com.sx.impl.CommentImpl;
import com.sx.impl.TagsImpl;
import com.sx.impl.TypeImpl;
import com.sx.utils.Factory;

public class ThemeAction extends BaseAction {
	BaseServlet base=new BaseServlet();
	public String getTheme(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		String opr=req.getParameter("opr");
		String flag=req.getParameter("flag");
		if("1".equals(opr)){
			return initData(req,resp,flag);
		}if("2".equals(flag)){	
			return saveTheme(req, resp);
		}if("3".equals(flag)){
			return loadTheme(req, resp);
		}if("4".equals(flag)){
			String id=req.getParameter("id");
			int msg=Factory.getThemeImpl().delete(id);
			Map<String,Object> map=Factory.getMap();
			map.put("msg", msg);
			Factory.getJson().toJson(map, resp);
			return null;
		}
		
		else{
			return null;
		}
		
	}

	private String loadTheme(HttpServletRequest req, HttpServletResponse resp) {//���۹���ģ��
		Map<String,Object> map=Factory.getMap();
		Theme themee=new Factory().getTheme();
		String currUserId=req.getParameter("userId");
		System.out.println(currUserId+"���ڶ���");
		List<LoadTheme> list=new ArrayList<LoadTheme>();
		Map<String,Object> mapCount=Factory.getMap();
		UserDaoImpl userImpl=(UserDaoImpl) Factory.getUserImpl();
		TypeImpl typeImpl=(TypeImpl) Factory.getTypeImpl();
		Theme theme=new Theme();
		theme.setUserid(StringUtils.str2Integer(currUserId, 2));
        List<Theme> themeList= Factory.getThemeImpl().find(theme);
		if(themeList!=null){
			if(themeList!=null && themeList.size()>0){
				for(Theme the:themeList){
					LoadTheme load=Factory.getLoadTheme();
					//������Ϣ
					load.setTcontext(the.getTcontext());
					load.setTdate(the.getTdate());
					load.setId(the.getId());
					//��������
					mapCount.put("themeId", the.getId());
					int count=Factory.getCommentImpl().findCount(mapCount);
				
					load.setCount(count);
					//���û�
					String loginName=userImpl.findById(the.getUserid()).getLoginName();
					load.setLoginName(loginName);
					load.setUserid(the.getUserid());
					//����
					Type type=Factory.getType();type.setId(the.getThemeType());
					load.setThemeType(type.getId());
					load.setType(typeImpl.find(type).get(0).getThemeType());
					list.add(load);
				}
				map.put("list", list);
				//map.put("themeList", theme.getList());
			}//map.put("theme", theme);
		}
		Factory.getJson().toJson(map, resp);
		return null;
	}

	private String saveTheme(HttpServletRequest req, HttpServletResponse resp) {
		String text=req.getParameter("text");//ǰ������֤���ݺϷ���
		String typeId=req.getParameter("typeId");
		String currUser=req.getParameter("curr_userID");
		Theme theme=Factory.getTheme();
		theme.setTcontext(text);
		theme.setTdate(new Date(System.currentTimeMillis()));
		theme.setUserid(StringUtils.str2Integer(currUser, 3));
		theme.setThemeType(StringUtils.str2Integer(typeId, 2));		
		int i=Factory.getThemeImpl().save(theme);
		Map<String,Object> map=Factory.getMap();
		map.put("msg", i);
		Factory.getJson().toJson(map,resp);
		return null;
	}
	
	//ȡ���������ŵ�һ�������Լ�����
	
	
	
	
	
	
	
	
	
	
	//��ȡ��ʼ�����ݣ���������ۣ�
	public String initData(HttpServletRequest req, HttpServletResponse resp,String flag){
		System.out.println(resp.getStatus() + "״̬��ֵ");
		String pageIndex = req.getParameter("pageIndex");
		String msg="��Ǹ�������»�δ������";
		CommentImpl commentImpl=(CommentImpl) Factory.getCommentImpl();
		UserDaoImpl userImpl=(UserDaoImpl) Factory.getUserImpl();
		TagsImpl TagsImpl=(TagsImpl) Factory.getTagsImpl();
		
		int count=0;//������
		List<Integer> zanList=new ArrayList<Integer>();
		PageBean<Theme> themepage = null;
		//��ȡ��ͬ��PageBean
		String typeId=req.getParameter("typeId");
		if(flag!=null && "7".equals(flag) && typeId!=null){//��ȡ�ض������µ�����,��������header.js������
			Map<String,Object> map=Factory.getMap();
			map.put("themeType", StringUtils.str2Integer(typeId, 2));
			themepage = Factory.getThemeImpl().findCondition(map, 3,
					StringUtils.str2Integer(pageIndex, 1));
		}else{
			themepage = Factory.getThemeImpl().findCondition(null, 3,
					StringUtils.str2Integer(pageIndex, 1));
		}
		
		
		List<Comment> commentList=new ArrayList<Comment>();
		List<User> themeUser=new ArrayList<User>();
		List<User> commUser=new ArrayList<User>();
		List<Integer> list=new ArrayList<Integer>();
		List<Integer> countList=new ArrayList<Integer>();
		Map<String, Object> map = Factory.getMap();
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		Map<String, Object> tagsMap =  Factory.getMap();
		
		Map<String,Object> mapCount=Factory.getMap();
		if (themepage.getList() != null && themepage.getList().size() > 0) {
			for(Theme the:themepage.getList()){//ȡ��Ӧ�����µ���������
				mapCount.put("themeId", the.getId());
				int findCount=Factory.getCommentImpl().findCount(mapCount);
				list.add(findCount);
			}
			map.put("list", list);
			map.put("theme", themepage);
		    msg="1";
			for(Theme theme:themepage.getList()){
				int commId=0;//����޵�����
				//ȡ��������Ϣ
				User userList= userImpl.findById(theme.getUserid());
				if(userList!=null ){
					themeUser.add(userList);
				}
				//�ҳ�����޵�����
				tagsMap.put("status","1");
				tagsMap.put("totheme",theme.getId());
				List<Util> tagUtil=TagsImpl.findId(tagsMap);
				if(tagUtil!=null && tagUtil.size()>0){
					commId = tagUtil.get(0).getCommentid();
					count= tagUtil.get(0).getCount();
					zanList.add(count);
				}else{
					zanList.add(null);
				}
				
				
				conditionMap.clear();
				conditionMap.put("themeId", theme.getId());
				
				Comment comment=Factory.getComment();
				Comment newcomment=Factory.getComment();
				comment.setThemeId(theme.getId());
				List<Comment> comm = commentImpl.find(comment);
				if(comm!=null && comm.size()>0){
					if(commId!=0){
						for(Comment com:comm){						
							if(com.getId()==commId){
								//ȡ��������Ϣ
								User userComment=  userImpl.findById(com.getToComment());
								if(userComment!=null){
									commUser.add(userComment);
								}
								commentList.add(com);//ȡ������޵�����
								int isReply=isHaveReply(com.getId());
								if(isReply!=0){//�ж��������Ƿ��лظ�
									countList.add(isReply);
								}else{countList.add(null);}
						 	}
						}	
					}else{
						commentList.add(comm.get(comm.size()-1));//��������
						User userComment=  userImpl.findById(comm.get(comm.size()-1).getToComment());
						if(userComment!=null){
							commUser.add(userComment);
						}
						int isReply=isHaveReply(comm.get(comm.size()-1).getId());
						if(isReply!=0){//�ж��������Ƿ��лظ�
							countList.add(isReply);
						}else{
							countList.add(null);
						}
					}	
					//commentList.add(comm);//һ�������µ�����
				}else{
					commentList.add(null);//��̨���жϱ�֤�������۵�˳���Ӧ
					commUser.add(null);
					countList.add(null);
				}
			}map.put("comment", commentList);//���и��������µĸ�������
			map.put("countList", countList);
		}
		
		map.put("status", resp.getStatus());
		map.put("zan", zanList);
		map.put("msg", msg);
		map.put("commUser", commUser);
		map.put("themeUser", themeUser);	
		base.toJson(map, resp);
		return null;
	}

	public void initReply(HttpServletRequest req, HttpServletResponse resp) {
		List<Reply> RepList = Factory.getThemeImpl().find(null);
		Map<String, Object> map = new HashMap<String, Object>();
		if (RepList != null && RepList.size() > 0) {
			map.put("reply", RepList);
		}
		map.put("status", resp.getStatus());
		
		base.toJson(map, resp);
	}
	
	public static int isHaveReply(Integer commid){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("commentId", commid);
		return Factory.getReplyImpl().findCount(map);
	}
	
}
