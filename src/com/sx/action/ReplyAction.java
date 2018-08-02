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
import com.sx.bean.PageBean;
import com.sx.bean.Reply;
import com.sx.bean.Theme;
import com.sx.impl.ReplyImpl;
import com.sx.utils.Factory;

public class ReplyAction extends BaseAction{

	
	public String getReply(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		BaseServlet base=Factory.getJson();
		
		
		String flag=req.getParameter("flag");
		System.out.println("flag是多少"+flag);
		if("1".equals(flag)){//插入第一条回复评论的
			return saveFirst(req,resp, base);
		}if("2".equals(flag)){//插入其余回复评论的
			return saveOther(req, resp, base);
		}if("3".equals(flag)){//查询其他对话列表
			findTalk(req, resp, base);	
		}if("4".equals(flag)){//保存回复的回复
			return saveTalkReply(req, resp, base);
		}if("5".equals(flag)){
			findReply(req, resp, base);
		}if("8".equals(flag)){
			String id=req.getParameter("repId");
			int msg=0;
			if(id!=null) msg=Factory.getReplyImpl().delete(id);
			Map<String, Object> postMap =Factory.getMap();
			postMap.put("msg", msg);
			Factory.getJson().toJson(postMap, resp);
		}
		return null;	
	}

	private void findReply(HttpServletRequest req, HttpServletResponse resp,
			BaseServlet base) {
		String replyId=req.getParameter("rep_id");
		String commentId=req.getParameter("rep_commid");
		List<Reply> newReply=null;
		Map<String, Object> postMap =Factory.getMap();
		UserDaoImpl userImpl=(UserDaoImpl) Factory.getUserImpl();
		ReplyImpl replyImpl=(ReplyImpl) Factory.getReplyImpl();
		List<User> newUser=new ArrayList<User>();
		List<User> toUser=new ArrayList<User>();
		List<Reply> reply=new ArrayList<Reply>();
		//获取回复回复的回复
		Reply theReply=Factory.getReply();
		theReply.setReplyType("0 ");
		theReply.setReplyId(StringUtils.str2Integer(replyId,3));
		theReply.setCommentId(StringUtils.str2Integer(commentId,3));
		newReply=replyImpl.findBy(theReply);
		String msg=null;
		if(newReply!=null && newReply.size()>0){
			for(Reply newreply:newReply){
				User userReply= userImpl.findById(newreply.getToUser());
				if(userReply!=null){
					toUser.add(userReply);
					newUser.add(null);
				}reply.add(newreply);
				//查找@的回复
				Reply findReply=Factory.getReply();
				findReply.setReplyType("0 ");
				findReply.setReplyId(newreply.getId());
				findReply.setCommentId(StringUtils.str2Integer(commentId,3));
				List<Reply> re=replyImpl.findBy(findReply);
				if(re!=null && re.size()>0){
					for(Reply rere:re){
						User reReply= userImpl.findById(rere.getToUser());
						if(userReply!=null){
							toUser.add(userReply);
						}
						User toReply= userImpl.findById(rere.getReplyUser());//被评论的人
						if(toReply.getUserId()!=null){
							newUser.add(toReply);
						}else{
							newUser.add(null);
						}
						reply.add(rere);
					}
				}
			}
			postMap.put("toUser", toUser);//被回复的用户
			postMap.put("newUser", newUser);//去评论回复的人
			postMap.put("newReply", reply);//回复的回复	
			
			msg="0";
		}	
		postMap.put("msg", msg);
		base.toJson(postMap, resp);
	}

	private String saveTalkReply(HttpServletRequest req,
			HttpServletResponse resp, BaseServlet base) {
		Map<String, Object> map =Factory.getMap();
		String rep_commid=req.getParameter("rep_commid");
		String rep_user=req.getParameter("rep_user");
		String text=req.getParameter("text");
		String rep_id=req.getParameter("rep_id");
		String toUser=req.getParameter("ToUser");
		String sign=req.getParameter("sign");//sign为0时是@某人的回复,为1的时候表示回复一条回复
		Reply reply=Factory.getReply();
		reply.setCommentId(StringUtils.str2Integer(rep_commid,3));
		reply.setReplyId(StringUtils.str2Integer(rep_id,3));
		reply.setReplyDate(new java.sql.Timestamp(System.currentTimeMillis()));
		if("0".equals(sign)) reply.setReplyUser(StringUtils.str2Integer(rep_user,3));
		reply.setReplyType("0");
		reply.setReplyContent(text);
		reply.setToUser(StringUtils.str2Integer(toUser,4));
		int rid=Factory.getReplyImpl().save(reply);	
		map.put("msg", rid);
		map.put("status", resp.getStatus());
		base.toJson(map, resp);
		return null;
	}

	private void findTalk(HttpServletRequest req, HttpServletResponse resp,
			BaseServlet base) {
		String commentId=req.getParameter("otherComid");
		String sign=req.getParameter("sign");
		ReplyImpl replyImpl=(ReplyImpl) Factory.getReplyImpl();
		UserDaoImpl userImpl=(UserDaoImpl) Factory.getUserImpl();
		
		List<User> commUser=new ArrayList<User>();
		List<Integer> integer=new ArrayList<Integer>();
		Map<String, Object> rep =Factory.getMap();
		Map<String, Object> map =Factory.getMap();
		Map<String, Object> postMap =Factory.getMap();
		String pageIndex=req.getParameter("pageIndex");System.out.println("下标"+pageIndex);
		
		if("1".equals(sign)) map.put("replyType","1 ");//char类型补空 传参为字符串“1”时无效，只能用数字，原因不详,传变量则此条件失效
		
		
		map.put("commentId", commentId);System.out.println(commentId+"评论的id");
		map.put("replyId", commentId);
		PageBean<Reply> list=replyImpl.findCondition(map, 4, StringUtils.str2Integer(pageIndex,1));
		if(list.getList()!=null && list.getList().size()>0){
			postMap.put("list", list);
			for(Reply reply:list.getList()){
			
				//查找此回复下是否有回复记录
			rep.put("replyId", reply.getId());
			rep.put("replyType", "0 ");
				int repCount=replyImpl.findCount(rep);
				if(repCount==0){integer.add(null);}else{integer.add(repCount);}
				User userComment=  userImpl.findById(reply.getToUser());
				if(userComment!=null){
					commUser.add(userComment);
				}
			}
			postMap.put("integer", integer);//判断回复下是否还有回复
			postMap.put("userPostMap", commUser);//评论评论的人
			postMap.put("postMap", list.getList());//评论的回复
		}
		
		base.toJson(postMap, resp);
//			Map<String, Object> relpyMap =Factory.getMap();
//			relpyMap.put("replyType", "1");
//			relpyMap.put("commentId", commentId);
//			relpyMap.put("commentId", commentId);
	}

	private String saveOther(HttpServletRequest req, HttpServletResponse resp,
			BaseServlet base) {
		Map<String, Object> map =Factory.getMap();
		String text=req.getParameter("text");System.out.println("其他插入内容："+text);
		String commentId=req.getParameter("comid");
		String to_userid=req.getParameter("to_userid");
		String realUser=req.getParameter("realUser");System.out.println(realUser+"ssssss");
		Reply reply=Factory.getReply();
		reply.setCommentId(StringUtils.str2Integer(commentId,3));
		reply.setReplyId(StringUtils.str2Integer(commentId,3));
		reply.setReplyUser(StringUtils.str2Integer(to_userid,3));
		reply.setToUser(StringUtils.str2Integer(realUser,4));
		reply.setReplyDate(new java.sql.Timestamp(System.currentTimeMillis()));
		reply.setReplyType("1");
		reply.setReplyContent(text);
		
		reply.setReplyUser(StringUtils.str2Integer(to_userid, 3));
		int rid=Factory.getReplyImpl().save(reply);	
		System.out.println(rid+"插入成功");
		map.put("msg", rid);
		map.put("status", resp.getStatus());
		base.toJson(map, resp);
		return null;
	}

	private String saveFirst(HttpServletRequest req,HttpServletResponse resp, BaseServlet base) {
		Map<String, Object> map =Factory.getMap();
		String text=req.getParameter("text");System.out.println("插入内容："+text);
		String commentId=req.getParameter("comid");
		String to_userid=req.getParameter("to_userid");
		String realUser=req.getParameter("realUser");
		Reply reply=Factory.getReply();
		reply.setCommentId(StringUtils.str2Integer(commentId,4));
		reply.setReplyId(StringUtils.str2Integer(commentId,4));
		reply.setReplyDate(new java.sql.Timestamp(System.currentTimeMillis()));
		reply.setReplyType("1");
		reply.setReplyContent(text);
		reply.setToUser(StringUtils.str2Integer(realUser,4));

		reply.setReplyUser(StringUtils.str2Integer(to_userid, null));
		int rid=Factory.getReplyImpl().save(reply);	
		System.out.println(rid+"插入成功");
		map.put("msg", rid);
		map.put("status", resp.getStatus());
		base.toJson(map, resp);
		return null;
	}
}
