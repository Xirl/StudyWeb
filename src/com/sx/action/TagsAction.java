package com.sx.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commom.BaseAction.BaseAction;
import com.commom.Utils.StringUtils;
import com.sx.bean.Comment;
import com.sx.bean.Tags;
import com.sx.impl.CommentImpl;
import com.sx.impl.TagsImpl;
import com.sx.utils.Factory;

public class TagsAction extends BaseAction {

	
	public String getTags(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		CommentImpl commImpl=(CommentImpl) Factory.getCommentImpl();
	String commid=req.getParameter("commid");
	String themeId=req.getParameter("themeid");
	String to_id=req.getParameter("to_userid");
	String userid=req.getParameter("userid");
		String flag=req.getParameter("flag");
		TagsImpl tagsImpl=new TagsImpl();
		if("1".equals(flag)){System.out.println("请求的commid"+commid);
		//生成点赞记录
			Tags tags=Factory.getTags();
			tags.setStatus("1");
			tags.setTypeId(StringUtils.str2Integer(commid, 3));
			tags.setType("1");
			tags.setTagsUser(StringUtils.str2Integer(userid, 3));
			
			tags.setTotheme(StringUtils.str2Integer(themeId, 3));
			int tagId=Factory.getTagsImpl().save(tags);System.out.println("保存的点赞："+tagId);
			Map<String,Object> map=Factory.getMap();
			map.put("tagid",tagId);
			//更新评论
			
			Comment comm=Factory.getComment();
			comm.setId(StringUtils.str2Integer(commid, 3));
			Comment comment=commImpl.find(comm).get(0);
			comment.setTag("1");	
			
			commImpl.update(comment);
			
			Factory.getJson().toJson(map, resp);
		}if("0".equals(flag)){	
			String zanId=req.getParameter("zan_id");
			Comment comm=Factory.getComment();
			comm.setId(StringUtils.str2Integer(commid, 3));
			Comment comment=commImpl.find(comm).get(0);
			comment.setTag("0");	
			commImpl.update(comment);

			Factory.getTagsImpl().delete(zanId);	
		}	
		return null;
		
	}
}
