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
		System.out.println("flag�Ƕ���"+flag);
		if("1".equals(flag)){//�����һ���ظ����۵�
			return saveFirst(req,resp, base);
		}if("2".equals(flag)){//��������ظ����۵�
			return saveOther(req, resp, base);
		}if("3".equals(flag)){//��ѯ�����Ի��б�
			findTalk(req, resp, base);	
		}if("4".equals(flag)){//����ظ��Ļظ�
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
		//��ȡ�ظ��ظ��Ļظ�
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
				//����@�Ļظ�
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
						User toReply= userImpl.findById(rere.getReplyUser());//�����۵���
						if(toReply.getUserId()!=null){
							newUser.add(toReply);
						}else{
							newUser.add(null);
						}
						reply.add(rere);
					}
				}
			}
			postMap.put("toUser", toUser);//���ظ����û�
			postMap.put("newUser", newUser);//ȥ���ۻظ�����
			postMap.put("newReply", reply);//�ظ��Ļظ�	
			
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
		String sign=req.getParameter("sign");//signΪ0ʱ��@ĳ�˵Ļظ�,Ϊ1��ʱ���ʾ�ظ�һ���ظ�
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
		String pageIndex=req.getParameter("pageIndex");System.out.println("�±�"+pageIndex);
		
		if("1".equals(sign)) map.put("replyType","1 ");//char���Ͳ��� ����Ϊ�ַ�����1��ʱ��Ч��ֻ�������֣�ԭ����,�������������ʧЧ
		
		
		map.put("commentId", commentId);System.out.println(commentId+"���۵�id");
		map.put("replyId", commentId);
		PageBean<Reply> list=replyImpl.findCondition(map, 4, StringUtils.str2Integer(pageIndex,1));
		if(list.getList()!=null && list.getList().size()>0){
			postMap.put("list", list);
			for(Reply reply:list.getList()){
			
				//���Ҵ˻ظ����Ƿ��лظ���¼
			rep.put("replyId", reply.getId());
			rep.put("replyType", "0 ");
				int repCount=replyImpl.findCount(rep);
				if(repCount==0){integer.add(null);}else{integer.add(repCount);}
				User userComment=  userImpl.findById(reply.getToUser());
				if(userComment!=null){
					commUser.add(userComment);
				}
			}
			postMap.put("integer", integer);//�жϻظ����Ƿ��лظ�
			postMap.put("userPostMap", commUser);//�������۵���
			postMap.put("postMap", list.getList());//���۵Ļظ�
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
		String text=req.getParameter("text");System.out.println("�����������ݣ�"+text);
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
		System.out.println(rid+"����ɹ�");
		map.put("msg", rid);
		map.put("status", resp.getStatus());
		base.toJson(map, resp);
		return null;
	}

	private String saveFirst(HttpServletRequest req,HttpServletResponse resp, BaseServlet base) {
		Map<String, Object> map =Factory.getMap();
		String text=req.getParameter("text");System.out.println("�������ݣ�"+text);
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
		System.out.println(rid+"����ɹ�");
		map.put("msg", rid);
		map.put("status", resp.getStatus());
		base.toJson(map, resp);
		return null;
	}
}
