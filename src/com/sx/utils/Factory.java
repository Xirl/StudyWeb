package com.sx.utils;

import java.util.HashMap;
import java.util.Map;

import com.ljc.Dao.UserDao;
import com.ljc.Dao.Impl.UserDaoImpl;
import com.sx.action.BaseServlet;
import com.sx.bean.Comment;
import com.sx.bean.Img;
import com.sx.bean.LoadComment;
import com.sx.bean.LoadTheme;
import com.sx.bean.Reply;
import com.sx.bean.Tags;
import com.sx.bean.Theme;
import com.sx.bean.Type;
import com.sx.dao.GeneralDao;
import com.sx.impl.CommentImpl;
import com.sx.impl.ImgImpl;
import com.sx.impl.ReplyImpl;
import com.sx.impl.TagsImpl;
import com.sx.impl.ThemeImpl;
import com.sx.impl.TypeImpl;

public class Factory {
	
	public static Comment getComment(){
		return new Comment();
	}
	
	public static Tags getTags(){
		return new Tags();
	}
	
	public static Reply getReply(){
		return new Reply();
	}
	
	public static Theme getTheme(){
		return new Theme();
	}
	
	public static GeneralDao getThemeImpl(){
		return new ThemeImpl();
	}
	
	public static GeneralDao getCommentImpl(){
		return new CommentImpl();
	}
	
	public static GeneralDao getReplyImpl(){
		return new ReplyImpl();
	}
	
	public static GeneralDao getTagsImpl(){
		return new TagsImpl();
	}
	
	public static GeneralDao getImgImpl(){
		return new ImgImpl();
	}
	
	public static Img getImg(){
		return new Img();
	}
	
	public static Type getType(){
		return new Type();
	}
	
	public static GeneralDao getTypeImpl(){
		return new TypeImpl();
	}
	
	public static BaseServlet getJson(){
		return new BaseServlet();
	}
	
	public static Map<String,Object> getMap(){
		return new HashMap<String,Object>(10);
	}
	
	public static UserDao getUserImpl(){
		return new UserDaoImpl();
	}
	
	public static LoadTheme getLoadTheme(){
		return new LoadTheme();
	}
	
	public static LoadComment getLoadComment(){
		return new LoadComment();
	}
	

}
