package com.sx.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import com.commom.BaseAction.BaseAction;
import com.commom.Utils.StringUtils;
import com.ljc.Dao.Impl.UserDaoImpl;
import com.ljc.entity.User;
import com.sx.bean.Comment;
import com.sx.bean.LoadComment;
import com.sx.bean.LoadTheme;
import com.sx.bean.Tags;
import com.sx.bean.Theme;
import com.sx.bean.Type;
import com.sx.bean.Util;
import com.sx.impl.CommentImpl;
import com.sx.impl.ReplyImpl;
import com.sx.impl.TagsImpl;
import com.sx.impl.ThemeImpl;
import com.sx.impl.TypeImpl;
import com.sx.utils.Factory;
import com.sx.utils.Utils;

public class CommentAction extends BaseAction {
	
	public String getComment(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		BaseServlet base=new BaseServlet();
		String themeid=req.getParameter("themeid");
		String tcontent=null;
		String userName=null;
		if(req.getParameter("tcontext")!=null)
		tcontent=URLDecoder.decode(req.getParameter("tcontext"), "UTF-8"); 	
		
		if(req.getParameter("tname")!=null) userName=URLDecoder.decode(req.getParameter("tname"), "UTF-8"); 
		
		String flag=req.getParameter("flag");
		System.out.println("����"+flag);
		if("5".equals(flag)){	//����
			return saveComment(req, resp, base, tcontent, userName);
		}if("4".equals(flag)){//����
			return update(req, resp, base);
		}else if(themeid!=null && flag!=null){
			return getSortComment(resp, base, themeid,flag);
		}else if("6".equals(flag)){
			return managerComment(req, resp);
		}else if("7".equals(flag)){
			String id=req.getParameter("id");
			if(id!=null){
				int msg=Factory.getCommentImpl().delete(id);
				Map<String,Object> map=Factory.getMap();
				map.put("msg", msg);
				Factory.getJson().toJson(map, resp);
			}
			return null;
		}else if("8".equals(flag)){
			String id=req.getParameter("commId");
			if(id!=null){
				int row=Factory.getCommentImpl().delete(id);
				Map<String,Object> map=Factory.getMap();
				map.put("msg", row);
				Factory.getJson().toJson(map, resp);
			}
			return null;
		}
		
		else{
			return SUCCESS;
		}	
	
	
	}







	private String managerComment(HttpServletRequest req,
			HttpServletResponse resp) {
		//String userId=req.getParameter("userId");
		Map<String,Object> map=Factory.getMap();
		Theme themee=new Factory().getTheme();
		String currUserId=req.getParameter("userId");
		System.out.println(currUserId+"���ڶ���");
		List<LoadComment> list=new ArrayList<LoadComment>();
		Map<String,Object> mapCount=Factory.getMap();
		UserDaoImpl userImpl=(UserDaoImpl) Factory.getUserImpl();
		TagsImpl tagsImpl=(TagsImpl) Factory.getTagsImpl();
		TypeImpl typeImpl=(TypeImpl) Factory.getTypeImpl();
		ReplyImpl replyImpl=(ReplyImpl) Factory.getReplyImpl();
		ThemeImpl themeImpl=(ThemeImpl) Factory.getThemeImpl();
		Comment co=Factory.getComment();co.setToComment(StringUtils.str2Integer(currUserId, 3));
		List<Comment> commList= Factory.getCommentImpl().findBy(co);

			if(commList!=null && commList.size()>0){
				for(Comment comment:commList){
					LoadComment load=Factory.getLoadComment();
					//������Ϣ
					load.setCcontent(comment.getCcontent());
					load.setCdate(comment.getCdate());
					load.setId(comment.getId());
					
					//�������
					mapCount.put("type", "1");
					mapCount.put("typeId", comment.getId());
					int count=tagsImpl.findCount(mapCount);
					load.setZcount(count);
					//��ظ���
					mapCount.clear();
					mapCount.put("replyType", "1");
					mapCount.put("commentId", comment.getId());
					int rcount=replyImpl.findCount(mapCount);
					load.setRcount(rcount);
					//������
					Theme theme=Factory.getTheme();theme.setId(comment.getThemeId());
					Theme the=themeImpl.find(theme).get(0);
					load.setTcontext(the.getTcontext());
					
					//���û�
					String loginName=userImpl.findById(the.getUserid()).getLoginName();
					load.setLoginName(loginName);
					load.setUserid(the.getUserid());
					//����
					Type type=Factory.getType();type.setId(the.getThemeType());
					load.setType(typeImpl.find(type).get(0).getThemeType());
					list.add(load);
				}
				map.put("list", list);
				//map.put("themeList", theme.getList());
			}//map.put("theme", theme);
		
		Factory.getJson().toJson(map, resp);
		return null;
	}







	private String update(HttpServletRequest req, HttpServletResponse resp,
			BaseServlet base) throws SerialException, SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		String commId=req.getParameter("commId");
		String context=req.getParameter("content"); if(context==null) return null;
		CommentImpl commentImpl=(CommentImpl) Factory.getCommentImpl();
		Comment comm=Factory.getComment();
		List<Integer> list=new ArrayList<Integer>(); 
		//�ó�֮ǰ�������������
		int row=0;
		comm.setId(StringUtils.str2Integer(commId, 2));
		List<Comment> commentList=commentImpl.find(comm);
		if(commentList!=null && commentList.size()>0){
			commentList.get(0).setCdate(new Date(System.currentTimeMillis()));
			commentList.get(0).setCcontent(new SerialClob(context.toCharArray()));
			commentList.get(0).setId(StringUtils.str2Integer(commId, 2));
			row=commentImpl.update(commentList.get(0));
			
			
			
		}
		map.put("msg", row);	
		base.toJson(map, resp);
		
		
		return SUCCESS;
	}







	private String saveComment(HttpServletRequest req,
			HttpServletResponse resp, BaseServlet base, String tcontent,
			String userName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String theme_id=req.getParameter("themeid");
     
		//����comment��¼���������������ؼ���Ϣ��summer.jsҳ��д��
		CommentImpl commentImpl=(CommentImpl) Factory.getCommentImpl();
		Comment comm=Factory.getComment();
		comm.setThemeId(StringUtils.str2Integer(theme_id, 0));
		String userId=req.getParameter("userId");System.out.println(userId+"Ƶ�����㷢���Ƿ��");
		if(userId!=null) comm.setToComment(StringUtils.str2Integer(userId, 2));
		
		int commid=commentImpl.save(comm);
		System.out.println(commid+"�½�id");
//		map.put("commId", commid);map.put("tcontent", tcontent);
//		map.put("userName", userName);
//		base.toJson(map, resp);
		
		
		if(commid!=0){
			resp.sendRedirect("note.jsp?commId="+commid+
					"&tcontent="+URLEncoder.encode(tcontent,"utf-8")+"&userName="+URLEncoder.encode(userName,"utf-8")); return null;
		}
				System.out.println("�����ɹ�");
		
			
		return INPUT;
	}

	
	
	
	
	
	
	/**
	 * �����������ó�ǰ��
	 * @param resp
	 * @param base
	 * @param themeid
	 * @return
	 */
	private String getSortComment(HttpServletResponse resp, BaseServlet base,
			String themeid,String flag) {
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		CommentImpl commentImpl=(CommentImpl) Factory.getCommentImpl();
		UserDaoImpl userImpl=(UserDaoImpl) Factory.getUserImpl();
		List<Comment> commentList=new ArrayList<Comment>();
		List<User> commUser=new ArrayList<User>();
		List<Util> newUtil=new ArrayList<Util>();
		List<Integer> replyCount=new ArrayList<Integer>();
		TagsImpl TagsImpl=(TagsImpl) Factory.getTagsImpl();
		Map<String, Object> tagsMap = new HashMap<String, Object>();
		
		tagsMap.put("status","1");
		tagsMap.put("totheme",themeid);
		List<Util> utilList= TagsImpl.findId(tagsMap);
		if(utilList!=null && utilList.size()>0){
			if("1".equals(flag)){//������������
				int j=0;
				for(int i=1;i<utilList.size();i++){
					Comment comment=Factory.getComment();
					comment.setId(utilList.get(i).getCommentid());//���ź�������۲���
					List<Comment> commList = commentImpl.find(comment);//����idһ��һ��ȡ
					if(commList!=null&& commList.size()>0) {
						commentList.add(commList.get(0));
						newUtil.add(utilList.get(i));
						//ȡ�û���Ϣ
						User userComment=  userImpl.findById(commList.get(0).getToComment());
						if(userComment!=null){
							commUser.add(userComment);j++;
						}
						//��ѯ������ �Ƿ���ڻظ�
						int isReply=ThemeAction.isHaveReply(commList.get(0).getId());
						if(isReply!=0){//�ж��������Ƿ��лظ�
							replyCount.add(isReply);
						}else{
							replyCount.add(null);
						}
					}					
					if(i==5) break;//ֻȡ��������ǰ�������	
				}
				if(j<5){//����5����������������
					Comment comment=Factory.getComment();
					comment.setThemeId(Integer.valueOf(themeid));comment.setTag("0 ");
					List<Comment> commList = commentImpl.findBy(comment);
					if(commList!=null && commList.size()>0){
						for(int i=0;i<5-j;i++){
							commentList.add(commList.get(i));
							newUtil.add(null);
							//
							User userComment=  userImpl.findById(commList.get(i).getToComment());
							if(userComment!=null){
								commUser.add(userComment);
							}
							//��ѯ������ �Ƿ���ڻظ�
							int isReply=ThemeAction.isHaveReply(commList.get(i).getId());
							if(isReply!=0){//�ж��������Ƿ��лظ�
								replyCount.add(isReply);
							}else{
								replyCount.add(null);
							}
						}
					}
				}
			}
			else if("2".equals(flag)){//ȡʣ����������
				for(int i=1;i<utilList.size();i++){//��ȥ�ѻ�ȡ�ĵ�һ����������
					Comment comment=Factory.getComment();
					comment.setId(utilList.get(i).getCommentid());//���ź�������۲���
					List<Comment> commList = commentImpl.find(comment);
					if(commList!=null&& commList.size()>0) {
						commentList.add(commList.get(0));	
						newUtil.add(utilList.get(i));
						//
						User userComment=  userImpl.findById(commList.get(0).getToComment());
						if(userComment!=null){
							commUser.add(userComment);
						}
						//��ѯ������ �Ƿ���ڻظ�
						int isReply=ThemeAction.isHaveReply(commList.get(0).getId());
						if(isReply!=0){//�ж��������Ƿ��лظ�
							replyCount.add(isReply);
						}else{
							replyCount.add(null);
						}
					}
				}
				Comment comment=Factory.getComment();//ȡ��������
				comment.setThemeId(Integer.valueOf(themeid));comment.setTag("0 ");
				List<Comment> commList = commentImpl.find(comment);
				if(commList!=null && commList.size()>0){
					for(int i=0;i<commList.size()-1;i++){
						commentList.add(commList.get(i));
						newUtil.add(null);
						//
						User userComment=  userImpl.findById(commList.get(i).getToComment());
						if(userComment!=null){
							commUser.add(userComment);
						}
						//��ѯ������ �Ƿ���ڻظ�
						int isReply=ThemeAction.isHaveReply(commList.get(i).getId());
						if(isReply!=0){//�ж��������Ƿ��лظ�
							replyCount.add(isReply);
						}else{
							replyCount.add(null);
						}
					}
				}
					
			}
			
			map.put("commentUser", commUser);
			map.put("newComm", commentList);
			map.put("newUtil", newUtil);
			map.put("replyCount", replyCount);
		}
		else{//���Ի�ȡ���޵�����
			//���������
			Comment comment=Factory.getComment();
			comment.setThemeId(Integer.valueOf(themeid));
			List<Comment> commList = commentImpl.find(comment);
			if(commList!=null&& commList.size()>0){
				commList.remove(commList.size()-1);//ȥ�������ö���
					map.put("newComm", commList);//��������
					
					for(int i=0;i<commList.size();i++){//���������û�
						User userComment=  userImpl.findById(commList.get(i).getToComment());
						if(userComment!=null){
							commUser.add(userComment);
						}
						//��ѯ������ �Ƿ���ڻظ�
						int isReply=ThemeAction.isHaveReply(commList.get(i).getId());
						if(isReply!=0){//�ж��������Ƿ��лظ�
							replyCount.add(isReply);
						}else{
							replyCount.add(null);
						}
					}map.put("commentUser", commUser);
					map.put("newUtil", newUtil);
					map.put("replyCount", replyCount);
			}
		}
		map.put("status", resp.getStatus());
		base.toJson(map, resp);
		return null;
	}
	
	public Object isNull(Object obj,Object dual){
		if(obj!=null){
			return obj;
		}else{
			return dual;
		}
	}

}
