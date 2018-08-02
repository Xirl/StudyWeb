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
		System.out.println("请求"+flag);
		if("5".equals(flag)){	//保存
			return saveComment(req, resp, base, tcontent, userName);
		}if("4".equals(flag)){//更新
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
		System.out.println(currUserId+"等于多少");
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
					//评论信息
					load.setCcontent(comment.getCcontent());
					load.setCdate(comment.getCdate());
					load.setId(comment.getId());
					
					//查点赞数
					mapCount.put("type", "1");
					mapCount.put("typeId", comment.getId());
					int count=tagsImpl.findCount(mapCount);
					load.setZcount(count);
					//查回复数
					mapCount.clear();
					mapCount.put("replyType", "1");
					mapCount.put("commentId", comment.getId());
					int rcount=replyImpl.findCount(mapCount);
					load.setRcount(rcount);
					//查主题
					Theme theme=Factory.getTheme();theme.setId(comment.getThemeId());
					Theme the=themeImpl.find(theme).get(0);
					load.setTcontext(the.getTcontext());
					
					//查用户
					String loginName=userImpl.findById(the.getUserid()).getLoginName();
					load.setLoginName(loginName);
					load.setUserid(the.getUserid());
					//类型
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
		//拿出之前保存的那条数据
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
     
		//创建comment记录，并返回主键，关键信息由summer.js页面写入
		CommentImpl commentImpl=(CommentImpl) Factory.getCommentImpl();
		Comment comm=Factory.getComment();
		comm.setThemeId(StringUtils.str2Integer(theme_id, 0));
		String userId=req.getParameter("userId");System.out.println(userId+"频道的你发的那封地");
		if(userId!=null) comm.setToComment(StringUtils.str2Integer(userId, 2));
		
		int commid=commentImpl.save(comm);
		System.out.println(commid+"新建id");
//		map.put("commId", commid);map.put("tcontent", tcontent);
//		map.put("userName", userName);
//		base.toJson(map, resp);
		
		
		if(commid!=0){
			resp.sendRedirect("note.jsp?commId="+commid+
					"&tcontent="+URLEncoder.encode(tcontent,"utf-8")+"&userName="+URLEncoder.encode(userName,"utf-8")); return null;
		}
				System.out.println("创建成功");
		
			
		return INPUT;
	}

	
	
	
	
	
	
	/**
	 * 按点赞排序拿出前五
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
			if("1".equals(flag)){//加载热门评论
				int j=0;
				for(int i=1;i<utilList.size();i++){
					Comment comment=Factory.getComment();
					comment.setId(utilList.get(i).getCommentid());//按排好序的评论查找
					List<Comment> commList = commentImpl.find(comment);//根据id一条一条取
					if(commList!=null&& commList.size()>0) {
						commentList.add(commList.get(0));
						newUtil.add(utilList.get(i));
						//取用户信息
						User userComment=  userImpl.findById(commList.get(0).getToComment());
						if(userComment!=null){
							commUser.add(userComment);j++;
						}
						//查询评论下 是否存在回复
						int isReply=ThemeAction.isHaveReply(commList.get(0).getId());
						if(isReply!=0){//判断评论下是否有回复
							replyCount.add(isReply);
						}else{
							replyCount.add(null);
						}
					}					
					if(i==5) break;//只取点赞排名前五的评论	
				}
				if(j<5){//不满5条则无赞评论来凑
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
							//查询评论下 是否存在回复
							int isReply=ThemeAction.isHaveReply(commList.get(i).getId());
							if(isReply!=0){//判断评论下是否有回复
								replyCount.add(isReply);
							}else{
								replyCount.add(null);
							}
						}
					}
				}
			}
			else if("2".equals(flag)){//取剩余有赞评论
				for(int i=1;i<utilList.size();i++){//除去已获取的第一条高赞评论
					Comment comment=Factory.getComment();
					comment.setId(utilList.get(i).getCommentid());//按排好序的评论查找
					List<Comment> commList = commentImpl.find(comment);
					if(commList!=null&& commList.size()>0) {
						commentList.add(commList.get(0));	
						newUtil.add(utilList.get(i));
						//
						User userComment=  userImpl.findById(commList.get(0).getToComment());
						if(userComment!=null){
							commUser.add(userComment);
						}
						//查询评论下 是否存在回复
						int isReply=ThemeAction.isHaveReply(commList.get(0).getId());
						if(isReply!=0){//判断评论下是否有回复
							replyCount.add(isReply);
						}else{
							replyCount.add(null);
						}
					}
				}
				Comment comment=Factory.getComment();//取无赞评论
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
						//查询评论下 是否存在回复
						int isReply=ThemeAction.isHaveReply(commList.get(i).getId());
						if(isReply!=0){//判断评论下是否有回复
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
		else{//尝试获取无赞的评论
			//按主题查找
			Comment comment=Factory.getComment();
			comment.setThemeId(Integer.valueOf(themeid));
			List<Comment> commList = commentImpl.find(comment);
			if(commList!=null&& commList.size()>0){
				commList.remove(commList.size()-1);//去除最新置顶的
					map.put("newComm", commList);//加载评论
					
					for(int i=0;i<commList.size();i++){//加载评论用户
						User userComment=  userImpl.findById(commList.get(i).getToComment());
						if(userComment!=null){
							commUser.add(userComment);
						}
						//查询评论下 是否存在回复
						int isReply=ThemeAction.isHaveReply(commList.get(i).getId());
						if(isReply!=0){//判断评论下是否有回复
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
